package com.xtensolution.kptt.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.riontech.retrofit.callback.OnCallExecuted;
import com.riontech.retrofit.errorhandler.APIError;
import com.xtensolution.core.ui.activity.CoreRecyclerActivity;
import com.xtensolution.core.utils.ResourceUtils;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.utils.AppLog;

import retrofit2.Response;

/**
 * Created by riontech2 on 23/4/18.
 */

public abstract class RecyclerBaseActivity<T> extends CoreRecyclerActivity implements OnCallExecuted<Response<T>> {

    private static final String TAG = RecyclerBaseActivity.class.getSimpleName();

    protected LinearLayout llNDF;

    protected View noResultFound;

    protected TextView txtMsg;

    protected void initNDF(int resId, int resTitle, String resMsg) {

        try {

            llNDF = _findViewById(R.id.llNDF);

            ImageView icon = _findViewById(R.id.imgNDF);

            TextView txtTitle = _findViewById(R.id.txtNDFTitle);

            txtMsg = _findViewById(R.id.txtNDFMsg);

            //   noResultFound = _findViewById(R.id.viewNoResultFound);

//            TextView noResultFound = _findViewById(R.id.txtNoDataFound);

            icon.setImageDrawable(ResourceUtils.getDrawable(resId));

            txtTitle.setText(ResourceUtils.getString(resTitle));

            txtMsg.setText(resMsg);

        } catch (Exception e) {

            AppLog.e(TAG, e.getMessage(), e);

        }

    }

    protected void initNDF(String resMsg) {

        try {

            llNDF = _findViewById(R.id.llNDF);

            ImageView icon = _findViewById(R.id.imgNDF);

            icon.setVisibility(View.GONE);

            TextView txtTitle = _findViewById(R.id.txtNDFTitle);

            txtTitle.setVisibility(View.GONE);

            txtMsg = _findViewById(R.id.txtNDFMsg);

            //   noResultFound = _findViewById(R.id.viewNoResultFound);

//            TextView noResultFound = _findViewById(R.id.txtNoDataFound);

            //icon.setImageDrawable(ResourceUtils.getDrawable(resId));

            // txtTitle.setText(ResourceUtils.getString(resTitle));

            txtMsg.setText(resMsg);

        } catch (Exception e) {

            AppLog.e(TAG, e.getMessage(), e);

        }

    }

    @Override

    public void onTimeout() {

        try {

            progressBar.setVisibility(View.GONE);

            String msg = ResourceUtils.getString(R.string.no_connection_found);

            initNDF(msg);

            llNDF.setVisibility(View.VISIBLE);

            AppLog.d(TAG, "onTimeout: ");

        } catch (Exception e) {

            AppLog.e(TAG, e.getMessage(), e);

        }

    }

    @Override

    public void onError(APIError error) {

        try {

            AppLog.e(TAG, error.message());

            progressBar.setVisibility(View.GONE);

            String msg = ResourceUtils.getString(R.string.something_wrong);

            initNDF(msg);

            llNDF.setVisibility(View.VISIBLE);

            AppLog.d(TAG, "onError: ");

        } catch (Exception e) {

            AppLog.e(TAG, e.getMessage(), e);

        }

    }

    @Override

    public void onConnectionError() {

        try {

            progressBar.setVisibility(View.GONE);

            String msg = ResourceUtils.getString(R.string.no_connection_found);

            initNDF(msg);

            llNDF.setVisibility(View.VISIBLE);

            AppLog.d(TAG, "onConnectionError: ");

        } catch (Exception e) {

            AppLog.e(TAG, e.getMessage(), e);

        }

    }

    @Override

    public int getRecyclerViewId() {

        return R.id.recyclerView;

    }

    @Override

    public int getRefreshLayoutId() {

        return R.id.refreshLayout;

    }

    @Override

    protected int getToolbarId() {

        return R.id.toolbar;

    }

    @Override

    protected int getLayout() {

        return R.layout.activity_recyclerview_layout;

    }

    @Override

    protected View getRootView() {

        return _findViewById(R.id.rootView);

    }

    @Override

    public int getProgressBarId() {

        return R.id.progressBar;

    }

}