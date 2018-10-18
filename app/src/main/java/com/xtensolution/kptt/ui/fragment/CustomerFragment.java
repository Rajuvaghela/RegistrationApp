package com.xtensolution.kptt.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.xtensolution.core.utils.ResourceUtils;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.Customer;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.rest.DataWrapper;
import com.xtensolution.kptt.ui.activity.MainActivity;
import com.xtensolution.kptt.ui.adapter.CustomerAdapter;
import com.xtensolution.kptt.ui.viewmodel.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RIONTECH
 * Riontech on 3/11/17.
 */

public class CustomerFragment extends RecyclerBaseFragment<Customer> implements View.OnClickListener {
    private static final String TAG = CustomerFragment.class.getSimpleName();
    private FloatingActionButton mFAB;
    private static final String ARG_TITLE = "title";
    private CustomerAdapter mAdapter;
    private CustomerViewModel mViewModel;
    private List<Customer> mSearchList = new ArrayList<>();
    private boolean isSearching;
    private RecyclerView recyclerView;
    private String searchStr;
    private boolean isFabShowing;
//    private boolean isQuerySearch;


    public CustomerFragment() {
    }

    public static CustomerFragment getInstance() {
        CustomerFragment customerFragment = new CustomerFragment();
        return customerFragment;
    }

    @Override
    public void onResponse(Response<List<Customer>> response) {
        progressBar.setVisibility(View.GONE);
        if (response.isStatus() && response.getData() != null) {
            if (isSearching) {
                isSearching = false;
                enableSwipeRefreshLayout(false);

                mAdapter.setList(response.getData());
                mAdapter.setPageLoading(false);
            } else {
                enableSwipeRefreshLayout(true);
                llNDF.setVisibility(View.GONE);

                mSearchList.addAll(response.getData());
                mAdapter.setList(response.getData());
                mAdapter.setPageLoading(true);

                DataWrapper.getInstance().addList(DataWrapper.CUSTOMER, (ArrayList<Customer>) response.getData());
            }
        } else {
            noDataFound(response);
        }

    }

    @Override
    public void noDataFound(Response<List<Customer>> response) {
        hideProgress();
        // Visible no data found
        llNDF.setVisibility(View.VISIBLE);
    }


    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        return null;
//                new DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL_LIST, 0);
    }

    @Override
    public SwipeRefreshLayout.OnRefreshListener getRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        };
    }

    @Override
    public void onRecyclerViewAttached() {
        mFAB = _findViewById(R.id.fabCreateNew);
        mViewModel = new CustomerViewModel();

        if (mAdapter != null) {
            if (mAdapter.getList() != null && mAdapter.getList().isEmpty()) {
                ArrayList<Customer> customers = DataWrapper.getInstance().getList(DataWrapper.CUSTOMER);
                if (customers.isEmpty()) {
                    showProgress();
                    mViewModel.fetchCustomer(this);
                } else
                    mAdapter.updateList(customers);
            }
        }

        changeDrawerIconState();
        mFAB.setOnClickListener(this);
        initNDF(R.drawable.notification_badge, R.string.customers, "No Customer Found");


//        mViewModel = new CustomerViewModel();
//        mFAB = _findViewById(R.id.fabCreateNew);
//        showProgress();
//        if (mAdapter != null) {
//            if (mAdapter.getList() != null && mAdapter.getList().isEmpty()) {
//                showProgress();
//                mViewModel.fetchCustomer(this);
//            }
//
//            changeDrawerIconState();
//            mFAB.setOnClickListener(this);
//            initNDF(R.drawable.notification_badge, R.string.customers, "No Customer Found");
//        }



   /* @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                isSearching = false;
                enableSwipeRefreshLayout(true);

//              mAdapter.updateList(mSearchList);
                llNDF.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                Log.d(TAG, "Search str =>" + query);
                if (TextUtils.isEmpty(query)) {
                    isSearching = false;
                    enableSwipeRefreshLayout(true);
                    mAdapter.updateList(mSearchList);
                    llNDF.setVisibility(View.GONE);
                } else {
                    if (query.length() > 3) {
                        isSearching = true;
                        enableSwipeRefreshLayout(false);
                        searchOnWeb(query);
//                    filter(query);
                    }
                }
                return true;
            }
        });
    }*/

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.menu_search);

        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                Log.d(TAG, "Search str => " + query);
                if (query.length() >= 3) {
                    mAdapter.getFilter().filter(query);
                } else if (TextUtils.isEmpty(query)) {
                    mAdapter.setList(mSearchList);
                }

                return true;
            }
        });
    }


    // Filter method
//    public void filter(String charText) {
//        try {
//            List<Customer> filtered = new ArrayList<>();
//            charText = charText.toLowerCase(Locale.getDefault());
//            if (charText.length() == 0) {
//                // noResultFound.setVisibility(View.GONE);
//                mAdapter.updateList(mSearchList);
////                addLoadMoreView();
//            } else {
//                AppLog.d(TAG, "cloneSize = >" + mSearchList.size());
//                for (Customer customer : mSearchList) {
//                    if (customer.getCompanyName().toLowerCase(Locale.getDefault()).contains(charText)) {
//                        filtered.add(customer);
//                    }
//                    else if(customer.getFirstName().toLowerCase(Locale.getDefault()).contains(charText))
//                    {
//                        filtered.add(customer);
//                    } else if(customer.getGstNo().toLowerCase(Locale.getDefault()).contains(charText)) {
//                        filtered.add(customer);
//                    }
//
//                }
//            }
//            if (filtered.size() == 0) {
//                searchStr = charText;
//                noResultFound.setVisibility(View.VISIBLE);
////                if (searchStr.length() > 3) {
////                    searchQuery(charText);
////                }
//            } else {
//                searchStr = null;
////                model.cancelSearchCallExecution();
//                 //noResultFound.setVisibility(View.GONE);
//                mAdapter.updateList(filtered);
//              // llNDF.setVisibility(View.GONE);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
 /*   private void addLoadMoreView() {
        if (getArguments() == null && !mAdapter.isPageLoading()) {
            Customer customer = new Customer();
            customer.setRoleIndex(-1);
            mAdapter.addAt(mAdapter.getCount(), customer);
            mAdapter.setPageLoading(true);
        }
    }*/

    private void searchOnWeb(String query) {
        // API call
        // visible progress bar
        // hide no data found
        llNDF.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        isSearching = true;
        mViewModel.searchCustomer(this, query);
    }

    @Override
    protected String getScreenTitle() {
        return ResourceUtils.getString(R.string.customers);
    }

    @Override
    protected void changeDrawerIconState() {
      /*  MainActivityTwo mainActivity = (MainActivityTwo) activity;
        mainActivity.changeDrawerIndicator(false, true);*/
        MainActivity mainActivity = (MainActivity) activity;
        mainActivity.changeDrawerIndicator(false, true);
    }

    @Override
    public int getCurrentMenuId() {
        return R.id.menu_customers;
    }

    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> getRecyclerAdapter() {
        if (mAdapter == null)
            mAdapter = new CustomerAdapter(activity, new ArrayList<Customer>());
        return mAdapter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabCreateNew:
               /* Intent intent = new Intent(activity, CreateNewCustomerActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 101);*/
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 101) {
                Customer customer = (Customer) data.getSerializableExtra("customer");
                mAdapter.addAt(0, customer);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DataWrapper.getInstance().removeList(DataWrapper.CUSTOMER);
    }
}
