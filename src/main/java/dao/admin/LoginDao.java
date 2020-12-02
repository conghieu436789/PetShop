package dao.admin;

import dao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    private final DBConnection dbConn;

    public LoginDao(DBConnection dbConn) {
        this.dbConn = dbConn;
    }

    //isLogged
    public boolean checkLogin(String username, String password) {
        String query = "SELECT username, password FROM users WHERE username = ? AND password = ?";
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            Connection conn = dbConn.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
