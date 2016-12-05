/*
 * Config
 *
 * Created by Roman Rodriguez on 20/05/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.imageloader;


/**
 * Config for image download.
 */
final class Config {// Must have 'Package' visibility

    public static final int THREAD_PRIORITY = Thread.NORM_PRIORITY - 2; // 3
    public static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50mb
    public static final int CONN_TIMEOUT = 15000;
    public static final int READ_TIMEOUT = 20000;
    public static final String SCREEN_WIDTH_KEY = "screen_width";
    public static final String SCREEN_HEIGHT_KEY = "screen_height";

    /**
     * Useful for identify image loader requests
     */
    public static final String USER_AGENT = "AGS Image Loader";

    /**
     * Useful to identify device platform.
     */
    public static final String OS_KEY = "os";
    public static final String OS_NAME = "android";

    private Config() { }

    // IMPORTANT:
    // The clases defined here must have java nomenclature.
    // Only constants must have Upercase names.

}
