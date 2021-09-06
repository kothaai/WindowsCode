package com.indianbank.cbs.interceptor;

import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component

public class LoggerServiceImpl implements LoggerService {
	private static final Logger LOGGERINTERNAL = LoggerFactory
			.getLogger(LoggerServiceImpl.class);
	
	 public static final Logger LOGGER = LoggerFactory
	            .getLogger(LoggerServiceImpl.class);
	
		private static final String NOTIFICATION_PREFIX = "* ";
    	private static final String REQUEST_PREFIX = "> ";
    	private static final String RESPONSE_PREFIX = "< ";
    	
    	private static final String ENTITY_LOGGER_PROPERTY = LoggerServiceImpl.class
    			.getName() + ".entityLogger";
    	private static final String LOGGING_ID_PROPERTY = LoggerServiceImpl.class
    			.getName() + ".id";
    	
    	private static final AtomicLong longId = new AtomicLong(0);
    	
    	private static final Comparator<Map.Entry<String, List<String>>> COMPARATOR = new Comparator<Map.Entry<String, List<String>>>() {
    		@Override
    		public int compare(final Map.Entry<String, List<String>> o1,
    				final Map.Entry<String, List<String>> o2) {
    			return o1.getKey().compareToIgnoreCase(o2.getKey());
    		}
    	};
    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
    	
        final long id = longId.incrementAndGet();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);
        try {
        	httpServletRequest.setAttribute(LOGGING_ID_PROPERTY, id);	
        ObjectMapper mapper=new ObjectMapper();
        String bodyJson="";
		bodyJson=mapper.writeValueAsString(body);
        StringBuilder stringBuilder1 = new StringBuilder();
        printRequestLine(stringBuilder1, "Sending client request", id, httpServletRequest.getMethod(),
        		httpServletRequest.getRequestURL().toString());
       printPrefixedHeaders(stringBuilder1, id, REQUEST_PREFIX, buildHeadersMap(httpServletRequest));
       stringBuilder1.append(bodyJson);
       stringBuilder1.append('\n');
       log(stringBuilder1);
        } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void log(final StringBuilder b) {
		if (LOGGERINTERNAL != null) {
			LOGGERINTERNAL.info("\n");
			if(b.toString().contains("clPn") && b.toString().contains("ofst")){
			
				LOGGERINTERNAL.info("Pin generated successfully");
			}else{
				
				LOGGERINTERNAL.info(b.toString());
			}
		}
	}
    private void printPrefixedHeaders(StringBuilder stringBuilder, long id,
			String requestPrefix, Map<String, String> buildHeadersMap) {
		for(Map.Entry<String, String> buildHeadersMap1:buildHeadersMap.entrySet()){
			String val= buildHeadersMap1.getValue();
			String key=buildHeadersMap1.getKey();
			final StringBuilder sb = new StringBuilder();
			sb.append(val);
			prefixId(stringBuilder, id).append(requestPrefix).append(key).append(": ")
			.append(sb.toString()).append("\n");
			}
	}
	private void printRequestLine(StringBuilder b, String note,
			long id, String method, String requestURI) {
		 prefixId(b, id).append(NOTIFICATION_PREFIX).append(note)
		.append(" on thread ").append(Thread.currentThread().getName())
		.append("\n");
    	
    	prefixId(b, id).append(REQUEST_PREFIX).append(method).append(" ")
		.append(requestURI.toString()).append("\n");
		
	}
    private StringBuilder prefixId(final StringBuilder b, final long id) {
		b.append(Long.toString(id)).append(" ");
		return b;
	}
		
		private Map<String, String> buildHeadersMap(HttpServletRequest request) {
	        Map<String, String> map = new HashMap<>();
	       
	        Enumeration headerNames = request.getHeaderNames();
	        while (headerNames.hasMoreElements()) {
	            String key = (String) headerNames.nextElement();
	            String value = request.getHeader(key);
	            map.put(key, value);
	        }
	       
	        return map;
	    }
	@Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
        	final Object requestId = httpServletRequest
    				.getAttribute(LOGGING_ID_PROPERTY);
    		final long id = requestId != null ? (Long) requestId : longId
    				.incrementAndGet();
    		
    		
        ObjectMapper mapper=new ObjectMapper();
        String bodyJson="";
		bodyJson=mapper.writeValueAsString(body);	
		
		LOGGER.info("cssfdsf::{}",buildHeadersMap(httpServletResponse));
		
		printResponseLine(stringBuilder, "Client response received", id,
				httpServletResponse.getStatus());
		
		printPrefixedHeaders(stringBuilder, id, RESPONSE_PREFIX,
				buildHeadersMap(httpServletResponse));
		stringBuilder.append(bodyJson);
		stringBuilder.append("\n");
		
		log(stringBuilder);
        } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void printResponseLine(StringBuilder stringBuilder, String note,
			long id, int status) {
		prefixId(stringBuilder, id).append(NOTIFICATION_PREFIX).append(note)
		.append(" on thread ").append(Thread.currentThread().getName())
		.append("\n");
		prefixId(stringBuilder, id).append(RESPONSE_PREFIX)
		.append(Integer.toString(status)).append("\n");
		
	}
	
    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }
        return resultMap;
    }
    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }
        return map;
    }
}