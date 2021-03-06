package com.indianbank.cbs.interceptor;
import java.util.Enumeration;
import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

	public class IndianBankInterceptor extends HandlerInterceptorAdapter {
		
		@Autowired
		LoggerService loggingService;
		
	    public static final Logger LOGGER = LoggerFactory
	            .getLogger(IndianBankInterceptor.class);
	    
	    @Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
	    	
	    	if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name()) && request.getMethod().equals(HttpMethod.GET.name())) {
	            loggingService.logRequest(request, null);
	        }
	        LOGGER.info("[preHandle][" + request + "]" + "[" + request.getMethod()
	                + "]" + request.getRequestURI() + getParameters(request));
	        String[] arrayPath = request.getRequestURI().split("\\/");
	        String path = arrayPath[arrayPath.length-1];
	        LOGGER.debug("Path {}", path);
	        return true;
	    }
	    
	    @Override
	    public void postHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	        LOGGER.info("[postHandle][" + request + "]");
	    }
	    
	    @Override
	    public void afterCompletion(HttpServletRequest request,
	            HttpServletResponse response, Object handler, Exception ex) {
	        if (ex != null) {
	            ex.printStackTrace();
	        }
	        LOGGER.info("[afterCompletion][" + request + "][exception: " + ex + "]");
	    }
	    private String getParameters(HttpServletRequest request) {
	        StringBuffer posted = new StringBuffer();
	        Enumeration<?> e = request.getParameterNames();
	        if (e != null) {
	            posted.append("?");
	        }
	        while (e.hasMoreElements()) {
	            if (posted.length() > 1) {
	                posted.append("&");
	            }
	            String curr = (String) e.nextElement();
	            posted.append(curr + "=");
	            if (curr.contains("password") || curr.contains("pass")
	                    || curr.contains("pwd")) {
	                posted.append("*****");
	            } else {
	                posted.append(request.getParameter(curr));
	            }
	        }
	        String ip = request.getHeader("X-FORWARDED-FOR");
	        String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
	        if (ipAddr != null && !ipAddr.equals("")) {
	            posted.append("&_psip=" + ipAddr);
	        }
	        return posted.toString();
	    }
	    private String getRemoteAddr(HttpServletRequest request) {
	        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
	        if (ipFromHeader != null && ipFromHeader.length() > 0) {
	            LOGGER.debug("ip from proxy - X-FORWARDED-FOR : {}", ipFromHeader);
	            return ipFromHeader;
	        }
	        return request.getRemoteAddr();
	    }
	}

