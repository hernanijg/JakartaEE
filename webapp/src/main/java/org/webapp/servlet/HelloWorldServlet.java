package org.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-world")
public class HelloWorldServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("     <head>");
        out.print("         <meta charset=\"UTF-8\">");
        out.print("         <title>Hello World</title>");
        out.print("     </head>");
        out.print("     <body>");
        out.print("         <h1>Hello World by Servlet </h1>");
        out.print("     </body>");
        out.print("</html>");

        out.close();
    }
    @Override
    public void init() throws ServletException {
        System.out.println("🚀 HelloWorldServlet inicializado");
    }
}

