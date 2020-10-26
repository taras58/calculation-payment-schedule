/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.neoflex.test.application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.management.modelmbean.XMLParseException;
import org.apache.commons.cli.*;
import ru.neoflex.test.service.CalculationPaymentsService;

/**
 *
 * @author alexander
 */
public class Application {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws javax.management.modelmbean.XMLParseException
     */
    public static void main(String[] args) throws IOException, XMLParseException {

        // Чтение входных параметров
        Options options = new Options();

        Option input = new Option("i", "input", true, "Путь к файлу в формате xml с исходными данными по кредиту");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "Путь к файлу, в который будут выводиться результаты расчета платежей");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        String inputFilePath = cmd.getOptionValue("input");
        String outputFilePath = cmd.getOptionValue("output");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        
        // Расчет платежей и сохранение их в указанный файл
        try {
            inputStream = new FileInputStream(inputFilePath);
            outputStream = new FileOutputStream(outputFilePath);
            CalculationPaymentsService.calculatePaymentPlan(inputStream, outputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

}
