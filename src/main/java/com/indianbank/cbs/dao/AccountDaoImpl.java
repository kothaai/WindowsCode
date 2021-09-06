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
import com.indianbank.cbs.bean.AddAccountRequest;
import com.indianbank.cbs.bean.RemoveAccountRequest;
import com.indianbank.cbs.bean.ViewAccountRequest;


@Repository("AccountDao")
public class AccountDaoImpl implements AccountDao{


	@Autowired
	private MessageSource messageSource;
     @Autowired
     private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public boolean addAccount(AddAccountRequest addAccountRequest) {
		String query = messageSource.getMessage("addaccount.details",null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("customerId", addAccountRequest.getCustomerId());
		namedParameters.put("accountId",UUID.randomUUID().toString());
		namedParameters.put("accountNumber", addAccountRequest.getAccountNumber());
		namedParameters.put("currency", addAccountRequest.getCurrency());
		namedParameters.put("ledgerBalance","1456");
		namedParameters.put("availableBalance","1459876");
		namedParameters.put("currentBalance","1456");
		namedParameters.put("accountStatus","Y");
		namedParameters.put("primaryFlag","Y");
		namedParameters.put("customerStatus","Y");
		namedParameters.put("accountType","Savings");
		
		int count = namedParameterJdbcTemplate.update(query, namedParameters);
		boolean result = false;
		if(count>0) {
			result = true;
		
		}
		return result;
	}

	@Override
	public boolean removeRequest(RemoveAccountRequest removeAccountRequest) {
		String query = messageSource.getMessage("removeaccount.remove",null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		
		
		namedParameters.put("customerId",removeAccountRequest.getCustomerId());
		namedParameters.put("accountId", removeAccountRequest.getAccountId());
		
		int count = namedParameterJdbcTemplate.update(query, namedParameters);
		boolean result = false;
		if(count>0) {
			result = true;
		
		}
		return result;
	}

	@Override
	public List<AccountDetails> viewAccount(ViewAccountRequest viewAccountRequest) {
		List<AccountDetails> accountDetails = new ArrayList<AccountDetails>();
		   String query = messageSource.getMessage("viewaccount.account",null, null);
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put("customerId",viewAccountRequest.getCustomerId());
			if(!viewAccountRequest.getAccountIds().isEmpty()) {
				
				query = query + " and ICAD_ACCT_ID IN (:accountIdList)";
				namedParameters.put("accountIdList", viewAccountRequest.getAccountIds());
			}
	
			accountDetails = namedParameterJdbcTemplate.query(query,namedParameters,BeanPropertyRowMapper.newInstance(AccountDetails.class));
		    return accountDetails;
	}

	@Override
	public List<AccountDetails> balanceEnquiry(ViewAccountRequest viewAccountRequest) {
		List<AccountDetails> accountDetails = new ArrayList<AccountDetails>();
		   String query = messageSource.getMessage("viewbalance.balance",null, null);
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put("customerId",viewAccountRequest.getCustomerId());
			if(!viewAccountRequest.getAccountIds().isEmpty()) {
				
				query = query + " and ICAD_ACCT_ID IN (:accountIdList)";
				namedParameters.put("accountIdList",viewAccountRequest.getAccountIds());
				
			}
			System.out.println(query);
			accountDetails = namedParameterJdbcTemplate.query(query,namedParameters,BeanPropertyRowMapper.newInstance(AccountDetails.class));
		    return accountDetails;
	
	}
}