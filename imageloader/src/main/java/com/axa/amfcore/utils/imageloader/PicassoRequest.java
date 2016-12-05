/*
 * PicassoRequest
 *
 * Created by Roman Rodriguez on 12/06/15.
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
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

import java.util.Map;

/**
 * Picasso Request for each image display.
 */
class PicassoRequest implements ImageDownloader.Request { // Must have 'Package' visibility

    private static final String TAG = "PicassoRequest";


    private final RequestCreator builder;
    private int mLoadingResId;
    private Drawable mLoadingDrawable;
    private int mDelayMilli;
    private Map<String, String> mExtras;
    private final PicassoSecureImageDownloader mDownloader;

    /**
     * Builder
     * @param uri
     */
    public PicassoRequest(@NonNull Picasso picasso,
                          @NonNull PicassoSecureImageDownloader downloader,
                          @NonNull Uri uri) {
        mDownloader = downloader;
        builder = picasso.load(uri);
        // Bitmaps in RGB_565 consume 2 times less memory than in ARGB_8888.
        builder.config(Bitmap.Config.RGB_565);
    }

    /**
     * Sets the on empty res id.
     * @param placeHolderResId
     * @return
     */
    public final ImageDownloader.Request placeholder(@DrawableRes int placeHolderResId) {
        // PRECONDITIONS
        if(placeHolderResId > 0) {
            builder.placeholder(placeHolderResId);
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
            builder.placeholder(placeHolderDrawable);
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
            mLoadingResId = loadingResId;
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
            mLoadingDrawable = loadingDrawable;
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
            builder.error(errorResId);
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
            builder.error(errorDrawable);
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
            mDelayMilli = delayMilli;
        }
        return this;
    }

    /**
     * Adds a transformer to the image.
     * @param transformer
     * @return
     */
    public final ImageDownloader.Request transform(@NonNull ImageDownloader.Transformer transformer) {
        // PRECONDITIONS
        if(transformer != null) {
            builder.transform((Transformation)
                    transformer.getTransformer(ImageLoader.Provider.PICASSO));
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
            mExtras = extras;
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
            builder.config(config);
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
     * WARNING, OPERATION NOT SUPPORTED.
     * Adds a download task to a image view with a progress callback.
     * @param target
     * @param progressCallback NOT SUPPORTED.
     */
    public final void into(@NonNull ImageView target, @NonNull final ImageDownloader.ProgressCallback progressCallback) {
        throw new UnsupportedOperationException("Picasso does not support this operation");
    }

    /**
     * Adds a download task to a image view with status and progress callback.
     * @param target
     * @param callback
     * @param progressCallback NOT SUPPORTED.
     */
    public final void into(@NonNull final ImageView target, @NonNull final ImageDownloader.Callback callback,
                           @NonNull final ImageDownloader.ProgressCallback progressCallback) {
        // PRECONDITIONS
        if(target == null) {
            Log.d(TAG, "ImageView is null");
        }
        else {

            // PRECONDITIONS
            if(mLoadingResId > 0) {
                target.setImageResource(mLoadingResId);
            }
            else if(mLoadingDrawable != null) {
                target.setImageDrawable(mLoadingDrawable);
            }

            if (callback == null && progressCallback == null) {


                if(mDelayMilli > 0) {
                    mDownloader.setExtra(mExtras);
                    builder.into(target);
                }
                else {
                    // Simulates delay
                    (new android.os.Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mDownloader.setExtra(mExtras);
                            builder.into(target);
                        }
                    }, mDelayMilli);
                }


            }
            else {

                final com.squareup.picasso.Callback picassoCallback = new Callback() {
                    @Override
                    public void onSuccess() {
                        callback.onLoadingComplete();
                    }

                    @Override
                    public void onError() {
                        callback.onLoadingFailed();
                    }
                };

                if(callback != null) {

                    if(mDelayMilli > 0) {
                        mDownloader.setExtra(mExtras);
                        builder.into(target, picassoCallback);
                        // Simulates loading started
                        if(callback != null) {
                            callback.onLoadingStarted();
                        }
                    }
                    else {
                        // Simulates delay
                        (new android.os.Handler()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mDownloader.setExtra(mExtras);
                                builder.into(target, picassoCallback);
                                // Simulates loading started
                                if(callback != null) {
                                    callback.onLoadingStarted();
                                }
                            }
                        }, mDelayMilli);
                    }

                }


            }

        }

    }

}
