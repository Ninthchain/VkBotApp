package com.dev.bot.controllers;

import com.dev.bot.database.dao.Dao;
import com.dev.bot.database.dao.SellPointDao;
import com.dev.bot.database.model.SellPoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.api.sdk.objects.groups.Address;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "sell-point", urlPatterns = {"/db/sell-point"})
public class SellPointServlet extends HttpServlet {
		Dao<SellPoint> sellPointDao;
		Gson gson;
		@Override
		public void init() throws ServletException {
				super.init();
				sellPointDao = new SellPointDao();
				gson = new GsonBuilder().setPrettyPrinting().create();
		}
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");

				if(req.getParameter("id") != null) {
						SellPoint sellPoint = sellPointDao.getEntityById(Long.parseLong(req.getParameter("id")));
						String jsonString = gson.toJson(sellPoint);
						resp.getWriter().print(jsonString);
						resp.getWriter().flush();
						resp.setStatus(200);
						return;
				}
				if(req.getParameter("all") != null) {
						String jsonString = "";
						if(req.getParameter("city") != null && !req.getParameter("city").isEmpty()) {
									jsonString = this.gson.toJson(this.sellPointDao.getEntitiesByColumnValue("city", req.getParameter("city")));
						}
						 else {
								 jsonString = this.gson.toJson(this.sellPointDao.getAll());
						 }
						resp.getWriter().print(jsonString);
						resp.getWriter().flush();
						resp.setStatus(200);
						return;
				}

				boolean isCityParamInvalid = req.getParameter("city").isEmpty() || req.getParameter("city") == null;
				boolean isAddressParamInvalid = req.getParameter("address") == null || req.getParameter("address").isEmpty();
				if(isCityParamInvalid || isAddressParamInvalid) {
						resp.sendError(400, gson.toJson("{\"status_code\": 400, \"msg\": invalid request. City or Address param is null or empty}"));
						return;
				}

				String address = req.getParameter("address");
				SellPoint sellPoint = sellPointDao.getEntitiesByColumnValue("address", address).getFirst();
				resp.getWriter().print(gson.toJson(sellPoint));
				resp.getWriter().flush();
				resp.setStatus(200);
		}
		
}
