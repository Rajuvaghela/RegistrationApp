
/*
 * Copyright (c) 2016 riontech-xten
 *
 * Licensed under the Apache License, Version member2.0 (the "License");
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

package com.xtensolution.core.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtensolution.core.R;
import com.xtensolution.core.ui.activity.CoreBaseActivity;
import com.xtensolution.core.utils.ErrorUtils;

import java.text.ParseException;


/**
 * Created by Vaghela Mithun R. on 05-Jun-17.
 * vaghela.mithun@gmail.com
 */
public abstract class CoreBaseFragment extends Fragment {

    protected CoreBaseActivity activity;
    protected View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getViewResourceId(), null, false);
        activity = (CoreBaseActivity) getActivity();
        setHasOptionsMenu(true);
        try {
            onViewBind();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onResume() {
        activity.setActionBarTitle(getScreenTitle());
        super.onResume();
    }

    public void setScreenTitle(String title) {
        activity.setActionBarTitle(title);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public <T extends View> T _findViewById(int resId) {
        return (T) view.findViewById(resId);
    }

    abstract protected String getScreenTitle();

    abstract protected int getViewResourceId();

    abstract protected void onViewBind() throws ParseException;

    abstract protected void changeDrawerIconState();

    abstract public int getCurrentMenuId();



}
