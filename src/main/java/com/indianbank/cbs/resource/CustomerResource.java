package com.indianbank.cbs.resource;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indianbank.cbs.bean.CustomerRegistrationRequest;
import com.indianbank.cbs.bean.CustomerRegistrationResponse;
import com.indianbank.cbs.bean.CustomerVerificationRequest;
import com.indianbank.cbs.bean.CustomerVerificationResponse;
import com.indianbank.cbs.bean.RemoveAccountResponse;
import com.indianbank.cbs.interceptor.LoggerServiceImpl;
import com.indianbank.cbs.service.CustomerService;

@RestController
public class CustomerResource {

	@Autowired
	private CustomerService customerService;
	
	 public static final Logger LOGGER = LoggerFactory
	            .getLogger(CustomerResource.class);

@RequestMapping("/customer/register")
public CustomerRegistrationResponse register(@RequestBody CustomerRegistrationRequest request, @RequestHeader Map<String,String> headers) {
	
	headers.forEach((key,value) ->{
        // System.out.println("Header Name: "+key+" Header Value: "+value);
		LOGGER.debug("header name{} and Key {}",key ,value);
     });
	return customerService.register(request);
}

@RequestMapping("/customer/verifyDetailsById")
public CustomerVerificationResponse verifyCustomer(@RequestBody CustomerVerificationRequest request) {
return customerService.verifyCustomer(request);
}
@RequestMapping("/customer/deregister")
public RemoveAccountResponse deRegisterCustomer(@RequestBody CustomerVerificationRequest request) {
	return customerService.deRegisterCustomer(request);
}
}
