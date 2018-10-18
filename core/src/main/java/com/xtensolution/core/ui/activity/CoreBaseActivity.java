package com.xtensolution.core.ui.activity;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.xtensolution.core.R;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.core.utils.ErrorUtils;

public abstract class CoreBaseActivity extends AppCompatActivity {
    protected ActionBar mActionBar;
    protected ProgressBar progressBar;
    protected FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(getLayout());


        if (getProgressBarId() != 0)
            progressBar = _findViewById(getProgressBarId());

        if (getFabId() != 0)
            fab = _findViewById(getFabId());

        onContentViewBind();

//        if (getSupportActionBar() != null)
//            mActionBar = getSupportActionBar();
//
//        // Hack. Forcing overflow button on actionbar on devices with hardware menu button
//        try {
//            ViewConfiguration config = ViewConfiguration.get(this);
//            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
//            if (menuKeyField != null) {
//                menuKeyField.setAccessible(true);
//                menuKeyField.setBoolean(config, false);
//            }
//        } catch (Exception ex) {
//            // Ignore
//        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int getFabId() {
        return 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setActionBarTitle(getScreenTitle());
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T _findViewById(int viewId) {
        return (T) findViewById(viewId);
    }

    public void setActionBarTitle(int title) {
        if (mActionBar != null) {
            mActionBar.setTitle(title);
        }
    }

    public void setActionBarTitle(CharSequence title) {
        if (mActionBar != null) {
            mActionBar.setTitle(title);
        }
    }

    protected void showSnackbarError(@StringRes int resId, Exception e, View.OnClickListener clickListener) {
        ErrorUtils.showSnackbar(getRootView(), resId, e, R.string.dlg_retry, clickListener);
    }

    protected void showSnackbar(@StringRes int resId, View.OnClickListener listener) {
        ErrorUtils.showSnackbar(getRootView(), resId, R.string.dlg_ok, listener);
    }

    protected void showSnackbar(String resId, View.OnClickListener listener) {
        ErrorUtils.showSnackbar(getRootView(), resId, R.string.dlg_ok, listener);
    }

    protected abstract String getScreenTitle();

    protected abstract int getLayout();

    protected abstract View getRootView();

    protected abstract void onContentViewBind();

    protected void setHomeButtonEnable(boolean enable) {
        if (mActionBar != null) {
            mActionBar.setHomeButtonEnabled(enable);
            mActionBar.setDisplayHomeAsUpEnabled(enable);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    public int getProgressBarId() {
        return 0;
    }


}
