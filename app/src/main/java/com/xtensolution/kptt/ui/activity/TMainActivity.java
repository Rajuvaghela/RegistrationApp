package com.xtensolution.kptt.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtensolution.core.ui.activity.CoreDrawerActivity;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.core.utils.CompatibilityUtil;
import com.xtensolution.core.utils.PermissionUtils;
import com.xtensolution.core.utils.SharedPrefsHelper;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.utils.Constants;


public class TMainActivity extends CoreDrawerActivity
        implements View.OnClickListener {

    private static final String TAG = TMainActivity.class.getSimpleName();

    private RelativeLayout rlViewOverlay;
    private TextView txtNotCount;
    private int unreadCount = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleIntent(getIntent());

//        spLayout = findView(R.id.slidingPLNotification);
//        spLayout.closePane();
        if (CompatibilityUtil.isLollipop())
            PermissionUtils.askForPermission(this, PermissionUtils.READ_PHONE_STATE);
        else
            registerDevice();

//        getUnreadCount();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    /**
     * To handle unknown intent from notification
     * or other application
     * @param intent
     */
    private void handleIntent(Intent intent) {
//        if (intent.hasExtra(Constants.NOTIFICATION_BODY)) {
//            try {
//                String json = intent.getStringExtra(Constants.NOTIFICATION_BODY);
//                EventNotification notification = new Gson().fromJson(json, EventNotification.class);
//                openNotificationView(notification);
//            } catch (JsonSyntaxException e) {
//                AppLog.e(TAG, e.getMessage(), e);
//            } catch (NumberFormatException e) {
//                AppLog.e(TAG, e.getMessage(), e);
//            } catch (Exception e) {
//                AppLog.e(TAG, e.getMessage(), e);
//            }
//        }
    }

    /**
     * To increase the count of notification when
     * received any new notification from server
     * or any user device
     * @param intent
     */
    private void increaseNotCount(Intent intent) {
//        if (intent.hasExtra(Constants.NOTIFICATION_BODY)) {
//            try {
//                int count = Integer.parseInt(txtNotCount.getText().toString());
//                count = count + 1;
//                txtNotCount.setVisibility(View.VISIBLE);
//                txtNotCount.setText("" + count);
//                unreadCount = count;
//
//            } catch (Exception e) {
//                AppLog.e(TAG, e.getMessage(), e);
//            }
//        }
    }

//    public void openNotificationView(EventNotification notification) {
//        try {
//            NavigationView navigationView = findView(R.id.nav_view);
//            Menu menu = navigationView.getMenu();
//            if (notification.getHeading().equalsIgnoreCase("CME")) {
//                navigationView.setCheckedItem(R.id.nav_cme);
//                MenuItem item = menu.findItem(R.id.nav_cme);
//                TMainActivity.this.onNavigationItemSelected(item);
//            } else if (notification.getHeading().equalsIgnoreCase("Activity")) {
//                navigationView.setCheckedItem(R.id.nav_activity);
//                MenuItem item = menu.findItem(R.id.nav_activity);
//                TMainActivity.this.onNavigationItemSelected(item);
//            } else {
//                Toast.makeText(this, "Undefined", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            AppLog.e(TAG, e.getMessage(), e);
//        }
//    }

//    private void initOverlay() {
//        try {
//            rlViewOverlay = findView(R.id.rlViewOverlay);
//            rlViewOverlay.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View view, MotionEvent motionEvent) {
//                    return true;
//                }
//            });
//            rlViewOverlay.setTag(true);
//
//            FloatingActionMenu fabMenu = (FloatingActionMenu) findViewById(R.id.fabSocialMenu);
//            fabMenu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
//                @Override
//                public void onMenuToggle(boolean opened) {
//                    AppLog.d(TAG, "opened = >" + opened);
//                    AnimUtils.viewCircleRevealEffect(rlViewOverlay);
//                }
//            });
//
//        } catch (Exception e) {
//            AppLog.e(TAG, e.getMessage(), e);
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        changeSelectedMenu();

        registerReceiver(messageReceiver, new IntentFilter(Constants.MESSAGE_RECEIVER));

        if (txtNotCount != null) {
            if (unreadCount > 0) {
                txtNotCount.setVisibility(View.VISIBLE);
                txtNotCount.setText("" + unreadCount);
            } else {
                txtNotCount.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected View getRootView() {
        return null;
    }


    @Override
    protected int getDrawerLayoutId() {
        return 0;
    }

    @Override
    protected int getNavigationViewId() {
        return 0;
    }

    /**
     * Handle navigation menu item selection
     */
    protected void changeSelectedMenu() {
//        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_main);
//        try {
//            if (currentFragment != null && currentFragment instanceof RecyclerBaseFragment) {
//                RecyclerBaseFragment baseFragment = (RecyclerBaseFragment) currentFragment;
//                mNavigationView.setCheckedItem(baseFragment.getCurrentMenuId());
//                //            Menu menu = navigationView.getMenu();
//            } else {
//                mNavigationView.setCheckedItem(R.id.nav_home);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onDrawerAttached() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        try {
//            getMenuInflater().inflate(R.menu.main, menu);
//
//            MenuItem item = menu.findItem(R.id.action_notification);
//            View badgeLayout = item.getActionView();
//            txtNotCount = (TextView) badgeLayout.findViewById(R.id.txtBadge);
//            txtNotCount.setVisibility(View.GONE);
//            badgeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(TMainActivity.this, NotificationActivity.class));
//                    AppLog.d(TAG, "badgeLayout = > onClick");
//                    txtNotCount.setText("0");
//                    txtNotCount.setVisibility(View.GONE);
//                    unreadCount = 0;
//                }
//            });
//        } catch (Exception e) {
//            AppLog.e(TAG, e.getMessage(), e);
//        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//        try {
//            if (id == R.id.nav_home) {
//                replaceFragment(FragmentUtils.getMainFragments(0));
//            } else if (id == R.id.nav_history) {
//                //            replaceFragment(FragmentUtils.getMainFragments(1));
////                startActivity(new Intent(TMainActivity.this, HistoryActivity.class));
//            } else if (id == R.id.nav_committee) {
//                replaceFragment(FragmentUtils.getMainFragments(1));
//            } else if (id == R.id.nav_member) {
//                replaceFragment(FragmentUtils.getMainFragments(2));
//            } else if (id == R.id.nav_download) {
//                replaceFragment(FragmentUtils.getMainFragments(6));
//            } else if (id == R.id.nav_cme) {
////                replaceFragment(EventFragment.getInstance(EVENT.CME));
//            } else if (id == R.id.nav_activity) {
////                replaceFragment(EventFragment.getInstance(EVENT.ACTIVITY));
//            } else if (id == R.id.nav_gallery) {
//                replaceFragment(FragmentUtils.getMainFragments(5));
//            } else if (id == R.id.nav_feedback) {
////                startActivity(new Intent(TMainActivity.this, FeedbackActivity.class));
//            } else if (id == R.id.nav_contact_us) {
////                startActivity(new Intent(TMainActivity.this, ContactUsActivity.class));
//            } else if (id == R.id.nav_developer) {
////                startActivity(new Intent(TMainActivity.this, DeveloperProfileActivity.class));
//            }
//        } catch (Exception e) {
//            AppLog.e(TAG, e.getMessage(), e);
//        }
//
//        closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        try {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_main);

//            if (fragment instanceof EventFragment && currentFragment instanceof EventFragment) {
//                Constants.EVENT event1 = ((EventFragment) fragment).getEventType();
//                Constants.EVENT eventCurrent = ((EventFragment) currentFragment).getEventType();
//                if (event1 == eventCurrent) {
//                    changeSelectedMenu();
//                    return;
//                }
//            } else if (currentFragment != null && currentFragment.getTag() != null) {
//                String tag = fragment.getClass().getSimpleName();
//                if (currentFragment.getTag().equalsIgnoreCase(tag)) {
//                    changeSelectedMenu();
//                    return;
//                }
//            }


//            if (fragment instanceof DashboardFragment)
//                onBackPressed();

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
    public void onClick(View v) {

//        switch (v.getSareeId()) {
//            case R.id.fabFacebook:
//                SocialPageUtils.startSocialPageIntent(SocialPage.FACEBOOK, TMainActivity.this);
//                break;
//
//            case R.id.fabLinkedin:
//                SocialPageUtils.startSocialPageIntent(SocialPage.GOOGLE_PLUS, TMainActivity.this);
//                break;
//
//            case R.id.fabTwitter:
//                SocialPageUtils.startSocialPageIntent(SocialPage.TWITTER, TMainActivity.this);
//                break;
//
//            case R.id.fabYoutube:
//                SocialPageUtils.startSocialPageIntent(SocialPage.YOUTUBE, TMainActivity.this);
//                break;
//
//            case R.id.fabSocialMenu:
//                AppLog.e(TAG, "fabSocialMenu clicked");
//                AnimUtils.viewCircleRevealEffect(rlViewOverlay);
//                break;
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try {
            if (grantResults != null && permissions != null) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        if (requestCode == PermissionUtils.READ_PHONE_STATE) {
                            registerDevice();
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerDevice() {
        try {
            String token = SharedPrefsHelper.getInstance().get(Constants.TOKEN);
            if (token != null && !TextUtils.isEmpty(token)) {
                return;
            } else {
//                token = FirebaseInstanceId.getInstance().getToken();
//                new UserDevice().register(token, this);
//                AppLog.d(TAG, "TOKEN " + token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            increaseNotCount(intent);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageReceiver);
    }

    @Override
    protected int getToolbarId() {
        return 0;
    }

//    private void getUnreadCount() {
//        NotificationViewModel model = new NotificationViewModel();
//        model.getUnreadCount(new OnCallExecuted<Response<Integer>>() {
//            @Override
//            public void onTimeout() {
//
//            }
//
//            @Override
//            public void onResponse(Response<Integer> response) {
//                if (response.isStatus() == 200 || response.isStatus() == 201) {
//                    unreadCount = response.getData();
//                    if (unreadCount > 0) {
//                        txtNotCount.setVisibility(View.VISIBLE);
//                        txtNotCount.setText("" + unreadCount);
//                    }
//                }
//            }
//
//            @Override
//            public void onError(APIError error) {
//
//            }
//
//            @Override
//            public void noDataFound(Response<Integer> response) {
//
//            }
//
//            @Override
//            public void onConnectionError() {
//
//            }
//        });
//    }
}
