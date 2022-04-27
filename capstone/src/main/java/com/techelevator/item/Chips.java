package com.techelevator.item;


import java.math.BigDecimal;

public class Chips extends ItemsForSale{


    public Chips(String location, String name, BigDecimal price, int stock ) {
        super(location,name,price,stock);
    }

    public String getSound() {
        return "Crunch Crunch, Yum!";
    }
}
