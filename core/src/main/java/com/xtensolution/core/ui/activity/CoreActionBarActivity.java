package com.xtensolution.core.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.xtensolution.core.utils.AppLog;

/**
 * Created by MIT on 01-Nov-17.
 */

public abstract class CoreActionBarActivity extends CoreBaseActivity {
    private static final String TAG = CoreActionBarActivity.class.getSimpleName();
    protected Toolbar mToolbar;

    @Override
    protected void onContentViewBind() {
        mToolbar = _findViewById(getToolbarId());
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        onActionBarAttached();
    }

    protected abstract int getToolbarId();

    protected abstract void onActionBarAttached();

    public void replaceFragment(Fragment fragment) {
        try {
            if(getContentFramLayoutId() == 0){
                AppLog.e(TAG, "replaceFragment->with 0 id ContentFramLayoutId, can't replaced fragment");
                return;
            }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(getContentFramLayoutId(), fragment, fragment.getClass().getSimpleName());
            ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
            ft.addToBackStack(fragment.getClass().getSimpleName());
            ft.commit();
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }

    protected int getContentFramLayoutId(){
        return 0;
    }
}
