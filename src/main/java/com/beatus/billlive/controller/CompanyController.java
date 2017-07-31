package com.beatus.billlive.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.billlive.domain.model.CompanyData;
import com.beatus.billlive.service.CompanyService;
import com.beatus.billlive.utils.Constants;
import com.beatus.billlive.validation.exception.CompanyDataException;

@Controller
@RequestMapping("/api")
public class CompanyController {
	

	@Resource(name = "companyService")
	private CompanyService companyService;
	
	//For add and update company both
	@RequestMapping(value= "/company/company/add", method = RequestMethod.POST)
	public @ResponseBody String addCompany(@RequestBody CompanyData companyData, HttpServletRequest request, HttpServletResponse response) throws CompanyDataException{
		String companyId = companyService.addCompany(companyData);
		HttpSession session = request.getSession();
    	session.setAttribute(Constants.COMPANY_ID, companyId);
		return companyId;
	}
	
	@RequestMapping(value= "/company/company/update", method = RequestMethod.POST)
    public @ResponseBody String editCompany(@RequestBody CompanyData companyData, HttpServletRequest request, HttpServletResponse response) throws CompanyDataException{
    	String companyId = companyService.updateCompany(companyData);
    	HttpSession session = request.getSession();
    	session.setAttribute(Constants.COMPANY_ID, companyId);
		return companyId;
    }
    
    @RequestMapping("/company/company/remove/{id}")
    public @ResponseBody String removeCompany(@PathVariable("id") String companyId, HttpServletRequest request, HttpServletResponse response) throws CompanyDataException{	
    	if(StringUtils.isNotBlank(companyId)){
        	String isCompanyRemoved = companyService.removeCompany(companyId);
    		return isCompanyRemoved;
		}else{
			throw new CompanyDataException("companyId passed cant be null or empty string");
		}
    }
 
    
    @RequestMapping(value = "/company/getcompany/{id}", method = RequestMethod.GET)
	public @ResponseBody CompanyData getCompanyById(@PathVariable("id") String companyId, HttpServletRequest request, HttpServletResponse response) throws CompanyDataException {
    	
    	if(StringUtils.isNotBlank(companyId) && StringUtils.isNotBlank(companyId)){
	    	CompanyData companyData = companyService.getCompanyById(companyId);
			return companyData;
		}else{
			throw new CompanyDataException("companyId passed cant be null or empty string");
		}
		
	}
    
    @RequestMapping(value = "/company/getallcompanys", method = RequestMethod.GET)
	public @ResponseBody List<CompanyData> getAllCompanys(HttpServletRequest request, HttpServletResponse response) throws CompanyDataException {
    	HttpSession session = request.getSession();
    	String companyId = (String) session.getAttribute(Constants.COMPANY_ID);
    	if(StringUtils.isNotBlank(companyId)){
        	List<CompanyData> companyList = companyService.getAllCompanys(companyId);
			return companyList;
		}else{
			throw new CompanyDataException("companyId passed cant be null or empty string");
		}
	}

}
