package se.kth.iv1350.amazingpos.view;

class TotalRevenueDisplay extends TotalRevenueView {

    @Override
    protected void doShowTotalIncome() throws Exception {
        System.out.println("Total revenue: " + totalRevenue);
        
    }

    @Override
    protected void handleErrors(Exception e) {
        System.out.println("An error was made when printing the revenue");
    }
}
