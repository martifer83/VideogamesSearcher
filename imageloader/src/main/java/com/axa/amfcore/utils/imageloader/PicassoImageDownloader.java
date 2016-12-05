/*
 * PicassoImageDownloader
 *
 * Created by Roman Rodriguez on 12/06/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.imageloader;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * UIL wrapper.
 */
public final class PicassoImageDownloader implements ImageDownloader {

    private static final String TAG = "PicassoImageDownloader";


    private static PicassoImageDownloader sInstance;
    private static Picasso sPicassoImageLoader;
    private static PicassoSecureImageDownloader sDownloader;


    /**
     * Thread safe singleton.
     * @param context
     * @return
     */
    public static ImageDownloader getInstance(@NonNull Context context, boolean pinned,
                                              int screenWidth, int screenHeight) {

        // PRECONDITIONS
        if(context == null) {
            throw new IllegalArgumentException("Context is null");
        }

        if(sInstance == null) {
            synchronized(PicassoImageDownloader.class) {
                if(sInstance == null) {
                    sInstance = new PicassoImageDownloader(context, pinned, screenWidth, screenHeight);
                }
            }
        }

        return sInstance;
    }

    /**
     * Builder.
     * @param context
     */
    private PicassoImageDownloader(@NonNull Context context, boolean pinned,
                                   int screenWidth, int screenHeight) {

        Picasso.Builder builder = new Picasso.Builder(context);

        sDownloader =
                new PicassoSecureImageDownloader(context, pinned, screenWidth, screenHeight);

        builder.downloader(sDownloader);

        sPicassoImageLoader = builder.build();

    }

    /**
     * Loads from path.
     * @param path
     * @return
     */
    public Request load(@NonNull String path) {
        return load(Uri.parse(path == null ? "" : path));
    }

    /**
     * Loads from file.
     * @param file
     * @return
     */
    public Request load(@NonNull File file) {
        return load(file == null ? Uri.parse("") : Uri.fromFile(file));
    }

    /**
     * Loads from uri.
     * @param uri
     * @return
     */
    public Request load(@NonNull Uri uri) {
        return new PicassoRequest(sPicassoImageLoader, sDownloader,
                uri == null ? Uri.parse("") : uri);
    }

    /**
     * Cancel the image view task.
     * @param target
     */
    public void cancel(@NonNull ImageView target) {
        if(target != null) {
            sPicassoImageLoader.cancelRequest(target);
        }
    }

}
