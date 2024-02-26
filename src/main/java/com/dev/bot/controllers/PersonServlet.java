package com.dev.bot.controllers;


import com.dev.bot.database.dao.Dao;

import com.dev.bot.database.dao.PersonDao;
import com.dev.bot.database.model.Person;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
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
            response.getWriter().print(new Gson().toJson(persons));
            response.getWriter().flush();
            return;
        }

        Person person;
        if (request.getParameter("id") != null) {
            person = personDao.getEntityById(Long.parseLong(request.getParameter("id")));
            if (person == null) return;
            
            response.getWriter().print(new Gson().toJson(person));
            return;
        }
        if ((request.getParameter("vkId") != null)) {
            try {
                
                person = personDao.getEntitiesByColumnValue("vkId", Long.parseLong(request.getParameter("vkId"))).getFirst();
                response.getWriter().print(new Gson().toJson(person));
                response.getWriter().flush();
            }
            catch (Exception e) {
                response.getWriter().print("null");
            }
        }
    }
    
    /**
     *
     * @param request the {@link HttpServletRequest} object that contains the request the client made of the servlet
     *
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     * @apiNote  vkId must be not null
     * @value status must be not null
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if ((request.getParameter("vkId") == null)) {
            response.setStatus(400);
            return;
        }
        
        Person person = new Person();
        
        long vkId = Long.parseLong(request.getParameter("vkId"));
        person.setVkId(vkId);
        person.setStatus((short) 0);

        if(request.getParameter("isVerified") != null)
            person.setIsVerified(Boolean.parseBoolean(request.getParameter("isVerified")));
        if(request.getParameter("token") != null)
            person.setToken(request.getParameter("token"));
        person.setIsVerified(false);
        this.personDao.persist(person);
        response.getWriter().printf("persist done {id: %n, userId: %n, token: %s}", person.getId(), person.getVkId(), person.getToken());


        response.setStatus(200);
    }
    
    /**
     *
     * @param request the {@link HttpServletRequest} object that contains the request the client made of the servlet
     *
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     * @value req param: vkId - not null
     * @value req param: status - not null. 0 - unknown user. 1 - sending age, 2 - verifying, 3 - verified
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if (request.getParameter("vkId") == null) {
            response.setStatus(400);
            return;
        }
        long vkId = Long.parseLong(request.getParameter("vkId"));
        short status = Short.parseShort(request.getParameter("status"));
        
        Person person = new Person();
        person.setId(personDao.getEntitiesByColumnValue("vkId", vkId).getFirst().getId());
        
        // must be not null
        person.setVkId(vkId);
        
        // by default, it is false
        person.setIsVerified(false);
        
        if(request.getParameter("status") != null)
            person.setStatus(status);
        
        if(request.getParameter("isVerified") != null)
            person.setIsVerified(Boolean.parseBoolean(request.getParameter("isVerified")));
        
        if(request.getParameter("token") != null)
            person.setToken(request.getParameter("token"));
        
        
        this.personDao.merge(person);
        response.setStatus(200);
    }
    
}
