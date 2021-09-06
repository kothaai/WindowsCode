package com.indianbank.cbs.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class LogUIDFilter implements Filter {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(LogUIDFilter.class);
	/**
	 * Default constructor.
	 */
	public LogUIDFilter() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LOGGER.info("%%%%%%%% Inside log filter %%%%%%%");
		
		
		String uniqueId = null;
		String hmacValue="";
		try {
			uniqueId = UUID.randomUUID().toString();
			hmacValue = RandomStringUtils.randomAlphanumeric(15);
			LOGGER.info("unique Id {} ", uniqueId);
			MDC.put("uniqueId", uniqueId);
			MDC.put("hmacValue", hmacValue);
			
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} catch (Exception ex) {
			LOGGER.error("Exception Occured ", ex);
			LOGGER.debug("Exception MEssage {}", ex.getMessage());
		} finally {
			LOGGER.info("finally block {} ");
			MDC.remove(uniqueId);
			MDC.remove(hmacValue);
		}
	}
}