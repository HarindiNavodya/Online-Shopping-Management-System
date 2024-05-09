public class Electronics extends Product{
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productId,String productName,int numbersOfAvailableItems,double price, String productType,String brand,int warrantyPeriod){
        super(productId,productName,numbersOfAvailableItems,price, productType);
        this.brand=brand;
        this.warrantyPeriod=warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public String displayProduct(){
        return (getProductId()+"|"+getProductName()+"|"+ getNoOfItems()+"|"+getPrice()+"|"+getBrand()+"|"+getWarrantyPeriod()+"|"+getProductType());
    }

}
