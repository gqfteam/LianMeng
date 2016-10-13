package com.hkd.lianmeng.model;

/**
 * Created by johe on 2016/10/11.
 */

public class Campus {
    int id;

    String campusname;
    /**
     *
     */
    int Collegeid;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCampusname() {
        return campusname;
    }
    public void setCampusname(String campusname) {
        this.campusname = campusname;
    }
    public int getCollegeid() {
        return Collegeid;
    }
    public void setCollegeid(int collegeid) {
        Collegeid = collegeid;
    }
}
