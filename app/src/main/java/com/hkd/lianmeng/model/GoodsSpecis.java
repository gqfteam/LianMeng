package com.hkd.lianmeng.model;

/**
 * Created by johe on 2016/10/10.
 */

public class GoodsSpecis {
    int id;
    String speciesname;
    int classificationid;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSpeciesname() {
        return speciesname;
    }
    public void setSpeciesname(String speciesname) {
        this.speciesname = speciesname;
    }
    public int getClassificationid() {
        return classificationid;
    }
    public void setClassificationid(int classificationid) {
        this.classificationid = classificationid;
    }
}
