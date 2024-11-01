package com.kojstarinnovations.afaas.commons.data.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DataHelper {

    public static BigDecimal intToBigDecimal(int value) {
        return BigDecimal.valueOf(value);
    }

    //Big decimal round to 2 decimals
    public static BigDecimal bigDecimalScale(Object value, int scale) {
        try{
            BigDecimal decimal = new BigDecimal(value.toString());
            return decimal.setScale(scale, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
}
