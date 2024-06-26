/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.kth.iv1350.amazingpos.integration;
import se.kth.iv1350.amazingpos.model.FinalSaleDTO;
import se.kth.iv1350.amazingpos.model.FinalSaleArticleDTO;
import java.time.format.DateTimeFormatter;

/**
 * ReceiptPrinter takes in SaleInformation and uses it to print a receipt at the end of the sale.
 */
public class ReceiptPrinter {

    /**
     * Method to print and format the receipt.
     * 
     * @param sale
     */
    public void printReceipt (FinalSaleDTO sale) {
        System.out.println("------------------ Begin receipt -------------------");
        System.out.println(sale.getSaleTime().format(DateTimeFormatter.ofPattern("d MMM uuuu HH:mm:ss")));
        System.out.println("");
        for (int i = 0; i < sale.getArticleList().size(); i++) {
            printArticleInReceipt(sale.getArticleList().get(i));
        }
        System.out.printf("Total: \t\t%5.2f%n", sale.getTotalCost());
        System.out.printf("VAT: \t\t%5.2f%n", sale.getTotalSaleVAT());
        if (sale.getDiscount() != 0.0) {
            System.out.printf("Discount:\t %5.2f\n",  sale.getDiscount());   
        }
        System.out.println("");
        System.out.printf("Cash: \t \t%5.2f%n", sale.getPayment());
        System.out.printf("Change: \t %5.2f\n",  sale.getChange());

        System.out.println("------------------ End receipt ---------------------");
    }

    private void printArticleInReceipt (FinalSaleArticleDTO article) {
        double quantity = article.getQuantity();
        double price = article.getPrice();
        String name = article.getName();
        double totalPriceForArticle = calculateTotalArticleCost(article);
        System.out.print(name);
        System.out.printf(" %.0f x %.2f\t", quantity, price);
        System.out.printf("%5.2f%n\n", totalPriceForArticle);
    }

    private double calculateTotalArticleCost (FinalSaleArticleDTO article) {
        return article.getPrice() * article.getQuantity();
    }
}
