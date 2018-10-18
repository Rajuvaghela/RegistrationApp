package com.xtensolution.core.ui.activity;

import android.animation.ObjectAnimator;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import com.xtensolution.core.R;
import com.xtensolution.core.utils.AppLog;

/**
 * Created by MIT on 01-Nov-17.
 */

public abstract class CoreDrawerActivity extends CoreActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = CoreDrawerActivity.class.getSimpleName();

    protected ActionBarDrawerToggle mToggle;
    private boolean hasArrow;
    protected NavigationView mNavigationView;
    protected DrawerLayout mDrawerLayout;

    @Override
    protected void onActionBarAttached() {
        initDrawer(getDrawerLayoutId());
        initNavigation(getNavigationViewId());
        onDrawerAttached();
    }

    private void initDrawer(int drawerId) {
        mDrawerLayout = _findViewById(drawerId);
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
    }

    private void initNavigation(int navigationViewId) {
        mNavigationView = _findViewById(navigationViewId);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    public void changeDrawerIndicator(boolean arrow, boolean hamburger) {
        try {
            if (hasArrow != arrow) {
                int start = hamburger ? 0 : 1;
                hasArrow = arrow;
                int drawerLockMode = hamburger ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
                mDrawerLayout.setDrawerLockMode(drawerLockMode);
                mToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
                ObjectAnimator.ofFloat(mToggle.getDrawerArrowDrawable(), "progress", start).start();
                mToggle.setDrawerIndicatorEnabled(hamburger);
                mActionBar.setDisplayHomeAsUpEnabled(arrow);
                mActionBar.setHomeButtonEnabled(arrow);
//                toggle.setDrawerSlideAnimationEnabled(true);
                mToggle.syncState();
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }

    protected void closeDrawer(int gravity) {
        mDrawerLayout.closeDrawer(gravity);
    }


    public void onBackPressed() {
        try {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
                changeSelectedMenu();
            }
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        if(getContentFramLayoutId() == 0){
            AppLog.e(TAG, "replaceFragment->with 0 id ContentFramLayoutId, can't replaced fragment");
            return;
        }

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(getContentFramLayoutId());

        if (currentFragment != null && currentFragment.getTag() != null) {
            String tag = fragment.getClass().getSimpleName();
            if (currentFragment.getTag().equalsIgnoreCase(tag)) {
                changeSelectedMenu();
                return;
            }
        }
        super.replaceFragment(fragment);
    }

    protected abstract int getDrawerLayoutId();

    protected abstract int getNavigationViewId();

    protected abstract void changeSelectedMenu();

    protected abstract void onDrawerAttached();
}
