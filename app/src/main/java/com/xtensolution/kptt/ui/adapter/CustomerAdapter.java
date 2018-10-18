package com.xtensolution.kptt.ui.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtensolution.core.ui.adapter.BaseSelectedRecyclerViewAdapter;
import com.xtensolution.core.ui.holder.BaseRecyclerHolder;
import com.xtensolution.core.utils.AppLog;
import com.xtensolution.kptt.R;
import com.xtensolution.kptt.model.Address;
import com.xtensolution.kptt.model.Agent;
import com.xtensolution.kptt.model.Contact;
import com.xtensolution.kptt.model.Customer;
import com.xtensolution.kptt.ui.activity.MainActivity;
import com.xtensolution.kptt.ui.fragment.CustomerFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by RIONTECH
 * Riontech on 3/11/17.
 */

public class CustomerAdapter extends BaseSelectedRecyclerViewAdapter<Customer> implements Filterable {
    private static final String TAG = CustomerAdapter.class.getSimpleName();
    private ArrayList<Customer> mUserList;
    private ArrayList<Customer> mFilteredList;


    public CustomerAdapter(Context context, List<Customer> objectsList) {
        super(context, objectsList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_listing, null);
        return new CustomersHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        CustomersHolder customersHolder = (CustomersHolder) holder;
        customersHolder.bindData(getItem(position));

    }



    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                List<Customer> filteredList = new ArrayList<>();

                FilterResults filterResults = new FilterResults();

                if (charString.isEmpty()) {
                    filteredList = mUserList;
                } else {
                    for (Customer customer : mUserList) {
                        if (customer.getCompanyName().toLowerCase().contains(charString.toLowerCase())) {
                            AppLog.e(getClass().getSimpleName(), "Row name::" + customer.getFirstName());
                            AppLog.e(getClass().getSimpleName(), "Query name::" + charString);
                            filteredList.add(customer);
                        }
                    }
                }

                filterResults.values = filteredList;
                return filterResults;
            }

            @Override

            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<Customer> filteredList = (List<Customer>) results.values;
                if (filteredList != null && !filteredList.isEmpty()) {
                    updateList(filteredList);
                } else {
                    Toast.makeText(context, "No customers found!", Toast.LENGTH_SHORT).show();
                }
            }
        };


    }

    /**
     * @param userList
     */
    public void setList(final List<Customer> userList) {
        this.mUserList = (ArrayList<Customer>) userList;
        this.mFilteredList = (ArrayList<Customer>) userList;

        updateList(userList);
    }


//    @Override
//    public void onClick(View v) {
//        mCustomer = new Customer();
//
//        switch (v.getId()) {
//            case R.id.imgCustomerDetailsMobile:
//                if (mCustomer.getAddresses() != null && !mCustomer.getAddresses().isEmpty()) {
//                    Contact contact = mCustomer.getContacts().get(0);
//                    String uri = contact.getMobile();
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    intent.setData(Uri.parse(uri));
//                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
//                    context.startActivity(intent);
//
//
//
//                }
//
//
//        }
//}

    class CustomersHolder extends BaseRecyclerHolder {
        private TextView txtCustomerName, txtCustomerAddress, txtCustomerAgent, txtCustomerTransport;
        //    private ImageView imgProfile;
        private ImageView imgCustomerCall;
        private ImageView imgAgentsCall;
        private ImageView imgTransportCall;

        private final String TAG = CustomerFragment.class.getSimpleName();


        public CustomersHolder(View itemView) {
            super(itemView);
//        imgProfile = _findView(R.id.imgProfile);
            txtCustomerName = _findView(R.id.txtCustomerName);
            txtCustomerAddress = _findView(R.id.txtCustomerAddress);
            txtCustomerAgent = _findView(R.id.txtagent);
            txtCustomerTransport = _findView(R.id.txttransport);
            imgCustomerCall = _findView(R.id.imgCustomerDetailsphone);
            imgAgentsCall = _findView(R.id.imgAgentphone);
            imgAgentsCall.setVisibility(View.GONE);
            imgTransportCall = _findView(R.id.imgTransportphone);
            imgTransportCall.setVisibility(View.GONE);
        }


        public void bindData(final Customer customer) {
            try {
                if (customer.getCompanyName() != null) {
                    txtCustomerName.setText(customer.getCompanyName());
                }

                if (customer.getAddresses() != null && !customer.getAddresses().isEmpty()) {
                    Address address = customer.getAddresses().get(0);
                    txtCustomerAddress.setText(address.getCity() + "," + address.getState());
                }

                if (customer.getAgents() != null && !customer.getAgents().isEmpty()) {
                    Agent agent = customer.getAgents().get(0);
                    txtCustomerAgent.setText(agent.getCompanyName());
                } else {
                    txtCustomerAgent.setText("No Agent");
                }

               /* if (customer.getTransports() != null && !customer.getTransports().isEmpty()) {
                    Transport transportCustomers = customer.getTransports().get(0);
                    txtCustomerTransport.setText(transportCustomers.getCompanyName());
                } else {
                    txtCustomerTransport.setText("No Transport");
                }*/


                itemView.setTag(customer);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       /* MainActivityTwo activity = (MainActivityTwo) v.getContext();
                        activity.replaceFragment(CustomerDetailsFragment.getInstance((Customer) v.getTag()));*/
                      /*  MainActivity activity = (MainActivity) v.getContext();
                        activity.replaceFragment(CustomerDetailsFragment.getInstance((Customer) v.getTag()));*/
                        // Intent intent = new Intent(v.getContext(), AgentsFragment.class);
                        //v.getContext().startActivity(intent);
                        // super.startActivity(new Intent(, AgentsFragment.class));
               /* Fragment fragment = new tasks();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
                    }

                });

                imgCustomerCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Contact contact = null;
                        try {
                            contact = customer.getContacts().get(0);
                            String uri = contact.getMobile();
                            Intent intents = new Intent(Intent.ACTION_CALL);
                            intents.setData(Uri.parse("tel:" + uri));
                            if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                return;
                            }
                            itemView.getContext().startActivity(intents);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            } catch (Exception e) {
                AppLog.e(TAG, e.getMessage(), e);
            }
        }
    }
}


