public abstract class Product implements Comparable<Product> {

    private String productId;
    private String productName;
    private int NoOfItems;
    private double price;
    private String productType;

    public Product(String productId,String productName,int  NoOfItems,double price, String productType){
        this.productId =productId;
        this.productName =productName;
        this.NoOfItems = NoOfItems;
        this.price =price;
        this.productType = productType;
    }

    public double getPrice(){
        return price;
    }

    public String getProductId() {
        return productId;
    }

    public int getNoOfItems() {
        return NoOfItems;
    }


    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public String getBrand() {return getBrand();}
    public int getWarrantyPeriod() { return getWarrantyPeriod();}

    public String getSize() { return getSize();}

    public String getColor() {return getColor();}


    abstract String displayProduct();

    public void setNoOfItems(int NoOfItems) {
        this.NoOfItems = NoOfItems;
    }

    public int compareTo(Product product) {
        return productId.compareTo(product.productId);


    }

}