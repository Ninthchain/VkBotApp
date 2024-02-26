package com.dev.bot.controllers;

import com.dev.bot.database.dao.Dao;
import com.dev.bot.database.dao.VkGroupDao;
import com.dev.bot.database.model.Person;
import com.dev.bot.database.model.VkGroup;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/db/vk-group"})
public class VkGroupServlet extends HttpServlet {
		private Dao<VkGroup> vkGroupDao;
		
		@Override
		public void init() throws ServletException {
				super.init();
				vkGroupDao = new VkGroupDao();
		}
		
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		}
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				VkGroup vkGroup = null;
				
				if(request.getParameter("id") != null) {
						vkGroup = vkGroupDao.getEntityById(Long.parseLong(request.getParameter("id")));
						response.getWriter().print(new Gson().toJson(vkGroup));
						response.setStatus(200);
						return;
				}
				if(request.getParameter("vkId") == null) {
						response.getWriter().println("Invalid request");
						
//						response.setStatus(400);
//						return;
				}
				vkGroup = vkGroupDao.getEntitiesByColumnValue("vkId", Long.parseLong(request.getParameter("vkId"))).getFirst();
				response.getWriter().print(new Gson().toJson(vkGroup));
				response.getWriter().flush();
				response.setStatus(200);
		}
		
}
