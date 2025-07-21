package ProjectScenario;
import java.util.ArrayList;
import java.util.Scanner;

//"Product" is a class to store the details of the products
class Product {   
	private int productID; 
	private String productName;
	private int quantity;
	private double price;
	private int salesQuantity;
	private double totalRevenue;
	
	// Constructor
	public  Product (int id, String name, double cost, int qty) { 
	 productID = id;
	 productName = name;
	 price = cost;
	 quantity = qty;
	 salesQuantity = 0;
	 totalRevenue = 0.0;
		
	}
	// Getter methods to access the private details
	public int getProductID() {
		return productID;
	}
	public String getProductName() {
		return productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	public int getSalesQuantity() {
	        return salesQuantity;
	}

	public double getTotalRevenue() {
	        return totalRevenue;
	}

	public int updateQty(int amount) {
		return quantity += amount;
	}
	public boolean recordSale(int quantitySold) {
		if (quantitySold <= quantity) {
			quantity = quantity-quantitySold;
			salesQuantity = salesQuantity + quantitySold; //for tracking quantity sold
			totalRevenue += quantitySold * price; //for total revenue
			return true;
		}else {
			return false;
		}
	}
	// Display product details
	void display () { 
		System.out.println("Product ID: "+ productID + " Product Name: " +productName +" Quantity: "+quantity+" Price: "+price);
		
	}
	void displayUpdateInventory() {
		System.out.println("Product ID: "+ productID + " Product Name: " +productName);
	}
		
	
	// Display sales information
	void displaySales() {
		 System.out.println("Product ID: " + productID + ", Name: " + productName +
                 ", Sales Quantity: " + salesQuantity + ", Total sales: £" + totalRevenue);
	}
}

public class Inventorymanagementsystem {
	static ArrayList<Product> products = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
	
    //Product finder 
	public static Product findProductByID(int id) {
 	   for (int i = 0; i<products.size(); i++) {
        	Product p = products.get(i);
        	
        	if (p.getProductID() == id) {
        		return p;
        	}
        }
 	   return null;
    }
	//1. Adding New product
	public static void addNewProduct() {
		System.out.print("Enter Product ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		if (findProductByID(id)!=null){
			System.out.println("Product ID: " + id + " is already in database.");
		return;
		}
		 System.out.println("Enter Product Name: ");
	     String name = scanner.nextLine();

	     System.out.println("Enter Price: ");
	     double price = scanner.nextDouble();

	     System.out.println("Enter Quantity: ");
	     int quantity = scanner.nextInt();
	     
	     Product newProduct = new Product (id, name, price, quantity);
	     products.add(newProduct);
	     
	     System.out.println("Product Added!");
	}
	//2. updating the inventory
	public static void updateInventory() {
		//to view the listed products
		if (products.isEmpty()) {
			System.out.println("No products in inventory");
			return;
		}
		for (int i = 0; i<products.size(); i++) {
        	Product p = products.get(i);
        	p.displayUpdateInventory();
		}
	
		System.out.print("Enter Product ID to Update: ");
		int id = scanner.nextInt();
		Product product = findProductByID(id);
		
		if (product == null) {
			System.out.println("Product not found");
			return;
		}
		
		System.out.println("Current Stock: "+product.getQuantity());
		System.out.println("Enter quantity to add or negative number to subtract: ");
		int amount = scanner.nextInt();
		if (amount < 0 && Math.abs(amount) > product.getQuantity()) {
			System.out.println("Not enough stock to update");
		} else {
			product.updateQty(amount);
			System.out.println("Stock updated. New stock: "+ product.getQuantity());
		}
	}
	//3. recording sales
	
	public static void recordSales() {
		//to view the listed products
		if (products.isEmpty()) {
			System.out.println("No products in inventory");
			return;
		}
		for (int i = 0; i<products.size(); i++) {
        	Product p = products.get(i);
        	p.displayUpdateInventory();
		}
		//Start using the sale input
		System.out.println("Enter product ID of sale: ");
		int id = scanner.nextInt();
		Product product = findProductByID(id);
		if (product == null) {
			System.out.println("Product not found");
			return;
		}
		
		System.out.println("Enter quantity sold: ");
		int quantitySold = scanner.nextInt();
		if (product.recordSale(quantitySold)){
			System.out.println("Sale recorded successfully!");
		} else {
			//If there isn't enough stock, display "Insufficient stock"
			System.out.println("Insufficient stock");
		
		}
		
	} 
		
	//4. Generate Sale Report
	public static void salesReport() {
		System.out.println("=====Sale Report=====");
		double totalRevenue =0.0;
		
		 for (int i = 0; i<products.size(); i++) {
	        	Product p = products.get(i);
	        	System.out.println("=======================");
	        	p.displaySales();
	        	totalRevenue += p.getTotalRevenue();
		 }
		 System.out.println("=== Overall Revenue ===");
		    System.out.printf("Total Revenue: %.2f", totalRevenue);  //Display total Revenue in 2 decimal points
	}
	//5. View Inventory
	
	public static void viewInventory() {
		if (products.isEmpty()) {
			System.out.println("No products in inventory");
			return;
		}
		for (int i = 0; i<products.size(); i++) {
        	Product p = products.get(i);
        	p.display();
		}
	}
    public static void main(String[] args) {
    	
        int choice;
        
        do {
        	//Inventory management system menu
            System.out.println("\n******Inventory Management System******");
            System.out.println("1. Add New Product");
            System.out.println("2. Update Inventory");
            System.out.println("3. Record Sales");
            System.out.println("4. Generate Sale Report");
            System.out.println("5. View Inventory");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                	// Add new inventory product
                    addNewProduct();
                    break;
                case 2:
                	// Update the inventory product
                    updateInventory();
                    break;
                case 3:
                	// Record the product sales
                    recordSales();
                    break;
                case 4:
                	// View sales, and total revenue
                    salesReport();
                    break;
                case 5:
                	//view inventory, available stocks
                    viewInventory();
                    break;
                case 6:
                	//Exit from the menu
                    System.out.println("Exited the program");
                    break;
                default:
                	//If the user input is not from 1 to 6, then print "Invalid choice. Please try again."
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6); // Continue until the user chooses 6 (Exit)
        
        scanner.close(); //closing the scanner
    }
}