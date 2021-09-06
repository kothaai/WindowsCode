package com.indianbank.cbs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.indianbank.cbs.bean.AccountDetails;
import com.indianbank.cbs.bean.AddressDetails;
import com.indianbank.cbs.bean.CustomerBasicDetails;
//import org.springframework.jdbc.core.JdbcTemplate;
import com.indianbank.cbs.bean.CustomerRegistrationRequest;
import com.indianbank.cbs.bean.CustomerVerificationRequest;

@Repository("customerRegistrationDao")
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private MessageSource messageSource;
     @Autowired
     private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public boolean saveCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
		String query = messageSource.getMessage("customerregisteration.save", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("customerId", customerRegistrationRequest.getCustomerId());
		namedParameters.put("firstName", customerRegistrationRequest.getFirstName());
		namedParameters.put("lastName", customerRegistrationRequest.getLastName());
		String fullName = customerRegistrationRequest.getFirstName() + " " + customerRegistrationRequest.getLastName();
		namedParameters.put("fullName", fullName);
		namedParameters.put("mobileNo", customerRegistrationRequest.getMobileNumber());	
		namedParameters.put("customerStatus","Y");
		
		int count = namedParameterJdbcTemplate.update(query, namedParameters);
		boolean result = false;
		if(count>0) {
			result = true;
			System.out.println("printed..");	
		}
		return result;
	
	}
	

	@Override
	public boolean addAccount(CustomerRegistrationRequest customerRegistrationRequest) {
		
		 String query = messageSource.getMessage("primaryaccount.add",null, null);
		 Map<String, Object> namedParameters = new HashMap<String, Object>();
		 int insertedRecord = 0;
		 int totalRecords = customerRegistrationRequest.getAccounts().size();
		 namedParameters.put("customerId", customerRegistrationRequest.getCustomerId());
			
		 for(AccountDetails accountDetail: customerRegistrationRequest.getAccounts())
		 {
			    namedParameters.put("accountId",UUID.randomUUID().toString());
				namedParameters.put("accountNumber", accountDetail.getAccountNumber());
				namedParameters.put("accountType", accountDetail.getAccountType());
				namedParameters.put("currency", accountDetail.getCurrency());
				namedParameters.put("primaryFlag", accountDetail.getPrimaryAccountFlag());
				namedParameters.put("ledgerBalance","1456");
				namedParameters.put("availableBalance","1459876");
				namedParameters.put("currentBalance","1456");
				namedParameters.put("accountStatus","Y");
				
				int count = namedParameterJdbcTemplate.update(query, namedParameters);
				if(count>0)
				{
					insertedRecord++;
				}
			
		 }
		 boolean result = false;
		 if(totalRecords==insertedRecord)
		 {
			 result = true;
		 }
		 return result;
		 
	}

	@Override
	public boolean addAddress(CustomerRegistrationRequest customerRegistrationRequest) {
		 String query = messageSource.getMessage("address.add",null, null);
		 Map<String, Object> namedParameters = new HashMap<String, Object>();
		 int insertedRecord = 0;
		 int totalRecords = customerRegistrationRequest.getAddressDetails().size();
		 namedParameters.put("customerId", customerRegistrationRequest.getCustomerId());
			
		 for(AddressDetails addressDetails: customerRegistrationRequest.getAddressDetails())
		 {
				
				namedParameters.put("addressType", addressDetails.getAddressType());
				namedParameters.put("streetName", addressDetails.getStreetName());
				namedParameters.put("areaName", addressDetails.getAreaName());
				namedParameters.put("city", addressDetails.getCity());
				namedParameters.put("state", addressDetails.getState());
				namedParameters.put("pinCode", addressDetails.getPinCode());
				int count = namedParameterJdbcTemplate.update(query, namedParameters);
				if(count>0)
				{
					insertedRecord++;
				}
			
		 }
		 boolean result = false;
		 if(totalRecords==insertedRecord)
		 {
			 result = true;
		 }
		 return result;
		
	}


	 @Override
	 public CustomerBasicDetails viewCustomers(CustomerVerificationRequest customerVerificationRequest) {
		 CustomerBasicDetails customerBasicDetails = new CustomerBasicDetails();
		String query = messageSource.getMessage("viewcustomer.view",null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("customerId",customerVerificationRequest.getCustomerId());
		
		customerBasicDetails = namedParameterJdbcTemplate.queryForObject(query.toString(), namedParameters,BeanPropertyRowMapper.newInstance(CustomerBasicDetails.class));
		return customerBasicDetails;
		
	 }
	@Override
	public List<AccountDetails> viewAccount(CustomerVerificationRequest customerVerificationRequest) {
	  List<AccountDetails> accountDetails = new ArrayList<AccountDetails>();
	   String query = messageSource.getMessage("customeraccount.view",null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("customerId",customerVerificationRequest.getCustomerId());
		accountDetails = namedParameterJdbcTemplate.query(query,namedParameters,BeanPropertyRowMapper.newInstance(AccountDetails.class));
	    return accountDetails;
	    }

	@Override
	public List<AddressDetails> viewAddress(CustomerVerificationRequest customerVerificationRequest) {
		List<AddressDetails> addressDetails = new ArrayList<AddressDetails>();
		 String query = messageSource.getMessage("customeraddress.view",null, null);
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put("customerId",customerVerificationRequest.getCustomerId());
			addressDetails = namedParameterJdbcTemplate.query(query, namedParameters, BeanPropertyRowMapper.newInstance(AddressDetails.class));
			return addressDetails;
	}

	@Override
	public boolean deRegisterCustomer(CustomerVerificationRequest customerVerificationRequest) {
		String query = messageSource.getMessage("removecustomer.remove", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("customerId", customerVerificationRequest.getCustomerId());
		
		int count = namedParameterJdbcTemplate.update(query, namedParameters);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	     
	}

	@Override
	public boolean isCustomerAlreadyExist(String customerId) {
		String query = messageSource.getMessage("customerduplicate.duplicate", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("customerId", customerId);
		
		int count = namedParameterJdbcTemplate.queryForObject(query, namedParameters, Integer.class);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}

	@Override
	public boolean isMobileNoAlreadyExist(String mobileNumber) {
		String query = messageSource.getMessage("customermobileno.duplicate", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("mobileNumber",mobileNumber);
		
		int count = namedParameterJdbcTemplate.queryForObject(query, namedParameters, Integer.class);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}


	
}
