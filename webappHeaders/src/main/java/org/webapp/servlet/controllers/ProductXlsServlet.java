package org.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
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

@WebServlet({"/product.html", "/product.xls"})
public class ProductXlsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService service = new ProductoServiceImpl();
        List<Product> products = service.show();

        String servletPath = req.getServletPath();
        boolean esXCls = servletPath.endsWith(".xls");

        if (esXCls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=product.xls");
        }
        try (PrintWriter out = resp.getWriter()) {
            if(!esXCls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\"UTF-8\">");
                out.println("         <title>Show Product</title>");
                out.println("     </head>");
                out.println("     <body>");

                out.println("     <h3> Show products </h3>");
                out.println("     <a href=\"" + req.getContextPath() + "/product.xls\">  XLS </a>");
                out.println("     <a href=\"" + req.getContextPath() + "/product.json\">  JSON </a>");
            }

            out.println("       <table>");
            out.println("           <tr>");
            out.println("               <th>id</th>");
            out.println("               <th>name</th>");
            out.println("               <th>type</th>");
            out.println("               <th>price</th>");
            out.println("           </tr>");
            products.forEach(p -> {
                out.println("          <tr>");
                out.println("               <td>"+p.getId()+"</td>");
                out.println("               <td>"+p.getName()+"</td>");
                out.println("               <td>"+p.getType()+"</td>");
                out.println("               <td>"+p.getPrice()+"</td>");
                out.println("          </tr>");
            });
            out.println("       </table>");

            if(!esXCls) {
                out.println("     </body>");
                out.println("</html>");
            }
        }
    }
}
