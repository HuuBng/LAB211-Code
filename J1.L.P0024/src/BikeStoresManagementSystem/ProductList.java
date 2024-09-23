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
    ArrayList<Product> pfList;

    public ProductList() {
        bList = new ArrayList<>();
        cList = new ArrayList<>();
        pList = new ArrayList<>();
        pfList = new ArrayList<>();
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
            System.out.println("FileNotFound: " + filename);
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
            System.out.println("FileNotFound: " + filename);
        }
    }

    public void loadProductFromFile(String filename) {
        try (Scanner scf = new Scanner(new File(filename))) {
            while (scf.hasNext()) {
                String[] data = scf.nextLine().split(", ");
                if (data.length < 6) {
                    continue;
                }
                Product product = new Product(data[0]);
                product.setName(data[1]);
                product.setBrandID(data[2]);
                product.setCategoryID(data[3]);
                product.setYear(Integer.parseInt(data[4]));
                product.setPrice(Integer.parseInt(data[5]));
                pList.add(product);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FileNotFound " + filename);
        }
    }

    /**
     * Check for unique ID
     *
     * @param id to check
     * @return {@code true} if {@code id} is unique
     */
    public boolean checkUniqueID(String id) {
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
            id = generateCodeFromStr();
            if ((!checkUniqueID(id))) {
                System.out.println("Please enter a unique ID number");
            }
        } while (!checkUniqueID(id));
        Product prod = new Product(id);

        String name;
        do {
            name = readStr("Enter NAME");
            if (name.isEmpty()) {
                System.out.println("Please enter a valid NAME");
            }
        } while (name.isEmpty());
        prod.setName(name);

        String brandID;
        do {
            brandID = readStr("Enter BRAND_ID").toUpperCase();
            if (!checkBrandID(brandID)) {
                System.out.println("ERROR: Please enter a valid BRAND_ID");
            }
        } while (!checkBrandID(brandID));
        prod.setBrandID(brandID);

        String categoryID;
        do {
            categoryID = readStr("Enter CATEGORY_ID").toUpperCase();
            if (!checkCategoryID(categoryID)) {
                System.out.println("ERROR: Please enter a valid CATEGORY_ID");
            }
        } while (!checkCategoryID(categoryID));
        prod.setCategoryID(categoryID);

        int year;
        do {
            year = readInt("Enter YEAR");
            if (year < 1000 || year > 9999) {
                System.out.println("ERROR: Please enter a valid YEAR");
            }
        } while (year < 1000 || year > 9999);
        prod.setYear(year);

        int price;
        do {
            price = readInt("Enter PRICE");
            if (price < 0) {
                System.out.println("ERROR: Please enter a valid PRICE");
            }
        } while (price < 0);
        prod.setPrice(price);

        pList.add(prod);
    }

    public void searchNamePartial() {
        String partialName = readStr("Enter name to search").toLowerCase();

        ArrayList<Product> tmp = new ArrayList<>();

        for (Product x : pList) {
            if (x.getName().toLowerCase().contains(partialName)) {
                tmp.add(x);
            }
        }

        if (tmp.isEmpty()) {
            System.out.println("ERROR: There is no Product in list");
            return;
        }

        tmp.sort(Comparator.comparing(Product::getYear));
        tmp.forEach(System.out::println);
    }

    public void updateProduct() {
        String id = generateCodeFromStr();
        if (checkUniqueID(id)) {
            System.out.println("ERROR: Product ID does not exist");
            return;
        }

        int index = -1;
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getId().equals(id)) {
                index = i;
            }
        }

        if (index == -1) {
            System.out.println("ERROR: Product ID not found in list but pass check");
            return;
        }

        String name = readStr("Enter NAME");
        if (!name.isEmpty()) {
            pList.get(index).setName(name);
            System.out.println("Update SUCCESS");
        }

        String brandID = readStr("Enter BRAND_ID").toUpperCase();
        if (!brandID.isEmpty()) {
            if (checkBrandID(brandID)) {
                pList.get(index).setBrandID(brandID);
                System.out.println("Update SUCCESS");
            } else {
                System.out.println("ERROR: Invalid BRAND_ID");
                System.out.println("Update FAIL");
            }
        }

        String categoryID = readStr("Enter CATEGORY_ID").toUpperCase();
        if (!categoryID.isEmpty()) {
            if (checkCategoryID(categoryID)) {
                pList.get(index).setCategoryID(categoryID);
                System.out.println("Update SUCCESS");
            } else {
                System.out.println("ERROR: Invalid CATEGORY_ID");
                System.out.println("Update FAIL");
            }
        }

        String year = readStr("Enter YEAR");
        if (!year.isEmpty()) {
            int yr = Integer.parseInt(year);
            if (yr > 1000 && yr < 10000) {
                pList.get(index).setYear(yr);
                System.out.println("Update SUCCESS");
            } else {
                System.out.println("ERROR: Invalid YEAR");
                System.out.println("Update FAIL");
            }
        }

        String price = readStr("Enter PRICE");
        if (!price.isEmpty()) {
            int pr = (int) Math.floor(Double.parseDouble(price));
            if (pr > 0) {
                pList.get(index).setPrice(pr);
                System.out.println("Update SUCCESS");
            } else {
                System.out.println("ERROR: Invalid PRICE");
                System.out.println("Update FAIL");
            }
        }

    }

    public void deleteProduct() {
        String id = generateCodeFromStr();
        if (checkUniqueID(id)) {
            System.out.println("ERROR: Product ID does not exist");
            return;
        }

        int index = -1;
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getId().equals(id)) {
                index = i;
            }
        }

        if (index == -1) {
            System.out.println("ERROR: Product ID not found in list but pass check");
            return;
        }

        String choice = readStr("Are you sure? (y/n)").toLowerCase();
        if (choice.equalsIgnoreCase("y")) {
            pList.remove(index);
            System.out.println("Remove SUCCESS");
        } else if (choice.equalsIgnoreCase("n")) {
            System.out.println("Remove ABORTED");
        } else {
            System.out.println("ERROR: Invalid choice");
            System.out.println("Remove FAIL");
        }

    }

    public void saveProductsToFile(String filename) {

        if (pList.isEmpty()) {
            System.out.println("Product list is empty");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            for (Product x : pList) {
                bw.write(x.toString());
                bw.newLine();
            }

            System.out.println("Done!!!");
        } catch (IOException e) {
            System.out.println("ERROR: Writing file: " + filename);
        } catch (Exception e) {
            System.out.println("ERROR: " + filename + " " + e);
        }
    }

    public void loadProduct(String filename) {
        pfList = new ArrayList<>();

        try (Scanner scf = new Scanner(new File(filename))) {

            while (scf.hasNext()) {
                String[] data = scf.nextLine().split(", ");
                if (data.length < 6) {
                    continue;
                }
                Product product = new Product(data[0]);
                product.setName(data[1]);
                product.setBrandID(data[2]);
                product.setCategoryID(data[3]);
                product.setYear(Integer.parseInt(data[4]));
                product.setPrice(Integer.parseInt(data[5]));
                pfList.add(product);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FileNotFound " + filename);
        }
    }

    public String toString(Product prod) {
        String brandName = "";
        for (Brand x : bList) {
            if (x.getBrandID().equals(prod.getBrandID())) {
                brandName = x.getBrandName();
                break;
            }
        }

        String categoryName = "";
        for (Category x : cList) {
            if (x.getCategoryID().equals(prod.getCategoryID())) {
                categoryName = x.getCategoryName();
                break;
            }
        }

        return prod.getId() + ", " + prod.getName() + ", " + brandName + ", " + categoryName + ", " + prod.getYear() + ", " + prod.getPrice();
    }

    public void displayProductInfo(String filename) {
        loadProduct(filename);

        pfList.sort((o1, o2) -> {
            if (o2.getPrice() - o1.getPrice() != 0) {
                return (int) (o2.getPrice() - o1.getPrice());
            }
            return o1.getName().compareTo(o2.getName());
        });

        for (Product x : pfList) {
            System.out.println(toString(x));
        }

    }
}
