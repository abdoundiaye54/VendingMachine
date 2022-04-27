package com.techelevator;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @Before
    public void setup(){
        vendingMachine= new VendingMachine();
    }

    @Test
    public void load_inventory_test() {

        File inputFile = new File("C:\\Users\\Student\\workspace\\capstone-1-team-4\\capstone\\vendingmachine.csv");

    }
    @Test
    public void feed_money_test(){
        //actual
        BigDecimal actual = new BigDecimal("1.00");

       // vendingMachine.feedMoney(1.00);

        assertEquals("Salary should remain the same when raise percentage is negative.", actual,0.0);

    }





}
