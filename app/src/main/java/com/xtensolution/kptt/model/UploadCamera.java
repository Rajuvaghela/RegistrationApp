package com.xtensolution.kptt.model;

import java.io.Serializable;

public class UploadCamera implements Serializable {
    private  String fileToUpload;
    private  String filename;

    public String getFileToUpload() {
        return fileToUpload;
    }

    public void setFileToUpload(String fileToUpload) {
        this.fileToUpload = fileToUpload;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
