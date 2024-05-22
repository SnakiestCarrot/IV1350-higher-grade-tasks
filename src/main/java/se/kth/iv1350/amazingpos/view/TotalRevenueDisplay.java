package se.kth.iv1350.amazingpos.view;

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
        System.out.println("Total revenue: " + totalRevenue);
        
    }

    @Override
    /**
     * Method inhereted by superclass.
     * Handles errors by printing an error message to the display.
     */
    protected void handleErrors(Exception e) {
        System.out.println("An error was made when printing the revenue");
    }
}
