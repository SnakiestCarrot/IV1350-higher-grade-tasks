package se.kth.iv1350.amazingpos.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.amazingpos.model.FinalSaleDTO;
import se.kth.iv1350.amazingpos.model.Sale;

public class ReceiptPrinterTest {
    private Sale testSaleInstance;
    private ReceiptPrinter testPrinter;
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        printOutBuffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printOutBuffer));
        testPrinter = new ReceiptPrinter();
        testSaleInstance = new Sale();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSysOut);
        testSaleInstance = null;
        testPrinter = null;
        printOutBuffer = null;
    }

    @Test
    public void testReceiptPrintOut() {
        ArticleDTO testArticleDTO = new ArticleDTO(101, 2.99, 0.25, "Banana", "This is a banana");
        testSaleInstance.enterArticleToSale(testArticleDTO, 1);

        FinalSaleDTO testSaleDTO = testSaleInstance.createFinalSaleDTO();
        testPrinter.printReceipt(testSaleDTO);

        String output = printOutBuffer.toString();
        assertTrue(output.contains("Banana"), "Receipt should contain the item name.");
        assertTrue(output.contains("2,99"), "Receipt should contain the item price.");
        assertTrue(output.contains("------------------ Begin receipt -------------------"),
                "Receipt should start with header.");
        assertTrue(output.contains("------------------ End receipt ---------------------"),
                "Receipt should end with footer.");
    }

    @Test
    public void testReceiptPrintOutWithMultipleItems() {
        ArticleDTO testArticleDTOBanana = new ArticleDTO(101, 2.99, 0.25, "Banana", "This is a banana");
        ArticleDTO testArticleDTOOrange = new ArticleDTO(102, 1.99, 0.25, "Orange", "This is an orange");

        testSaleInstance.enterArticleToSale(testArticleDTOBanana, 1);
        testSaleInstance.enterArticleToSale(testArticleDTOOrange, 2);

        FinalSaleDTO testSaleDTO = testSaleInstance.createFinalSaleDTO();
        testPrinter.printReceipt(testSaleDTO);

        String output = printOutBuffer.toString();
        assertTrue(output.contains("Banana"), "Receipt should contain the item name 'Banana'.");
        assertTrue(output.contains("2,99"), "Receipt should contain the price for 'Banana'.");
        assertTrue(output.contains("Orange"), "Receipt should contain the item name 'Orange'.");
        assertTrue(output.contains("1,99"), "Receipt should contain the price for 'Orange'.");
        assertTrue(output.contains("2 x 1,99"), "Receipt should contain the quantity and price for 'Orange'.");
        assertTrue(output.contains("2 x 1,99"), "Receipt should contain the quantity and price for 'Orange'.");
        assertTrue(output.contains("------------------ Begin receipt -------------------"),
                "Receipt should start with header.");
        assertTrue(output.contains("------------------ End receipt ---------------------"),
                "Receipt should end with footer.");
    }
}