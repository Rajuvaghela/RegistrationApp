package com.xtensolution.kptt.model;

import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * Created by Test on 11/8/2017.
 */

public interface ChipInterface {
    Object getId();

    Uri getAvatarUri();

    Drawable getAvatarDrawable();

    String getLabel();

    String getInfo();
}
