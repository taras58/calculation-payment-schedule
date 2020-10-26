/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.neoflex.test.calculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ru.neoflex.test.model.LoanParameters;
import ru.neoflex.test.model.Payment;
import ru.neoflex.test.utils.RoundUtil;

/**
 * Аннуитетный тип расчета графика платежей по кредиту с точностью до двух
 * знаков
 *
 * @author alexander
 */
public class AnnuityCalculation {

    private static final int NEW_SCALE = 2;

    /**
     * Вычисление списка платежей на основании входных параметров
     *
     * @param parameters - исходные данные по кредиту
     * @return - список платежей
     */
    public static List<Payment> calc(LoanParameters parameters) {

        List<Payment> payments = new ArrayList<>();
        Payment previous = new Payment();
        previous.setBalanceDebt(parameters.getLoanAmount());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parameters.getLoanDate());

        // Вычисление ставки на месяц
        double monthlyRate = parameters.getInterestRate() / 12 / 100;

        // Вычисление суммы аннуитет
        double annuity = calcAnnuitySums(parameters.getLoanAmount(), parameters.getLoanTerm(), monthlyRate);

        // Формирование списка платежей
        for (int index = 1; index <= parameters.getLoanTerm(); index++) {

            calendar.add(Calendar.MONTH, 1);
            Payment payment = createPayment(index, calendar.getTime(), annuity,
                    monthlyRate, previous.getBalanceDebt());

            // Расчет последнего платежа по кредиту
            if (index == parameters.getLoanTerm() && payment.getBalanceDebt() > 0) {
                calcLastPayment(payment);
            }

            previous = payment;
            payments.add(payment);
        }

        return payments;

    }

    /**
     * Формирование нового платежа на основании входных параметров
     *
     * @param number
     * @param date
     * @param parameters
     * @param monthlyRate
     * @param previousBalanceDebt
     * @return
     */
    private static Payment createPayment(int number, Date date, double amountPayment,
            double monthlyRate, double previousBalanceDebt) {

        double interestAmount = previousBalanceDebt * monthlyRate;
        double principalAmount = amountPayment - interestAmount;
        double balanceDebt = previousBalanceDebt - principalAmount;

        // Округление данных до двух знаков после запятой
        double amountPaymentRound = RoundUtil.round(amountPayment, NEW_SCALE, BigDecimal.ROUND_HALF_UP);
        double principalAmountRound = RoundUtil.round(principalAmount, NEW_SCALE, BigDecimal.ROUND_CEILING);
        double interestAmountRound = RoundUtil.round(interestAmount, NEW_SCALE, BigDecimal.ROUND_HALF_UP);
        double balanceDebtRound = RoundUtil.round(balanceDebt, NEW_SCALE, BigDecimal.ROUND_HALF_UP);

        return new Payment(number, date, amountPaymentRound,
                principalAmountRound, interestAmountRound, balanceDebtRound);
    }

    /**
     * Расчет последнего платежа по кредиту
     *
     * @param payment
     */
    private static void calcLastPayment(Payment payment) {
        double amountPayment = payment.getAmountPayment() + payment.getBalanceDebt();
        double amountPaymentRound = RoundUtil.round(amountPayment, NEW_SCALE, BigDecimal.ROUND_HALF_UP);
        payment.setAmountPayment(amountPaymentRound);
        double principalAmount = payment.getPrincipalAmount() + payment.getBalanceDebt();
        double principalAmountRound = RoundUtil.round(principalAmount, NEW_SCALE, BigDecimal.ROUND_CEILING);
        payment.setPrincipalAmount(principalAmountRound);
        payment.setBalanceDebt(0);
    }

    /**
     * Рассчитать сумму аннуитета на основании общепринятой формулы.
     *
     * @param ammount - общая сумма кредита
     * @param duration - продолжительность выплат по кредиту в месяцах
     * @param rate - ставка по кредиту на один месяц
     * @return сумма аннуитета
     */
    private static double calcAnnuitySums(double ammount, int duration, double rate) {
        return ((Math.pow(1 + rate, duration) * rate) / (Math.pow(1 + rate, duration) - 1)) * ammount;
    }

}
