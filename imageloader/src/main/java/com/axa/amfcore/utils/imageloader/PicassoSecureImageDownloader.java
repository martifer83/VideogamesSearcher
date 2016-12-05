/*
 * PicassoSecureImageDownloader
 *
 * Created by Roman Rodriguez on 12/06/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.imageloader;


import android.content.Context;

import com.axa.amfcore.utils.pinning.OkHttpPinningClient;
import com.axa.amfcore.utils.pinning.PinningFactory;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Custom image downloader for add headers for authentication and certificate pinning on image
 * requests. It uses OkHttp as a http client.
 */
class PicassoSecureImageDownloader extends OkHttpDownloader { // Must have 'Package' visibility

    private static final OkHttpClient CLIENT = new OkHttpClient();
    private Map<String, String> mExtra;

    public PicassoSecureImageDownloader(Context context, boolean pinned,
                                        final int screenWidht, final int screenHeight) {
        super(CLIENT);

        // Default ok http CLIENT
        CLIENT.setConnectTimeout(Config.CONN_TIMEOUT, TimeUnit.MILLISECONDS);
        CLIENT.setReadTimeout(Config.READ_TIMEOUT, TimeUnit.MILLISECONDS);

        // Setup pinning.
        if(pinned) {
            PinningFactory.setPinnedClient(context, new OkHttpPinningClient(context, CLIENT));
        }

        // Adds interceptor
        CLIENT.interceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                com.squareup.okhttp.Request.Builder builder = chain.request().newBuilder();

                // Adds 'extraForDownloader' headers
                Map<String, String> headers = (Map<String, String>) getExtra();
                if (headers != null) {
                    for (Map.Entry<String, String> header : headers.entrySet()) {
                        builder.addHeader(header.getKey(), header.getValue());
                    }
                }


                // Adds screen size headers
                builder
                        .addHeader(Config.SCREEN_WIDTH_KEY, String.valueOf(screenWidht))
                        .addHeader(Config.SCREEN_HEIGHT_KEY, String.valueOf(screenHeight))
                        .removeHeader("User-Agent")
                        .addHeader("User-Agent", Config.USER_AGENT) // To identify image download requests
                        .addHeader(Config.OS_KEY, Config.OS_NAME); // To identify OS on requests

                return chain.proceed(builder.build());
            }
        });

    }

    public synchronized Map<String, String> getExtra() {
        return mExtra;
    }

    public synchronized void setExtra(Map<String, String> extra) {
        mExtra = extra;
    }

}
