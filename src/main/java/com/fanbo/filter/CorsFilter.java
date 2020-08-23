package com.fanbo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(CorsFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
			HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;


		if(httpReq.getRequestURI().endsWith(".html")||httpReq.getRequestURI().endsWith(".ico")||
				httpReq.getRequestURI().endsWith(".css")||httpReq.getRequestURI().endsWith(".js")){
			chain.doFilter(request, response);
			return;
		}

		httpResp.setHeader("Access-Control-Allow-Origin", "*");
		httpResp.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
		httpResp.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
		httpResp.setHeader("Access-Control-Allow-Credentials", "false");
		httpResp.setHeader("Access-Control-Allow-Headers","Origin,Accept,x-target,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,X-Auth-Token,Cache-Control");
		
		
		if (httpReq.getMethod().equalsIgnoreCase("OPTIONS")) {
			httpResp.setStatus(200);
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}