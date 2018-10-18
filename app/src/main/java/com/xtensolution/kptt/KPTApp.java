package com.xtensolution.kptt;

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

import android.content.Context;

import com.riontech.retrofit.RestApplication;
import com.xtensolution.kptt.rest.DataWrapper;
import com.xtensolution.kptt.rest.RestClient;




/**
 * Created by Vaghela Mithun R. on 16-Jun-17.
 * vaghela.mithun@gmail.com
 */
public class KPTApp extends RestApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
//        dataWrapper = new DataWrapper();
        context = this;
    }

    @Override
    protected String getBaseUrl() {
        return RestClient.BASE_URL;
    }

    public static KPTApp getInstance() {
        return (KPTApp) context;
    }

    public Context getContext() {
        return context;
    }

    public RestClient getResClient() {
        return getRetrofit().create(RestClient.class);
    }

}
