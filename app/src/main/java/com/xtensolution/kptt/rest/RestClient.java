/*
 * Copyright (c) 2016 riontech-xten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xtensolution.kptt.rest;

import com.xtensolution.kptt.model.ContactDetails;
import com.xtensolution.kptt.model.Customer;
import com.xtensolution.kptt.model.Login;
import com.xtensolution.kptt.model.PdfClass;
import com.xtensolution.kptt.model.Registration;
import com.xtensolution.kptt.model.Response;
import com.xtensolution.kptt.model.SubmitData;
import com.xtensolution.kptt.model.WorkSpace;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by suresh dobariya.
 */

public interface RestClient {
//    String BASE_URL = "http://jaiveekfood.com/api/";
      String BASE_URL =  "http://register.hillcrossfinance.co.za/";

    @Multipart
    @POST("/retrofit_tutorial/retrofit_client.php")
    Call<Response>uploadFile(@Part MultipartBody.Part file, @Part("file") RequestBody name);



//    http://register.hillcrossfinance.co.za/index.php?option=com_kycregistration&task=webservice&tmpl=component&format=json

//    http://register.hillcrossfinance.co.za/index.php?option=com_kycregistration&task=phonevalidation&tmpl=component&format=json
    @POST("index.php?option=com_kycregistration&task=phonevalidation&tmpl=component&format=json")
    Call<Response<List<ContactDetails>>>getCellNo(@Body HashMap<String, String> map);

   /* @Multipart
    @POST("index.php?option=com_kycregistration&task=webservice&tmpl=component&format=json")
    Call<Response<List<SubmitData>>> submitData(@Body SubmitData submitData,@Part MultipartBody.Part file, @Part("file") RequestBody name);*/

  /*  @Multipart
    @POST("index.php?option=com_kycregistration&task=webservice&tmpl=component&format=json")
    Call<Response<List<SubmitData>>> submitData(;
    @POST("index.php/api/customers/createCustomer")
    Call<Response<List<Registration>>> createRegistration(@Body Registration registration);*/


    @POST("upload.php")
    Call<Response<PdfClass>>pdfUploadFunction (@Body HashMap<String, String> map);

    //login
    @POST("index.php/api/users/login")
    Call<Response<Login>> login(@Body HashMap<String, String> map);

    //workSpace
    @POST("index.php/api/users/workspace")
    Call<Response<WorkSpace>> workSpace(@Body HashMap<String, String> map);
    // Customer
    @POST("index.php/api/customers/createCustomer")
    Call<Response<List<Customer>>> createCustomer(@Body Customer customer);

    @POST("index.php/api/customers/getCustomers")
    Call<Response<List<Customer>>> getCustomer(@Body HashMap<String, String> map);

    @POST("index.php/api/customers/updateCustomer")
    Call<Response<List<Customer>>> updateCustomer(@Body Customer customer);

    @POST("index.php/api/customers/searchCustomer")
    Call<Response<List<Customer>>> searchCustomer(@Body HashMap<String, String> map);


   /* @Multipart
    @POST("Api.php?apicall=upload")
    Call<SubmitData> submitData(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("desc") RequestBody desc);*/



    @Multipart
    @POST("index.php?option=com_kycregistration&task=webservice&tmpl=component&format=json")
    Call<SubmitData> submitData(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody passport_picture,
                                RequestBody international_passport, RequestBody proof_address,RequestBody proof_employment,
                                RequestBody passport_pic_upload, RequestBody international_pic_upload,
                                RequestBody  proof_address_pic_upload,RequestBody  proof_employment_pic_upload,
                                @Body SubmitData submitData);

