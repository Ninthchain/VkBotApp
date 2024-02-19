package com.dev.website;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class RootRedirectServlet extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) {
				final AsyncContext ctx = request.getAsyncContext();
				ctx.start(() -> {
						String homepageServletPath = request.getParameter("page");
						if (homepageServletPath == null) {
								homepageServletPath = "/home";
						}
						
						ServletContext servletContext = this.getServletContext();
						RequestDispatcher dispatcher = servletContext.getRequestDispatcher(homepageServletPath);
						
						try {
								dispatcher.forward(request, response);
						} catch (ServletException | IOException e) {
								throw new RuntimeException(e);
						}
				});
				
		}
}
