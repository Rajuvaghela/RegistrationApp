package com.xtensolution.kptt.model;

import java.io.Serializable;

/**
 * Created by riontech2 on 26/2/18.
 */

public class DrawerMenu implements Serializable {

    private  String Name = null;
    private  Integer img = null;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }
}
