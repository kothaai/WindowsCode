package com.indianbank.cbs.dao;

import com.indianbank.cbs.bean.AccountCheck;
import com.indianbank.cbs.bean.FundTransferRequest;

public interface FundTransferDao {
	boolean customerValidation(String customerId);
	   boolean accountValidation(String accountNumber);
	   boolean customerAccountMatching(String customerId, String accountId);
	   boolean debitCheck(String amount,String customerId, String accountId);
	   //boolean updateBalance(String amount,String customerId, String accountId, String tranferType);
	   boolean creditFundTransfer(FundTransferRequest fundTransferRequest);
	   boolean debitFundTransfer(FundTransferRequest fundTransferRequest);
	   AccountCheck accountCheck(String accountNumber);
}
