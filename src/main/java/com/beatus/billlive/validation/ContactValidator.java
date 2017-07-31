package com.beatus.billlive.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.beatus.billlive.domain.model.ContactInfo;
import com.beatus.billlive.validation.exception.ContactInfoException;

@Component("contactValidator")
public class ContactValidator {
	
	public boolean validateContactInfo(ContactInfo contactData) throws ContactInfoException{
		if(contactData == null || StringUtils.isBlank(contactData.getContactId())){
			throw new com.beatus.billlive.validation.exception.ContactInfoException("ContactInfo data is null");
		}
		if(StringUtils.isBlank(contactData.getContactId())){
			throw new ContactInfoException("UId is not available " );
		}
		if(StringUtils.isBlank(contactData.getFirstName())){
			throw new ContactInfoException("Contact First Name is not available " + contactData.getContactId());
		}
		if(StringUtils.isBlank(contactData.getLastName())){
			throw new ContactInfoException("Contact Last Name is not available " + contactData.getContactId());
		}
		if(StringUtils.isBlank(contactData.getCompanyName())){
			throw new ContactInfoException("Company is not available " + contactData.getContactId());
		}
		if(StringUtils.isBlank(contactData.getAddress())){
			throw new ContactInfoException("Address is not available " + contactData.getContactId());
		}
		if(StringUtils.isBlank(contactData.getCountry())){
			throw new ContactInfoException("Country is not available " + contactData.getContactId());
		}
		if(StringUtils.isBlank(contactData.getState())){
			throw new ContactInfoException("State is not available " + contactData.getContactId());
		}
		if(StringUtils.isBlank(contactData.getDistrict())){
			throw new ContactInfoException("Company is not available " + contactData.getContactId());
		}
		if(StringUtils.isBlank(contactData.getCity())){
			throw new ContactInfoException("Company is not available " + contactData.getContactId());
		}
		if(StringUtils.isBlank(contactData.getEmail())){
			throw new ContactInfoException("Email is not available " + contactData.getContactId());
		}
		return true;
		
	}
}