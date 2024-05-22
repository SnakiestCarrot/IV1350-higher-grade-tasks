package se.kth.iv1350.amazingpos.startup;

import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.integration.ArticleCatalogHandler;
import se.kth.iv1350.amazingpos.integration.ExternalAccountingManager;
import se.kth.iv1350.amazingpos.integration.ReceiptPrinter;
import se.kth.iv1350.amazingpos.view.View;


/**
 * Initializes the entire application, contains the main method
 * @author caspt
 */
public class Main {
    /**
     * The main method where the application is run within.
     * @param args main does not take any arguments from the command line.
     */
    public static void main(String[] args) {
        System.out.print("Initializing receipt printer... ");
        ReceiptPrinter printer = new ReceiptPrinter();
        System.out.println("Done.");
        System.out.print("Initializing accounting manager... ");
        ExternalAccountingManager accountingManager = ExternalAccountingManager.getExternalAccountingManager();
        System.out.println("Done.");
        System.out.print("Initializing catalog handler... ");
        ArticleCatalogHandler catalogHandler = ArticleCatalogHandler.getArticleCatalogHandler();
        System.out.println("Done.");

        
        Controller contr = new Controller(printer, accountingManager, catalogHandler);
        View view = new View(contr);
        
        view.runFakeView();
        
    }
}
