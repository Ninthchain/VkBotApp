package com.dev.bot.controllers;

import com.dev.bot.database.dao.Dao;
import com.dev.bot.database.dao.VkGroupDao;
import com.dev.bot.database.model.Person;
import com.dev.bot.database.model.VkGroup;
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
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				resp.setContentType("application/json");
				VkGroup vkGroup = null;
				
				if(req.getParameter("id") != null) {
						vkGroup = vkGroupDao.getEntityById(Long.parseLong(req.getParameter("id")));
						resp.setStatus(200);
						
						return;
				}
				if(req.getParameter("vkId") == null) {
						resp.getWriter().println("Invalid request");
						resp.setStatus(400);
						return;
				}
				VkGroup group = vkGroupDao.getEntitiesByColumnValue("vkId", Long.parseLong(req.getParameter("vkId"))).getFirst();
				resp.getWriter().println(getPrettyJsonOutputString(group));
				resp.setStatus(200);
		}
		private String getPrettyJsonOutputString(VkGroup group) {
				return String.format("{\"id\": %d, groupId:%d ,\"token\": \"%s\"}",
					group.getId(),
					group.getVkId(),
					group.getAccessToken());
		}
}
