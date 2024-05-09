public class Clothing extends Product {
    private String size;
    private String color;

    public Clothing(String productId, String productName, int availableItems, double price,String productType, String size, String color) {
        super(productId, productName, availableItems, price, productType);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String displayProduct(){
        return (getProductId()+"|"+getProductName()+"|"+ getNoOfItems()+"|"+getPrice()+"|"+getSize()+"|"+getColor()+"|"+getProductType());
    }
}
