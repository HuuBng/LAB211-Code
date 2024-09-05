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
                System.err.println("Please enter a unique ID number");
            }
        } while (!checkUniqueID(id));
        Product prod = new Product(id);

        String name;
        do {
            name = readStr("Enter NAME");
            if (name.isEmpty()) {
                System.err.println("Please enter a valid NAME");
            }
        } while (name.isEmpty());
        prod.setName(name);

        String brandID;
        do {
            brandID = readStr("Enter BRAND_ID").toUpperCase();
            if (!checkBrandID(brandID)) {
                System.err.println("Please enter a valid BRAND_ID");
            }
        } while (!checkBrandID(brandID));
        prod.setBrandID(brandID);

        String categoryID;
        do {
            categoryID = readStr("Enter CATEGORY_ID").toUpperCase();
            if (!checkCategoryID(categoryID)) {
                System.err.println("Please enter a valid CATEGORY_ID");
            }
        } while (!checkCategoryID(categoryID));
        prod.setCategoryID(categoryID);

        int year;
        do {
            year = Integer.parseInt(readStr("Enter YEAR"));
            if (year < 1000 || year > 9999) {
                System.err.println("Please enter a valid YEAR");
            }
        } while (year < 1000 || year > 9999);
        prod.setYear(year);

        double price;
        do {
            price = Double.parseDouble(readStr("Enter PRICE"));
            if (price < 0) {
                System.err.println("Please enter a valid PRICE");
            }
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
            System.err.println("There is no Product in list");
            return false;
        }

        tmp.sort(Comparator.comparing(Product::getYear));
        tmp.forEach(System.out::println);
        return true;
    }

    public void listAll() {
        System.out.println(" === bList ===");
        bList.forEach(System.out::println);

        System.out.println(" === cList ===");
        cList.forEach(System.out::println);

        System.out.println(" === pList ===");
        pList.forEach(System.out::println);
    }

    public boolean updateProduct() {
        String id = generateCodeFromStr();
        if (checkUniqueID(id)) {
            System.err.println("Product ID does not exist");
            return false;
        }

        int index = -1;
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getId().equals(id)) {
                index = i;
            }
        }

        if (index == -1) {
            System.err.println("Product ID not found in list but pass check");
            return false;
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
                System.err.println("Invalid BRAND_ID");
                System.out.println("Update FAIL");
            }
        }

        String categoryID = readStr("Enter CATEGORY_ID").toUpperCase();
        if (!categoryID.isEmpty()) {
            if (checkCategoryID(categoryID)) {
                pList.get(index).setCategoryID(categoryID);
                System.out.println("Update SUCCESS");
            } else {
                System.err.println("Invalid CATEGORY_ID");
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
                System.err.println("Invalid YEAR");
                System.out.println("Update FAIL");
            }
        }

        String price = readStr("Enter PRICE");
        if (!price.isEmpty()) {
            double pr = Double.parseDouble(price);
            if (pr > 0) {
                pList.get(index).setPrice(pr);
                System.out.println("Update SUCCESS");
            } else {
                System.err.println("Invalid PRICE");
                System.out.println("Update FAIL");
            }
        }

        return true;
    }

    public boolean deleteProduct() {
        String id = generateCodeFromStr();
        if (checkUniqueID(id)) {
            System.err.println("Product ID does not exist");
            return false;
        }

        int index = -1;
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getId().equals(id)) {
                index = i;
            }
        }

        if (index == -1) {
            System.err.println("Product ID not found in list but pass check");
            return false;
        }

        String choice = readStr("Are you sure? (y/n)").toLowerCase();
        if (choice.equalsIgnoreCase("y")) {
            pList.remove(index);
            System.out.println("Remove SUCCESS");
        } else if (choice.equalsIgnoreCase("n")) {
            System.out.println("Remove ABORTED");
        } else {
            System.err.println("Invalid choice");
            System.out.println("Remove FAIL");
        }

        return true;
    }

    public boolean saveProductsToFile(String filename) {

        if (pList.isEmpty()) {
            System.out.println("Product list is empty");
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            for (Product x : pList) {
                bw.write(x.toString());
                bw.newLine();
            }

            System.out.println("Done!!!");
            return true;
        } catch (IOException e) {
            System.err.println("Error writing file: " + filename);
        } catch (Exception e) {
            System.err.println("ErrBrandWrite: " + e);
        }
        return false;
    }

    public void loadProduct(String filename) {
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
                product.setPrice(Double.parseDouble(data[5]));
                pfList.add(product);
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFound: " + filename);
            throw new RuntimeException(e);
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

        pfList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o2.getPrice() - o1.getPrice() != 0) {
                    return (int) (o2.getPrice() - o1.getPrice());
                }
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (Product x : pfList) {
            System.out.println(toString(x));
        }

    }
}
