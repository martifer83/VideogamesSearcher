/*
 * UILRequest
 *
 * Created by Roman Rodriguez on 20/05/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.imageloader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import java.util.Map;

/**
 * UIL Request for each image display.
 */
class UILRequest implements ImageDownloader.Request { // Must have 'Package' visibility

    private static final String TAG = "UILRequest";


    private final com.nostra13.universalimageloader.core.ImageLoader mUILImageLoader;
    private final DisplayImageOptions.Builder mOptionsBuilder = new DisplayImageOptions.Builder();
    private final Uri mUri;
    private int mPlaceHolderResId;
    private Drawable mPlaceHolderDrawable;

    /**
     * Builder
     * @param uri
     */
    public UILRequest(@NonNull com.nostra13.universalimageloader.core.ImageLoader uILImageLoader,
                      @NonNull Uri uri) {

        mUILImageLoader = uILImageLoader;
        mUri = uri;
        // Bitmaps in RGB_565 consume 2 times less memory than in ARGB_8888.
        mOptionsBuilder.bitmapConfig(Bitmap.Config.RGB_565);
    }

    /**
     * Sets the on empty res id.
     * @param placeHolderResId
     * @return
     */
    public final ImageDownloader.Request placeholder(@DrawableRes int placeHolderResId) {
        // PRECONDITIONS
        if(placeHolderResId > 0) {
            mPlaceHolderResId = placeHolderResId;
        }
        return this;
    }

    /**
     * Sets the on empty drawable.
     * @param placeHolderDrawable
     * @return
     */
    public final ImageDownloader.Request placeholder(@NonNull Drawable placeHolderDrawable) {
        // PRECONDITIONS
        if (placeHolderDrawable != null) {
            mPlaceHolderDrawable = placeHolderDrawable;
        }
        return this;
    }

    /**
     * Sets the on loading res id.
     * @param loadingResId
     * @return
     */
    public final ImageDownloader.Request loading(@DrawableRes int loadingResId) {
        // PRECONDITIONS
        if(loadingResId > 0) {
            mOptionsBuilder.showImageOnLoading(loadingResId);
        }
        return this;
    }

    /**
     * Sets the on loading drawable.
     * @param loadingDrawable
     * @return
     */
    public final ImageDownloader.Request loading(@NonNull Drawable loadingDrawable) {
        // PRECONDITIONS
        if(loadingDrawable != null) {
            mOptionsBuilder.showImageOnLoading(loadingDrawable);
        }
        return this;
    }

    /**
     * Sets the on error res id.
     * @param errorResId
     * @return
     */
    public final ImageDownloader.Request error(@DrawableRes int errorResId) {
        // PRECONDITIONS
        if(errorResId > 0) {
            mOptionsBuilder.showImageOnFail(errorResId);
        }
        return this;
    }

    /**
     * Sets the on error drawable.
     * @param errorDrawable
     * @return
     */
    public final ImageDownloader.Request error(@NonNull Drawable errorDrawable) {
        // PRECONDITIONS
        if(errorDrawable != null) {
            mOptionsBuilder.showImageOnFail(errorDrawable);
        }
        return this;
    }

    /**
     * Sets delay before load the image.
     * @param delayMilli Time in milliseconds.
     * @return
     */
    public final ImageDownloader.Request delayBeforeLoading(int delayMilli) {
        // PRECONDITIONS
        if(delayMilli >= 0) {
            mOptionsBuilder.delayBeforeLoading(delayMilli);
        }
        return this;
    }

    /**
     * Adds a transformer to the image.
     * @param transformer
     * @return
     */
    public final ImageDownloader.Request transform(@NonNull ImageLoader.Transformer transformer) {
        // PRECONDITIONS
        if(transformer != null) {
            mOptionsBuilder.displayer((BitmapDisplayer)
                    transformer.getTransformer(ImageLoader.Provider.UIL));
        }
        return this;
    }

    /**
     * Adds extra info into the headers.
     * @param extras
     * @return
     */
    public final ImageDownloader.Request extras(@NonNull Map<String, String> extras) {
        // PRECONDITIONS
        if(extras != null) {
            mOptionsBuilder.extraForDownloader(extras);
        }
        return this;
    }

    /**
     * Adds bitmap quality.
     * By default is setted to Bitmaps RGB_565.
     * @param config
     * @return
     */
    public final ImageDownloader.Request bitmapConfig(@NonNull Bitmap.Config config) {
        // PRECONDITIONS
        if(config != null) {
            mOptionsBuilder.bitmapConfig(config);
        }
        return this;
    }

    /**
     * Adds a download task to a image view.
     * @param target
     */
    public final void into(@NonNull ImageView target) {
        into(target, null, null);
    }

    /**
     * Adds a download task to a image view with a status callback.
     * @param target
     * @param callback
     */
    public final void into(@NonNull ImageView target, @NonNull final ImageDownloader.Callback callback) {
        into(target, callback, null);
    }

    /**
     * Adds a download task to a image view with a progress callback.
     * @param target
     * @param progressCallback
     */
    public final void into(@NonNull ImageView target, @NonNull final ImageDownloader.ProgressCallback progressCallback) {
        into(target, null, progressCallback);
    }

    /**
     * Adds a download task to a image view with status and progress callback.
     * @param target
     * @param callback
     * @param progressCallback
     */
    public final void into(@NonNull ImageView target, @NonNull final ImageDownloader.Callback callback,
                           @NonNull final ImageDownloader.ProgressCallback progressCallback) {
        // PRECONDITIONS
        if(target == null) {
            Log.d(TAG, "ImageView is null");
        }
        else {

            // PRECONDITIONS
            if(mPlaceHolderResId > 0) {
                target.setImageResource(mPlaceHolderResId);
            }
            else if(mPlaceHolderDrawable != null) {
                target.setImageDrawable(mPlaceHolderDrawable);
            }

            if (callback == null && progressCallback == null) {

                mUILImageLoader.displayImage(mUri.toString(), target, mOptionsBuilder.build());
            }
            else {

                ImageLoadingListener imageLoadingListener = null;
                ImageLoadingProgressListener imageLoadingProgressListener = null;

                if(callback != null) {
                    imageLoadingListener = new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String s, View view) {
                            callback.onLoadingStarted();
                        }
                        @Override
                        public void onLoadingFailed(String s, View view, FailReason failReason) {
                            Log.d(TAG, failReason.getType().toString());
                            callback.onLoadingFailed();
                        }
                        @Override
                        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                            callback.onLoadingComplete();
                        }
                        @Override
                        public void onLoadingCancelled(String s, View view) { // DO NOTHING
                        }
                    };
                }

                if(progressCallback != null) {
                    imageLoadingProgressListener = new ImageLoadingProgressListener() {
                        @Override
                        public void onProgressUpdate(String s, View view, int current, int total) {
                            progressCallback.onProgressUpdate(current, total);
                        }
                    };
                }

                mUILImageLoader.displayImage(mUri.toString(), target,
                        mOptionsBuilder.build(), imageLoadingListener,
                        imageLoadingProgressListener);

            }

        }

    }

}
