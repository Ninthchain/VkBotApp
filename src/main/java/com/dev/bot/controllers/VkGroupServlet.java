package com.dev.bot.controllers;

import com.dev.bot.database.dao.Dao;
import com.dev.bot.database.model.VkGroup;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/db/vk-group"})
public class VkGroupServlet extends HttpServlet {
		private Dao<VkGroup> vkGroupDao;
		
		@Override
		public void init() throws ServletException {
				super.init();
				
		}
		
		@Override
		public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				super.service(req, resp);
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				super.doPost(req, resp);
		}
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				super.doGet(req, resp);
		}
}
