package com.indianbank.cbs.dao;

import java.util.List;

import com.indianbank.cbs.bean.AccountDetails;
import com.indianbank.cbs.bean.AddressDetails;
import com.indianbank.cbs.bean.CustomerBasicDetails;
import com.indianbank.cbs.bean.CustomerRegistrationRequest;
import com.indianbank.cbs.bean.CustomerVerificationRequest;

public interface CustomerDao {
	boolean saveCustomer(CustomerRegistrationRequest customerRegistrationRequest);
	boolean addAccount(CustomerRegistrationRequest customerRegistrationRequest);
	boolean addAddress(CustomerRegistrationRequest customerRegistrationRequest);
	CustomerBasicDetails viewCustomers(CustomerVerificationRequest customerVerificationRequest);
	List<AccountDetails> viewAccount(CustomerVerificationRequest customerVerificationRequest);
	List<AddressDetails> viewAddress(CustomerVerificationRequest customerVerificationRequest);
	boolean deRegisterCustomer(CustomerVerificationRequest customerVerificationRequest);
	boolean isCustomerAlreadyExist(String customerId);
	boolean isMobileNoAlreadyExist(String mobileNumber);
}
