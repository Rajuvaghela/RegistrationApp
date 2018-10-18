package com.xtensolution.kptt.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.xtensolution.core.ui.fragment.CoreBaseFragment;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.core.utils.ResourceUtils;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.builders.OrderCalculator;
import com.xtensolution.kptt.ui.activity.MainActivity;
import com.xtensolution.kptt.ui.activity.MainActivityTwo;

/**
 * Created by suresh dobariya
 *  2/11/17.
 */

public class DashboardFragment extends CoreBaseFragment {
    private static final String TAG = DashboardFragment.class.getSimpleName();
    private String title;
    private static final String ARG_TITLE = "title";

    public DashboardFragment() {
    }

    public static DashboardFragment getInstance(String title) {
        DashboardFragment dashboardFragment = new DashboardFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        dashboardFragment.setArguments(bundle);
        return dashboardFragment;
    }

    @Override
    protected String getScreenTitle() {
        return ResourceUtils.getString(R.string.dashboard);
    }

    @Override
    protected int getViewResourceId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void onViewBind() {
        title = ResourceUtils.getString(R.string.dashboard);
        if (getArguments() != null && getArguments().containsKey(ARG_TITLE)) {
            title = getArguments().getString(ARG_TITLE);
        }

        TextView txtTitle = _findViewById(R.id.txtViewName);
        txtTitle.setText(title);
        OrderCalculator calculator = null;
        OrderCalculator.Builder builder = new OrderCalculator.Builder();
        for (int i = 0; i < 3; i++) {
            calculator = builder.quantity(5).rate(100).build();
        }

        AppLog.d(TAG, "Gross total =>" + calculator.getGrossTotal());
        AppLog.d(TAG, "Discount =>" + calculator.getDiscount(10));
        AppLog.d(TAG, "Subtotal =>" + calculator.getSubTotal());
        AppLog.d(TAG, "GST =>" + calculator.getGst(5));
        AppLog.d(TAG, "Net Total =>" + calculator.getNetTotal());
    }

    @Override
    public void onResume() {
        super.onResume();
        changeDrawerIconState();
    }

    @Override
    protected void changeDrawerIconState() {
        MainActivity mainActivity = (MainActivity) activity;
        mainActivity.changeDrawerIndicator(false, true);
      /*  MainActivityTwo mainActivity = (MainActivityTwo) activity;
        mainActivity.changeDrawerIndicator(false, true);*/
    }

    @Override
    public int getCurrentMenuId() {
        return R.id.menu_dashboard;
    }
}
