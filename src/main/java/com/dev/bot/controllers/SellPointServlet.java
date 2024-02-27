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
						SellPoint sellPoint = this.sellPointDao.getEntityById(Long.parseLong(req.getParameter("id")));
						String jsonString = this.gson.toJson(sellPoint);
						resp.getWriter().print(jsonString);
						resp.getWriter().flush();
						resp.setStatus(200);
						return;
				}
				if(req.getParameter("all") != null) {
						String jsonString = "";
						if(this.isCityParameterValid(req)) {
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

				if(!(this.isAddressParamValid(req) || this.isCityParameterValid(req))) {
						resp.sendError(400, this.gson.toJson("{\"status_code\": 400, \"msg\": invalid request. City or Address param is null or empty}"));
						return;
				}

				String address = req.getParameter("address");
				SellPoint sellPoint = sellPointDao.getEntitiesByColumnValue("address", address).getFirst();
				resp.getWriter().print(this.gson.toJson(sellPoint));
				resp.getWriter().flush();
				resp.setStatus(200);
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!(this.isAddressParamValid(req) || this.isCityParameterValid(req))) {
			resp.sendError(400, this.gson.toJson("{\"status_code\": 400, \"msg\": invalid request. City or Address param is null or empty}"));
		}
		SellPoint sellPoint = new SellPoint();
		sellPoint.setAddress(req.getParameter("address"));
		sellPoint.setCity(req.getParameter("city"));

		if(req.getParameter("active") != null) {
			boolean isActive = Boolean.parseBoolean(req.getParameter("active"));
			sellPoint.setActive(isActive);
		}
		if(req.getParameter("sellerId") != null) {
			long sellerId = Long.parseLong(req.getParameter("sellerId"));
			sellPoint.setSellerId(sellerId);
		}
		this.sellPointDao.persist(sellPoint);
		resp.getWriter().print(this.gson.toJson(sellPoint));
		resp.getWriter().flush();
		resp.setStatus(200);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SellPoint sellPoint = null;
		if(req.getParameter("id") != null) {
			long id = Long.parseLong(req.getParameter("id"));
			sellPoint = this.sellPointDao.getEntityById(id);
		}
		if(this.isAddressParamValid(req)) {
			sellPoint = this.sellPointDao.getEntitiesByColumnValue("address", req.getParameter("address")).getFirst();
		}
		if(sellPoint == null) {
			resp.sendError(400, "err");
			return;
		}

		if(req.getParameter("active") != null) {
			boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
			sellPoint.setActive(isActive);
		}
		if(req.getParameter("sellerId") != null) {
			long sellerId = Long.parseLong(req.getParameter("sellerId"));
			sellPoint.setSellerId(sellerId);
		}

		this.sellPointDao.merge(sellPoint);
		resp.getWriter().print(this.gson.toJson(sellPoint));
		resp.getWriter().flush();
	}

	private boolean isCityParameterValid(HttpServletRequest request) {
			return (!request.getParameter("city").isEmpty() && request.getParameter("city") != null);
	}
	private boolean isAddressParamValid(HttpServletRequest request) {
			return (!request.getParameter("address").isEmpty() && request.getParameter("city") != null);
	}
}
