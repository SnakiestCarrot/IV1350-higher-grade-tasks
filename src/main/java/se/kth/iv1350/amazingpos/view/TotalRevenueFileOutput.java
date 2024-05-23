package se.kth.iv1350.amazingpos.view;

import java.io.FileWriter;
import java.io.PrintStream;

import se.kth.iv1350.amazingpos.model.Filelogger;

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
        logger.logMessage("Total revenue: " + totalRevenue);      
    }

    @Override
    /**
     * Method inhereted by superclass.
     * Logs any errors to log file.
     */
    protected void handleErrors(Exception e) {
        Filelogger logger = new Filelogger("exceptions.txt");
        logger.logException(e);
    }
    
}
