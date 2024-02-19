package com.dev.vkbot;

import jdk.javadoc.internal.doclets.toolkit.Content;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HomeServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
		{

			res.setContentType(Content);

			//We need printwriter object to write html content
			PrintWriter pw = res.getWriter();

			pw.close();// close the stream

		}
}
