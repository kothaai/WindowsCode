package com.indianbank.cbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.indianbank.cbs.bean.AccountDetails;
import com.indianbank.cbs.bean.AddAccountRequest;
import com.indianbank.cbs.bean.AddAccountResponse;


import com.indianbank.cbs.bean.RemoveAccountRequest;
import com.indianbank.cbs.bean.RemoveAccountResponse;
import com.indianbank.cbs.bean.ViewAccountRequest;
import com.indianbank.cbs.bean.ViewAccountResponse;
import com.indianbank.cbs.dao.AccountDao;

@Service("addAccountService")
public class AccountServiceImp implements AccountService {
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private MessageSource messageSource;
	@Override
	public AddAccountResponse addAccount(AddAccountRequest addAccountRequest) {
		AddAccountResponse addAccountResponse = new AddAccountResponse();
			boolean result = accountDao.addAccount(addAccountRequest);
			
			if(result) {
				addAccountResponse.setResponseCode(messageSource.getMessage("addaccount.000", null,"000", null));
		        addAccountResponse.setResponseMessage(messageSource.getMessage("addaccount.response." + addAccountResponse.getResponseCode(), null, null));	
			}else {
				addAccountResponse.setResponseCode(messageSource.getMessage("001", null,"001", null));
		        addAccountResponse.setResponseMessage(messageSource.getMessage(addAccountResponse.getResponseCode(), null, null));
			}
			return addAccountResponse;
		}
	public RemoveAccountResponse removeAccount(RemoveAccountRequest removeAccountRequest) {
		RemoveAccountResponse removeAccountResponse = new RemoveAccountResponse();
		boolean result = accountDao.removeRequest(removeAccountRequest);
			
			if(result) {
				removeAccountResponse.setResponseCode(messageSource.getMessage("removeaccount.000", null,"000", null));
		        removeAccountResponse.setResponseMessage(messageSource.getMessage("removeaccount.response." + removeAccountResponse.getResponseCode(), null, null));	
			}else {
				removeAccountResponse.setResponseCode(messageSource.getMessage("removeaccount.001", null,"001", null));
		        removeAccountResponse.setResponseMessage(messageSource.getMessage(removeAccountResponse.getResponseCode(), null, null));
			}
		return removeAccountResponse;
	}
	@Override
	public ViewAccountResponse viewAccount(ViewAccountRequest viewAccountRequest) {
		ViewAccountResponse viewAccountResponse = new ViewAccountResponse();

		List<AccountDetails> accountDetails = accountDao.viewAccount(viewAccountRequest);

			if(accountDetails!=null) {
		       for(int count=0; count<accountDetails.size();count++) {
		    	   viewAccountResponse.setAccounts(accountDetails);
		       }
			    viewAccountResponse.setResponseCode(messageSource.getMessage("viewaccount.000", null, null));
				viewAccountResponse.setResponseMessage(messageSource.getMessage("viewaccount.response." + viewAccountResponse.getResponseCode(), null, null));	
			}else {
				viewAccountResponse.setResponseCode(messageSource.getMessage("viewaccount.001", null,"001", null));
				viewAccountResponse.setResponseMessage(messageSource.getMessage(viewAccountResponse.getResponseCode(), null, null));
			}
			
	
		return viewAccountResponse;
	}
	@Override
	public ViewAccountResponse balanceEnquiry(ViewAccountRequest viewAccountRequest) {
		ViewAccountResponse viewAccountResponse = new ViewAccountResponse();

		List<AccountDetails> accountDetails = accountDao.balanceEnquiry(viewAccountRequest);

			if(accountDetails!=null) {
		       for(int count=0; count<accountDetails.size();count++) {
		    	   viewAccountResponse.setAccounts(accountDetails);
		       }
			    viewAccountResponse.setResponseCode(messageSource.getMessage("viewbalance.000", null, null));
				viewAccountResponse.setResponseMessage(messageSource.getMessage("viewbalance.response." + viewAccountResponse.getResponseCode(), null, null));	
			}else {
				viewAccountResponse.setResponseCode(messageSource.getMessage("viewbalance.001", null,"001", null));
				viewAccountResponse.setResponseMessage(messageSource.getMessage(viewAccountResponse.getResponseCode(), null, null));
			}
			
	
		return viewAccountResponse;
	}
}
