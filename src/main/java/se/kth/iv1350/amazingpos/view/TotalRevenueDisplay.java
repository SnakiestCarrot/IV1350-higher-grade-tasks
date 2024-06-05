package se.kth.iv1350.amazingpos.view;

import se.kth.iv1350.amazingpos.model.Filelogger;

/**
 * Subclass to the class TotalRevenueView that prints total revenue to the display.
 */
class TotalRevenueDisplay extends TotalRevenueView {

    @Override
    /**
     * Method inhereted by superclass.
     * Prints revenue to display.
     */
    protected void doShowTotalIncome() throws Exception {
        System.out.printf("\nTotal revenue: %.2f\n" , totalRevenue);
        
    }

    @Override
    /**
     * Method inhereted by superclass.
     * Handles errors by printing an error message to the display.
     */
    protected void handleErrors(Exception e) {
        System.out.println("An error was made when printing the revenue");
        Filelogger logger = new Filelogger("exceptions.txt");
        logger.logException(e);
    }
}
