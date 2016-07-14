package com.softech.ls360.dashboard.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component("reactFilter")
public class ReactFilter extends GenericFilterBean {

	//private static final Logger logger = LogManager.getLogger();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        
        String requestURI =  httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        //logger.info("Get request: " + requestURI);
        
        String routingMatch = contextPath + "(/\\S+)";
		
		if (requestURI.matches(routingMatch) && !requestURI.endsWith("/token/get")) {
			httpRequest.getRequestDispatcher("/").forward(httpRequest, httpResponse);
		} else {
			chain.doFilter(httpRequest, httpResponse);
		}
        
	}
	
}
