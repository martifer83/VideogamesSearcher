/*
 * ImageLoader
 *
 * Created by Roman Rodriguez on 20/05/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.imageloader;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.File;

/**
 * Wrapps UIL & Picasso but should work with any image downloader library.
 * Uses okhttp as a http connection client but should work with any http client.
 *
 * By default uses:
 *      Thread priority: 3
 *      50MB of disk cache size
 *      15 seconds of timeout
 *      20 seconds of image read timeout
 *      Sends device screen size as "screen_width" and "screen_height" headers
 *      Bitmaps in RGB_565, that consumes 2 times less memory than in ARGB_8888.
 *
 * How to define Bitmap size needed for exact ImageView? It searches defined parameters:
 *      Get actual measured width and height of ImageView.
 *      Get android:layout_width and android:layout_height parameters.
 *      Get android:maxWidth and/or android:maxHeight parameters.
 *      Get maximum width and/or height parameters from configuration (memoryCacheExtraOptions(int, int) option)
 *      Get width and/or height of device screen.
 */
public final class ImageLoader implements ImageDownloader {

    private static final String TAG = "ImageLoader";

    public enum Provider {UIL, PICASSO};
    private static ImageLoader sInstance;
    private static int sScreenWidth;
    private static int sScreenHeight;
    private ImageDownloader mImageDownloader;


    /**
     * Thread safe singleton.
     * @param context
     * @return
     */
    public static ImageDownloader init(@NonNull Context context) {
        return ImageLoader.init(context, Provider.UIL, true);
    }

    /**
     * Thread safe singleton.
     * @param context
     * @return
     */
    public static ImageDownloader init(@NonNull Context context, boolean pinned) {
        return ImageLoader.init(context, Provider.UIL, pinned);
    }

    /**
     * Thread safe singleton.
     * @param context
     * @return
     */
    public static ImageDownloader init(@NonNull Context context,
                                       @NonNull Provider type, boolean pinned) {
        // PRECONDITIONS
        if(context == null) {
            throw new IllegalArgumentException("Context is null");
        }

        if(sInstance == null) {
            synchronized(ImageLoader.class) {
                if(sInstance == null) {
                    sInstance = new ImageLoader(context, type, pinned);
                }
            }
        }

        return sInstance;
    }

    /**
     * Singleton.
     * @return
     */
    public static ImageDownloader getInstance() {
        // PRECONDITIONS
        if(sInstance == null) {
            throw new IllegalStateException("'init' must be call previously");
        }

        return sInstance;
    }

    /**
     * Resets the configuration.
     */
    public static void reset() {
        sInstance = null;
    }


    /**
     * Builder.
     * @param context
     */
    private ImageLoader(@NonNull Context context, @NonNull Provider type, boolean pinned) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // Gets screen size
        sScreenWidth = size.x;
        sScreenHeight = size.y;

        // Inits the libraries
        switch (type) {
            case PICASSO:
                mImageDownloader = PicassoImageDownloader
                        .getInstance(context, pinned, sScreenWidth, sScreenHeight);
                break;
            case UIL:
            default:
                mImageDownloader = UILImageDownloader
                        .getInstance(context, pinned, sScreenWidth, sScreenHeight);
                break;
        }

    }


    /**
     * Loads from path.
     * @param path
     * @return
     */
    public ImageDownloader.Request load(@NonNull String path) {
        return mImageDownloader.load(path);
    }

    /**
     * Loads from file.
     * @param file
     * @return
     */
    public ImageDownloader.Request load(@NonNull File file) {
        return mImageDownloader.load(file);
    }

    /**
     * Loads from uri.
     * @param uri
     * @return
     */
    public ImageDownloader.Request load(@NonNull Uri uri) {
        return mImageDownloader.load(uri);
    }

    /**
     * Cancel the image view task.
     * @param target
     */
    public void cancel(@NonNull ImageView target) {
        mImageDownloader.cancel(target);
    }

}
