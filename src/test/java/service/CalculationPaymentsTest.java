/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.neoflex.test.service.CalculationPaymentsService;

public class CalculationPaymentsTest {

    private final String FILE_NAME_INVALID_TEST = "src/main/resources/invalid.xml";

    public CalculationPaymentsTest() {
    }

    /**
     * Проверка правильности расчета графика платежей по аннуитетному типу
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     */
    @Test
    public void getAnnuityPayments() throws IOException, XMLParseException, ParserConfigurationException, SAXException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>Аннуитетный</loanType>"
                + "</loanParameters>";
        String fileName = "src/main/resources/annuity.xml";
        String testFileName = "src/main/resources/annuityTest.xml";

        // Расчет платежей и сохранение их в файл
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

        // Проверка правильности вычислений
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setCoalescing(true);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc1 = db.parse(new File(fileName));
        doc1.normalizeDocument();

        Document doc2 = db.parse(new File(testFileName));

        doc2.normalizeDocument();
        Assert.assertTrue(doc1.isEqualNode(doc2));

    }

    /**
     * Проверка правильности расчета графика платежей по дифференцированному
     * типу
     *
     * @throws IOException
     * @throws javax.management.modelmbean.XMLParseException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    @Test
    public void getDifferentiatedPayments() throws IOException, XMLParseException, SAXException, ParserConfigurationException {

        String xmlString = "<loanParameters>"
                + "    <loanAmount>1000</loanAmount>"
                + "    <interestRate>17</interestRate>"
                + "    <loanTerm>12</loanTerm>"
                + "    <loanDate>2020-10-23T03:31:12</loanDate>"
                + "    <loanType>диФференцированный</loanType>"
                + "</loanParameters>";
        String fileName = "src/main/resources/differentiated.xml";
        String testFileName = "src/main/resources/differentiatedTest.xml";

        // Расчет платежей и сохранение их в файл
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

        // Проверка правильности вычислений
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setCoalescing(true);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc1 = db.parse(new File(fileName));
        doc1.normalizeDocument();

        Document doc2 = db.parse(new File(testFileName));

        doc2.normalizeDocument();
        Assert.assertTrue(doc1.isEqualNode(doc2));
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

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME_INVALID_TEST)) {
            InputStream stream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            CalculationPaymentsService.calculatePaymentPlan(stream, fos);
            fos.close();
        }

    }

}
