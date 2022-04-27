package com.techelevator.item;

import java.math.BigDecimal;

public class Candy extends ItemsForSale {

    public Candy(String location, String name, BigDecimal price, int stock ) {
        super(location,name,price,stock);
    }



    public String getSound() {
        return "Munch Munch, Yum!";
    }
}
