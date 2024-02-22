package com.dev.vkbot.database.webcontrollers;

import com.dev.vkbot.database.personmanagment.dao.PersonDao;
import com.dev.vkbot.database.personmanagment.model.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet()
public class PersonServlet extends HttpServlet {
		private PersonDao personDao;
		
		@Override
		public void init() throws ServletException {
				super.init();
				personDao = new PersonDao();
		}
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				if (request.getParameter("all") != null) {
						for (Person person : personDao.getAllPersons()) {
								response.getWriter().println(person.getId());
								response.getWriter().println(person.getVkId());
								response.getWriter().println(person.getIsVerified());
						}
						return;
						
				}
				
				Person person = null;
				if (request.getParameter("id") == null) {
						if (this.isParametersValid(request, response)) {
								person = personDao.GetPerson(Long.parseLong(request.getParameter("vkId")), true);
						}
				} else {
						person = personDao.GetPerson(Long.parseLong(request.getParameter("id")));
				}
				
				if (person == null)
						return;
				
				response.getWriter().println(person.getId());
				response.getWriter().println(person.getVkId());
				response.getWriter().println(person.getIsVerified());
		}
		
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				if (this.isParametersValid(request, response)) {
						
						return;
				}
				
				Person person = new Person();
				person.setVkId(Long.parseLong(request.getParameter("vkId")));
				person.setIsVerified(Boolean.parseBoolean(request.getParameter("isVerified")));
				this.personDao.PersistPerson(person);
		}
		
		private boolean isParametersValid(HttpServletRequest request, HttpServletResponse response) throws IOException {
				if (!(request.getParameter("vkId") == null || request.getParameter("isVerified") == null)) {
						return false;
				}
				response.setStatus(400);
				return true;
		}
}
