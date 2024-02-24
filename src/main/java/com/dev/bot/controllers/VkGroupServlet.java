package com.dev.bot.controllers;

import com.dev.bot.database.dao.Dao;
import com.dev.bot.database.dao.VkGroupDao;
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
				List<VkGroup> groups = vkGroupDao.getEntitiesByColumnValue("vkId", Long.parseLong(req.getParameter("vkId")));
				for (VkGroup group : groups) {
					if(group.getVkId() != Long.parseLong(req.getParameter("vkId"))) {
							continue;
					}
					vkGroup = group;
					
				}
				req.setAttribute("token", vkGroup.getAccessToken());
				resp.setStatus(200);
		}
}
