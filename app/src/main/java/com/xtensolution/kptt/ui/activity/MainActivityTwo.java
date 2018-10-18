package com.xtensolution.kptt.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtensolution.core.ui.activity.CoreDrawerActivity;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.ui.adapter.ExpandListAdapter;
import com.xtensolution.kptt.ui.fragment.CustomerFragment;
import com.xtensolution.kptt.ui.fragment.DashboardFragment;
import com.xtensolution.kptt.utils.AppUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by riontech on 7/12/17.
 */

public class MainActivityTwo extends CoreDrawerActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView txtEmail, txtUserName, txtLastName;
    private List<String> listDataHeader;
    private List<Integer> listImgHeader;
    //    private List<Integer> listDataImage;
    private HashMap<String, List<String>> listDataChild;

    private ExpandableListView expListView;
    private ExpandListAdapter listAdapter;

    private void enableExpandableList() {
        listDataHeader = new ArrayList<>();
        listImgHeader =new ArrayList<>();
        listDataChild = new HashMap<>();
//        expListView = (ExpandableListView) findViewById(R.id.left_drawer);

        prepareListData(listDataHeader, listImgHeader,listDataChild);
        listAdapter = new ExpandListAdapter(this, listDataHeader,listImgHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                switch (groupPosition) {
                    case 0:
                        closeDrawer(GravityCompat.START);
                        replaceFragment(DashboardFragment.getInstance("Dashboard"));
                        break;
                    case 1:

//                        replaceFragment(DashboardFragment.getInstance("Dashboard"));
                        break;
                    case 2:
                        closeDrawer(GravityCompat.START);
//                        replaceFragment(DesignFragment.getInstance("Design"));
                        break;
                    case 3:
                        closeDrawer(GravityCompat.START);
                        replaceFragment(DashboardFragment.getInstance("Dashboard"));
                        break;
                    case 4:
                        closeDrawer(GravityCompat.START);
//                        replaceFragment(OrderFragment.getInstance());
                        break;
                    case 5:
                       /* closeDrawer(GravityCompat.START);
                        replaceFragment(SalesFragment.getInstance());*/
                        break;
                    case 6:

                        break;
                    case 7:
                        replaceFragment(DashboardFragment.getInstance("Dashboard"));
                       /* closeDrawer(GravityCompat.START);
                        replaceFragment(DashboardFragment.getInstance("Dashboard"));*/
                        break;
                    case 8:
                        closeDrawer(GravityCompat.START);
//                        replaceFragment(LedgersFragment.getInstance());
                        break;
                    case 9:
                        closeDrawer(GravityCompat.START);
//                        replaceFragment(ExpensesFragment.getInstance(""));
                        break;
                    case 10:
                        closeDrawer(GravityCompat.START);
//                        replaceFragment(ReportFragments.getInstance(""));
                        break;
                    case 11:
                        logoutDialog(MainActivityTwo.this);
//                        AppUtils.onLogoutEvent(MainActivityTwo.this);
                        break;
                }
                return false;
            }
        });
//        // Listview Group expanded listener
//        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        // Listview Group collasped listener
//        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                // Temporary code:
                 if(groupPosition==1) {
                     switch (childPosition) {
                         case 0:
                             closeDrawer(GravityCompat.START);

                             replaceFragment(CustomerFragment.getInstance());
                             break;
                         case 1:
                             closeDrawer(GravityCompat.START);
//                             replaceFragment(AgentsFragment.getInstance());
                             break;
                         case 2:
                             closeDrawer(GravityCompat.START);
//                             replaceFragment(VendorFragment.getInstance());
                             break;
                         case 3:
                             closeDrawer(GravityCompat.START);
//                             replaceFragment(LabourFragment.getInstance());
                             break;
                         case 4:
                             closeDrawer(GravityCompat.START);
//                             replaceFragment(TransportFragment.getInstance());
                             break;

                         case 5:
                             closeDrawer(GravityCompat.START);
//                             replaceFragment(SalesFragment.getInstance());
                             break;
                     }
                 }

                if(groupPosition==5) {
                    switch (childPosition) {
                        case 0:
                            closeDrawer(GravityCompat.START);
//                            replaceFragment(SalesFragment.getInstance());
                            break;
                        case 1:
                            closeDrawer(GravityCompat.START);
//                            replaceFragment(SalesReturnFragment.getInstance());
                            break;

                    }
                }

                if(groupPosition==6) {
                    switch (childPosition) {
                        case 0:
                            closeDrawer(GravityCompat.START);
//                            replaceFragment(PurchaseFragments.getInstance());
                            break;
                        case 1:
                            closeDrawer(GravityCompat.START);
//                            replaceFragment(PurchaseReturnFragment.getInstance());
                            break;

                    }
                }


