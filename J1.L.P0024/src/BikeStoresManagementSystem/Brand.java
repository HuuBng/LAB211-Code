package BikeStoresManagementSystem;

public class Brand {

    private String brandID;
    private String brandName;
    private String brandCountry;

    public Brand() {
    }

    public Brand(String brandID, String brandName, String brandCountry) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandCountry = brandCountry;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCountry() {
        return brandCountry;
    }

    public void setBrandCountry(String brandCountry) {
        this.brandCountry = brandCountry;
    }

    @Override
    public String toString() {
        return brandID + ", " + brandName + ", " + brandCountry;
    }
}
