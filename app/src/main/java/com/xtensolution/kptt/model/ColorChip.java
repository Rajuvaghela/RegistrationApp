package com.xtensolution.kptt.model;

import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * Created by riontech on 23/11/17.
 */

public class ColorChip implements  ChipInterface {

    private  String colorId = null;
    private  String colorCode = null;
    @Override
    public Object getId() {
        return null;
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
        return null;
    }

    @Override
    public String getInfo() {
        return null;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