//                // till here
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
                return false;
            }
        });
    }


    private void logoutDialog(final Context ctx) {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivityTwo.this);
        // Setting Dialog Title
        alertDialog.setTitle("Logout");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure, you want to Logout ?");

//                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.ic_logouts);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AppUtils.onLogoutEvent(ctx);
                // Write your code here to invoke YES event
                Toast.makeText(getApplicationContext(), "Logout Successfully....", Toast.LENGTH_SHORT).show();

            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                Toast.makeText(getApplicationContext(), "Are you Continues...", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }



    private void prepareListData(List<String> listDataHeader,  List<Integer> listImgHeader, Map<String,
            List<String>> listDataChild) {
//        List<Integer> listImgHeader;

        // Adding child data
        listDataHeader.add("Dashboard");
        listDataHeader.add("Master");
        listDataHeader.add("Design");
        listDataHeader.add("Users");
        listDataHeader.add("Orderform");
        listDataHeader.add("Sales");
        listDataHeader.add("Raw Materials");
        listDataHeader.add("Job Work");
        listDataHeader.add("Ledgers");
        listDataHeader.add("Expenses");
        listDataHeader.add("Reports/Analytics");
        listDataHeader.add("Logout");




        // Adding child data
        List<String> dashboard = new ArrayList<>();
        List<String> master = new ArrayList<>();
        master.add("Customers");
        master.add("Agents");
        master.add("Vendors");
        master.add("Labours");
        master.add("Transport");
        List<String> design = new ArrayList<>();
        List<String> users = new ArrayList<>();
        List<String> orderform = new ArrayList<>();
        List<String> sales = new ArrayList<>();
        sales.add("Sales");
        sales.add("Sales Return");
        List<String> rawmaterials = new ArrayList<>();
        rawmaterials.add("Purchase");
        rawmaterials.add("Purchase Return");
        List<String> jobwork = new ArrayList<>();
        List<String> ledgers = new ArrayList<>();
        List<String> expenses = new ArrayList<>();
        List<String> reportAnalytics = new ArrayList<>();
        List<String> logout = new ArrayList<>();

        listDataChild.put(listDataHeader.get(0), dashboard);
        listDataChild.put(listDataHeader.get(1), master); // Header, Child data
        listDataChild.put(listDataHeader.get(2), design);
        listDataChild.put(listDataHeader.get(3), users);
        listDataChild.put(listDataHeader.get(4), orderform);
        listDataChild.put(listDataHeader.get(5), sales);
        listDataChild.put(listDataHeader.get(6), rawmaterials);
        listDataChild.put(listDataHeader.get(7), jobwork);
        listDataChild.put(listDataHeader.get(8), ledgers);
        listDataChild.put(listDataHeader.get(9), expenses);
        listDataChild.put(listDataHeader.get(10), reportAnalytics);
        listDataChild.put(listDataHeader.get(11), logout);
    }





    //    private ArrayList<DrawerMenu> getData(){
//        DrawerMenu t1=new DrawerMenu("Man Utd",R.drawable.logo);
//        t1.setImg(R.drawable.selector_img);
//
//        ArrayList<DrawerMenu> addMenu=new ArrayList<DrawerMenu>();
//        addMenu.add(t1);
//
//        addMenu.add(t1);
//
//
//        return addMenu;
//
//    }


    @Override
    public void replaceFragment(Fragment fragment) {
        if (fragment instanceof DashboardFragment)
            onBackPressed();
        super.replaceFragment(fragment);
    }

    @Override
    protected int getContentFramLayoutId() {
        return R.id.content_main;
    }

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

    }


    @Override
    protected void onDrawerAttached() {
//        if (PreferenceHelper.getInstance().getUser() != null) {
//            TextView txtuser = (TextView) findViewById(R.id.txtUserName);
//            txtuser.setText(PreferenceHelper.getInstance().getUser().getUsername());
//        }
        enableExpandableList();
       /* setProfile();*/

    }


    /*private void setProfile() {
        try {
            Login user = PreferenceHelper.getInstance().getUser();

           *//* View view = mNavigationView.getHeaderView(0).getRootView();*//*
//            txtEmail = _findViewById(R.id.txtEmail);
            txtUserName = _findViewById(R.id.txtUserName);
//            mImgUser = view.findViewById(R.id.imgUser);
//            txtEmail.setText(user.getEmail());
            txtUserName.setText(user.getUsername());
//            ImageUtils.displayImageCenterInside(this, user.getImageUrl(), mImgUser);
        } catch (Exception e) {
            AppLog.e(TAG, e.getMessage(), e);
        }
    }*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected View getRootView() {
        return _findViewById(R.id.content_main);
    }
}
