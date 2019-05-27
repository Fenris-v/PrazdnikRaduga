package com.petersburg_studio.prazdnikraduga.test.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("type_id")
    @Expose
    private Integer type_id;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("img_url0")
    @Expose
    private String img_url0;

    @SerializedName("img_url1")
    @Expose
    private String img_url1;

    @SerializedName("img_url2")
    @Expose
    private String img_url2;

    @SerializedName("img_url3")
    @Expose
    private String img_url3;

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url0() {
        return img_url0;
    }

    public void setImg_url0(String img_url0) {
        this.img_url0 = img_url0;
    }

    public String getImg_url1() {
        return img_url1;
    }

    public void setImg_url1(String img_url1) {
        this.img_url1 = img_url1;
    }

    public String getImg_url2() {
        return img_url2;
    }

    public void setImg_url2(String img_url2) {
        this.img_url2 = img_url2;
    }

    public String getImg_url3() {
        return img_url3;
    }

    public void setImg_url3(String img_url3) {
        this.img_url3 = img_url3;
    }
}
