package se.kth.iv1350.amazingpos.view;

import se.kth.iv1350.amazingpos.model.TotalRevenueObserver;

public abstract class TotalRevenueView implements TotalRevenueObserver{
    double totalRevenue;

    @Override
    public void printRevenue (double totalCost) {
        totalRevenue = totalCost;
        showTotalIncome();
    }  

    private void showTotalIncome() {
        try {
            doShowTotalIncome();
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    protected abstract void doShowTotalIncome() throws Exception;

    protected abstract void handleErrors(Exception e);
}
