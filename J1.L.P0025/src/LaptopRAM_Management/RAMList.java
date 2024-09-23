package LaptopRAM_Management;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.util.*;

import static LaptopRAM_Management.Tool.*;

public class RAMList extends ArrayList<RAMItem> {
    ArrayList<RAMModule> modules;
    ArrayList<RAMItem> rList;

    public RAMList() {
        modules = new ArrayList<>();
        rList = new ArrayList<>();
    }

    /**
     * Load RAM modules from file
     *
     * @param filename contains path to file
     */
    public void loadRAMModules(String filename) {
        try (Scanner scf = new Scanner(new File(filename))) {

            while (scf.hasNext()) {
                String[] data = scf.nextLine().split("-");
                if (data.length < 2) {
                    continue;
                }

                String[] data2 = data[1].replaceAll("[\\[\\]]", "").split(", ");

                RAMModule ramModule = new RAMModule();
                ramModule.setType(data[0]);
                ramModule.setBus(data2);
                modules.add(ramModule);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FileNotFound " + filename);
        }
    }

    /**
     * Check for unique code
     *
     * @param code to check
     * @return {@code true} if {@code code} is unique
     */
    private boolean isUniqueCode(String code) {
        for (RAMItem x : rList) {
            if (x.getCode().equals(code)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check for valid RAM module type
     *
     * @param type to check
     * @return {@code true} if {@code type} is valid
     */
    private boolean isValidType(String type) {
        for (RAMModule x : modules) {
            if (x.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check for valid RAM module bus speed
     *
     * @param type to check
     * @param bus  to check
     * @return {@code true} if {@code bus} is valid
     */
    private boolean isValidBus(String type, String bus) {
        if (!isValidType(type)) {
            System.out.println("ERROR: Invalid RAM module type");
            return false;
        }

        // Get index
        int index = -1;
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getType().equals(type)) {
                index = i;
            }
        }

        if (index == -1) {
            System.out.println("ERROR: Type not found but pass check");
        }

        for (String x : modules.get(index).getBus()) {
            if (x.equalsIgnoreCase(bus)) {
                return true;
            }
        }
        return false;
    }

    public void list() {
        modules.forEach((System.out::println));
        rList.forEach(System.out::println);
    }

    /**
     * Display available RAM module type
     */
    private void displayType() {
        System.out.println("Available RAM module types: ");
        for (RAMModule x : modules) {
            System.out.println(x.getType());
        }
    }

    /**
     * Display available BUS speed for {@code type}
     *
     * @param type to display available speed
     */
    private void displayBusForType(String type) {
        System.out.println("Available BUS speed for " + type + ":");
        for (RAMModule x : modules) {
            if (x.getType().equals(type)) {
                System.out.println(Arrays.toString(x.getBus()).replaceAll("MHz", ""));
            }
        }
    }

    /**
     * Get {@code type} of RAM module
     *
     * @return {@code type} of RAM module
     */
    private String getType() {
        displayType();
        String type;
        do {
            type = readStr("Enter RAM module TYPE").toUpperCase();
            if (!isValidType(type)) {
                System.out.println("ERROR: Please enter a valid TYPE");
            }
        } while (!isValidType(type));
        return type;
    }

    /**
     * Get position of {@code code}
     *
     * @return position of {@code code} in list. {@code -1} if code not found in list
     */
    private int getCodePos() {
        if (rList.isEmpty()) {
            return -2;
        }

        // Display CODE
        System.out.println("Available CODE: ");
        rList.forEach((tmp) -> System.out.println(tmp.getCode()));

        // Get and validate CODE
        String code;
        boolean flag = true;
        int index;
        do {
            index = -1;
            code = readStr("Enter CODE").toUpperCase();
            for (RAMItem x : rList) {
                index++;
                if (x.getCode().equals(code)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("ERROR: Please enter a valid CODE");
            }
        } while (flag);

        return index;
    }

    /**
     * Get {@code bus} of {@code type} RAM module
     *
     * @param type to get BUS speed
     * @return {@code bus} speed of {@code type}
     */
    private String getBus(String type) {
        displayBusForType(type);
        String bus;
        do {
            bus = generateBusFromStr();
            if (!isValidBus(type, bus)) {
                System.out.println("ERROR: Please enter a valid BUS speed");
            }
        } while (!isValidBus(type, bus));
        return bus;
    }

    /**
     * Create product and then store into list
     */
    public void createProduct() {

        String type = getType();

        String code;
        do {
            code = generateCodeFromStr(type);
            if (!isUniqueCode(code)) {
                System.out.println("ERROR: Please enter a unique CODE");
            }
        } while (!isUniqueCode(code));
        RAMItem item = new RAMItem(type, code);

        item.setBus(getBus(type));

        String brand;
        do {
            brand = readStr("Enter BRAND");
            if (brand.isEmpty()) {
                System.out.println("ERROR: BRAND must not be blank");
            }
        } while (brand.isEmpty());
        item.setBrand(brand);

        int quantity;
        do {
            quantity = readInt("Enter QUANTITY");
            if (quantity <= 0) {
                System.out.println("ERROR: Please enter a positive QUANTITY");
            }
        } while (quantity <= 0);
        item.setQuantity(quantity);

        int year;
        int month;
        do {
            year = readInt("Enter YEAR");
            if (year < 1000 || year > 10000) {
                System.out.println("ERROR: Please enter a valid YEAR");
            }
        } while (year < 1000 || year > 10000);

        do {
            month = readInt("Enter MONTH");
            if (month < 1 || month > 12) {
                System.out.println("ERROR: Please enter a valid MONTH");
            }
        } while (month < 1 || month > 12);

        YearMonth prodDate = YearMonth.of(year, month);
        item.setProdDate(prodDate);

        item.setActive(true);

        rList.add(item);
        System.out.println("Added!!!");
    }

    public void searchByType() {
        boolean flag = false;
        String type = getType();
        for (RAMItem x : rList) {
            if (x.getType().equals(type)) {
                System.out.println(x.getCode() + ", " + x.getType() + ", " + x.getProdDate() + ", " + x.getQuantity());
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("Not found: " + type);
        }
    }

    public void searchByBus() {
        // Import all BUS speed
        TreeSet<Integer> busSpeed = new TreeSet<>();
        for (RAMModule x : modules) {
            for (String str : x.getBus()) {
                busSpeed.add(Integer.parseInt(str.replaceAll("\\D+", "")));
            }
        }

        // Display and check BUS speed
        System.out.println("Available BUS speed");
        busSpeed.forEach((tmp) -> System.out.println(tmp + "MHz"));

        String bus;
        boolean flag = true;
        do {
            bus = generateBusFromStr();

            for (int x : busSpeed) {
                if (x == Integer.parseInt(bus.replaceAll("\\D+", ""))) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                System.out.println("ERROR: Please enter a valid BUS speed");
            }

        } while (flag);

        // Reset flag
        flag = false;

        for (RAMItem x : rList) {
            if (x.getBus().equals(bus)) {
                System.out.println(x.getCode() + ", " + x.getBus() + ", " + x.getProdDate() + ", " + x.getQuantity());
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("Not found: " + bus);
        }

    }

    public void searchByBrand() {
        boolean flag = false;
        String brand = readStr("Enter BRAND");
        for (RAMItem x : rList) {
            if (x.getBrand().equalsIgnoreCase(brand)) {
                System.out.println(x.getCode() + ", " + x.getBrand() + ", " + x.getProdDate() + ", " + x.getQuantity());
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("Not found: " + brand);
        }
    }

    public void updateProduct() {

        int index = getCodePos();

        if (index == -1) {
            System.out.println("Error getting CODE pos");
            return;
        } else if (index == -2) {
            System.out.println("Empty list");
            return;
        }

        RAMItem xRAM = rList.get(index);

        // Get and update TYPE if TYPE is not blank
        boolean typeChange = false;
        displayType();
        String oldType = xRAM.getType();
        String newType;
        do {
            newType = readStr("Enter RAM module TYPE").toUpperCase();
            if (newType.isEmpty()) {
                break;
            }
            if (!isValidType(newType)) {
                System.out.println("ERROR: Please enter a valid TYPE");
            }
        } while (!isValidType(newType));

        if (!newType.isEmpty() && isValidType(newType)) {
            xRAM.setType(newType);
            typeChange = true;
            System.out.println("Update SUCCESS");
        }

        // Generate new CODE to check for existing CODE
        String tmpCode = xRAM.getCode();
        if (typeChange) {
            String prefix = "RAM_" + oldType + "_";
            int intCode = getNumberInCode(xRAM.getCode(), prefix);
            tmpCode = generateCodeFromStr(xRAM.getType(), intCode);

            if (!isUniqueCode(tmpCode)) {
                System.out.println("ERROR: The CODE is already exist");
            }

            while (!isUniqueCode(tmpCode)) {
                tmpCode = generateCodeFromStr(newType);
                if (!isUniqueCode(tmpCode)) {
                    System.out.println("ERROR: Please enter a unique CODE");
                }
            }

            if (isUniqueCode(tmpCode)) {
                xRAM.setCode(tmpCode);
                System.out.println("Update SUCCESS");
            }
        }

        // Get and update BUS if BUS not blank
        displayBusForType(xRAM.getType());
        String newBus;
        do {
            newBus = readStr("Enter BUS speed");
            if (newBus.isEmpty()) {
                break;
            }
            newBus = newBus + "MHz";
            if (!isValidBus(xRAM.getType(), newBus)) {
                System.out.println("ERROR: Please enter a valid BUS speed");
            }
        } while (!isValidBus(xRAM.getType(), newBus));

        if (!newBus.isEmpty() && isValidBus(xRAM.getType(), newBus)) {
            xRAM.setBus(newBus);
            System.out.println("Update SUCCESS");
        }

        // Get and update BRAND if BRAND is not blank
        String newBrand = readStr("Enter BRAND");
        if (!newBrand.isEmpty()) {
            xRAM.setBrand(newBrand);
            System.out.println("Update SUCCESS");
        }

        // Get and update QUANTITY if QUANTITY is not blank
        String newQuantity;
        int intQuantity = -1;
        do {
            newQuantity = readStr("Enter QUANTITY");
            if (newQuantity.isEmpty()) {
                break;
            }
            intQuantity = readIntFromStr(newQuantity);
            if (intQuantity <= 0) {
                System.out.println("ERROR: Please enter a valid NUMBER");
            }
        } while (intQuantity <= 0);

        if (!newQuantity.isEmpty()) {
            xRAM.setQuantity(intQuantity);
            System.out.println("Update SUCCESS");
        }

        // Get and update active
        int choice = -1;
        if (!xRAM.isActive()) {
            System.out.println("Do you want to activate " + xRAM.getCode() + "?");
            choice = int_menu("Yes", "No");
            if (choice == 1) {
                xRAM.setActive(true);
                System.out.println("Update SUCCESS");
            }

        }

        rList.set(index, xRAM);
    }

    public void deleteProduct() {

        int index = getCodePos();

        if (index == -1) {
            System.out.println("Error getting CODE pos");
            return;
        } else if (index == -2) {
            System.out.println("Empty list");
            return;
        }

        RAMItem xRAM = rList.get(index);

        System.out.println("Do you want to DELETE " + xRAM.getCode() + "?");
        int choice = int_menu("Yes", "No");
        if (choice == 1) {
            xRAM.setActive(false);
            System.out.println("DELETE success");
        } else {
            System.out.println("DELETE aborted");
        }

        rList.set(index, xRAM);
    }

    public void displayAllItem() {
        rList.sort((o1, o2) -> {
            int int_o1 = Integer.parseInt(o1.getBus().replaceAll("\\D+", ""));
            int in_o2 = Integer.parseInt(o2.getBus().replaceAll("\\D+", ""));
            if (o1.getType().compareTo(o2.getType()) != 0) {
                return o1.getType().compareTo(o2.getType());
            } else if (int_o1 - in_o2 != 0) {
                return int_o1 - in_o2;
            } else {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        });

        for (RAMItem x : rList) {
            if (x.isActive()) {
                System.out.println(x);
            }
        }
    }

    public void saveToFile(String filename) {

        if (rList.isEmpty()) {
            System.out.println("Empty list");
        }

        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            output.writeObject(rList);
            System.out.println("Saved!!!");
        } catch (IOException e) {
            System.out.println("ERROR: Saving file " + filename);
        }
    }

    public void loadFromFile(String filename) {
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            rList = (ArrayList<RAMItem>) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR: Reading file " + filename);
        }
    }
}
