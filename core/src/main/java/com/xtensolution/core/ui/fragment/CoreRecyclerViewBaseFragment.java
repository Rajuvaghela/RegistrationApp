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

package com.xtensolution.core.ui.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.xtensolution.core.exceptions.RecyclerViewNotFoundException;
import com.xtensolution.core.ui.listener.RecyclerBinderListener;
import com.xtensolution.core.utils.AppLog;

/**
 * Created by Vaghela Mithun R. on 17-Jun-17.
 * vaghela.mithun@gmail.com
 */
public abstract class CoreRecyclerViewBaseFragment extends CoreBaseFragment implements RecyclerBinderListener {
    private static final String TAG = CoreRecyclerViewBaseFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    protected int orientation = LinearLayoutManager.VERTICAL;
    protected ProgressBar progressBar;
    protected SwipeRefreshLayout refreshLayout;
    protected FloatingActionButton fab;
    protected boolean isRefreshing;
    protected boolean isLoading;
    protected View noResultFound;
    private boolean loading;
    private boolean isLoadMoreEnable = true;
    private boolean isItemDecorationEnable = true;

    @Override
    protected void onViewBind() {
        initRecyclerView();
    }

    public void initRecyclerView() {
        try {
            if (getRecyclerViewId() == 0) {
                throw new RecyclerViewNotFoundException("Recycler View resource id not found");
            }

            recyclerView = _findViewById(getRecyclerViewId());
            if (getProgressBarId() != 0)
                progressBar = _findViewById(getProgressBarId());


            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(getLayoutManager());
            if (getFabId() != 0) {
                fab = _findViewById(getFabId());
                // recyclerView.addOnScrollListener(new RecyclerScrollListener());
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                            fab.hide();
                        } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                            fab.show();
                        }
                    }
                });


            }


            if (getItemDecoration() != null)
                recyclerView.addItemDecoration(getItemDecoration());

            recyclerView.setAdapter(getRecyclerAdapter());

            if (getRefreshLayoutId() != 0) {
                refreshLayout = _findViewById(getRefreshLayoutId());
                refreshLayout.setOnRefreshListener(getRefreshListener());
                enableSwipeRefreshLayout(false);
            }


            onRecyclerViewAttached();
        } catch (RecyclerViewNotFoundException e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }


    private int getFabId() {
        return 0;
    }


//    protected  abstract  RecyclerView.Adapter<RecyclerView.ViewHolder> getRecyclerAdapter();
    public   abstract  RecyclerView.Adapter<RecyclerView.ViewHolder> getRecyclerAdapter();

    public <T extends RecyclerView.Adapter<RecyclerView.ViewHolder>> void bindAdapter(T adapter) {
        recyclerView.setAdapter(adapter);
    }


    public void notifyAdapter() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void enableSwipeRefreshLayout(boolean enable) {
        refreshLayout.setEnabled(enable);
        refreshLayout.setSaveEnabled(true);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Override
    public int getRefreshLayoutId() {
        return 0;
    }



    @Override
    public int getProgressBarId() {
        return 0;
    }

    public class RecyclerScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dy > 0 || dy < 0 && fab.isShown()) {
                fab.hide();
            }
            super.onScrolled(recyclerView, dx, dy);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                fab.show();
            }

            super.onScrollStateChanged(recyclerView, newState);
        }
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    //lode more
}
