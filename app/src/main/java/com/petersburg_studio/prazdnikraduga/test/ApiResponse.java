package com.petersburg_studio.prazdnikraduga.test;

import java.util.List;

class Product {
    public int type_id;
    public int id;
    public String name;
    String img_url;
}

class ApiResponse {
    List<Product> products;
//    public int total_pages;
//    public int total_records;
    boolean has_more;
}
