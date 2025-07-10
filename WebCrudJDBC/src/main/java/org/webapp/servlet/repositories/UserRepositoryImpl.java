package org.webapp.servlet.repositories;

import org.webapp.servlet.models.UserM;
import org.webapp.servlet.services.DatabaseConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements Repository<UserM> {

    private Connection getConnection() throws SQLException {
        return DatabaseConn.getConn();
    }

    @Override
    public List<UserM> show() throws SQLException {
        List<UserM> users = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            while (rs.next()){
                UserM user = getUserM(rs);
                users.add(user);
            }

            return users;
        }
    }

    @Override
    public UserM forUnique(String email, Long i) throws SQLException {
        UserM user = null;
        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM users WHERE email = ?")) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) user = getUserM(rs);
            }
        }

        return user;
    }

    @Override
    public void save(UserM userM) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    public UserM getUserM (ResultSet rs) throws SQLException {
        UserM user = new UserM();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));

        return user;
    }
}
