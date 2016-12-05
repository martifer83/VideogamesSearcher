/*
 * UILImageDownloader
 *
 * Created by Roman Rodriguez on 20/05/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.imageloader;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * UIL wrapper.
 */
public final class UILImageDownloader implements ImageDownloader {

    private static final String TAG = "UILImageDownloader";


    private static UILImageDownloader sInstance;
    private static com.nostra13.universalimageloader.core.ImageLoader sUILImageLoader;


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
            synchronized(UILImageDownloader.class) {
                if(sInstance == null) {
                    sInstance = new UILImageDownloader(context, pinned, screenWidth, screenHeight);
                }
            }
        }

        return sInstance;
    }

    /**
     * Builder.
     * @param context
     */
    private UILImageDownloader(@NonNull Context context, boolean pinned, int screenWidth, int screenHeight) {

        // Gets library and inits library
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context);
        // Task priority
        builder.threadPriority(Config.THREAD_PRIORITY);
        // Disk cache
        builder.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        builder.diskCacheSize(Config.DISK_CACHE_SIZE);

        // Take care about memory cache, using it has worst performance.

        // Universal Image Loader keeps reduced images in memory to save memory. Size of every
        // reduced image is calculated and it depends on target ImageView for this image
        // (android:layout_width, android:layout_height, android:maxWidth, android:maxHeight
        // parameters, android:scaleType, device screen size are considered).

        // Authentication headers info and certs handle: Dev must call 'extras' request
        builder.imageDownloader(new UILSecureImageDownloader(context, pinned, screenWidth, screenHeight));


        sUILImageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        sUILImageLoader.init(builder.build());
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
        return new UILRequest(sUILImageLoader, uri == null ? Uri.parse("") : uri);
    }

    /**
     * Cancel the image view task.
     * @param target
     */
    public void cancel(@NonNull ImageView target) {
        if(target != null) {
            sUILImageLoader.cancelDisplayTask(target);
        }
    }

}
