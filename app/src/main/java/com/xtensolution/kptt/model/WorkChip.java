package com.xtensolution.kptt.model;

import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * Created by riontech on 23/11/17.
 */

public class WorkChip implements ChipInterface {

    private String workId = null;
    private String workName = null;

    @Override
    public Object getId() {
        return workId;
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
        return workName;
    }

    @Override
    public String getInfo() {
        return null;
    }

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
}
