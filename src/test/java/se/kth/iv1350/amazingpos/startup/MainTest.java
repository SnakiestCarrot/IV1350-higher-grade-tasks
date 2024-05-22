package se.kth.iv1350.amazingpos.startup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.amazingpos.startup.Main;


public class MainTest {
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        printOutBuffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printOutBuffer));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSysOut);
    }

    @Test
    public void testInitializationMessages() {
        String[] args = {};
        Main.main(args);

        String output = printOutBuffer.toString();

        assertTrue(output.contains("Initializing receipt printer... Done."), "The receipt printer's initialization message was not printed.");
        assertTrue(output.contains("Initializing accounting manager... Done."), "The accounting manager's initialization message was not printed.");
        assertTrue(output.contains("Initializing catalog handler... Done."), "The catalog handler's initialization message was not printed.");
    }
}
