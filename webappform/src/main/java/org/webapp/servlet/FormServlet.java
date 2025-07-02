package org.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/register")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String[] languages = req.getParameterValues("languages");
        String[] rol = req.getParameterValues("roles");
        String language = req.getParameter("language");
        boolean ty = req.getParameter("ty") != null
                && req.getParameter("ty").equals("on");
        String secret = req.getParameter("secret");

        List<String> e = new ArrayList<>();

        if (username == null || username.isBlank()) e.add("User Require.");
        if (password == null || password.isBlank()) e.add("password Require.");
        if (email == null || !email.contains("@")) e.add("email Require.");
        if (country == null || country.isBlank()) e.add("country Require.");
        if (languages == null || languages.length == 0) e.add("languages Require.");
        if (rol == null || rol.length == 0) e.add("rol Require.");
        if (language == null) e.add("language Require.");

        //if (ty == null || languages.length==0) e.add("User Require.");

        if (e.isEmpty()) {

            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\"UTF-8\">");
                out.println("         <title>Form Result</title>");
                out.println("     </head>");
                out.println("     <body>");
                out.println("         <h1>result:  </h1>");

                out.println("         <h4>Username: " + username + "</h4>");
                out.println("         <h4>Password: " + password + "</h4>");
                out.println("         <h4>Email: " + email + "</h4>");
                out.println("         <h4>country: " + country + "</h4>");
                out.println("         <h4>languages: </h4><ul>");

                Arrays.asList(languages).forEach(l ->
                        out.println("<li>" + l + "</li>")
                );

                out.println("         </ul><h4>Rol: </h4> <ul>");

                Arrays.asList(rol).forEach(r -> {
                    out.println("<li>" + r + "</li>");
                });

                out.println("</ul> ");

                out.println("         <h4>language:" + language + "</h4>");
                out.println("         <h4>Ty:" + ty + "</h4>");
                out.println("         <h4>Secret:" + secret + "</h4>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
//            e.forEach(er -> out.println("<li>" + er + "</li>"));
//            out.println("<a href=\"/webappform/index.jsp\"> Click </a>");
            req.setAttribute("e", e);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}

