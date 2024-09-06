package LaptopRAM_Management;

import java.time.YearMonth;

public class RAMItem {

    private final String code;
    private String type;
    private String bus;
    private String brand;
    private int quantity;
    private YearMonth prodDate;
    private boolean active;

    public RAMItem(String code) {
        this.code = code;
    }

    public RAMItem(String type, String code, String bus, String brand, int quantity, YearMonth prodDate, boolean active) {
        this.type = type;
        this.code = code;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.prodDate = prodDate;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public YearMonth getProdDate() {
        return prodDate;
    }

    public void setProdDate(YearMonth prodDate) {
        this.prodDate = prodDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return code + ", " + type + ", " + bus + ", " + brand + ", " + quantity + ", " + prodDate + ", " + active;
    }
}
