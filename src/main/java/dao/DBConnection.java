package dao;

//<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//public class DBConnection {
//    private String jdbcURL = "jdbc:mysql://localhost:3306/petshop";
//    private String jdbcUsername = "root";
//    private String jdbcPassword = "1234";
//
//    public DBConnection() {
//    }
//
//    protected Connection getConnection() {
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
////        return connection;
//=======
//import java.sql.*;

public class DBConnection {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petshop";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 1. Dang ky Driver
            Class.forName(JDBC_DRIVER);
            // 2. Mo ket noi
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to database.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Could not connect: " + e.getMessage());
        }
        return conn;
//>>>>>>> 67c36b19a0a62a20b63d76a696aaf37301f9ec5d
    }
}
