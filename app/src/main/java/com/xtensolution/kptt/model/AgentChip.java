package com.xtensolution.kptt.model;

import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * Created by Test on 11/4/2017.
 */

public class AgentChip implements ChipInterface {
    private String agentId;
    private String companyName;

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public Object getId() {
        return agentId;
    }

    @Override
    public Uri getAvatarUri() {
        return null;
    }

    @Override
    public Drawable getAvatarDrawable() {
        return null;
    }

    @Override
    public String getLabel() {
        return companyName;
    }

    @Override
    public String getInfo() {
        return null;
    }
}
