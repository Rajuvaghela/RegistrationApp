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

package com.xtensolution.kptt.utils;


import com.xtensolution.kptt.KPTApp;


public class Constants {
    public static final String TOKEN = "token";

    public static final String NOTIFICATION_BODY = "body";
    public static final String MESSAGE_RECEIVER = KPTApp.getInstance().getPackageName() + "_MESSAGE_RECEIVER";
    public static final String IME = "ime";
    public static String tensType;
    public static String selectedType;
    public static final String NOTE = "note";
    public static final String TYPE = "type";
    public static final String NOTE_TEXT = "note_text";
    public static final String DICTIONARY_SECTION = "dictionary_section";
    public static final long SPLASH_SCREEN_TIME_OUT = 2000;

    public static final String PREF_USER = "user";
    public static final String PREF_WORKSPACE = "workspace";
    public static final String WELCOME_SLIDE = "welcomeslide";

    public static final String PREF_PERSONALDETAILS = "personaldetails";
    public static final String PREF_CONTACTDETAILS = "contactdetails";
    public static final String PREF_EMPLOYMENTDETAILS = "employmentdetails";
    public static final String PREF_UPLOADDETAILS = "uploaddetails";

    public enum EVENT {CME, ACTIVITY}

    public enum GALLERY {PHOTOS, VIDEOS}
    //http://smadoctors.org/upload/gallery/smf2017_cricket_tournament/1487771983HMD_3187.JPG

    public static int ADDCONTACTS = 101;
    public static int ADDADDRESS = 102;
    public static int AGENT = 103;
    public static int TRANSPORT = 104;
    public static int ITEM = 105;
    public static int CUSTOMER = 106;
    public static int NEW_ORDER = 107;
    public static int VENDOR = 108;
    public static int LABOUR = 109;
    public static int SALES = 110;
    public static int DESIGN = 111;
    public static int PAYMENTRECEIVED = 112;
    public static int FILTER = 113;
    public static int SALESRETURN = 114;


    // Intent extras for customer edit
    public static final String CUSTOMER_ID = "customerId";
    public static final String VENDOR_ID = "vendorId";
    public static final String LABOUR_ID = "labourId";
    public static final String AGENT_ID = "agentId";
    public static final String TRANSPORT_ID ="transportId";
    public static final String IS_EDIT = "isEdit";
    public static final String DESIGN_ID = "designId";
    public static final String SALES_ID = "salesId";
    public static final String ORDER_ID = "orderId";

    public static final String FILTER_DATA = "filter_data";

    public static final int LIMIT = 20;
}
