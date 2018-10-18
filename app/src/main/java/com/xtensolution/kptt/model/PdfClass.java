package com.xtensolution.kptt.model;

import com.google.gson.annotations.SerializedName;

public class PdfClass {

    @SerializedName("title")
    private String Title;

    @SerializedName("pdf")
    private String Pdf;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
