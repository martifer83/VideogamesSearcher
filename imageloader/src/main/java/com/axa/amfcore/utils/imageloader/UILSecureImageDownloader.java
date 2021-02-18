/*
 * UILSecureImageDownloader
 *
 * Created by Roman Rodriguez on 20/05/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.imageloader;


import android.content.Context;
import android.support.annotation.NonNull;

import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Custom image downloader for add headers for authentication and certificate pinning on image
 * requests. It uses OkHttp as a http sClient.
 *
 * Issue:
 * https://github.com/square/okhttp/issues/184
 * OkHttp enables SPDY for the shared SSL context, and other HTTP clients like
 * HttpURLConnection don't anticipate this, causing them to freak out and crash the app.
 * The problem is that Java and Android programs default to using a single global SSL context,
 * accessible to our HTTP clients as the SSLSocketFactory. When OkHttp enables NPN for its own
 * SPDY-related stuff, it ends up turning on NPN for the singleton shared SSL socket factory.
 * Later when HttpURLConnection attempts to do SSL, it freaks out and crashes because NPN is
 * enabled when it isn't expected to be.
 *
 * Solution 1 (Implemented Here):
 * Workaround is to use only OkHttp connection. Possible problems that 3rd party libraries will
 * still use Android UrlConnection that could cause this crushes.
 *
 * Solutions 2:
 * A simple immediate workaround for OkHttp is to configure the sClient to create its own SSL
 * context rather than using the system default.
 */
class UILSecureImageDownloader extends BaseImageDownloader { // Must have 'Package' visibility

    private static final OkHttpClient CLIENT = new OkHttpClient();
    private final int mScreenWidht;
    private final int mScreenHeight;


    public UILSecureImageDownloader(@NonNull Context context, boolean pinned, int screenWidht, int screenHeight) {
        super(context);
        mScreenWidht = screenWidht;
        mScreenHeight = screenHeight;

        // Default ok http CLIENT
        CLIENT.setConnectTimeout(Config.CONN_TIMEOUT, TimeUnit.MILLISECONDS);
        CLIENT.setReadTimeout(Config.READ_TIMEOUT, TimeUnit.MILLISECONDS);

    }

    @Override
    @SuppressWarnings("unchecked")
    protected InputStream getStreamFromNetwork(String imageUri, Object extra) throws IOException {
        com.squareup.okhttp.Request.Builder builder = new com.squareup.okhttp.Request.Builder().url(imageUri);

        if(null != extra && extra instanceof Map) {
            // Adds 'extraForDownloader' headers
            Map<String, String> headers = (Map<String, String>) extra;
            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    builder.addHeader(header.getKey(), header.getValue());
                }
            }
        }

        // Adds screen size headers
        builder
                .addHeader(Config.SCREEN_WIDTH_KEY, String.valueOf(mScreenWidht))
                .addHeader(Config.SCREEN_HEIGHT_KEY, String.valueOf(mScreenHeight))
                .removeHeader("User-Agent")
                .addHeader("User-Agent", Config.USER_AGENT) // To identify image download requests
                .addHeader(Config.OS_KEY, Config.OS_NAME); // To identify OS on requests

        return CLIENT.newCall(builder.build()).execute().body().byteStream();
    }

}
