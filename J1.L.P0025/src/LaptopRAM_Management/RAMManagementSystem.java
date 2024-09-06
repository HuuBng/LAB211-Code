package LaptopRAM_Management;

import java.util.ArrayList;

import static LaptopRAM_Management.Tool.*;

public class RAMManagementSystem {

    public static void main(String[] args) {

        RAMList ramList = new RAMList();

        String moduleFile = "J1.L.P0025/src/RAM_data.txt";
        ramList.loadRAMModules(moduleFile);

        // menu
        ArrayList<String> opts = new ArrayList<>();
        opts.add("Add item");
        opts.add("Search item");
        opts.add("Update item");
        opts.add("Delete item");
        opts.add("Show all item");
        opts.add("Store data to file");
        opts.add("Quit menu");

        int choice;

        do {

            System.out.println("\n||=======================||");
            System.out.println("||                       ||");
            System.out.println("|| RAM Management System ||");
            System.out.println("||                       ||");
            System.out.println("||=======================||");

            choice = int_menu(opts);
            System.out.println();
            switch (choice) {
                case 1:
                    do {
                        ramList.createProduct();
                    } while (exitChoice("Add another", "Exit"));
                    System.out.println("Exiting!!!");
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }

        } while (choice != opts.size());

    }
}
