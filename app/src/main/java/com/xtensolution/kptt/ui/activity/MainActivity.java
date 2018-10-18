package com.xtensolution.kptt.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.view.View;

import com.xtensolution.core.ui.activity.CoreDrawerActivity;
import com.xtensolution.core.ui.fragment.CoreBaseFragment;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.ui.fragment.CustomerFragment;
import com.xtensolution.kptt.ui.fragment.DashboardFragment;
import com.xtensolution.kptt.utils.AppUtils;
import com.xtensolution.kptt.utils.FragmentUtils;

/**
 * Created by
 * Riontech on 2/11/17.
 */

public class MainActivity extends CoreDrawerActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected int getDrawerLayoutId() {
        return R.id.drawer_layout;
    }

    @Override
    protected int getNavigationViewId() {
        return R.id.nav_view;
    }

    @Override
    protected void changeSelectedMenu() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_main);
        try {
            if (currentFragment != null && currentFragment instanceof CoreBaseFragment) {
                CoreBaseFragment baseFragment = (CoreBaseFragment) currentFragment;
                mNavigationView.setCheckedItem(baseFragment.getCurrentMenuId());
            } else {
                mNavigationView.setCheckedItem(R.id.menu_dashboard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDrawerAttached() {


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        try {
            if (id == R.id.menu_dashboard) {
//                replaceFragment(FragmentUtils.getMainFragments(0));
                replaceFragment(DashboardFragment.getInstance("Dashboard"));
            } else if (id == R.id.menu_design) {
//                replaceFragment(DesignFragment.getInstance("Design"));
                //            replaceFragment(FragmentUtils.getMainFragments(1));
//                startActivity(new Intent(TMainActivity.this, HistoryActivity.class));
            } else if (id == R.id.menu_orderform) {
//                replaceFragment(OrderFragment.getInstance());
//                replaceFragment(FragmentUtils.getMainFragments(1));
            } else if (id == R.id.menu_sales) {
//                replaceFragment(SalesFragment.getInstance());
            } else if (id == R.id.menu_jobwork) {
                replaceFragment(FragmentUtils.getMainFragments(6));
            } else if (id == R.id.menu_rawmaterial) {
//                replaceFragment(EventFragment.getInstance(EVENT.CME));
            } else if (id == R.id.menu_ledgers) {
//                replaceFragment(EventFragment.getInstance(EVENT.ACTIVITY));
            } else if (id == R.id.menu_expenses) {
//                replaceFragment(FragmentUtils.getMainFragments(5));
            } else if (id == R.id.menu_customers) {
                replaceFragment(CustomerFragment.getInstance());
//                startActivity(new Intent(TMainActivity.this, FeedbackActivity.class));
            } else if (id == R.id.menu_agents) {
//                replaceFragment(AgentsFragment.getInstance());
            } else if (id == R.id.menu_vendors) {
//                replaceFragment(VendorFragment.getInstance());
//                startActivity(new Intent(TMainActivity.this, DeveloperProfileActivity.class));
            } else if (id == R.id.menu_labours) {
//                replaceFragment(LabourFragment.getInstance());
            } else if (id == R.id.menu_users) {
//                replaceFragment(LabourFragment.getInstance());
            } else if (id == R.id.menu_transport) {
//                replaceFragment(TransportFragment.getInstance());
//                startActivity(new Intent(TMainActivity.this, DeveloperProfileActivity.class));

            } else if (id == R.id.menu_reports) {
//                replaceFragment(TransportFragment.getInstance());
//                startActivity(new Intent(TMainActivity.this, DeveloperProfileActivity.class));

            } else if (id == R.id.menu_logout) {
                AppUtils.onLogoutEvent(MainActivity.this);
//                startActivity(new Intent(TMainActivity.this, DeveloperProfileActivity.class));
            }

        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }

        closeDrawer(GravityCompat.START);
        return true;
    }


    public void replaceFragment(Fragment fragment) {
        try {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_main);

            if (currentFragment != null && currentFragment.getTag() != null) {
                String tag = fragment.getClass().getSimpleName();
                if (currentFragment.getTag().equalsIgnoreCase(tag)) {
                    changeSelectedMenu();
                    return;
                }
            }


            if (fragment instanceof DashboardFragment)
                onBackPressed();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment, fragment.getClass().getSimpleName());
            ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
            ft.addToBackStack(fragment.getClass().getSimpleName());
            ft.commit();
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected String getScreenTitle() {
        return "";
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected View getRootView() {
        return _findViewById(R.id.content_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
