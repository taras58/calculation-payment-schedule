/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.management.modelmbean.XMLParseException;
import org.junit.Test;
import ru.neoflex.test.service.CalculationPayments;

/**
 * Тестирование процентной ставки кредита
 *
 * @author alexander
 */
public class InterestRateTest {

    public InterestRateTest() {
    }

    /**
     * Проверка получения исключения при не указании процентной ставки
     * кредита(interestRate)
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getInterestRateNullException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>диФференцированный</loanType>"
                + "</loanParameters>";
        String fileName = "invalid.txt";

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPayments.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

    /**
     * Проверка получения исключения при указании отрицательной процентной
     * ставки кредита(interestRate)
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getInterestRateInvalidException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>-17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>диФференцированный</loanType>"
                + "</loanParameters>";
        String fileName = "invalid.txt";

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPayments.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

    /**
     * Проверка получения исключения при указании нулевой процентной ставки
     * кредита(interestRate)
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getInterestRateZeroException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>0</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>диФференцированный</loanType>"
                + "</loanParameters>";
        String fileName = "invalid.txt";

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPayments.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

    /**
     * Проверка получения исключения при указании процентной ставки
     * кредита(interestRate) близкой к нулю
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSmallInterestRateException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>0.005</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>диФференцированный</loanType>"
                + "</loanParameters>";
        String fileName = "invalid.txt";

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPayments.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

}
