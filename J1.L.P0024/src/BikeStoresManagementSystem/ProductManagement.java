package BikeStoresManagementSystem;

import java.util.ArrayList;

import static BikeStoresManagementSystem.Tool.int_menu;

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
                    prodList.createProduct();
                    break;
                case 2:
                    prodList.searchNamePartial();
                    break;
                case 3:
                    prodList.updateProduct();
                    break;
                case 4:
                    prodList.deleteProduct();
                    break;
                case 5:
                    prodList.saveProductsToFile(pFile);
                    break;
                case 6:
                    prodList.displayProductInfo(pFile);
                    break;
                default:
                    System.out.println("Exiting!!!");
            }

        } while (choice > 0 && choice <= opts.size());
    }
}
