package com.dev.vkbot;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;

public class HomeServletHandler extends ServletRequestWrapper
{
	public HomeServletHandler(ServletRequest request)
		{
			super(request);
		}

	@Override
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException
		{

			return servletRequest.startAsync();
		}

}
