
package com.xtensolution.kptt.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeanUploadEmployeeDetail implements Serializable
{

    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("data")
    @Expose
    public Data data;
    private final static long serialVersionUID = -225475578273855910L;

}
