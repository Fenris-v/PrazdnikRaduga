package com.petersburg_studio.prazdnikraduga.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    @SerializedName("total_records")
    @Expose
    private Integer total_records;

    @SerializedName("total_pages")
    @Expose
    private Integer total_pages;

    @SerializedName("has_more")
    @Expose
    private boolean has_more;

    @SerializedName("results")
    @Expose
    private List<Items> items = null;

    public Integer getTotal_records() {
        return total_records;
    }

    public void setTotal_records(Integer total_records) {
        this.total_records = total_records;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}