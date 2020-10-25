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

    private static final String NUMBER_INVALID = "Указано неверное количество знаков после запятой: ";

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

        // Проверка правильности указанного количества знаков после запятой
        if (number <= 0) {
            throw new IllegalArgumentException(NUMBER_INVALID + number);
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(number, roundingMode);
        return bd.doubleValue();
    }

}
