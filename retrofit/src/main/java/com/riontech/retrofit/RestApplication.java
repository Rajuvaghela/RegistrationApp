package com.riontech.retrofit;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.riontech.retrofit.exception.BaseUrlNotFoundException;
import com.riontech.retrofit.modules.NetModule;
import com.riontech.retrofit.network.NetworkChangeReceiver;
import com.xtensolution.core.CoreApp;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


/**
 * Created by Vaghela Mithun on 28-Feb-17.
 * vaghela.mithun@gmail.com
 */

public abstract class RestApplication extends CoreApp {

    private static final String TAG = RestApplication.class.getSimpleName();
    private static RestApplication instance;
    protected Retrofit mRetrofit;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // in child app initNetModule in onCreate first
        if (getBaseUrl() != null && !getBaseUrl().isEmpty())
            initNetModule();
        else
            throw new BaseUrlNotFoundException(getString(R.string.require_base_url));

        registerNetworkChangeReceiver();
    }

    private void registerNetworkChangeReceiver() {
        networkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter intentFilters = new IntentFilter();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, intentFilters);
    }

    protected synchronized void initNetModule() {
        try {
            NetModule netModule = new NetModule(getBaseUrl());
            Cache cache = netModule.provideOkHttpCache(this);
            OkHttpClient okHttpClient = netModule.provideOkHttpClient(cache);
            mRetrofit = netModule.provideRetrofit(okHttpClient);
        } catch (Exception e) {
            Log.e(TAG, "initNetModule: " + e.getMessage(), e);
        }
    }

    public static RestApplication getInstance() {
        return instance;
    }

    public synchronized Retrofit getRetrofit() {
        if (mRetrofit == null) {
            synchronized (Retrofit.class) {
                initNetModule();
            }
        }
        return mRetrofit;
    }

    protected abstract String getBaseUrl();

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterReceiver(networkChangeReceiver);
    }

    public void addOnNetworkChangeListener(NetworkChangeReceiver.OnNetworkChangeListener listener) {
        networkChangeReceiver.setOnNetworkChangeListener(listener);
    }
}
