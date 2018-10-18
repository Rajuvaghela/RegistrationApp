package com.riontech.retrofit.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.riontech.retrofit.wrapper.WrappingCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetModule {

    private String mBaseUrl;
//    private static final int CONNECTION_TIMEOUT = 60000;
   private static final int CONNECTION_TIMEOUT = 300000;


    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    public SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    public Cache provideOkHttpCache(Application application) {
//        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        int cacheSize = 20 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }


    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    public OkHttpClient provideOkHttpClient(Cache cache) {
        // set your desired log level
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

//        builder.authenticator(new TokenAuthenticator());
        builder.addInterceptor(httpLoggingInterceptor);
//        builder.interceptors().add(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                String token = PreferenceHelper.getInstance().getUserToken();
//                Request newRequest = chain.request().newBuilder()
//                        .header("Content-Type", "application/json")
//                        .header("X-Access-Token", token)
//                        .build();
//                AppLog.d("NetworkModule", new Gson().toJson(newRequest.headers()));
//                AppLog.d("NetworkModule", new Gson().toJson(newRequest.url()));
//                return chain.proceed(newRequest);
//
//            }
//        });


        builder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS).
                connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.cache(cache);
        return builder.build();
    }

    public Retrofit provideRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(WrappingCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

}
