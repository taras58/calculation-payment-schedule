/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.neoflex.test.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Входные параметры для расчета графика платежей по кредиту
 *
 * @author alexander
 */
@XmlRootElement(name = "loanParameters")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LoanParameters implements Serializable {

    // Сумма кредита
    private double loanAmount;
    // Процентная ставка по кредиту в %
    private double interestRate;
    // Срок кредита в месяцах
    private int loanTerm;
    // Дата  получения кредита
    private Date loanDate;
    // Тип кредита (Аннуитетный и дифференцированный)
    private String loanType;

    public LoanParameters() {
    }

    /**
     * @return the loanAmount
     */
    public double getLoanAmount() {
        return loanAmount;
    }

    /**
     * @param loanAmount the loanAmount to set
     */
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * @return the interestRate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * @param interestRate the interestRate to set
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * @return the loanTerm
     */
    public int getLoanTerm() {
        return loanTerm;
    }

    /**
     * @param loanTerm the loanTerm to set
     */
    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    /**
     * @return the loanDate
     */
    public Date getLoanDate() {
        return loanDate;
    }

    /**
     * @param loanDate the loanDate to set
     */
    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * @return the loanType
     */
    public String getLoanType() {
        return loanType;
    }

    /**
     * @param loanType the loanType to set
     */
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

}
