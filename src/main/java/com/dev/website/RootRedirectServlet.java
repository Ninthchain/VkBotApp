package com.dev.website;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class RootRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Huy");
        ServletContext servletContext = request.getServletContext();
        String servletUriPath = request.getParameter("servlet-uri");
        if (servletUriPath == null) {
            servletUriPath = "/home";
        } else if (servletContext.getServletRegistration(servletUriPath) == null) {
            servletUriPath = "/not-found";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(servletUriPath);

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {

            throw new RuntimeException(e);
        }
    }
}
