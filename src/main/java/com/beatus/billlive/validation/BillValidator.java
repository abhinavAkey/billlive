package com.beatus.billlive.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.beatus.billlive.domain.model.BillDTO;
import com.beatus.billlive.domain.model.ItemDTO;
import com.beatus.billlive.validation.exception.BillDataException;

@Component("billValidator")
public class BillValidator {
	
	public boolean validateBillData(BillDTO bill) throws BillDataException{
		if(bill == null || StringUtils.isBlank(bill.getUid())){
			throw new BillDataException("Bill data is null");
		}
		if(StringUtils.isBlank(bill.getBillFrom())){
			throw new BillDataException("Bill data the From field is not available " + bill.getUid());
		}
		if(StringUtils.isBlank(bill.getBillTo())){
			throw new BillDataException("Bill data the To field is not available " + bill.getUid());
		}
		if(StringUtils.isBlank(bill.getDateOfBill())){
			throw new BillDataException("Bill data the date field is not available " + bill.getUid());
		}
		if(StringUtils.isBlank(bill.getDueDate())){
			throw new BillDataException("Bill data the due date field is not available " + bill.getUid());
		}
		if(bill.getItems() != null && bill.getItems().size() > 0){
			for(ItemDTO item : bill.getItems()){
				if(StringUtils.isBlank(item.getItemId())){
					throw new BillDataException("Item Id can't be null");
				}
				if(StringUtils.isBlank(item.getIsTaxeble())){
					throw new BillDataException("Is taxeble can't be null " + item.getItemId());
				}
				if(StringUtils.isBlank(item.getProductValue())){
					throw new BillDataException("Product Value can't be null " + item.getItemId());
				}
				if(StringUtils.isBlank(item.getTotalAmount())){
					throw new BillDataException("Product Value can't be null " + item.getItemId());
				}
				if(StringUtils.isBlank(item.getTaxId())){
					throw new BillDataException("Tax Id can't be null " + item.getItemId());
				}
			}
		}else {
			throw new BillDataException("In Bill the items data is not available");
		}		
		return true;
	}
}
