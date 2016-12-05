/*
 * ImageDownloader
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
import android.widget.ImageView;

import java.io.File;
import java.util.Map;

/**
 * Wrapps any image downloader library.
 */
public interface ImageDownloader {

    /**
     * Loads from path.
     * @param path
     * @return
     */
    @NonNull Request load(@NonNull String path);

    /**
     * Loads from file.
     * @param file
     * @return
     */
    @NonNull Request load(@NonNull File file);

    /**
     * Loads from uri.
     * @param uri
     * @return
     */
    @NonNull Request load(@NonNull Uri uri);

    /**
     * Cancel the image view task.
     * @param target
     */
    void cancel(@NonNull ImageView target);

    /**
     * Request interface for each image display.
     */
    public interface Request {

        /**
         * Sets the on empty res id.
         * @param placeHolderResId
         * @return
         */
        Request placeholder(@DrawableRes int placeHolderResId);

        /**
         * Sets the on empty drawable.
         * @param placeHolderDrawable
         * @return
         */
        Request placeholder(@NonNull Drawable placeHolderDrawable);

        /**
         * Sets the on loading res id.
         * @param loadingResId
         * @return
         */
        Request loading(@DrawableRes int loadingResId);

        /**
         * Sets the on loading drawable.
         * @param loadingDrawable
         * @return
         */
        Request loading(@NonNull Drawable loadingDrawable);

        /**
         * Sets the on error res id.
         * @param errorResId
         * @return
         */
        Request error(@DrawableRes int errorResId);

        /**
         * Sets the on error drawable.
         * @param errorDrawable
         * @return
         */
        Request error(@NonNull Drawable errorDrawable);

        /**
         * Sets delay before load the image.
         * @param delayMilli Time in milliseconds.
         * @return
         */
        Request delayBeforeLoading(int delayMilli);

        /**
         * Adds a transformer to the image.
         * @param transformer
         * @return
         */
        Request transform(@NonNull Transformer transformer);

        /**
         * Adds extra info into the headers.
         * @param extras
         * @return
         */
        Request extras(@NonNull Map<String, String> extras);

        /**
         * Adds bitmap quality.
         * By default is setted to Bitmaps RGB_565.
         * @param config
         * @return
         */
        Request bitmapConfig(@NonNull Bitmap.Config config);

        /**
         * Adds a download task to a image view.
         * @param target
         */
        void into(@NonNull ImageView target);

        /**
         * Adds a download task to a image view with a status callback.
         * @param target
         * @param callback
         */
        void into(@NonNull ImageView target, @NonNull final Callback callback);

        /**
         * Adds a download task to a image view with a progress callback.
         * @param target
         * @param progressCallback
         */
        void into(@NonNull ImageView target, @NonNull final ProgressCallback progressCallback);

        /**
         * Adds a download task to a image view with status and progress callback.
         * @param target
         * @param callback
         * @param progressCallback
         */
        void into(@NonNull ImageView target, @NonNull final Callback callback,
                  @NonNull final ProgressCallback progressCallback);

    }

    /**
     * Download status callback.
     */
    public interface Callback {
        void onLoadingStarted();
        void onLoadingFailed();
        void onLoadingComplete();
    }

    /**
     * Download progress callback.
     * You must enable caching on disk: UIL requirement.
     */
    public interface ProgressCallback {
        void onProgressUpdate(int current, int total);
    }

    /**
     * Make transformations on the image.
     */
    public interface Transformer {
        Transformer getTransformer(ImageLoader.Provider type);
    }

}
