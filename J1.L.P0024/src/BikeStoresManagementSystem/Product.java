package BikeStoresManagementSystem;

public class Product {

    private final String id;
    private String name;
    private String brandID;
    private String categoryID;
    private int year;
    private int price;

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String name, String brandID, String categoryID, int year, int price) {
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

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + brandID + ", " + categoryID + ", " + year + ", " + price;
    }
}
