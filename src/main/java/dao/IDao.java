package dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    boolean add(T obj) throws SQLException;
    boolean remove(int id) throws SQLException;
    boolean edit(T id) throws SQLException;
    List<T> getRecords(int currentPage, int recordsPerPage) throws SQLException;
    List<T> findByName(String query, int currentPage, int recordsPerPage);
    T findById(int id) throws SQLException;
}
