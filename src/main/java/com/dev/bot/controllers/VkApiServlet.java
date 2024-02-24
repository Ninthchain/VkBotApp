package com.dev.bot.controllers;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = {"/vk"})
public class VkApiServlet extends HttpServlet {
		VkApiClient vkApiClient;
		
		@Override
		public void init() throws ServletException {
				super.init();
				vkApiClient = new VkApiClient(HttpTransportClient.getInstance());
		}
		
		@Override
		public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
				super.service(req, res);
		}
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				System.out.println(req.getRequestURI());
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				super.doPost(req, resp);
		}
}
