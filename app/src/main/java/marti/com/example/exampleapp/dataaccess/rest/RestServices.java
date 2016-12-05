package marti.com.example.exampleapp.dataaccess.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.OkHttpClient;

import java.security.cert.CertificateException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import marti.com.example.exampleapp.BuildConfig;
import marti.com.example.exampleapp.dataaccess.deserializer.DateTypeDeserializer;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by mferrando on 02/06/16.
 */
public class RestServices<T> {

    private T mService;
    private OkHttpClient mOkHttpClient;

    public RestServices(Class<T> servicesClass, String endpoint/*, RequestInterceptor requestInterceptor*/) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTypeDeserializer())
                .create();

        mOkHttpClient = getUnsafeOkHttpClient();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .setEndpoint(endpoint)
                .setConverter(new GsonConverter(gson))
                //.setRequestInterceptor(requestInterceptor)
                .setClient(new OkClient(mOkHttpClient))
                .build();

        mService = restAdapter.create(servicesClass);
    }

    public void setAuthenticator(Authenticator authenticator) {
        if (authenticator != null) {
            mOkHttpClient.setAuthenticator(authenticator);
        }
    }

    public T getService() {
        return mService;
    }

    //TODO qutar este metodo
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
