package se.kth.iv1350.amazingpos.view;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.amazingpos.model.Filelogger;

public class TotalRevenueDisplayTest {
    private TotalRevenueDisplay instanceToTest;
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        instanceToTest = new TotalRevenueDisplay();

        originalSysOut = System.out;
        printOutBuffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printOutBuffer));        
    }

    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testDoShowTotalIncome() {
        instanceToTest.totalRevenue = 1234.56;

        try {
            instanceToTest.doShowTotalIncome();
            String expectedOutput = String.format("\nTotal revenue: %.2f\n", 1234.56);
            assertTrue(printOutBuffer.toString().contains(expectedOutput), "The output should match the expected output.");
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testHandleErrors() {
        Exception testException = new Exception("Test exception");

        instanceToTest.handleErrors(testException);
        String expectedOutput = "An error was made when printing the revenue";
        assertTrue(printOutBuffer.toString().contains(expectedOutput), "The error message should be printed to the display.");
    }
}
