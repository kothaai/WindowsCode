package com.indianbank.cbs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indianbank.cbs.bean.AccountDetails;
import com.indianbank.cbs.bean.AddressDetails;
import com.indianbank.cbs.bean.CustomerBasicDetails;
import com.indianbank.cbs.bean.CustomerRegistrationRequest;
import com.indianbank.cbs.bean.CustomerRegistrationResponse;
import com.indianbank.cbs.bean.CustomerVerificationRequest;
import com.indianbank.cbs.bean.CustomerVerificationResponse;
import com.indianbank.cbs.bean.RemoveAccountResponse;
import com.indianbank.cbs.bean.Exception.CustomerException;
import com.indianbank.cbs.dao.CustomerDao;


@Service("customerRegistrationService")
public class CustomerServiceImpl implements CustomerService {
@Autowired
private CustomerDao customerDao;
@Autowired
private MessageSource messageSource;

	@Override
	@Transactional(rollbackFor = {CustomerException.class})
	public CustomerRegistrationResponse register(CustomerRegistrationRequest customerRegistrationRequest) {
		CustomerRegistrationResponse customerRegistrationResponse = new CustomerRegistrationResponse();
		
		try {
		boolean isMobileNoAlreadyExist = customerDao.isMobileNoAlreadyExist(customerRegistrationRequest.getMobileNumber());
			if(isMobileNoAlreadyExist)
			{
				String responseCode=messageSource.getMessage("mobileduplicate.error.code", null, null);
			    String responseMessage=messageSource.getMessage(responseCode, null,null);
			 throw new CustomerException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", customerRegistrationRequest);
			}
			boolean isCustomerAlreadyExist = customerDao.isCustomerAlreadyExist(customerRegistrationRequest.getCustomerId());
			if(isCustomerAlreadyExist)
			{
				String responseCode=messageSource.getMessage("customerduplicate.error.code", null, null);
			    String responseMessage=messageSource.getMessage(responseCode, null,null);
			 throw new CustomerException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", customerRegistrationRequest);
			}
			boolean result = customerDao.saveCustomer(customerRegistrationRequest);
			if(!result) {
				
				
				String responseCode=messageSource.getMessage("customerregistration.001", null, null);
				String responseMessage=messageSource.getMessage(responseCode, null,null);
				throw new CustomerException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", customerRegistrationRequest);
			}
			result = customerDao.addAccount(customerRegistrationRequest);
			if(!result) {
				
				
				String responseCode=messageSource.getMessage("customerregistration.002", null, null);
				String responseMessage=messageSource.getMessage(responseCode, null,null);
		
				throw new CustomerException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", customerRegistrationRequest);
			}
			result = customerDao.addAddress(customerRegistrationRequest);
            if(!result) {
				
				
				String responseCode=messageSource.getMessage("customerregistration.003", null, null);
				String responseMessage=messageSource.getMessage(responseCode, null,null);
		
				throw new CustomerException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", customerRegistrationRequest);
			}
				customerRegistrationResponse.setResponseCode(messageSource.getMessage("customerregistration.000", null,"000", null));
		        customerRegistrationResponse.setResponseMessage(messageSource.getMessage("customerregistration.response." + customerRegistrationResponse.getResponseCode(), null, null));	
			
		}
		catch(CustomerException e)
		{
			throw e;
		}
		catch(DuplicateKeyException e1)
		{
			String responseCode=messageSource.getMessage("error.response.code.500", null, null);
			String responseMessage=messageSource.getMessage(responseCode, null,null);
			 throw new CustomerException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", customerRegistrationRequest);
		}
		return customerRegistrationResponse;
	}
	
	@Override
	public CustomerVerificationResponse verifyCustomer(CustomerVerificationRequest customerVerificationRequest) {
		CustomerVerificationResponse customerVerificationResponse = new CustomerVerificationResponse();

		CustomerBasicDetails customerBasicDetails = customerDao.viewCustomers(customerVerificationRequest);
		List<AccountDetails> accountDetails = customerDao.viewAccount(customerVerificationRequest);
		List<AddressDetails> addressDetails = customerDao.viewAddress(customerVerificationRequest);
			if(customerBasicDetails!=null) {
				customerVerificationResponse.setFirstName(customerBasicDetails.getFirstName());
				customerVerificationResponse.setLastName(customerBasicDetails.getLastName());
				customerVerificationResponse.setMobileNumber(customerBasicDetails.getMobileNumber());
		       for(int count=0; count<accountDetails.size();count++) {
		    	   customerVerificationResponse.setAccounts(accountDetails);
		       }
			  for(AddressDetails num: addressDetails) {
				  customerVerificationResponse.setAddressDetails(addressDetails);
			  }
				
				customerVerificationResponse.setResponseCode(messageSource.getMessage("viewcustomer.000", null, null));
				customerVerificationResponse.setResponseMessage(messageSource.getMessage("viewcustomer.response." + customerVerificationResponse.getResponseCode(), null, null));	
			}else {
				customerVerificationResponse.setResponseCode(messageSource.getMessage("viewcustomer.001", null,"001", null));
				customerVerificationResponse.setResponseMessage(messageSource.getMessage(customerVerificationResponse.getResponseCode(), null, null));
			}
		return customerVerificationResponse;
	}

	public RemoveAccountResponse deRegisterCustomer(CustomerVerificationRequest customerVerificationRequest) {
		RemoveAccountResponse removeAccountResponse = new RemoveAccountResponse();
		try {
		boolean result = customerDao.deRegisterCustomer(customerVerificationRequest);
		if(!result) {
			String responseCode =  messageSource.getMessage("customer.deregister.error.code", null,"001", null);
			String responseMessage = messageSource.getMessage(responseCode, null, null);
			throw new CustomerException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", customerVerificationRequest);
		}
		removeAccountResponse.setResponseCode(messageSource.getMessage("removecustomer.000", null,"000", null));
		removeAccountResponse.setResponseMessage(messageSource.getMessage("removecustomer.response." + removeAccountResponse.getResponseCode(), null, null));
		}
		catch(CustomerException e)
		{
			throw e;
		}
		return removeAccountResponse;
		}}
