package BikeStoresManagementSystem;

import static BikeStoresManagementSystem.Tool.*;

public class Product {

    private final String id;
    private String name;
    private String brandID;
    private String categoryID;
    private int year;
    private double price;

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String name, String brandID, String categoryID, int year, double price) {
        this.id = id;
        this.name = name;
        this.brandID = brandID;
        this.categoryID = categoryID;
        this.year = year;
        this.price = price;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        while (name.trim().isEmpty()) {
            System.err.println("NAME must not be empty!!!");
            name = readStr("Enter NAME");
        }
        this.name = name;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
