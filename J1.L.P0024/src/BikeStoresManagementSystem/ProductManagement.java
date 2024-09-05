package BikeStoresManagementSystem;

public class ProductManagement {

    public static void main(String[] args) {

        ProductList prodList = new ProductList();

        /* TODO: Remove J1.L.P0024 when commit */
        prodList.loadBrand("J1.L.P0024/src/Brand.txt");
        prodList.loadCategory("J1.L.P0024/src/Category.txt");

    }
}
