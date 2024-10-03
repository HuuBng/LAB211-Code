package LaptopRAM_Management;

import java.util.ArrayList;

import static LaptopRAM_Management.Tool.exitChoice;
import static LaptopRAM_Management.Tool.int_menu;

public class RAMManagementSystem {

    public static void main(String[] args) {

        RAMList ramList = new RAMList();

        String moduleFile = "src/RAM_data.txt";
        ramList.loadRAMModules(moduleFile);
        String RAMModules = "src/RAMModules.dat";
        ramList.loadFromFile(RAMModules);

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
                    if (ramList.rList.isEmpty()) {
                        System.out.println("Empty list");
                        break;
                    }
                    do {
                        switch (int_menu("Search by TYPE", "Search by BUS", "Search by BRAND")) {
                            case 1:
                                ramList.searchByType();
                                break;
                            case 2:
                                ramList.searchByBus();
                                break;
                            case 3:
                                ramList.searchByBrand();
                                break;
                            default:
                                System.out.println("ERROR: Please enter a valid choice");
                        }
                    } while (exitChoice("Search again", "Exit"));
                    System.out.println("Exiting!!!");
                    break;
                case 3:
                    do {
                        ramList.updateProduct();
                    } while (exitChoice("Update another", "Exit"));
                    System.out.println("Exiting!!!");
                    break;
                case 4:
                    do {
                        ramList.deleteProduct();
                    } while (exitChoice("Delete another", "Exit"));
                    break;
                case 5:
                    do {
                        ramList.displayAllItem();
                    } while (exitChoice("Display again", "Exit"));
                    break;
                case 6:
                    do {
                        ramList.saveToFile(RAMModules);
                    } while (exitChoice("Save again", "Exit"));
                    break;
                case 7:
                    System.out.println("Quitting program!!!");
                    break;
                default:
                    System.out.println("Please enter a valid option");
            }

        } while (choice != opts.size());

    }
}
