import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WestminsterShoppingManager {

    public static ShoppingCart productList =new ShoppingCart();

    private static Scanner input =new Scanner(System.in);

    public static void main(String[] args) {

        loadFromFile();
        while(true){
            System.out.println("1.Add Product");
            System.out.println("2.Remove Product");
            System.out.println("3.Display all Products");
            System.out.println("4.Save");
            System.out.println("5.Display GUI");
            System.out.println("0.Exit");
            System.out.println("Enter the number: ");

            int number =input.nextInt();
            switch (number){
                case 0:
                    return;
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    displayProduct();
                    break;
                case 4:
                    savefile();
                    break;
                case 5:
                    new WestminsterFrame();
                    break;
                default:
                    System.out.println("wrong input");
            }
        }

    }

    public static void addProduct() {

        System.out.println("Enter the productID");
        String ProductId =input.next();
        System.out.println("Enter the product Name");
        String ProductName =input.next();
        System.out.println("Enter the NoofItems");
        int Numberofavailableitems=input.nextInt();
        System.out.println("Enter the Price");
        double price = input.nextDouble();

        System.out.println("Enter the products want");
        System.out.println("1.Clothing");
        System.out.println("2.Electronics");
        int number =input.nextInt();
        if (number==1){
            System.out.println("Enter the size");
            String size =input.next();
            System.out.println("Enter the color");
            String color =input.next();

            Clothing clothing =new Clothing(ProductId,ProductName,Numberofavailableitems,price,"clothing",size,color);
            productList.addProduct(clothing);
        } else if (number==2){
            System.out.println("Enter the brand");
            String brand =input.next();
            System.out.println("Enter the warrentyPeriod");
            int warrentyperiod =input.nextInt();

            Electronics electronics =new Electronics(ProductId,ProductName,Numberofavailableitems,price,"electronic",brand,warrentyperiod);
            productList.addProduct(electronics);
        }else {
            System.out.println("Invalid product type");
        }
    }


    private static void displayProduct() {
        if(productList.getProductList().isEmpty()){
            System.out.println("Product cart is Empty!");
        }else {
            for (int i = 0; i < productList.getProductList().size(); i++) {
                System.out.println(productList.getProductList().get(i).displayProduct());
            }
        }
    }

    private static void deleteProduct() {
        displayProduct();

        System.out.println("Enter product ID");
        String productToDlt = input.next();

        System.out.println("Enter the products want");
        System.out.println("1.Clothing");
        System.out.println("2.Electronics");
        int number =input.nextInt();

        boolean itemDeleted = false;

        switch (number) {
            case 1:
                for (int i = 0; i < productList.getProductList().size(); i++) {
                    if (productList.getProductList().get(i).getProductType().equals("clothing") && productList.getProductList().get(i).getProductId().equals(productToDlt)){
                        System.out.println(productList.getProductList().get(i).displayProduct());
                        productList.removeProduct(productList.getProductList().get(i));
                        itemDeleted = true;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < productList.getProductList().size(); i++) {
                    if (productList.getProductList().get(i).getProductType().equals("electronic") && productList.getProductList().get(i).getProductId().equals(productToDlt)){
                        System.out.println(productList.getProductList().get(i).displayProduct());
                        productList.removeProduct(productList.getProductList().get(i));
                        itemDeleted = true;
                    }
                }
                break;
            default:
                System.out.println("Invalid product Type");
        }
        if(itemDeleted){
            System.out.println("Product Deleted Successfully!");
            System.out.println("Items remaining in the Cart"+ productList.getProductList().size());
        }else {
            System.out.println("Product Not Found!");
        }
    }

    private static void savefile() {
        try{
            FileWriter productFile = new FileWriter("SavedProducts.txt");
            for (int i = 0; i < productList.getProductList().size(); i++) {
                productFile.write(productList.getProductList().get(i).displayProduct()+"\n");
            }
            productFile.close();
            System.out.println("Products saved successfully in SavedProducts.txt");
        } catch(IOException e){
            System.out.println("An error occured!");
        }
    }

    public static void loadFromFile() {
        try {
            File savedFile = new File("SavedProducts.txt");
            Scanner fileReader = new Scanner(savedFile);
            while (fileReader.hasNextLine()) {
                String dataLine = fileReader.nextLine();
                String[] dataArray = dataLine.split("\\|");

                if(dataArray.length == 7){
                    if (dataArray[6].equals("clothing")) {
                        Clothing clothing = new Clothing(dataArray[0], dataArray[1], Integer.parseInt(dataArray[2]),Double.parseDouble(dataArray[3]) , "clothing", dataArray[4], dataArray[5]);
                        productList.addProduct(clothing);
                    } else if (dataArray[6].equals("electronic")) {
                        Electronics electronics =new Electronics(dataArray[0],dataArray[1], Integer.parseInt(dataArray[2]),Double.parseDouble(dataArray[3]) , "electronic", dataArray[4], Integer.parseInt(dataArray[5]));
                        productList.addProduct(electronics);
                    }
                }else{
                    System.out.println("Invalid file format!");
                }
            }
            System.out.println("Loaded saved products from file!");
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No previously saved products found!");
        }
    }
}

