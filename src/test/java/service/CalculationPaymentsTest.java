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

public class CalculationPaymentsTest {

    public CalculationPaymentsTest() {
    }

    /**
     * Проверка правильности расчета графика платежей по аннуитетному типу
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test
    public void getAnnuityPayments() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>Аннуитетный</loanType>"
                + "</loanParameters>";
        String fileName = "annuity.txt";

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPayments.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

    /**
     * Проверка правильности расчета графика платежей по дифференцированному
     * типу
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test
    public void getDifferentiatedPayments() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>диФференцированный</loanType>"
                + "</loanParameters>";
        String fileName = "differentiated.txt";

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPayments.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

    /**
     * Проверка получения исключения при неверном формате xml
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = XMLParseException.class)
    public void getXmlFormatInvalidException() throws IOException, XMLParseException {

        String xmlString = "<parameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>диФференцированный</loanType>"
                + "</parameters>";
        String fileName = "invalid.txt";

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPayments.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

}
