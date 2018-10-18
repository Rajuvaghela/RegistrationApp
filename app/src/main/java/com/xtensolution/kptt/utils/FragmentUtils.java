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

package com.xtensolution.kptt.utils;


import android.support.v4.app.Fragment;

import com.xtensolution.kptt.ui.fragment.DashboardFragment;

/**
 * Created by Mithun on 7/28/2015.
 */
public class FragmentUtils {

    public static Fragment getMainFragments(int position) {
        Fragment[] newInstance = new Fragment[]{new DashboardFragment()};
        return newInstance[position];
    }

}
