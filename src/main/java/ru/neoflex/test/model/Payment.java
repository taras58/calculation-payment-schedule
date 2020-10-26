/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.neoflex.test.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Модель одного платежа
 *
 * @author alexander
 */
@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlSeeAlso(ArrayList.class)
public class Payment implements Serializable {
    
    private static final int NEW_SCALE = 2;

    // Номер платежа
    private int paymentNumber;
    // Дата платежа 
    private Date paymentDate;
    // Сумма платежа
    private double amountPayment;
    // Сумма на погашение основного долга
    private double principalAmount;
    // Сумма на погашение процентов
    private double interestAmount;
    // Остаток задолженности
    private double balanceDebt;

    public Payment() {
    }

    public Payment(int paymentNumber, Date paymentDate, double amountPayment,
            double principalAmount, double interestAmount, double balanceDebt) {
        this.paymentNumber = paymentNumber;
        this.paymentDate = paymentDate;
        this.amountPayment = amountPayment;
        this.principalAmount = principalAmount;
        this.interestAmount = interestAmount;
        this.balanceDebt = balanceDebt;

    }

    /**
     * @return the paymentNumber
     */
    public int getPaymentNumber() {
        return paymentNumber;
    }

    /**
     * @param paymentNumber the paymentNumber to set
     */
    public void setPaymentNumber(int paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    /**
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @return the amountPayment
     */
    public double getAmountPayment() {
        return amountPayment;
    }

    /**
     * @param amountPayment the amountPayment to set
     */
    public void setAmountPayment(double amountPayment) {
        this.amountPayment = amountPayment;
    }

    /**
     * @return the principalAmount
     */
    public double getPrincipalAmount() {
        return principalAmount;
    }

    /**
     * @param principalAmount the principalAmount to set
     */
    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    /**
     * @return the interestAmount
     */
    public double getInterestAmount() {
        return interestAmount;
    }

    /**
     * @param interestAmount the interestAmount to set
     */
    public void setInterestAmount(double interestAmount) {
        this.interestAmount = interestAmount;
    }

    /**
     * @return the balanceDebt
     */
    public double getBalanceDebt() {
        return balanceDebt;
    }

    /**
     * @param balanceDebt the balanceDebt to set
     */
    public void setBalanceDebt(double balanceDebt) {
        this.balanceDebt = balanceDebt;
    }

}
