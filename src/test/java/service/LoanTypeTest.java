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
import ru.neoflex.test.service.CalculationPaymentsService;

/**
 * Тестирование типа расчета платежей кредита
 *
 * @author alexander
 */
public class LoanTypeTest {

    private final String FILE_NAME_INVALID_TEST = "src/main/resources/invalid.xml";

    public LoanTypeTest() {
    }

    /**
     * Проверка получения исключения при не указании типа расчета платежей
     * кредита(loanType)
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getLoanTypeNullException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "</loanParameters>";

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME_INVALID_TEST)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

    /**
     * Проверка получения исключения при указании неверного типа расчета
     * платежей кредита(loanType)
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getLoanTypeInvalidException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>какойтотип</loanType>"
                + "</loanParameters>";

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME_INVALID_TEST)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

    /**
     * Проверка получения исключения при указании пустого типа расчета платежей
     * кредита(loanType)
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getLoanTypeEmptyException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType></loanType>"
                + "</loanParameters>";

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME_INVALID_TEST)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }
}
