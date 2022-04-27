package com.techelevator.item;

import java.math.BigDecimal;

public class Change {
    private final BigDecimal QUARTER = new BigDecimal("0.25");
    private final BigDecimal DIME = new BigDecimal("0.10");
    private final BigDecimal NICKEL = new BigDecimal("0.05");

    public BigDecimal getQuarter() {
        return QUARTER;
    }

    public BigDecimal getDime() {
        return DIME;
    }

    public BigDecimal getNickel() {
        return NICKEL;
    }


}
