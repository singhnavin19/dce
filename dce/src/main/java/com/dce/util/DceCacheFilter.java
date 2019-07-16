package com.dce.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class DceCacheFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		System.out.println("Init Filter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("do filter");
		HttpServletResponse httpresponse = (HttpServletResponse) response;
		httpresponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpresponse.setHeader("This-Header-Is-Set", "no-cache, no-store, must-revalidate");
		httpresponse.setHeader("Expires", "0");
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

		System.out.println("destroy");

	}

}
