package com.example.sd6501_final_project_2173138.model;

import java.io.Serializable;

public class ToyModel implements Serializable {
    
    private int toyImage;
    
    private String toyName;
    
    private int toyPrice;
    
    private int toyNum;
    
    public int getToyImage() {
        return toyImage;
    }
    
    public void setToyImage(int toyImage) {
        this.toyImage = toyImage;
    }
    
    public String getToyName() {
        return toyName;
    }
    
    public void setToyName(String toyName) {
        this.toyName = toyName;
    }
    
    public int getToyPrice() {
        return toyPrice;
    }
    
    public void setToyPrice(int toyPrice) {
        this.toyPrice = toyPrice;
    }
    
    public int getToyNum() {
        return toyNum;
    }
    
    public void setToyNum(int toyNum) {
        this.toyNum = toyNum;
    }
}
