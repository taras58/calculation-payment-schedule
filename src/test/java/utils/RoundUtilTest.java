/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import ru.neoflex.test.utils.RoundUtil;

/**
 *
 * @author alexander
 */
public class RoundUtilTest {

    private static final double DELTA = 1e-15;

    public RoundUtilTest() {

    }

    /**
     * Проверка правильности округления чисел
     *
     */
    @Test
    public void getRoundUtil() {
        double expected = 1.02;

        double actual = RoundUtil.round(1.02348, 2, BigDecimal.ROUND_HALF_UP);

        Assert.assertEquals(expected, actual, DELTA);
    }

    /**
     * Проверка получения исключения при указании отрицательного количестква
     * знаков после запятой
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void getRoundUtilException() {
        double expected = 1.02;

        double actual = RoundUtil.round(1.02348, -2, BigDecimal.ROUND_HALF_UP);

        Assert.assertEquals(expected, actual, DELTA);
    }

}
