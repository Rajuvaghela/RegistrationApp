package com.riontech.retrofit.errorhandler;

import java.util.HashMap;
import java.util.Map;


/**
 */
public class HttpStatusCodes {

    private static final String TAG = HttpStatusCodes.class.getSimpleName();


    public static final int CODE_NO_INTERNET = 000;
    public static final int CODE_NO_DATA = 001;
    public static final int CODE_WENT_WRONG = 002;

    public static final int CODE_400 = 400;
    public static final int CODE_401 = 401;
    public static final int CODE_402 = 402;
    public static final int CODE_403 = 403;
    public static final int CODE_404 = 404;
    public static final int CODE_405 = 405;
    public static final int CODE_406 = 406;
    public static final int CODE_407 = 407;
    public static final int CODE_408 = 408;
    public static final int CODE_409 = 409;
    public static final int CODE_410 = 410;
    public static final int CODE_411 = 411;
    public static final int CODE_412 = 412;
    public static final int CODE_413 = 413;
    public static final int CODE_414 = 414;
    public static final int CODE_415 = 415;
    public static final int CODE_416 = 416;
    public static final int CODE_417 = 417;

    public static final int CODE_500 = 500;
    public static final int CODE_501 = 501;
    public static final int CODE_502 = 502;
    public static final int CODE_503 = 503;
    public static final int CODE_504 = 504;
    public static final int CODE_505 = 505;
    public static final int CODE_506 = 506;

    public static Map<Integer, String> getCodesMap() {
        Map<Integer, String> mCodes = new HashMap<>();

        mCodes.put(CODE_NO_INTERNET, "No internet found");
        mCodes.put(CODE_NO_DATA, "No data found");
        mCodes.put(CODE_WENT_WRONG, "Something went to wrong");

        mCodes.put(CODE_400, "Bad Request");
        mCodes.put(CODE_401, "Unauthorized");
        mCodes.put(CODE_402, "Payment Required");
        mCodes.put(CODE_403, "Forbidden, User not authorized to perform this action in current workspace");
        mCodes.put(CODE_404, "Not Found");
        mCodes.put(CODE_405, "Method Not Allowed");
        mCodes.put(CODE_406, "Not Acceptable");
        mCodes.put(CODE_407, "Proxy Authentication Required");
        mCodes.put(CODE_408, "Request Timeout");
        mCodes.put(CODE_409, "Conflict");
        mCodes.put(CODE_410, "Gone");
        mCodes.put(CODE_411, "Length Required");
        mCodes.put(CODE_412, "Precondition Failed");
        mCodes.put(CODE_413, "Request Entity Too Large");
        mCodes.put(CODE_414, "Request-URI Too Long");
        mCodes.put(CODE_415, "Unsupported Media Type");
        mCodes.put(CODE_416, "Requested Range Not Satisfiable");
        mCodes.put(CODE_417, "Expectation Failed");

        mCodes.put(CODE_500, "Internal Server Error");
        mCodes.put(CODE_501, "Not Implemented");
        mCodes.put(CODE_502, "Bad Gateway");
        mCodes.put(CODE_503, "Service Unavailable");
        mCodes.put(CODE_504, "Gateway Timeout");
        mCodes.put(CODE_505, "HTTP Version Not Supported");
        mCodes.put(CODE_506, "Server not responding");

        return mCodes;
    }

    public static String getErrorMessage(int statusCode) {
        Map<Integer, String> codeMap = getCodesMap();
        try {
            if (codeMap.containsKey(statusCode))
                return codeMap.get(statusCode);
            else
                return codeMap.get(CODE_WENT_WRONG);
        } catch (Exception e) {
            return codeMap.get(CODE_WENT_WRONG);
        }
    }
}
