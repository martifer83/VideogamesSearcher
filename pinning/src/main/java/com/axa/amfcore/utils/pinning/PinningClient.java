/*
 * PinningClient
 *
 * Created by Roman Rodriguez on 20/05/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */
package com.axa.amfcore.utils.pinning;

import java.util.List;

/**
 * All http clients must implement this interface.
 */
public interface PinningClient {
    /**
     * Pin X.509 PEM certs.
     * @param paths Path of files.
     */
    void pinCerts(List<String> paths);

    /**
     * Pin public keys.
     * @param paths Path of files.
     */
    void pinKeys(List<String> paths);

    /**
     * Pin hosts public key hashes
     * @param path Path of file.
     */
    void pinHashes(String path);
}
