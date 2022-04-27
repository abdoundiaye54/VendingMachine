package com.techelevator;

import com.techelevator.item.*;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {

    private final static int INITIAL_STOCK = 5;


    private BigDecimal machineBalance = new BigDecimal("0.00");


    List<ItemsForSale> inventory = new ArrayList<>();

    List<String> logEntries = new ArrayList<>();


    public List<String> getLogEntries() {
        return this.logEntries;
    }

    public BigDecimal getMachineBalance() {
        return this.machineBalance;
    }


    public List<ItemsForSale> getInventory() {
        return this.inventory;
    }

    public VendingMachine() {
        loadInventory();
    }

    private void loadInventory() {

        File inputFile = new File("C:\\Users\\Student\\workspace\\capstone-1-team-4\\capstone\\vendingmachine.csv");
        try (Scanner fileToRead = new Scanner(inputFile)) {
            while (fileToRead.hasNextLine()) {
                String line = fileToRead.nextLine();
                String[] parts = line.split("\\|");
                if (parts[3].equals("Chip")) {
                    Chips chip = new Chips(parts[0], parts[1], new BigDecimal(parts[2]), 5);
                    inventory.add(chip);
                } else if (parts[3].equals("Candy")) {
                    Candy candy = new Candy(parts[0], parts[1], new BigDecimal(parts[2]), 5);
                    inventory.add(candy);
                } else if (parts[3].equals("Drink")) {
                    Beverages drink = new Beverages(parts[0], parts[1], new BigDecimal(parts[2]), 5);
                    inventory.add(drink);
                } else {
                    Gum gum = new Gum(parts[0], parts[1], new BigDecimal(parts[2]), 5);
                    inventory.add(gum);
                }


            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public BigDecimal feedMoney() { //fixed feed money to update the current balance everytime funds are added.

        Scanner userInput = new Scanner(System.in);// should be in vending machine cli
        String amount = userInput.nextLine();
        BigDecimal amountToDeposit = new BigDecimal(amount);
        //rounds value up to the nearest whole number.
        amountToDeposit = amountToDeposit.setScale(0, RoundingMode.UP);

        return this.machineBalance = machineBalance.add(amountToDeposit);


    }


    public void selectProduct() {
        //show list of prods available, and allow customer to enter corresponding code.


        Scanner userInput = new Scanner(System.in);//cli
        String userResponse = userInput.nextLine();
        String userResponseToUpper = userResponse.toUpperCase();

        String result = "Invalid input";
        String name = "";
        BigDecimal price = new BigDecimal("0.00");

        for (ItemsForSale item : inventory) {
            if (item.getLocation().equals(userResponseToUpper)) { //make case-insensitive


                if (item.getStock() > 0) {

                    BigDecimal startingBalance = getMachineBalance(); // new added for log

                    if (machineBalance.compareTo(item.getPrice()) > 0) {
                        this.machineBalance = this.machineBalance.subtract(item.getPrice());
                        int newStock = item.removeStock();
                        item.setStock(newStock);

                            result = item.getSound();//return name of item and its price
                            name = item.getName();
                            price = item.getPrice();

                            System.out.println(name);
                            System.out.println("Cost: $" + price);
                            System.out.println(result);
                            System.out.println("Remaining balance: $" + this.getMachineBalance());
                            logFormat(item.getName() + " " + item.getLocation(), startingBalance, getMachineBalance());

                    } else {
                        System.out.println("insufficient funds, please add more!");
                    }
                } else {
                    System.out.println("Item is sold out!");
                }
            }
        }
    }


    public void finishTransaction() {
        returnChange();
        this.machineBalance = new BigDecimal("0");


    }

    public String returnChange() {
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickel = new BigDecimal("0.05");

        BigDecimal startingBalance = machineBalance;

        BigDecimal quarters = machineBalance.divide(quarter).setScale(0, RoundingMode.FLOOR);
        machineBalance = machineBalance.remainder(quarter);
        BigDecimal dimes = machineBalance.divide(dime).setScale(0, RoundingMode.FLOOR);
        machineBalance = machineBalance.remainder(dime);
        BigDecimal nickels = machineBalance.divide(nickel).setScale(0, RoundingMode.FLOOR);

        this.machineBalance= BigDecimal.ZERO;
        logFormat("GIVE CHANGE: ", startingBalance, machineBalance);

        String coins = "Your change is: " + quarters + " quarter(s), " + dimes + " dime(s), and " + nickels + " nickel(s).  Have a nice day!";

        System.out.println(coins);

        return coins;
    }

    public List<String> logFormat(String message, BigDecimal startingBalance, BigDecimal endingBalance) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String formattedDate = dateFormatter.format(LocalDateTime.now());


        String format = formattedDate + " " + message + " " + startingBalance + " " + endingBalance;

        logEntries.add(format);

        return logEntries;
    }

    public void log() {
        File outputFile = new File("log.txt");

        try (PrintWriter logFile = new PrintWriter(new FileWriter("log.txt", true))) {

            for (String logEntry : logEntries) {
                logFile.println(logEntry);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}