package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao implements IDao<User> {

    public UserDao() {}

    @Override
    public boolean add(User obj) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO users (username, password, phone_number, gender) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, obj.getUsername());
            pstmt.setString(2, obj.getPassword());
            pstmt.setString(3, obj.getPhone());
            pstmt.setString(4, obj.getGender());
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean edit(User user) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE users SET username = ?, phone_number = ?, gender = ?, updated_at = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPhone());
            pstmt.setString(3, user.getGender());
            pstmt.setTimestamp(4, timestamp);
            pstmt.setInt(5, user.getId());
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getRecords(int currentPage, int recordsPerPage) {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            int start = currentPage * recordsPerPage - recordsPerPage;
            String query = "SELECT * FROM users ORDER BY id LIMIT ?, ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, start);
            pstmt.setInt(2, recordsPerPage);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = extractUserResultset(rs);
                users.add(user);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User extractUserResultset(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPhone(rs.getString("phone_number"));
        user.setGender(rs.getString("gender"));
        user.setCreatedAt(rs.getDate("created_at"));
        return user;
    }

    @Override
    public User findById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractUserResultset(rs);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findByName(String search, int currentPage, int recordsPerPage) {
        List<User> users = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        String query = "SELECT * FROM users WHERE username LIKE ? ORDER BY id LIMIT ?, ?";
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            Connection conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, '%' + search + '%');
            pstmt.setInt(2, start);
            pstmt.setInt(3, recordsPerPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = extractUserResultset(rs);
                users.add(user);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public int getNumberOfRows() {
        int numOfRows = 0;
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            String query = "SELECT COUNT(*) as total FROM users";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                numOfRows = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numOfRows;
    }
}
