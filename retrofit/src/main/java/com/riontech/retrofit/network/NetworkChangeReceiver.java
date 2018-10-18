/*
 * Copyright (c) 2016 riontech-xten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.riontech.retrofit.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;

import com.xtensolution.core.utils.NetworkUtil;


/**
 * Created by MIT on 21-Jul-17.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    private OnNetworkChangeListener listener;

    public void setOnNetworkChangeListener(OnNetworkChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener != null) {
            NetworkInfo info = NetworkUtil.getActiveNetworkInfo(context);
            switch (info.getState()) {
                case CONNECTED:
                    listener.onConnected();
                    break;
                case DISCONNECTED:
                case DISCONNECTING:
                case SUSPENDED:
                case UNKNOWN:
                    listener.onDisconnected();
                    break;
                case CONNECTING:
                    listener.onConnecting();
                    break;
            }
        }
    }

    public interface OnNetworkChangeListener {
        void onDisconnected();

        void onConnecting();

        void onConnected();
    }
}
