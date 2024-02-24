package com.dev.bot.controllers;


import com.dev.bot.database.dao.Dao;

import com.dev.bot.database.dao.PersonDao;
import com.dev.bot.database.model.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/db/person"})
public class PersonServlet extends HttpServlet {
    private Dao<Person> personDao;

    @Override
    public void init() throws ServletException {
        super.init();
        personDao = new PersonDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        if (request.getParameter("all") != null) {
            List<Person> persons = personDao.getAll();
            for (Person person : persons) {
                response.getWriter().print(this.getPrettyJsonOutputString(person));
                if(person != persons.getLast())
                    response.getWriter().print(',');
            }
            return;
        }

        Person person;
        if (request.getParameter("id") != null) {
            person = personDao.getEntityById(Long.parseLong(request.getParameter("id")));
            if (person == null) return;
            
            response.getWriter().println(this.getPrettyJsonOutputString(person));
            return;
        }
        if ((request.getParameter("vkId") != null)) {
            person = personDao.getEntitiesByColumnValue("vkId", Long.parseLong(request.getParameter("vkId"))).getFirst();
            response.getWriter().print(this.getPrettyJsonOutputString(person));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        if ((request.getParameter("vkId") == null)) {
            return;
        }

        Person person = new Person();
        
        long vkId = Long.parseLong(request.getParameter("vkId"));
        person.setVkId(vkId);

        if(request.getParameter("isVerified") != null)
            person.setIsVerified(Boolean.parseBoolean(request.getParameter("isVerified")));
        if(request.getParameter("token") != null)
            person.setToken(request.getParameter("token"));
        
        if (request.getParameter("method-merge") != null) {
            
            this.personDao.merge(person);
            response.getWriter().printf("merge done {id: %n, userId: %n, token: %s}", person.getId(), person.getVkId(), person.getToken());
        }
        else if (request.getParameter("method-persist") != null){
            person.setIsVerified(false);
            this.personDao.persist(person);
            response.getWriter().printf("persist done {id: %n, userId: %n, token: %s}", person.getId(), person.getVkId(), person.getToken());
        }

        response.setStatus(200);
    }
    
    private String getPrettyJsonOutputString(Person person) {
        return String.format("{\"id\": %d, \"userId\": %d, \"isVerified\": %b, \"token\": \"%s\"}",
             person.getId(),
             person.getVkId(),
             person.getIsVerified(),
             person.getToken());
    }
}
