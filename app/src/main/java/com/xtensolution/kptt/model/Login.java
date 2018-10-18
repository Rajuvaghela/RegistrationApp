package com.xtensolution.kptt.model;

import java.io.Serializable;

/**
 * Created by riontech on 30/11/17.
 */

public class Login implements Serializable {
    private String workspaceId = null;
    private String userId = null;
    private String username = null;
    private String password = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }
}
