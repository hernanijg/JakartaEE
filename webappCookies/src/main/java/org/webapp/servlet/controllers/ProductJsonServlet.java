package org.webapp.servlet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webapp.servlet.models.Product;
import org.webapp.servlet.services.ProductService;
import org.webapp.servlet.services.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/product.json")
public class ProductJsonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(inputStream, Product.class);
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("     <head>");
            out.println("         <meta charset=\"UTF-8\">");
            out.println("         <title>Show Product</title>");
            out.println("     </head>");
            out.println("     <body>");

            out.println("     <h3> Show products </h3>");
            out.println("     <ul>");
            out.println("       <li>Id: " + product.getId() + "</li>");
            out.println("       <li>Name: " + product.getName() + "</li>");
            out.println("       <li>Type: " + product.getType() + "</li>");
            out.println("       <li>Price: " + product.getPrice() + "</li>");
            out.println("     </ul>");

            out.println("     <a href=\"" + req.getContextPath() + "/product.xls\">  XLS </a>");
            out.println("     <a href=\"" + req.getContextPath() + "/product.json\">  JSON </a>");
            out.println("     </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService service = new ProductoServiceImpl();
        List<Product> products = service.show();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(products);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
