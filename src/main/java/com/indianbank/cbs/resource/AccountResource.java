package com.indianbank.cbs.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indianbank.cbs.bean.AddAccountRequest;
import com.indianbank.cbs.bean.AddAccountResponse;
import com.indianbank.cbs.bean.RemoveAccountRequest;
import com.indianbank.cbs.bean.RemoveAccountResponse;
import com.indianbank.cbs.bean.ViewAccountRequest;
import com.indianbank.cbs.bean.ViewAccountResponse;
import com.indianbank.cbs.service.AccountService;


@RestController()
public class AccountResource {
	
	@Autowired
	private AccountService accountService;

	
@RequestMapping("/account/add")
public AddAccountResponse addAccount(@RequestBody AddAccountRequest request) {
	return accountService.addAccount(request);
}
@RequestMapping("/account/remove")
public RemoveAccountResponse removeAccount(@RequestBody RemoveAccountRequest request) {
	return accountService.removeAccount(request);
}
@RequestMapping("/account/view")
public ViewAccountResponse viewAccount(@RequestBody ViewAccountRequest request) {
	return accountService.viewAccount(request);
	
}
@RequestMapping("/account/balance")
public ViewAccountResponse balanceEnquiry(@RequestBody ViewAccountRequest request) {
	return accountService.balanceEnquiry(request) ;
}
}
