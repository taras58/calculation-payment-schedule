/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.neoflex.test.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.management.modelmbean.XMLParseException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import ru.neoflex.test.model.LoanParameters;
import ru.neoflex.test.model.Payments;
import ru.neoflex.test.utils.AnnuityCalculation;
import ru.neoflex.test.utils.DifferentialCalculation;
import ru.neoflex.test.utils.RoundUtil;

/**
 * Сервис для расчета графика платежей
 *
 * @author alexander
 */
public class CalculationPayments {

    private static final String ANNUITY_TYPE = "аннуитетный";
    private static final String DIFFERENTIATED_TYPE = "дифференцированный";
    private static final String XML_FORMAT_INVALID = "Неверный формат xml: ";
    private static final String LOAN_AMOUNT_INVALID = "Указана неверная сумма кредита: ";
    private static final String INTEREST_RATE_INVALID = "Указана неверная процентная ставка по кредиту: ";
    private static final String LOAN_TERM_INVALID = "Указан неверный срок кредита: ";
    private static final String LOAN_TYPE_INVALID = "Неподдерживаемый тип расчета платежей : ";
    private static final String LOAN_DATE_INVALID = "Указана неверная дата получения кредита: ";

    static Payments payments = new Payments();

    /**
     * Расчет графика платежей по кредиту на основании входных параметров
     *
     * @param inputStream - данные по кредиту (LoanParameters) в формате xml
     * @param outputStream
     * @throws XMLParseException
     */
    public static void calculatePaymentPlan(InputStream inputStream, OutputStream outputStream) throws XMLParseException {

        payments.setPayments(new ArrayList<>());

        try {
            // Чтение входных параметров из InputStream и их десериализация
            JAXBContext inputContext = JAXBContext.newInstance(LoanParameters.class);
            Unmarshaller jaxbUnmarshaller = inputContext.createUnmarshaller();
            LoanParameters loanParameters = (LoanParameters) jaxbUnmarshaller.unmarshal(inputStream);

            // Проверка правильности указанной суммы кредита
            if (RoundUtil.round(loanParameters.getLoanAmount(), 2, BigDecimal.ROUND_HALF_DOWN) <= 0) {
                throw new IllegalArgumentException(LOAN_AMOUNT_INVALID + loanParameters.getLoanAmount());
            }

            // Проверка правильности указанной процентной ставки по кредиту
            if (RoundUtil.round(loanParameters.getInterestRate(), 2, BigDecimal.ROUND_HALF_DOWN) <= 0) {
                throw new IllegalArgumentException(INTEREST_RATE_INVALID + loanParameters.getInterestRate());
            }

            // Проверка правильности указанного срока кредита
            if (loanParameters.getLoanTerm() <= 0) {
                throw new IllegalArgumentException(LOAN_TERM_INVALID + loanParameters.getLoanTerm());
            }

            // Проверка правильности указанного типа расчета платежей кредита
            if (loanParameters.getLoanType() == null) {
                throw new IllegalArgumentException(LOAN_TYPE_INVALID + loanParameters.getLoanType());
            }

            // Проверка правильности указанной даты получения кредита
            if (loanParameters.getLoanDate() == null) {
                throw new IllegalArgumentException(LOAN_DATE_INVALID + loanParameters.getLoanDate());
            }

            switch (loanParameters.getLoanType().toLowerCase()) {
                case ANNUITY_TYPE:
                    // Аннуитетный асчет графика платежей по кредиту
                    payments.setPayments(AnnuityCalculation.calc(loanParameters));
                    break;
                case DIFFERENTIATED_TYPE:
                    // Дифференцированный асчет графика платежей по кредиту
                    payments.setPayments(DifferentialCalculation.calc(loanParameters));
                    break;
                default:
                    throw new IllegalArgumentException(LOAN_TYPE_INVALID + loanParameters.getLoanType());
            }

            // Сериализация результатов и их запись в OutputStream
            JAXBContext outputContext = JAXBContext.newInstance(Payments.class);
            Marshaller marshaller = outputContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(payments, outputStream);

        } catch (JAXBException e) {
            throw new XMLParseException(XML_FORMAT_INVALID + e.getMessage());
        }
    }
}
