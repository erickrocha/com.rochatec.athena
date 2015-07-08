package com.rochatec.pos.athena.utils;

import com.rochatec.pos.athena.app.math.Currency;
import com.rochatec.pos.athena.app.math.Decimal;

public class Formatter {

    private static Currency currency;
    private static Decimal decimal;

    static {
        currency = new Currency();
        decimal = new Decimal();
    }

    public static Currency getCurrency() {
        return currency != null ? currency : new Currency();
    }


    public static Decimal getDecimal() {
        return decimal != null ? decimal : new Decimal();
    }


}
