package org.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webapp.servlet.models.UserM;
import org.webapp.servlet.repositories.Repository;
import org.webapp.servlet.repositories.UserRepositoryImpl;
import org.webapp.servlet.services.LoginService;
import org.webapp.servlet.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@WebServlet({"/login", "/login.jsp"})
public class LoginServlet extends HttpServlet {
    private static String username;
    private static String password;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);

        if (!username.isPresent())
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Repository<UserM> userM = new UserRepositoryImpl();
        UserM user;
        Map<String, String> errors = new HashMap<>();

        String email =  req.getParameter("email");
        if(email == null || email.isBlank()){
            errors.put("email", "Email is empty");
        }
        String password = req.getParameter("password");
        if(password == null || password.isBlank()){
            errors.put("password", "Password is empty");
        }

        if (!errors.isEmpty()) {
            req.getSession().setAttribute("errors", errors);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        try {
            user = userM.forUnique(email, null);
            if(user != null) {
                if (user.getPassword().equals(password)) {
                    req.getSession().setAttribute("username", user.getUsername());
                    req.getSession().setAttribute("email", user.getEmail());
                    resp.sendRedirect(req.getContextPath() + "/index.jsp"); // => SUCCESS
                } else {
                    errors.put("Password", "The password is incorrect");
                    req.getSession().setAttribute("errors", errors);
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The email or the password is incorrect");
                }
            } else {
                errors.put("User", "The user not exist");
                req.getSession().setAttribute("errors", errors);
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
