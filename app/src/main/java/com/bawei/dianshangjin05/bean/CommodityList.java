package com.bawei.dianshangjin05.bean;

import java.util.List;

/**
 * 商品分类信息
 */
public class CommodityList {
    private int id;
    private String name;
    private List<Commodity> commodityList;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Commodity> getCommodityList() {
        return commodityList;
    }
    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }
}
