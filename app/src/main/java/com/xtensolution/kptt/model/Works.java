package com.xtensolution.kptt.model;

import java.io.Serializable;

/**
 * Created by riontech on 23/11/17.
 */

public class Works implements Serializable {

    private  String workId= null;
    private  String workName =null;
    private String workspaceId = null;


    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }
}
