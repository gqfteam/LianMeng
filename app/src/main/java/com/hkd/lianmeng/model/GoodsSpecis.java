package com.hkd.lianmeng.model;

/**
 * Created by johe on 2016/10/10.
 */

public class GoodsSpecis {
    int id;
    int ClassificationId;
    String SpecisName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecisName() {
        return SpecisName;
    }

    public void setSpecisName(String specisName) {
        SpecisName = specisName;
    }

    public int getClassificationId() {
        return ClassificationId;
    }

    public void setClassificationId(int classificationId) {
        ClassificationId = classificationId;
    }
}