   /* @POST("index.php/api/customers/getCustomerById")
    Call<Response<List<Customer>>> getCustomerById(@Body HashMap<String, String> map);

    @POST("index.php/api/customers/updateCustomer")
    Call<Response<List<Customer>>> updateCustomer(@Body Customer customer);

    // Agent
    @POST("index.php/api/agents/searchAgents")
    Call<Response<List<AgentChip>>> searchAgent(@Body HashMap<String, String> map);

    @POST("index.php/api/agents/getAgents")
    Call<Response<List<Agent>>> getAgents(@Body HashMap<String, String> map);

    @POST("index.php/api/agents/createAgent")
    Call<Response<List<Agent>>> createAgent(@Body Agent transport);

    @POST("index.php/api/agents/getAgentById")
    Call<Response<List<Agent>>> getAgentById(@Body HashMap<String, String> map);

    @POST("index.php/api/agents/updateAgent")
    Call<Response<List<Agent>>> updateAgent(@Body Agent agent);


    // Transport
    @POST("index.php/api/transport/createTransport")
    Call<Response<List<Transport>>> createTransport(@Body Transport transport);

    @POST("index.php/api/transport/searchTransports")
    Call<Response<List<TransportChip>>> searchTransport(@Body HashMap<String, String> map);

    @POST("index.php/api/transport/getTransports")
    Call<Response<List<Transport>>> getTransport(@Body HashMap<String, String> map);

    @POST("index.php/api/transport/getTransportById")
    Call<Response<List<Transport>>> getTransportById(@Body HashMap<String, String> map);

    @POST("index.php/api/transport/updateTransport")
    Call<Response<List<Transport>>> updateTransport(@Body Transport transport);


    // Vendor
    @POST("index.php/api/vendors/createVendor")
    Call<Response<List<Vendor>>> createVendor(@Body Vendor vendor);

    @POST("index.php/api/vendors/getVendors")
    Call<Response<List<Vendor>>> getVendor(@Body HashMap<String, String> map);

    @POST("index.php/api/labors/searchVendor")
    Call<Response<List<Vendor>>> searchVendor(@Body HashMap<String, String> map);

    @POST("index.php/api/vendors/getVendorById")
    Call<Response<List<Vendor>>> getVendorById(@Body HashMap<String, String> map);

    @POST("index.php/api/vendors/updateVendor")
    Call<Response<List<Vendor>>> updateVendor(@Body Vendor vendor);


    // Labor
    @POST("index.php/api/labors/createLabor")
    Call<Response<List<Labor>>> createLabor(@Body Labor labor);

    @POST("index.php/api/labors/getLabors")
    Call<Response<List<Labor>>> getLabors(@Body HashMap<String, String> map);

    @POST("index.php/api/labors/searchLabor")
    Call<Response<List<Labor>>> searchLabor(@Body HashMap<String, String> map);

    @POST("index.php/api/labors/getLaborById")
    Call<Response<List<Labor>>> getLabourById(@Body HashMap<String, String> map);

    @POST("index.php/api/labors/updateLabor")
    Call<Response<List<Labor>>> updateLabour(@Body Labor labor);


    // Order
    @POST("index.php/api/orderForm/getOrders")
    Call<Response<List<Order>>> getOrder(@Body HashMap<String, String> map);

    @POST("index.php/api/orderForm/getOrderDetails")
    Call<Response<List<Item>>> getOrderFormDetails(@Body HashMap<String, String> map);

    @POST("index.php/api/orderForm/getOrderbyId")
    Call<Response<List<Order>>> getOrderById(@Body HashMap<String, String> map);


    @POST("index.php/api/orderForm/createOrderForm")
    Call<Response<List<Order>>> createOrder(@Body Order order);

    @POST("index.php/api/orderForm/searchOrder")
    Call<Response<List<Sales>>> searchOrder(@Body HashMap<String, String> map);

    @POST("index.php/api/orderForm/updateOrder")
    Call<Response<List<Order>>> updateOrder(@Body Order order);

    //Appbar search
    @POST("index.php/api/orderForm/searchOrders")
    Call<Response<List<Order>>> searchOrderAll(@Body HashMap<String, String> map);

    @POST("index.php/api/orderForm/getLastOrderNo")
    Call<Response<Integer>> getLastOrderNo(@Body HashMap<String, String> map);

    // Sales
    @POST("index.php/api/sales/getSales")
    Call<Response<List<Sales>>> getSales(@Body HashMap<String, String> map);

    @POST("index.php/api/sales/createSales")
    Call<Response<List<Sales>>> createSales(@Body Sales sales);

    @POST("index.php/api/sales/getSalesDetails")
    Call<Response<List<Item>>> getSalesDetails(@Body HashMap<String, String> map);

    @POST("index.php/api/sales/getSalesbyId")
    Call<Response<List<Sales>>> getSalesById(@Body HashMap<String, String> map);

    @POST("index.php/api/sales/getLastInvoiceNo")
    Call<Response<Integer>> getLastInvoiceNo(@Body HashMap<String, String> map);

    @POST("index.php/api/sales/updateSales")
    Call<Response<List<Sales>>> updateSales(@Body Sales sales);

    @POST("index.php/api/sales/searchSales")
    Call<Response<List<Sales>>> searchSales(@Body HashMap<String, String> map);

    @POST("index.php/api/sales/getSalesInvoices")
    Call<Response<List<GetSalesReturn>>> getSalesReturnInvoiceNo(@Body HashMap<String, String> map);

    @POST("index.php/api/sales/createSalesReturn")
    Call<Response<List<Sales>>>createSalesReturn(@Body Sales sales);

    @POST("index.php/api/sales/getSalesReturn")
    Call<Response<List<Sales>>>getSalesReturn(@Body HashMap<String, String> map);

    @POST("index.php/api/sales/getSalesReturnDetails")
    Call<Response<List<Item>>> getSalesReturnDetails(@Body HashMap<String, String> map);



    // Design
    @POST("index.php/api/design/createDesign")
    Call<Response<List<Design>>> createDesign(@Body Design design);

    @POST("index.php/api/design/getTypes")
    Call<Response<List<Item>>> getDesign(@Body HashMap<String, String> map);

    @POST("index.php/api/design/getTypes")
    Call<Response<List<Saree>>> getSarees(@Body HashMap<String, String> map);

    @POST("index.php/api/design/getTypes")
    Call<Response<List<FabricChip>>> searchFabric(@Body HashMap<String, String> map);

    @POST("index.php/api/design/getTypes")
    Call<Response<List<WorkChip>>> searchWork(@Body HashMap<String, String> map);

    @POST("index.php/api/design/getDesigns")
    Call<Response<List<Design>>> getAllDesigns(@Body HashMap<String, String> map);

    @POST("index.php/api/design/getDesignById")
    Call<Response<List<Design>>> getDesignById(@Body HashMap<String, String> map);

    @POST("index.php/api/design/searchBarcode")
    Call<Response<List<Item>>> searchBarcode(@Body HashMap<String, String> map);

    @POST("index.php/api/design/updateDesign")
    Call<Response<List<Design>>> updateDesign(@Body Design design);
//    //filter
//    @POST("index.php/api/filter/getTypes")
//    Call<Response<List<Customer>>>getFilterCustomer(@Body HashMap<String, String> map);
//
//    @POST("index.php/api/filter/getTypes")
//    Call<Response<List<Agent>>>getFilterAgent(@Body HashMap<String, String> map);
//
//    @POST("index.php/api/filter/getTypes")
//    Call<Response<List<Transport>>>getFilterTransport(@Body HashMap<String, String> map);

    //Expenses
    @POST("index.php/api/expenses/createExpensesCategory")
    Call<Response<List<ExpensesCategory>>> createExpensesCategory(@Body ExpensesCategory expensesCategory);

    @POST("index.php/api/expenses/getExpensesCategory")
    Call<Response<List<ExpensesCategory>>> getExpensesCategory(@Body HashMap<String, String> map);

    @POST("index.php/api/filter/filter")
    Call<Response<List<Sales>>> filter(@Body SalesFilter sales);

    @POST("index.php/api/filter/filter")
    Call<Response<List<Order>>> filterOrder(@Body SalesFilter sales);

    //Leadgers

    @POST("index.php/api/ledgers/list")
    Call<Response<List<Ledgers>>> getLedgers(@Body HashMap<String, String> map);

    @POST("index.php/api/ledgers/getInvoices")
    Call<Response<List<Ledgers>>> getInvoiceNo(@Body HashMap<String, String> map);



    @POST("index.php/api/ledgers/receive")
    Call<Response<List<Ledgers>>>creditPayment(@Body Ledgers ledgers);

    @POST("index.php/api/ledgers/details")
    Call<Response<List<Ledgers>>> ledgersDetails(@Body HashMap<String, String> map);

    //Purchase

    @POST("index.php/api/agents/getPurchase")
    Call<Response<List<Purchase>>> getPurchase(@Body HashMap<String, String> map);
*/


}
