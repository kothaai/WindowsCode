package com.indianbank.cbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoggerService {
	void logRequest(HttpServletRequest httpServletRequest, Object body);
	   void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body);
}
