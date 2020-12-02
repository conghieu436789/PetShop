package dao;

import model.entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDao implements iOrderDao{
    private final DBConnection dbConnection = new DBConnection();
    private static final String INSERT_ORDERS_SQL = "insert into orders" + "(customer_id) values" +"(?);";
    @Override
    public void insertOrder(Order order) throws SQLException {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDERS_SQL)) {
            preparedStatement.setInt(1, order.getCustomer_id());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
