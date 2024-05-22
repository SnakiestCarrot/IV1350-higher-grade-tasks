package se.kth.iv1350.amazingpos.view;

import se.kth.iv1350.amazingpos.model.Filelogger;
import se.kth.iv1350.amazingpos.model.TotalRevenueObserver;

/**
 * Subclass to TotalRevenueView.
 * Writes total revenue to a file.
 */
class TotalRevenueFileOutput extends TotalRevenueView {

    @Override
    /**
     * Method inhereted by superclass. 
     * Writes total revenue to log file.
     */
    protected void doShowTotalIncome() throws Exception {
        Filelogger logger = new Filelogger("revenue.txt");
        logger.log("Total revenue: " + totalRevenue);      
    }

    @Override
    /**
     * Method inhereted by superclass.
     * Logs any errors to log file.
     */
    protected void handleErrors(Exception e) {
        Filelogger logger = new Filelogger("errorlog.txt");
        logger.log("An error happened when trying to log the revenue");
    }
    
}
