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

package com.xtensolution.kptt.rest;


import com.xtensolution.core.utils.AppLog;
import com.xtensolution.kptt.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MIT on 28-Jul-17.
 */

public class DataWrapper {
    private static final String TAG = DataWrapper.class.getSimpleName();
    public static final String CUSTOMER = "customers";
    public static final String AGENTS = "agents";
    public static final String VENDORS = "vendors";
    public static final String TRANSPORT = "transport";
    public static final String LABOR = "labor";
    public static final String LEDGERS = "ledgers";
    public static final String ITEMS = "items";
    public static final String DESIGN = "design";

    public static final String SALES = "sales";
    public static final String ORDER = "order";
    public static final String PURCHASE = "purchase";

    private HashMap<String, ArrayList> mapWraper;
    private static DataWrapper instance;

    private DataWrapper() {
        mapWraper = new HashMap<>();
    }

    public static DataWrapper getInstance() {
        if (instance == null) {
            synchronized (DataWrapper.class) {
                if (instance == null) {
                    instance = new DataWrapper();
                }
            }
        }
        return instance;
    }

    public <T> ArrayList<T> getList(String key) {
        if (mapWraper.containsKey(key)) {
            return (ArrayList<T>) mapWraper.get(key);
        } else {
            return new ArrayList<>();
        }
    }

    public <T> void add(String key, T object) {
        ArrayList<T> list;
        if (hasKeyList(key)) {
            list = mapWraper.get(key);
        } else {
            list = new ArrayList<>();
        }

        list.add(object);
        mapWraper.put(key, list);
    }

    public <T> void addList(String key, ArrayList<T> list) {
        mapWraper.put(key, list);
    }

    public void removeList(String key) {
        if (hasKeyList(key)) {
            mapWraper.remove(key);
        } else {
            AppLog.d(TAG, "No List exists in wrapper with name of " + key);
        }
    }

    private boolean hasKeyList(String key) {
        return mapWraper.containsKey(key);
    }

}
