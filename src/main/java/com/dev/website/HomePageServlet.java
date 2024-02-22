package com.dev.website;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/home"}, asyncSupported = true)
public class HomePageServlet extends HttpServlet {
		@Override
		public void init() throws ServletException {
				super.init();
		}
		
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				super.service(req, resp);
		}
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_OK);
				System.out.println("Running");
		}
}