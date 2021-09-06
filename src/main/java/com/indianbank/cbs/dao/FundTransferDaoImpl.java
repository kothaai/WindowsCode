package com.indianbank.cbs.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.indianbank.cbs.bean.AccountCheck;
import com.indianbank.cbs.bean.CustomerBasicDetails;
import com.indianbank.cbs.bean.FundTransferRequest;


@Repository("fundTransferDao")
public class FundTransferDaoImpl implements FundTransferDao{
	@Autowired
	private MessageSource messageSource;
     @Autowired
     private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
     public static final Logger LOGGER = LoggerFactory
	            .getLogger(FundTransferDaoImpl.class);
	
	public boolean customerValidation(String customerId) {
		String query = messageSource.getMessage("customerValidation.validate", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("customerId", customerId);
		LOGGER.info("SQL{} FilterParams{}",query, namedParameters);
		
		int count = namedParameterJdbcTemplate.queryForObject(query, namedParameters, Integer.class);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}
	@Override
	public boolean accountValidation(String accountId) {
		String query = messageSource.getMessage("accountValidation.validate", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("accountId",accountId);
		LOGGER.info("SQL{} FilterParams{}",query, namedParameters);
		
		int count = namedParameterJdbcTemplate.queryForObject(query, namedParameters, Integer.class);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}
	@Override
	public boolean customerAccountMatching(String customerId, String accountId) {
		String query = messageSource.getMessage("customeraccount.match", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("customerId", customerId);
		namedParameters.put("accountId", accountId);
		
		int count = namedParameterJdbcTemplate.queryForObject(query, namedParameters, Integer.class);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;	
	}
	@Override
	public boolean debitCheck(String amount,String customerId, String accountId) {
		String query = messageSource.getMessage("balance.check", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("amount",amount);
		namedParameters.put("customerId", customerId);
		namedParameters.put("accountId", accountId);
		LOGGER.info("SQL{} FilterParams{}",query, namedParameters);
		
		int count = namedParameterJdbcTemplate.queryForObject(query, namedParameters, Integer.class);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}
	/*@Override
	public boolean updateBalance(String amount, String customerId, String accountId,String transferType) {
		String query = messageSource.getMessage("update.balance", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("amount",amount);
		namedParameters.put("customerId",customerId);
		namedParameters.put("accountId", accountId);
		namedParameters.put("transferType", transferType);
		int count = namedParameterJdbcTemplate.update(query, namedParameters);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}*/
	@Override
	public boolean creditFundTransfer(FundTransferRequest fundTransferRequest) {
		String query = messageSource.getMessage("credit.balance", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("amount",fundTransferRequest.getAmount());
		namedParameters.put("accountId",fundTransferRequest.getAccountId());
		LOGGER.info("SQL{} FilterParams{}",query, namedParameters);
		
		int count = namedParameterJdbcTemplate.update(query, namedParameters);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}
	@Override
	public boolean debitFundTransfer(FundTransferRequest fundTransferRequest) {
		String query = messageSource.getMessage("debit.balance", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("amount",fundTransferRequest.getAmount());
		namedParameters.put("customerId",fundTransferRequest.getCustomerId());
		namedParameters.put("accountId",fundTransferRequest.getAccountId());
		LOGGER.info("SQL{} FilterParams{}",query, namedParameters);
		
		int count = namedParameterJdbcTemplate.update(query, namedParameters);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}
	@Override
	public AccountCheck accountCheck(String beneficiaryAccountNumber) {
		AccountCheck accountCheck = new AccountCheck();
		String query = messageSource.getMessage("check.account", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("beneficiaryAccountNumber",beneficiaryAccountNumber);

		accountCheck = namedParameterJdbcTemplate.queryForObject(query.toString(), namedParameters,BeanPropertyRowMapper.newInstance(AccountCheck.class));
		return accountCheck;
		
	}

}

