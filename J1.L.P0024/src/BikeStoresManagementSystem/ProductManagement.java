package BikeStoresManagementSystem;

import java.util.ArrayList;

import static BikeStoresManagementSystem.Tool.int_menu;
import static BikeStoresManagementSystem.Tool.exitChoice;

public class ProductManagement {

    public static void main(String[] args) {

        ProductList prodList = new ProductList();

        /* TODO: Remove J1.L.P0024 when commit */
        String bFile = "J1.L.P0024/src/Brand.txt";
        String cFile = "J1.L.P0024/src/Category.txt";
        String pFile = "J1.L.P0024/src/Product.txt";

        prodList.loadBrand(bFile);
        prodList.loadCategory(cFile);

        // Menu
        ArrayList<String> opts = new ArrayList<>();
        opts.add("Add a product");
        opts.add("Search product by name");
        opts.add("Update product");
        opts.add("Delete product");
        opts.add("Save product to file");
        opts.add("Print list from the file");

        int choice;

        do {

            System.out.println("\n||===============================||");
            System.out.println("||                               ||");
            System.out.println("|| Bike Stores Management System ||");
            System.out.println("||                               ||");
            System.out.println("||===============================||");

            choice = int_menu(opts);
            System.out.println();
            switch (choice) {
                case 1:
                    do {
                        prodList.createProduct();
                    } while (exitChoice("Add another", "Exit"));
                    break;
                case 2:
                    do {
                        prodList.searchNamePartial();
                    } while (exitChoice("Search again", "Exit"));
                    break;
                case 3:
                    do {
                        prodList.updateProduct();
                    } while (exitChoice("Update another", "Exit"));
                    break;
                case 4:
                    do {
                        prodList.deleteProduct();
                    } while (exitChoice("Delete another", "Exit"));
                    break;
                case 5:
                    do {
                        prodList.saveProductsToFile(pFile);
                    } while (exitChoice("Save again", "Exit"));
                    break;
                case 6:
                    do {
                        prodList.displayProductInfo(pFile);
                    } while (exitChoice("Display again", "Exit"));
                    break;
                default:
                    System.out.println("Exiting!!!");
            }

        } while (choice > 0 && choice <= opts.size());
    }
}
