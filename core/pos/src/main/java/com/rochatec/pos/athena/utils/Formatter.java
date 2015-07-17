package com.rochatec.pos.athena.utils;

import com.rochatec.pos.athena.app.math.Currency;
import com.rochatec.pos.athena.app.math.Decimal;
import com.rochatec.pos.athena.app.math.Weight;

public class Formatter {

    private static Currency currency;
    private static Decimal decimal;
    private static Weight weight;

    static {
        currency = new Currency();
        decimal = new Decimal();
        weight = new Weight();
    }

    public static Currency getCurrency() {
        return currency != null ? currency : new Currency();
    }


    public static Decimal getDecimal() {
        return decimal != null ? decimal : new Decimal();
    }

    public static Weight getWeight(){
        return weight != null ? weight : new Weight();
    }

}
