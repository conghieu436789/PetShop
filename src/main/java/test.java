import dao.ProductDao;
import model.entities.Product;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        ArrayList<Product> list = productDao.selectAllProduct();

    }
}
