package se.kth.iv1350.amazingpos.view;

import se.kth.iv1350.amazingpos.model.Filelogger;
import se.kth.iv1350.amazingpos.model.TotalRevenueObserver;

/**
 * Writes total revenue to a file.
 */
class TotalRevenueFileOutput extends TotalRevenueView {

    @Override
    protected void doShowTotalIncome() throws Exception {
        Filelogger logger = new Filelogger("revenue.txt");
        logger.log("Total revenue: " + totalRevenue);      
    }

    @Override
    protected void handleErrors(Exception e) {
        Filelogger logger = new Filelogger("errorlog.txt");
        logger.log("An error happened when trying to log the revenue");
    }
    
}
