package com.xtensolution.kptt.ui.activity;

import android.view.View;

import com.riontech.retrofit.callback.OnCallExecuted;
import com.riontech.retrofit.errorhandler.APIError;
import com.xtensolution.core.ui.activity.CoreActionBarActivity;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.core.utils.ErrorUtils;
import com.xtensolution.core.utils.ResourceUtils;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.Response;

import java.util.List;

/**
 * Created by riontech on 6/11/17.
 */

public abstract class BaseActionBarActivity<T> extends CoreActionBarActivity implements OnCallExecuted<Response<T>> {
    private static final String TAG = BaseActionBarActivity.class.getSimpleName();

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected View getRootView() {
        return _findViewById(R.id.rootView);
    }

    @Override
    public void onTimeout() {
        try {
            progressBar.setVisibility(View.GONE);
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onError(APIError error) {
        try {
            AppLog.e(TAG, error.message());
            progressBar.setVisibility(View.GONE);
            AppLog.d(TAG, "onError: ");
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onConnectionError() {
        try {
            progressBar.setVisibility(View.GONE);
            AppLog.d(TAG, "onConnectionError: ");
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public int getProgressBarId() {
        return R.id.progressBar;
    }

    protected void resetView(){

    }

}
