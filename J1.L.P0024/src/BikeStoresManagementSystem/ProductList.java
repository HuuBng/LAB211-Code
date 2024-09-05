package BikeStoresManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static BikeStoresManagementSystem.Tool.*;

public class ProductList extends ArrayList<Product> {

    ArrayList<Brand> bList;
    ArrayList<Category> cList;
    ArrayList<Product> pList;

    public ProductList() {
        bList = new ArrayList<>();
        cList = new ArrayList<>();
        pList = new ArrayList<>();
    }

    public void loadBrand(String filename) {
        try (Scanner scf = new Scanner(new File(filename))) {
            
            while (scf.hasNext()) {
                String[] data = scf.nextLine().split(", ");
                if (data.length < 3) {
                    continue;
                }
                Brand brand = new Brand();
                brand.setBrandID(data[0]);
                brand.setBrandName(data[1]);
                brand.setBrandCountry(data[2]);
                bList.add(brand);
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFound: " + filename);
            throw new RuntimeException(e);
        }
    }

    public void loadCategory(String filename) {
        try (Scanner scf = new Scanner(new File(filename))) {

            while (scf.hasNext()) {
                String[] data = scf.nextLine().split(", ");
                if (data.length < 2) {
                    continue;
                }
                Category category = new Category(data[0], data[1]);
                cList.add(category);
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFound: " + filename);
            throw new RuntimeException(e);
        }
    }

    /**
     * Check for unique ID
     *
     * @param id to check
     * @return {@code true} if {@code id} is unique
     */
    public boolean checkID(String id) {
        for (Product x : pList) {
            if (x.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check for existing brandID
     *
     * @param brand to check
     * @return {@code true} if {@code brand} exists in Brand list
     */
    public boolean checkBrandID(String brand) {
        for (Brand x : bList) {
            if (x.getBrandID().equals(brand)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check for existing categoryID
     *
     * @param category to check
     * @return {@code true} if {@code category} exists in Category list
     */
    public boolean checkCategoryID(String category) {
        for (Category x : cList) {
            if (x.getCategoryID().equals(category)) {
                return true;
            }
        }
        return false;
    }

    public void createProduct() {
        String id;
        do {
            id = readStr("Enter ID");
        } while (!checkID(id));
        Product prod = new Product(id);

        prod.setName(readStr("Enter NAME"));

        String brandID;
        do {
            brandID = readStr("Enter BRAND_ID");
        } while (!checkBrandID(brandID));
        prod.setBrandID(brandID);

        String categoryID;
        do {
            categoryID = readStr("Enter CATEGORY_ID");
        } while (!checkCategoryID(categoryID));
        prod.setCategoryID(categoryID);

        int year;
        do {
            year = Integer.parseInt(readStr("Enter YEAR"));
        } while (year < 1000 || year > 9999);
        prod.setYear(year);

        double price;
        do {
            price = Double.parseDouble(readStr("Enter PRICE"));
        } while (price < 0);
        prod.setPrice(price);

        pList.add(prod);
    }

    public boolean searchNamePartial() {
        String partialName = readStr("Enter name to search").toLowerCase();

        ArrayList<Product> tmp = new ArrayList<>();

        for (Product x : pList) {
            if (x.getName().toLowerCase().contains(partialName)) {
                tmp.add(x);
            }
        }

        if (tmp.isEmpty()) {
            System.out.println("There is no Product in list");
            return false;
        }

        tmp.sort(Comparator.comparing(Product::getYear));
        tmp.forEach(System.out::println);
        return true;
    }

}
