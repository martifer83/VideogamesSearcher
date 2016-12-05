/*
 * OkHttpPinningClient
 *
 * Created by Roman Rodriguez on 18/06/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.pinning;

import android.content.Context;
import android.util.Log;

import com.squareup.okhttp.CertificatePinner;
import com.squareup.okhttp.OkHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Wrapps OkHttp pinning functionality.
 */
public class OkHttpPinningClient implements PinningClient {

    private static final String TAG = "OkHttpPinningClient";


    private OkHttpClient mClient;
    private Context mContext;

    public OkHttpPinningClient(Context context, OkHttpClient client) {
        mClient = client;
        mContext = context;
    }

    public OkHttpClient getClient() {
        return mClient;
    }

    @Override
    public void pinCerts(List<String> paths) {

        if(paths == null) return;

        try {

            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Collection<Certificate> certificates = new ArrayList<>(paths.size());

            for(String path : paths) {
                certificates.add(certificateFactory
                        .generateCertificate(mContext.getAssets().open(path)));
            }

            SSLContext sslContext = sslContextForTrustedCertificates(certificates);
            mClient.setSslSocketFactory(sslContext.getSocketFactory());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch(IOException e) {
            Log.e(TAG, e.getMessage());
        }

    }


    /**
     * Inits an OkHttpClient for public key pinning.
     * More info: https://www.owasp.org/index.php/Certificate_and_Public_Key_Pinning
     */
    @Override
    public void pinKeys(List<String> paths) {

        if(paths == null) return;

        try {

            List<String> keys = new ArrayList<>(paths.size());
            for(String path : paths) {
                keys.add(readInputStream(mContext.getAssets().open(path)));
            }

            TrustManager tm[] = { new PubKeyManager(keys) };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tm, null);
            mClient.setSslSocketFactory(sslContext.getSocketFactory());
        } catch (GeneralSecurityException e) {
            Log.e(TAG, e.getMessage());
            throw new AssertionError("The system has no TLS"); // Just give up.
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

    }

    /**
     * Inits an OkHttpClient for hash pinning.
     * More info:
     * http://square.github.io/okhttp/javadoc/com/squareup/okhttp/CertificatePinner.html
     * http://src.chromium.org/viewvc/chrome/trunk/src/net/http/transport_security_state_static.certs
     */
    @Override
    public void pinHashes(String path) {

        if(path == null) return;

        try {
            Properties properties = new Properties();
            InputStream is = mContext.getAssets().open(path);
            if (is != null) {
                properties.load(is);

                CertificatePinner.Builder builder = new CertificatePinner.Builder();

                final Iterator<Map.Entry<Object, Object>> it = properties.entrySet().iterator();
                Map.Entry<Object, Object> entry;
                while(it.hasNext()) {
                    entry = it.next();
                    builder.add((String)entry.getKey(), (String)entry.getValue());
                }

                mClient.setCertificatePinner(builder.build());
            }
        } catch(IOException e) {
            Log.e(TAG, e.getMessage());
        }

    }

    /**
     * Returns a SSL context that trusts {@code certificates} and none other. HTTPS services whose
     * certificates have not been signed by these certificates will fail with a {@code
     * SSLHandshakeException}.
     *
     * <p>This can be used to replace the host platform's built-in trusted certificates with a custom
     * set. This is useful in development where certificate authority-trusted certificates aren't
     * available. Or in production, to avoid reliance on third-party certificate authorities.
     *
     * <p>See also {@link CertificatePinner}, which can limit trusted certificates while still using
     * the host platform's built-in trust store.
     */
    private static SSLContext sslContextForTrustedCertificates(Collection<Certificate> certificates) {
        try {
            // Put the certificates a key store.
            char[] password = "password".toCharArray(); // Any password will work.
            KeyStore keyStore = newEmptyKeyStore(password);
            int index = 0;
            for (Certificate certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificate);
            }

            // Wrap it up in an SSL context.
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, password);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext;

        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private static KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private static String readInputStream(InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = input.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return new String(baos.toByteArray());
    }

}
