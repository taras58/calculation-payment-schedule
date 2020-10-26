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
 * Тестирование суммы кредита
 *
 * @author alexander
 */
public class LoanAmountTest {

    private final String FILE_NAME_INVALID_TEST = "src/main/resources/invalid.xml";

    public LoanAmountTest() {
    }

    /**
     * Проверка получения исключения при не указании суммы кредита(loanAmount)
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getLoanAmountNullException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>диФференцированный</loanType>"
                + "</loanParameters>";

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME_INVALID_TEST)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

    /**
     * Проверка получения исключения при указании отрицательной суммы
     * кредита(loanAmount)
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getLoanAmountInvalidException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>-1000</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>диФференцированный</loanType>"
                + "</loanParameters>";

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME_INVALID_TEST)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

    /**
     * Проверка получения исключения при указании нулевой суммы
     * кредита(loanAmount)
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getLoanAmountZeroException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>0</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>диФференцированный</loanType>"
                + "</loanParameters>";

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME_INVALID_TEST)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

    /**
     * Проверка получения исключения при указании суммы кредита(loanAmount)
     * близкой к нулю
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getLoanSmallAmountException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>0.005</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>диФференцированный</loanType>"
                + "</loanParameters>";

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME_INVALID_TEST)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

}
