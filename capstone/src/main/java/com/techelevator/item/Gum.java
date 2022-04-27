package com.techelevator.item;

import java.math.BigDecimal;

public class Gum extends ItemsForSale{

    public Gum(String location, String name, BigDecimal price, int stock ) {
        super(location,name,price,stock);
    }




    public String getSound() {
        return "Chew Chew, Yum!";
    }
}

