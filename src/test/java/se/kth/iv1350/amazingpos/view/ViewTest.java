package se.kth.iv1350.amazingpos.view;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.integration.ArticleCatalogHandler;
import se.kth.iv1350.amazingpos.integration.ExternalAccountingManager;
import se.kth.iv1350.amazingpos.integration.ReceiptPrinter;
import se.kth.iv1350.amazingpos.model.Sale;

public class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() {
        ReceiptPrinter printer = new ReceiptPrinter();
        ExternalAccountingManager accountingManager = ExternalAccountingManager.getExternalAccountingManager();
        ArticleCatalogHandler catalogHandler = ArticleCatalogHandler.getArticleCatalogHandler();
        
        Controller contr = new Controller(printer, accountingManager, catalogHandler);
        
        this.instanceToTest = new View(contr);
        
        originalSysOut = System.out;
        printOutBuffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printOutBuffer));        
    }
    
    @AfterEach
    public void tearDown() {
        this.instanceToTest = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testRunFakeView() {
        instanceToTest.runFakeView();
    }

    @Test
    public void testRevenueFileOutput() {
        instanceToTest.addRevenueObserver(new TotalRevenueDisplay());
        instanceToTest.addRevenueObserver(new TotalRevenueFileOutput());
        instanceToTest.runFakeView();

        StringBuilder builder = new StringBuilder();
        String str;
        String outcome;

        try {
            BufferedReader buffer = new BufferedReader(new FileReader("revenue.txt"));
            while ((str = buffer.readLine()) != null) {
 
                builder.append(str.split(":")[0]);
            }
            outcome = builder.toString();
            buffer.close();

            assertTrue(outcome.equals("Total revenue"), "Not writing correct message to file.");
        }

        catch (FileNotFoundException exception){
            fail("FileNotFoundException.");
        }
        catch (IOException exception) {
            fail("IOEXception");
        }  
        
    }

    @Test
    public void testPrintAfterIdentifierEnteredPrint() {
        instanceToTest.runFakeView();
        
        String output = printOutBuffer.toString();

        assertTrue(output.contains("items with item id"), "The view did not print the number of items and their ID.");
        assertTrue(output.contains("Item ID:"), "View did not print the item ID");
        assertTrue(output.contains("Item name:"), "View did not print the item name.");
        assertTrue(output.contains("Item cost:"), "View did not print item cost.");
        assertTrue(output.contains("VAT:"), "View did not print VAT.");
        assertTrue(output.contains("Item description:"), "View did not print item description.");
        assertTrue(output.contains("Total cost (incl VAT):"), "View did not print total cost including VAT.");
        assertTrue(output.contains("Total VAT:"), "View did not print total VAT.");
    }

    @Test
    public void testEnterItemIdentifierErrorPrint() {
        instanceToTest.runFakeView();

        String output = printOutBuffer.toString();

        assertTrue(output.contains("Operation failed."), "Operation failed did not print.");
        assertTrue(output.contains("Error: Identifier"), "Identifer Error not printing.");
    }
}
