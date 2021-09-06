package com.indianbank.cbs.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indianbank.cbs.bean.AccountCheck;
import com.indianbank.cbs.bean.FundTransferRequest;
import com.indianbank.cbs.bean.FundTransferResponse;
import com.indianbank.cbs.bean.Exception.FundTransferException;
import com.indianbank.cbs.dao.FundTransferDao;
import com.indianbank.cbs.dao.FundTransferDaoImpl;
import com.indianbank.cbs.util.CBSConstants;

@Service("fundTransferService")
public class FundTransferServiceImpl implements FundTransferService {
	 public static final Logger LOGGER = LoggerFactory.getLogger(FundTransferServiceImpl.class);
	 @Autowired
	 private FundTransferDao fundTransferDao;
	 @Autowired
	 private MessageSource messageSource;
	 @Transactional(rollbackFor = {FundTransferException.class})
	 	public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest) {
		
		
		String responseCode = "";
		String responseMessage = "";
		
	 		FundTransferResponse fundTransferResponse = new FundTransferResponse();
	 		if(CBSConstants.CREDIT.equals(fundTransferRequest.getTransferType())) {
	 			AccountCheck accountCheck = fundTransferDao.accountCheck(fundTransferRequest.getBeneficiaryAccountNumber());
	 			if(accountCheck == null) {
	 				responseCode=messageSource.getMessage("accountcheck.001", null, null);
	 	 		   responseMessage=messageSource.getMessage(responseCode, null,null);
	 				throw new FundTransferException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", fundTransferRequest);
	 				}
	 			
	 			fundTransferRequest.setAccountId(accountCheck.getAccountId());
	 			fundTransferRequest.setCustomerId(accountCheck.getCustomerId());
	 			
	 		}
	 		boolean customerValidation = fundTransferDao.customerValidation(fundTransferRequest.getCustomerId());
	         if(!customerValidation) {
	        	 
	            responseCode=messageSource.getMessage("customervalidation.001", null, null);
	 		   responseMessage=messageSource.getMessage(responseCode, null,null);
	 		 throw new FundTransferException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", fundTransferRequest);
	         }
	         boolean accountValidation = fundTransferDao.accountValidation(fundTransferRequest.getAccountId());
	         LOGGER.info("AccountValidation{}",accountValidation);
	 		if(!accountValidation) {
	 			
	 			responseCode=messageSource.getMessage("accountvalidation.001", null, null);
	 			responseMessage=messageSource.getMessage(responseCode, null,null);
	 			 throw new FundTransferException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", fundTransferRequest);
	 	        }
	 		boolean customerAccountMatching = fundTransferDao.customerAccountMatching(fundTransferRequest.getCustomerId(), fundTransferRequest.getAccountId());
	 		if(!customerAccountMatching) {
	 			responseCode=messageSource.getMessage("accountcustomermatch.001", null, null);
	 			 responseMessage=messageSource.getMessage(responseCode, null,null);
	 			 throw new FundTransferException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", fundTransferRequest);
	 	        }  
	 		boolean amountCheck = fundTransferDao.debitCheck(fundTransferRequest.getAmount(),fundTransferRequest.getCustomerId(),fundTransferRequest.getAccountId());
	 		LOGGER.info("AmountCheck{}",amountCheck);
	 		if(!amountCheck) {
	 			responseCode=messageSource.getMessage("amountValidation.001", null, null);
	 			responseMessage=messageSource.getMessage(responseCode, null,null);
	 			 throw new FundTransferException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", fundTransferRequest);
	 	        }
	 		

	 		boolean result = false;
			if(CBSConstants.CREDIT.equals(fundTransferRequest.getTransferType())) {
				result = fundTransferDao.creditFundTransfer(fundTransferRequest);
			}else {
				result = fundTransferDao.debitFundTransfer(fundTransferRequest);
			}
			if (result) {
				fundTransferResponse.setResponseCode(messageSource.getMessage(fundTransferRequest.getTransferType() + ".000", null, "000", null));
				fundTransferResponse.setResponseMessage(messageSource
						.getMessage(fundTransferRequest.getTransferType() + ".response." + fundTransferResponse.getResponseCode(), null, null));
			} else {
				fundTransferResponse.setResponseCode(messageSource.getMessage(fundTransferRequest.getTransferType() + ".001", null, "001", null));
				fundTransferResponse
						.setResponseMessage(messageSource.getMessage(fundTransferResponse.getResponseCode(), null, null));
			}
			return fundTransferResponse;
	 }
}