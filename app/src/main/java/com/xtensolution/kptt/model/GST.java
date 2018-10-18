package com.xtensolution.kptt.model;

/**
 * Created by Test on 11/8/2017.
 */

public class GST {
    private long id;
    private String state;
    private String code;
    private int no;

    public GST() {
    }

    public GST(long id, String state, String code, int no) {
        this.id = id;
        this.state = state;
        this.code = code;
        this.no = no;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
