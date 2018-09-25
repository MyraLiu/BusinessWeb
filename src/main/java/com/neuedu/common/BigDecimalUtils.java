package com.neuedu.common;

import java.math.BigDecimal;

/**
 * 通用的价格计算工具类
 */
public class BigDecimalUtils {

    /**
     * 加法
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal add(BigDecimal b1,BigDecimal b2){
        return b1.add(b2);
    }

    /**
     * 加法
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal sub(BigDecimal b1,BigDecimal b2){
        return b1.subtract(b2);
    }

    /**
     * 加法
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal multiply(BigDecimal b1,BigDecimal b2){
        return b1.multiply(b2);
    }

    /**
     * 加法
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal divide(BigDecimal b1,BigDecimal b2){
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);
    }

}
