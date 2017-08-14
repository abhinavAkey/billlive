package com.beatus.billlive.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.billlive.domain.model.ItemData;
import com.beatus.billlive.service.ItemService;
import com.beatus.billlive.session.management.SessionModel;
import com.beatus.billlive.utils.BillliveMediaType;
import com.beatus.billlive.validation.ItemValidator;
import com.beatus.billlive.validation.exception.InventoryValidationException;
import com.beatus.billlive.validation.exception.ItemDataException;

@Controller
public class ItemController extends BaseController{
	

	@Resource(name = "itemService")
	private ItemService itemService;
	
	@Resource(name = "itemValidator")
	private ItemValidator itemValidator;
	
	//For add and update item both
	@RequestMapping(value= "/company/item/add", method = RequestMethod.POST, consumes = {BillliveMediaType.APPLICATION_JSON}, produces = {BillliveMediaType.APPLICATION_JSON})
	public @ResponseBody ItemData addItem(@RequestBody ItemData itemData, HttpServletRequest request, HttpServletResponse response) throws ItemDataException, InventoryValidationException{
		if(itemValidator.validateItemData(itemData)){
			SessionModel sessionModel = initSessionModel(request);
	    	String companyId = sessionModel.getCompanyId();
	    	ItemData itemDataAdded = itemService.addItem(itemData,companyId);
		return itemDataAdded;
		}else{
			throw new ItemDataException("Item data passed cant be null or empty string");
		}
	}
	
	@RequestMapping(value= "/company/item/update", method = RequestMethod.POST, consumes = {BillliveMediaType.APPLICATION_JSON}, produces = {BillliveMediaType.APPLICATION_JSON})
	public @ResponseBody ItemData updateItem(@RequestBody ItemData itemData, HttpServletRequest request, HttpServletResponse response) throws ItemDataException, InventoryValidationException{
		if(itemValidator.validateItemData(itemData)){
			SessionModel sessionModel = initSessionModel(request);
	    	String companyId = sessionModel.getCompanyId();
	    	ItemData isItemUpdated = itemService.updateItem(itemData,companyId);
		return isItemUpdated;
		}else{
			throw new ItemDataException("Item data passed cant be null or empty string");
		}
	}

    @RequestMapping(value ="/company/item/remove/{id}", method = RequestMethod.DELETE, consumes = {BillliveMediaType.APPLICATION_JSON}, produces = {BillliveMediaType.APPLICATION_JSON})
    public @ResponseBody String removeItem(@PathVariable("id") String itemId, HttpServletRequest request, HttpServletResponse response) throws ItemDataException{	
    	if(StringUtils.isNotBlank(itemId)){
    		SessionModel sessionModel = initSessionModel(request);
	    	String companyId = sessionModel.getCompanyId();
	    	String isItemRemoved = itemService.removeItem(companyId, itemId);
    		return isItemRemoved;
		}else{
			throw new ItemDataException("itemId passed cant be null or empty string");
		}
    }
 
    
    @RequestMapping(value = "/company/getitem/{id}", method = RequestMethod.GET, consumes = {BillliveMediaType.APPLICATION_JSON}, produces = {BillliveMediaType.APPLICATION_JSON})
	public @ResponseBody ItemData getItemById(@PathVariable("id") String itemId, HttpServletRequest request, HttpServletResponse response) throws ItemDataException {
    	SessionModel sessionModel = initSessionModel(request);
    	String companyId = sessionModel.getCompanyId();
    	if(StringUtils.isNotBlank(itemId) && StringUtils.isNotBlank(itemId)){
	    	ItemData itemData = itemService.getItemById(companyId, itemId);
			return itemData;
		}else{
			throw new ItemDataException("itemId passed cant be null or empty string");
		}
		
	}
    
    @RequestMapping(value = "/company/getallitems", method = RequestMethod.GET, consumes = {BillliveMediaType.APPLICATION_JSON}, produces = {BillliveMediaType.APPLICATION_JSON})
	public @ResponseBody List<ItemData> getAllItems(HttpServletRequest request, HttpServletResponse response) throws ItemDataException {
    	SessionModel sessionModel = initSessionModel(request);
    	String companyId = sessionModel.getCompanyId();
    	if(StringUtils.isNotBlank(companyId)){
        	List<ItemData> itemList = itemService.getAllItems(companyId);
			return itemList;
		}else{
			throw new ItemDataException("itemId passed cant be null or empty string");
		}
	}

}
