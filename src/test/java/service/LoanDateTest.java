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
 * Тестирование даты получения кредита
 *
 * @author alexander
 */
public class LoanDateTest {

    private final String FILE_NAME_INVALID_TEST = "src/main/resources/invalid.xml";

    public LoanDateTest() {
    }

    /**
     * Проверка получения исключения при не указании даты получения
     * кредита(loanDate)
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getLoanDateNullException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanType>Аннуитетный</loanType>"
                + "</loanParameters>";

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME_INVALID_TEST)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

    /**
     * Проверка получения исключения при указании даты получения
     * кредита(loanDate) в неверном формате
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    @Test(expected = IllegalArgumentException.class)
    public void getLoanDateInvalidException() throws IOException, XMLParseException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanType>Аннуитетный</loanType>"
                + "    <loanDate>2020-11-23T03:31</loanDate>"
                + "</loanParameters>";

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME_INVALID_TEST)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

}
