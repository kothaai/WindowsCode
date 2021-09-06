package com.indianbank.cbs.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indianbank.cbs.bean.FundTransferRequest;
import com.indianbank.cbs.bean.FundTransferResponse;
import com.indianbank.cbs.service.FundTransferService;

@RestController
public class FundResourse {
	@Autowired
	private FundTransferService fundTransferService;
	
	@RequestMapping("/fund/transfer")
	public FundTransferResponse fundTransfer(@RequestBody FundTransferRequest fundTransferRequest) {
	return fundTransferService.fundTransfer(fundTransferRequest);
	}

}
