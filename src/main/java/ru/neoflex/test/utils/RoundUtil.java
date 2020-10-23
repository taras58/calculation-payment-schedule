/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.neoflex.test.utils;

import java.math.BigDecimal;

/**
 * Общие методы для разных типов платежей
 *
 * @author alexander
 */
public class RoundUtil {

    /**
     * Округлить значение до определенного количества знаков после разделителя
     * цифр.
     *
     * @param value - значение для округления
     * @param number - количество знаков после разделителя цифр для округления
     * до
     * @param roundingMode - режим округления
     * @return округленное значение.
     */
    public static double round(double value, int number, int roundingMode) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(number, roundingMode);
        return bd.doubleValue();
    }

}
