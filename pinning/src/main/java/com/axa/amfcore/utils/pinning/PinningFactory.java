/*
 * PinningFactory
 *
 * Created by Roman Rodriguez on 20/05/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.pinning;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * <p>Cert files with .cer extension: These are X.509 PEM format certs
 *
 * <p>Hash files with .hash extension: Paired host/key values, like properties file.
 * This is a white list:
 *      All items here are checked.
 *      Hosts not added here works but they are not checked.
 *      If host has more than one pair and one does not check, the operation fails.
 * <p>Public key files with .key extension.
 *
 * <p>These files are stored on assets/pinning
 */
public class PinningFactory {

    private static final String TAG = "PinningFactory";


    /** Pinning assets folder for storing all pinning info */
    private static final String PINNING_ASSETS_FOLDER = "pinning";

    private static final Pattern sCertPattern = Pattern.compile(".*.cer");
    private static final Pattern sHashPattern = Pattern.compile(".*.hash");
    private static final Pattern sKeyPattern = Pattern.compile(".*.key");

    /**
     * Inits an client for pinning.
     * @param context
     * @param client
     * @return PinningClient
     */
    public static PinningClient setPinnedClient(Context context, PinningClient client) {

        // PRECONDITIONS
        if (context == null) {
            throw new IllegalArgumentException("Context is null");
        }
        else if (client == null) {
            throw new IllegalArgumentException("PinningClient is null");
        }


        client.pinCerts(getFiles(context, sCertPattern));
        client.pinKeys(getFiles(context, sKeyPattern));
        final List<String> files = getFiles(context, sHashPattern);
        if(files != null) {
            client.pinHashes(files.get(0));
        }

        return client;
    }

    /**
     * List the files on 'PINNING_ASSETS_FOLDER' that matches the pattern
     * @param context
     * @param pattern
     * @return List of files, null if none.
     */
    private static List<String> getFiles(Context context, Pattern pattern) {

        List<String> files = null;

        try {

            final String[] list = context.getAssets().list(PINNING_ASSETS_FOLDER);

            if (list != null && list.length > 0) {
                files = new ArrayList<>();

                for (String name : list) {
                    if (pattern.matcher(name).matches()) {
                        files.add(PINNING_ASSETS_FOLDER + File.separator + name);
                    }
                }

                if(files.isEmpty()) {
                    files = null;
                }
            }

        } catch(IOException e) {

            Log.e(TAG, e.getMessage());

        } finally {

            return files;

        }

    }


}
