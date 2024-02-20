package com.dev.website;


import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/homepage")
public class HomePageServlet extends HttpServlet {
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_OK);
				final AsyncContext ctx = request.startAsync();
				ctx.start(() -> {
						System.out.println("Running");
						ctx.complete();
				});
		}
}