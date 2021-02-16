package com.example.sd6501_final_project_2173138.model;

import java.io.Serializable;

public class OrderModel implements Serializable {
    
    private int orderId;
    
    private String toyInfo;
    
    private int price;
    
    private String orderUsername;
    
    private String orderAddress;
    
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public String getToyInfo() {
        return toyInfo;
    }
    
    public void setToyInfo(String toyInfo) {
        this.toyInfo = toyInfo;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getOrderUsername() {
        return orderUsername;
    }
    
    public void setOrderUsername(String orderUsername) {
        this.orderUsername = orderUsername;
    }
    
    public String getOrderAddress() {
        return orderAddress;
    }
    
    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }
}
