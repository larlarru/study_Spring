package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter("/*")
public class DefaultParameterFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultParameterFilter.class);

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		logger.debug("==================================================================================");
		logger.debug("init_DefaultParameterFilter");
		logger.debug("==================================================================================");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		logger.debug("==================================================================================");
		logger.debug("DefaultParameterFilter_doFilter");
		logger.debug("==================================================================================");
		
		// 인자로 들어온 request 객체를 이용하여 Wrapper로 만들고
		// chain.doFilter 메소드를 이용하여 다른 필터나 서블릿으로 요청을 전달할때
		// Wrapper 클래스를 전달
		
		DefaultParameterRequestWrapper wrapper 
				= new DefaultParameterRequestWrapper((HttpServletRequest)request);
		
		chain.doFilter(wrapper, response);
		
	}

	@Override
	public void destroy() {
		
	}

}
