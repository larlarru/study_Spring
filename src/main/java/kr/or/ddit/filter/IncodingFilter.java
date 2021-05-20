package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncodingFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(IncodingFilter.class);
	
	private Map<String, Integer> requestCountMap = new HashMap<String, Integer>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		logger.debug("init_incodingFilter");
		
		ServletContext sc = filterConfig.getServletContext();
		sc.setAttribute("requestCountMap", requestCountMap);
	}

	// filter.doFIlter == servlet.service
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		logger.debug("IncodingFilter_doFIlter");
		
		// request 객체를 아용하여 요청된 URI 정보를 카운팅
		HttpServletRequest req = (HttpServletRequest) request;
		
		request.setCharacterEncoding("utf-8");

		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
