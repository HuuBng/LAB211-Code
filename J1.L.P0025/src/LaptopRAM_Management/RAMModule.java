package LaptopRAM_Management;

import java.util.Arrays;

public class RAMModule {

    private String type;
    private String[] bus;

    public RAMModule(String type, String[] bus) {
        this.type = type;
        this.bus = bus;
    }

    public RAMModule() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getBus() {
        return bus;
    }

    public void setBus(String[] bus) {
        this.bus = bus;
    }

    @Override
    public String toString() {
        return type + "-" + Arrays.toString(bus);
    }

}
