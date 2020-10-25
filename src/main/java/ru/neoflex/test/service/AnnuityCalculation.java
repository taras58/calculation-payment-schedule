/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.neoflex.test.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
class AnnuityCalculation {

    /**
     * Вычисление списка платежей на основании входных параметров
     *
     * @param parameters - исходные данные по кредиту
     * @return - список платежей
     */
    static List<Payment> calc(LoanParameters parameters) {
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
        for (int index = 0; index < parameters.getLoanTerm(); index++) {
            calendar.add(Calendar.MONTH, 1);
            Payment payment = new Payment();
            payment.setPaymentNumber(index + 1);
            payment.setPaymentDate(calendar.getTime());
            payment.setAmountPayment(annuity);
            double interestAmount = RoundUtil.round(previous.getBalanceDebt() * monthlyRate, 2, BigDecimal.ROUND_HALF_UP);
            payment.setInterestAmount(interestAmount);
            double principalAmount = RoundUtil.round(annuity - payment.getInterestAmount(), 2, BigDecimal.ROUND_CEILING);
            payment.setPrincipalAmount(principalAmount);
            double balanceDebt = RoundUtil.round(previous.getBalanceDebt() - payment.getPrincipalAmount(), 2, BigDecimal.ROUND_HALF_UP);
            payment.setBalanceDebt(balanceDebt);
            // Расчет последнего платежа по кредиту
            if (index == parameters.getLoanTerm() - 1 && payment.getBalanceDebt() > 0) {
                double amountPayment = RoundUtil.round(payment.getAmountPayment() + payment.getBalanceDebt(), 2, BigDecimal.ROUND_HALF_UP);
                payment.setAmountPayment(amountPayment);
                principalAmount = RoundUtil.round(payment.getPrincipalAmount() + payment.getBalanceDebt(), 2, BigDecimal.ROUND_CEILING);
                payment.setPrincipalAmount(principalAmount);
                payment.setBalanceDebt(0);
            }
            previous = payment;
            payments.add(payment);
        }

        return payments;

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
        double sum;
        sum = ((Math.pow(1 + rate, duration) * rate) / (Math.pow(1 + rate, duration) - 1)) * ammount;
        return RoundUtil.round(sum, 2, BigDecimal.ROUND_HALF_UP);
    }

}
