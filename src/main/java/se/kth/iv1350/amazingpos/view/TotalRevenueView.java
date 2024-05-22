package se.kth.iv1350.amazingpos.view;

import se.kth.iv1350.amazingpos.model.TotalRevenueObserver;

/**
 * Abstract class totalrevenueview that implements public interface totalrevenueobserver.
 */
public abstract class TotalRevenueView implements TotalRevenueObserver{
    double totalRevenue;

    @Override
    /**
     * Prints total revenue.
     */
    public void printRevenue (double totalCost) {
        totalRevenue = totalCost;
        showTotalIncome();
    }  

    /**
     * Method that tries to show total income-
     */
    private void showTotalIncome() {
        try {
            doShowTotalIncome();
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    /**
     * Method that shows total income.
     * @throws Exception
     */
    protected abstract void doShowTotalIncome() throws Exception;

    /**
     * Method that handles error when showing income.
     * @param e
     */
    protected abstract void handleErrors(Exception e);
}
