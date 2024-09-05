package BikeStoresManagementSystem;

public class Category {

    private final String categoryID;
    private final String categoryName;

    public Category(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return categoryID + ", " + categoryName;
    }
}
