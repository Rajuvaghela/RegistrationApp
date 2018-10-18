package com.xtensolution.core.ui.listener;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

/**
 * Created by MIT on 01-Nov-17.
 */

public interface RecyclerBinderListener {
    /**
     * Initialization of recycler view
     */
    void initRecyclerView();
    /**
     * To find the recycler view by id,
     * receive from extended activity
     * @return
     */
    int getRecyclerViewId();

    /**
     * LayoutManager require to init recycler view,
     * receive from extended activity or default
     * @return
     */
    RecyclerView.LayoutManager getLayoutManager();

    /**
     * To display progress on data loading,
     * get progress bar id where it defined into
     * child view
     * @return
     */
    int getProgressBarId();

    /**
     * Option, if require then init to bind with
     * recycler view
     * @return
     */
    RecyclerView.ItemDecoration getItemDecoration();

    /**
     * Bind adapter to recycler view to manipulate the data
     * @param adapter
     * @param <T>
     */
    <T extends RecyclerView.Adapter<RecyclerView.ViewHolder>> void bindAdapter(T adapter);

    /**
     * Swipe to refresh layout find id from child view
     * @return
     */
    int getRefreshLayoutId();

    /**
     * To listen, refresh layout has been swipe,
     * get from child view
     * @return
     */
    SwipeRefreshLayout.OnRefreshListener getRefreshListener();

    /**
     * To enable and disable swipe refresh layout
     * in current view
     */
    void enableSwipeRefreshLayout(boolean enable);

    /**
     * Call this function after recycler view attached
     * into main view
     */
    void onRecyclerViewAttached();

        RecyclerView.Adapter<RecyclerView.ViewHolder> getRecyclerAdapter();

}
