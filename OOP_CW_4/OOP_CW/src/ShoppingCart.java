import java.util.ArrayList;
import java.util.Collections;

public class ShoppingCart {
    private ArrayList<Product> productList = new ArrayList<>();

    public double firstDisVal;

    public double threeItemDisVal;


    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public double totalCost() {
        double totalCost = 0;
        for (int i = 0; i < productList.size(); i++)
            totalCost = totalCost + productList.get(i).getPrice() * productList.get(i).getNoOfItems();
        return totalCost;
    }


    public double firstDiscount(boolean newAccount) {
        if (newAccount) {
            firstDisVal = totalCost() * 0.1;
        }
        System.out.println("firstDisVal - " + firstDisVal);
        return firstDisVal;

    }


    public double threeItemsDiscount() {
        threeItemDisVal = totalCost()*0.2;
        System.out.println("threeItemDisVal - " + threeItemDisVal);
        return threeItemDisVal;
    }
    public double finalTotalValue(){
        double finalTotalValue = totalCost() - firstDisVal - threeItemDisVal;
        System.out.println("finalTotalValue - "+ finalTotalValue);
        System.out.println("totalCost - " + totalCost());
        return finalTotalValue;
    }


    public ArrayList<Product> getProductList() {
        Collections.sort(productList);
        return productList;
    }


}
