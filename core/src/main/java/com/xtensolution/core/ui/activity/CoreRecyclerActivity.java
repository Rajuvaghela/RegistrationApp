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

package com.xtensolution.core.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.xtensolution.core.exceptions.RecyclerViewNotFoundException;
import com.xtensolution.core.ui.listener.RecyclerBinderListener;


/**
 * Created by Vaghela Mithun R. on 22-Jun-17.
 * vaghela.mithun@gmail.com
 */
public abstract class CoreRecyclerActivity extends CoreActionBarActivity implements RecyclerBinderListener {
    private RecyclerView recyclerView;
    protected ProgressBar progressBar;

    protected SwipeRefreshLayout refreshLayout;

    @Override
    protected void onActionBarAttached() {
        initRecyclerView();
    }

    public void initRecyclerView() {
        if (getRecyclerViewId() == 0) {
            throw new RecyclerViewNotFoundException("Recycler View resource id not found");
        }

        recyclerView = _findViewById(getRecyclerViewId());
        if (getProgressBarId() != 0)
            progressBar = _findViewById(getProgressBarId());

        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(getLayoutManager());
        if (getItemDecoration() != null)
            recyclerView.addItemDecoration(getItemDecoration());
            recyclerView.setAdapter(getRecyclerAdapter());
        if(getRefreshLayoutId() != 0){
            refreshLayout = _findViewById(getRefreshLayoutId());
            refreshLayout.setOnRefreshListener(getRefreshListener());
            enableSwipeRefreshLayout(false);
        }

        onRecyclerViewAttached();
    }


    public  <T extends RecyclerView.Adapter<RecyclerView.ViewHolder>> void bindAdapter(T adapter) {
        recyclerView.setAdapter(adapter);
    }

    public LayoutManager getLayoutManager(){
        return new LinearLayoutManager(this);
    }

    public void enableSwipeRefreshLayout(boolean enable) {
        refreshLayout.setEnabled(enable);
        refreshLayout.setSaveEnabled(true);
    }

    @Override
    public int getRefreshLayoutId() {
        return 0;
    }

    @Override
    public int getProgressBarId() {
        return 0;
    }
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
