package org.webapp.servlet.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> show() throws SQLException;
    T forUnique(String t, Long i) throws SQLException;
    void save(T t) throws SQLException;
    void delete(Long id) throws SQLException;
}
