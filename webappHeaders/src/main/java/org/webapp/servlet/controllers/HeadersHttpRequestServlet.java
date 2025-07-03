package org.webapp.servlet.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/header-request")
public class HeadersHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String methodHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ip = req.getLocalAddr();
        String ipClient = req.getRemoteAddr();
        int port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url = scheme+"://"+host+contextPath+servletPath;
        String url2 = scheme+"://"+ip+":"+port+contextPath+servletPath;
        Enumeration<String> headerNames = req.getHeaderNames();

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("     <head>");
            out.println("         <meta charset=\"UTF-8\">");
            out.println("         <title>HEADERS</title>");
            out.println("     </head>");
            out.println("     <body>");
            out.println("       <ul>");
            out.println("           <li>" + methodHttp + "</li>");
            out.println("           <li>" + requestUri + "</li>");
            out.println("           <li>" + requestUrl + "</li>");
            out.println("           <li>" + contextPath + "</li>");
            out.println("           <li>" + servletPath + "</li>");
            out.println("           <li>" + ip + "</li>");
            out.println("           <li>" + ipClient + "</li>");
            out.println("           <li>" + port + "</li>");
            out.println("           <li>" + scheme + "</li>");
            out.println("           <li>" + host + "</li>");
            out.println("           <li>" + url + "</li>");
            out.println("           <li>" + url2 + "</li>");
            while (headerNames.hasMoreElements()){
                String header = headerNames.nextElement();
                out.println("           <li>" + header + ": " + req.getHeader(header) + "</li>");
            }
            out.println("       </ul>");
            out.println("     </body>");
            out.println("</html>");

        }
    }
}
