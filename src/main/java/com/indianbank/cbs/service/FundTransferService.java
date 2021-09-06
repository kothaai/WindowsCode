package com.indianbank.cbs.service;

import com.indianbank.cbs.bean.AccountCheck;
import com.indianbank.cbs.bean.FundTransferRequest;
import com.indianbank.cbs.bean.FundTransferResponse;

public interface FundTransferService {
FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest);
//AccountCheck accountCheck(FundTransferRequest fundTransferRequest);
}
