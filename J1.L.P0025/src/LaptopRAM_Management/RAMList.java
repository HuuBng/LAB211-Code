package LaptopRAM_Management;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RAMList extends ArrayList<RAMItem> {
    ArrayList<RAMModule> modules;
    ArrayList<RAMItem> rList;

    public RAMList() {
        modules = new ArrayList<>();
        rList = new ArrayList<>();
    }

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
            System.err.println("FileNotFound: " + filename);
            throw new RuntimeException(e);
        }
    }

    public void list() {
        modules.forEach((System.out::println));
    }

}
