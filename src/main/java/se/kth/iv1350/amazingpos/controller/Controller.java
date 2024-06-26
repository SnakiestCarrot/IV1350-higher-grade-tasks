package se.kth.iv1350.amazingpos.controller;

import java.util.ArrayList;

import se.kth.iv1350.amazingpos.integration.*;
import se.kth.iv1350.amazingpos.model.Filelogger;
import se.kth.iv1350.amazingpos.model.FinalSaleDTO;
import se.kth.iv1350.amazingpos.model.Sale;
import se.kth.iv1350.amazingpos.model.SaleStatusDTO;
import se.kth.iv1350.amazingpos.model.TotalRevenueObserver;


/**
 * This is the application's controller, all method calls from view go through this class.
 */
public class Controller {
    private ReceiptPrinter printer;
    private ExternalAccountingManager accountingManager;
    private ArticleCatalogHandler catalogHandler;
    private Discounter comDiscounter;
    private Sale sale;
    private ArrayList<TotalRevenueObserver> revenueObserversList = new ArrayList<TotalRevenueObserver>();

    /**
     * Initializes a new instance of the Controller class, setting up the necessary components for handling receipt printing,
     * accounting updates, and article information retrieval.
     * 
     * @param printer Takes a ReceiptPrinter instance to later be used for the printing of the receipt.
     * @param accountingManager Takes an ExternalAccountingManager instance to later update the external accounting system
     * @param catalogHandler Takes an ArticleCatalogHandler instance to fetch article information
     */
    public Controller (ReceiptPrinter printer,
            ExternalAccountingManager accountingManager,
            ArticleCatalogHandler catalogHandler) 
    {
        this.printer = printer;
        this.accountingManager = accountingManager;
        this.catalogHandler = catalogHandler;
        this.comDiscounter = new CompositeDiscounter();
    }
    
    
    /**
     * Creates instance of Sale class.
     */
    public void requestNewSale() {
        this.sale = new Sale();
        sale.addObservers(revenueObserversList);
    }

    /**
     * Saves TotalRevenueObserver to the list of observers
     * @param revenueObserver
     */
    public void addRevenueObserver(TotalRevenueObserver revenueObserver) {
        revenueObserversList.add(revenueObserver);
    }
    
    private ArticleDTO fetchArticleDTO (int identifier) throws ArticleDTONotFoundException, DatabaseFailureException {
            return catalogHandler.fetchArticleDTO(identifier);
    }

    /**
     * Method to enter article based on identifier and quantity to the sale object.
     * Returns a SaleStatusDTO for view to display information.
     * 
     * @param identifier Article Identifier entered from view
     * @param quantity Quantity of article
     * @return SaleStausDTO  
     * @throws InvalidArticleIdentifierException writes error to log file.
     * @throws DatabaseFailureException writes error to log file.
     */
    public SaleStatusDTO enterArticle (int identifier, double quantity) throws InvalidArticleIdentifierException, OperationFailedException {
        try {
            return this.sale.enterArticleToSale(fetchArticleDTO(identifier), quantity);
        }
        
        catch (ArticleDTONotFoundException exception) {
            Filelogger logger = new Filelogger("log.txt");
            logger.logException(exception);
            throw new InvalidArticleIdentifierException(exception.getInvalidIdentifier());
        }   

        catch (DatabaseFailureException exception) {
            Filelogger logger = new Filelogger("log.txt");
            logger.logException(exception);
            throw new OperationFailedException("Could not get article.", exception);
        }
        
    }

    public double getCurrentTotalSaleCost () {
        return this.sale.getTotalCost();
    }

    /**
     * Registers payment to sale. 
     * @param payment
     */
    public void registerPayment (double payment) {
        this.sale.registerFinalPayment(payment);

        accountingManager.updateAccountingSystem(this.sale.getTotalCost());


        catalogHandler.updateInventorySystem(this.sale.getArticleList());
    }

    public FinalSaleDTO getFinalSaleDTO () {
        return sale.createFinalSaleDTO();
    }

    /**
     * Tells printer to print receipt.
     */
    public void printReceipt ()  {
        this.printer.printReceipt(getFinalSaleDTO());
    }

    /**
     * Method to request a discount. Fetches the current total cost, applies a discount from the discounter.
     * Saves the new discount in the sale and returns a new FinalSaleDTO to be displayed in view.
     * @return FinalSaleDTO to be displayed in view
     */
    public FinalSaleDTO requestDiscount() {
        double newCost = comDiscounter.discountSale(this.sale.getTotalCost());
        this.sale.updateDiscount(newCost);
        return sale.createFinalSaleDTO();
    }
}
