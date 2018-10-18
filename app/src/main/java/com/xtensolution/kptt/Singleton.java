package com.xtensolution.kptt;

import com.xtensolution.kptt.model.Agent;
import com.xtensolution.kptt.model.Customer;

import java.util.List;

/**
 * Created by RIONTECH
 * Riontech on 3/2/18.
 */

public class Singleton {
    private final static String TAG = Singleton.class.getSimpleName();
    private static FinalWrapper<Singleton> helperWrapper;
    private static final Object LOCK = new Object();

    /*private SalesFilter salesFilter;*/
    private List<Customer> customerList;
    private List<Agent> agentList;
   /* private List<Transport> transportList;
    private List<Design> designList;*/

    private Singleton() {
        // Rest client without basic authorization
    }

    /**
     * @return
     */
    public static Singleton getInstance() {
        FinalWrapper<Singleton> wrapper = helperWrapper;

        if (wrapper == null) {
            synchronized (LOCK) {
                if (helperWrapper == null) {
                    helperWrapper = new FinalWrapper<>(new Singleton());
                }
                wrapper = helperWrapper;
            }
        }
        return wrapper.value;
    }

   /* public SalesFilter getSalesFilter() {
        return salesFilter;
    }

    public void setSalesFilter(SalesFilter salesFilter) {
        this.salesFilter = salesFilter;
    }*/

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Agent> getAgentList() {
        return agentList;
    }

    public void setAgentList(List<Agent> agentList) {
        this.agentList = agentList;
    }

    /*public List<Transport> getTransportList() {
        return transportList;
    }

    public void setTransportList(List<Transport> transportList) {
        this.transportList = transportList;
    }*/

   /* public List<Design> getDesignList() {
        return designList;
    }

    public void setDesignList(List<Design> designList) {
        this.designList = designList;
    }*/
}