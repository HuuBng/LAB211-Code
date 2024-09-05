package BikeStoresManagementSystem;

import java.util.ArrayList;

import static BikeStoresManagementSystem.Tool.*;

public class ProductManagement {

    public static void main(String[] args) {

        ProductList prodList = new ProductList();

        /* TODO: Remove J1.L.P0024 when commit */
        prodList.loadBrand("J1.L.P0024/src/Brand.txt");
        prodList.loadCategory("J1.L.P0024/src/Category.txt");

        // Menu
        ArrayList<String> opts = new ArrayList<>();
        opts.add("Add a product");
        opts.add("Search product by name");
        opts.add("Update product");
        opts.add("Delete product");
        opts.add("Save product to file");
        opts.add("Print list from the file");
        opts.add("listAll");

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
                    prodList.createProduct();
                    break;
                case 2:
                    prodList.searchNamePartial();
                    break;
                case 3:
                    prodList.updateProduct();
                    break;
                case 7:
                    prodList.listAll();
                    break;
                default:
                    System.out.println("Exiting!!!");
            }

        } while (choice > 0 && choice <= opts.size());
    }
}
