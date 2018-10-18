package com.xtensolution.kptt.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.riontech.retrofit.callback.OnCallExecuted;
import com.riontech.retrofit.errorhandler.APIError;
import com.xtensolution.core.ui.activity.CoreDrawerActivity;
import com.xtensolution.core.ui.fragment.CoreBaseFragment;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.core.utils.ErrorUtils;
import com.xtensolution.core.utils.ResourceUtils;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.ui.activity.MainActivity;
import com.xtensolution.kptt.ui.activity.MainActivityTwo;

import java.util.List;

/**
 * Created by RIONTECH
 * Riontech on 13/11/17.
 */

public abstract  class BaseFragment<T> extends CoreBaseFragment implements OnCallExecuted<Response<List<T>>> {
    private static final String TAG = RecyclerBaseFragment.class.getSimpleName();
    protected LinearLayout llNDF;
    protected ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void changeDrawerIconState() {
//        MainActivity mainActivity = (MainActivity) activity;
//        mainActivity.changeDrawerIndicator(true,false);
        if(activity instanceof CoreDrawerActivity){
            CoreDrawerActivity mainActivity = (CoreDrawerActivity) activity;
            mainActivity.changeDrawerIndicator(true,false);
        }


    }

    @Override
    public int getCurrentMenuId() {
        return 0;
    }

    @Override
    public void onTimeout() {

    }

    @Override
    public void onError(APIError error) {

    }

    @Override
    public void onConnectionError() {

    }

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

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }


}
