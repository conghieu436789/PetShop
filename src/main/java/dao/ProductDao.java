package dao;

import model.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements iProductDao {
    private final DBConnection dbConnection = new DBConnection();
    private static final String INSERT_USERS_SQL = "insert into users" + "(name,email,country) values" +"(?,?,?);";
    private static final String SELECT_USER_BY_ID = "select * from products where id =?";
    private static final String SELECT_ALL_PRODUCTS = "select * from products order by discount DESC LIMIT 3";
    private static final String SELECT_ALL_PRODUCTS_LIMIT = "select * from products";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

    public ProductDao() {

    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        try (Connection connection = DBConnection.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int category_id = rs.getInt("category_id");
                String description = rs.getString("description");
                int quantityInStock = rs.getInt("quantityInStock");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double realPrice = Math.round(price - price*discount/100);
                String img = rs.getString("img");
                Timestamp createdDate = rs.getTimestamp("created_at");
                product = new Product(id, name, category_id, description, quantityInStock, price, discount, img, realPrice, createdDate);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public ArrayList<Product> selectAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int category_id = rs.getInt("category_id");
                String description = rs.getString("description");
                int quantityInStock = rs.getInt("quantityInStock");
                double price = rs.getDouble("price");
                double discount = rs.getDouble("discount");
                double realPrice = Math.round(price - price*discount/100);
                String img = rs.getString("img");
                Timestamp createdDate = rs.getTimestamp("created_at");

                Product product = new Product(id, name, category_id, description, quantityInStock, price, discount, img, realPrice, createdDate);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
}
