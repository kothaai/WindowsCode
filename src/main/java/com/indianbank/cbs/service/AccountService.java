package com.indianbank.cbs.service;

import com.indianbank.cbs.bean.AddAccountRequest;
import com.indianbank.cbs.bean.AddAccountResponse;
import com.indianbank.cbs.bean.RemoveAccountRequest;
import com.indianbank.cbs.bean.RemoveAccountResponse;
import com.indianbank.cbs.bean.ViewAccountRequest;
import com.indianbank.cbs.bean.ViewAccountResponse;

public interface AccountService {
AddAccountResponse addAccount(AddAccountRequest addAccountRequest);
RemoveAccountResponse removeAccount(RemoveAccountRequest removeccountRequest);
ViewAccountResponse viewAccount(ViewAccountRequest viewAccountRequest);
ViewAccountResponse balanceEnquiry(ViewAccountRequest viewAccountRequest);
}
