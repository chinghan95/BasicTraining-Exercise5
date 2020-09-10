package com.pegatron.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// Map the filter to all request (/*)
@WebFilter(filterName = "/LanguageFilter", urlPatterns = "/*")
public class LanguageFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String codingmode = "utf-8";
		request.setCharacterEncoding(codingmode);
		response.setContentType("text/html;charset=" + codingmode);
		response.setCharacterEncoding(codingmode);
		chain.doFilter(request, response);
	}
	
	public void destroy() {
	}
	
}
