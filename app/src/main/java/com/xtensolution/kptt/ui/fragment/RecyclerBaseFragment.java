package com.xtensolution.kptt.ui.fragment;

import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.riontech.retrofit.callback.OnCallExecuted;
import com.riontech.retrofit.errorhandler.APIError;
import com.xtensolution.core.ui.fragment.CoreRecyclerViewBaseFragment;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.core.utils.ErrorUtils;
import com.xtensolution.core.utils.ResourceUtils;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.Response;

import java.util.List;


/**
 * Created by MIT on 01-Nov-17.
 */

public abstract class RecyclerBaseFragment<T> extends CoreRecyclerViewBaseFragment implements OnCallExecuted<Response<List<T>>> {

    private static final String TAG = RecyclerBaseFragment.class.getSimpleName();
    protected LinearLayout llNDF;
    protected View noResultFound;

    protected void initNDF(int resId, int resTitle, String resMsg) {
        try {
            llNDF = _findViewById(R.id.llNDF);
            ImageView icon = _findViewById(R.id.imgNDF);
            TextView txtTitle = _findViewById(R.id.txtNDFTitle);
            TextView txtMsg = _findViewById(R.id.txtNDFMsg);
         //   noResultFound = _findViewById(R.id.viewNoResultFound);
//            TextView noResultFound = _findViewById(R.id.txtNoDataFound);
            icon.setImageDrawable(ResourceUtils.getDrawable(resId));
            txtTitle.setText(ResourceUtils.getString(resTitle));
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
            initNDF(R.drawable.nointernet, R.string.ooops, msg);
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
            initNDF(R.drawable.oops, R.string.ooops, msg);
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
            initNDF(R.drawable.nointernet, R.string.ooops, msg);
            llNDF.setVisibility(View.VISIBLE);
            AppLog.d(TAG, "onConnectionError: ");
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public int getProgressBarId() {
        return R.id.progressBar;
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.recyclerView;
    }

    @Override
    protected int getViewResourceId() {
        return R.layout.recyclerview_layout;
    }

    @Override
    public int getRefreshLayoutId() {
        return R.id.refreshLayout;
    }


}
