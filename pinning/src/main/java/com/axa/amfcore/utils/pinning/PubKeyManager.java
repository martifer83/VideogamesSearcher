/*
 * PubKeyManager
 *
 * Created by Roman Rodriguez on 20/05/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.pinning;

import android.util.Base64;

import java.math.BigInteger;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Trust manager for public key pinning
 */
final class PubKeyManager implements X509TrustManager { // Must have 'Package' visibility

    public final ArrayList<String> mPublicKeys;

    private static final char[] DIGITS =
            {'0', '1', '2', '3', '4', '5', '6', '7',
                    '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public PubKeyManager(final List<String> publicKeys) {
        mPublicKeys = new ArrayList<>(publicKeys.size());

        // Remove 'begin key' and 'end key' flags
        // Remove new line scape code
        // Encode from base64 to hex
        String auxKey;
        for(String key : publicKeys) {
            auxKey = key;
            auxKey = auxKey.replace("-----BEGIN PUBLIC KEY-----", "");
            auxKey = auxKey.replace("-----END PUBLIC KEY-----", "");
            auxKey = auxKey.replace("\n", "");
            byte[] textBytes = Base64.decode(auxKey, Base64.DEFAULT);

            mPublicKeys.add(toHex(textBytes));
        }

    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        if (chain == null) {
            throw new IllegalArgumentException("X509Certificate array is null");
        }
        if (chain.length == 0) {
            throw new IllegalArgumentException("X509Certificate is empty");
        }
        // TODO : Do we have to check equals 'RSA' or contains 'RSA'?
        /*if (null == authType || !authType.equalsIgnoreCase("RSA")) {
            throw new CertificateException("CheckServerTrusted: AuthType is not RSA. AuthType: " + authType);
        }*/

        // Perform customary SSL/TLS checks
        TrustManagerFactory tmf;
        try {
            tmf = TrustManagerFactory.getInstance("X509");
            tmf.init((KeyStore) null);

            for (TrustManager trustManager : tmf.getTrustManagers()) {
                ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
            }

        } catch (Exception e) {
            throw new CertificateException(e);
        }

        // Hack ahead: BigInteger and toString(). We know a DER encoded Public
        // Key starts with 0x30 (ASN.1 SEQUENCE and CONSTRUCTED), so there is
        // no leading 0x00 to drop.
        RSAPublicKey pubkey = (RSAPublicKey) chain[0].getPublicKey();
        String encoded = new BigInteger(1, pubkey.getEncoded()).toString(16);

        boolean expected = false;
        for(String key : mPublicKeys) {

            if(key.equalsIgnoreCase(encoded)) {
                expected = true;
                break;
            }

        }

        if (!expected) {
            throw new CertificateException("Expected public keys: " +
                    mPublicKeys + ", have not got public key:" + encoded);
        }

    }

    public void checkClientTrusted(X509Certificate[] xcs, String string) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // TODO: checkClientTrusted
    }

    public X509Certificate[] getAcceptedIssuers() {
        // throw new UnsupportedOperationException("Not supported yet.");
        // TODO: getAcceptedIssuers
        return null;
    }

    /**
     * Converts a byte array to a hexadecimal String
     * @param data the data to encode
     * @return String the resulting String
     */
    private static final String toHex(byte[] data) {
        final StringBuffer sb = new StringBuffer(data.length * 2);
        for (int i = 0; i < data.length; i++) {
            sb.append(DIGITS[(data[i] >>> 4) & 0x0F]);
            sb.append(DIGITS[data[i] & 0x0F]);
        }
        return sb.toString();
    }

}
