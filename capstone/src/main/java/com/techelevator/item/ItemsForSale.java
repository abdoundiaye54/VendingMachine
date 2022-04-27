package com.techelevator.item;

import java.math.BigDecimal;

public abstract class ItemsForSale {

    private String  name;
    private BigDecimal price;
    private int stock =  5;
    private String location;



    public ItemsForSale(){
    }

    public  ItemsForSale(String location,String name, BigDecimal price, int stock) {
        //Super() slot location
        this.location= location;
        this.name = name;
        this.price = price;
        this.stock = stock;

    }

    public String getLocation(){
        return location;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    //new
    public int removeStock() {
        stock--;
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public abstract String getSound();
}
