package com.dev.vkbot.database.webcontrollers;

import com.dev.vkbot.database.personmanagment.model.Person;
import com.dev.vkbot.database.utils.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import java.io.IOException;

@WebServlet(urlPatterns = "pidoras")
public class PersonServlet extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				Session session = HibernateUtil.GetSessionFactory().getCurrentSession();
				session.beginTransaction();
				
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				if (req.getParameter("vkId") == null || req.getParameter("isVerified") == null) {
						resp.setStatus(400);
						resp.getWriter().write("ХУй тебе, параметры впиши");
						return;
				}
				
				Session session = HibernateUtil.GetSessionFactory().getCurrentSession();
				
				session.beginTransaction();
				Person person = new Person();
				person.setVkId(Long.parseLong(req.getParameter("vkId")));
				person.setIsVerified(Boolean.parseBoolean(req.getParameter("isVerified")));
				session.persist(person);
				session.getTransaction().commit();
		}
}
