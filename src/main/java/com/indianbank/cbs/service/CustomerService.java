package com.indianbank.cbs.service;



import com.indianbank.cbs.bean.CustomerRegistrationRequest;
import com.indianbank.cbs.bean.CustomerRegistrationResponse;
import com.indianbank.cbs.bean.CustomerVerificationRequest;
import com.indianbank.cbs.bean.CustomerVerificationResponse;
import com.indianbank.cbs.bean.RemoveAccountResponse;

public interface CustomerService  {
CustomerRegistrationResponse register(CustomerRegistrationRequest customerRegistrationRequest);
CustomerVerificationResponse verifyCustomer(CustomerVerificationRequest customerVerificationRequest);
RemoveAccountResponse deRegisterCustomer(CustomerVerificationRequest customerVerificationRequest);

}
