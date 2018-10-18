package com.xtensolution.core.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MIT on 10/21/2016.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected LayoutInflater inflater;
    public static final int PAGE_LIMIT = 15;
    protected Context context;
    protected List<T> objectsList;
    protected static final int FOOTER_VIEW = 2;
    protected int displayItemCount;
    private PaginationListener paginationListener;
    private boolean isPageLoading;

    private static final int THRESOLD = 3;

    public BaseRecyclerViewAdapter(Context context) {
        this(context, new ArrayList<T>());
    }

    public BaseRecyclerViewAdapter(Context context, List<T> objectsList) {
        this.context = context;
        this.objectsList = objectsList;
        this.inflater = LayoutInflater.from(context);
        displayItemCount = objectsList.size();
    }

    public void setPageLoading(boolean pageLoading) {
        isPageLoading = pageLoading;
    }

    public boolean isPageLoading() {
        return isPageLoading;
    }

    public void setDisplayItemCount(int itemCount) {
        this.displayItemCount = itemCount;
    }

    public void setPaginationListener(PaginationListener paginationListener) {
        this.paginationListener = paginationListener;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (objectsList.size() < displayItemCount)
            displayItemCount = objectsList.size();
        return displayItemCount;
    }

    public int getCount() {
        return objectsList.size();
    }

    public T getItem(int position) {
        return objectsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateList(List<T> newData) {
//        objectsList.clear();
        objectsList = newData;
        displayItemCount = objectsList.size();
        notifyDataSetChanged();
    }

    public void add(T item) {
        objectsList.add(item);
        displayItemCount = objectsList.size();
        notifyDataSetChanged();
    }

    public void addAt(int position, T item) {
        objectsList.add(position, item);
        displayItemCount = objectsList.size();
        notifyDataSetChanged();
    }

    public void addList(List<T> items) {
        objectsList.addAll(0, items);
        displayItemCount = objectsList.size();
        notifyDataSetChanged();
    }

    public void addAtLast(List<T> items) {
        if (isPageLoading())
            objectsList.addAll(objectsList.size() - 1, items);
        else
            objectsList.addAll(items);
        displayItemCount = objectsList.size();
        notifyDataSetChanged();
    }


    public List<T> getList() {
        return objectsList;
    }

    public void remove(T item) {
        objectsList.remove(item);
        displayItemCount = objectsList.size();
        notifyDataSetChanged();
    }

    public void remove(int position) {
        objectsList.remove(position);
        displayItemCount = objectsList.size();
        notifyItemRemoved(position);
    }

    public interface PaginationListener {
        void onPageLoad(int offset);
    }

    protected void loadNextPage(int position) {
        if (paginationListener != null && position == (objectsList.size() - THRESOLD)) {
            if (isPageLoading())
                paginationListener.onPageLoad(objectsList.size() - 1);
            else
                paginationListener.onPageLoad(objectsList.size());
        }
    }
}
