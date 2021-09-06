package com.indianbank.cbs.dao;

import java.util.List;

import com.indianbank.cbs.bean.AccountDetails;
import com.indianbank.cbs.bean.AddAccountRequest;
import com.indianbank.cbs.bean.RemoveAccountRequest;
import com.indianbank.cbs.bean.ViewAccountRequest;

public interface AccountDao {
	boolean addAccount(AddAccountRequest addAccountRequest);
	boolean removeRequest(RemoveAccountRequest removeAccountRequest);
	List<AccountDetails> viewAccount(ViewAccountRequest viewAccountRequest);
	List<AccountDetails> balanceEnquiry(ViewAccountRequest viewAccountRequest);

}
