package com.adp.testadp.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adp.testadp.controller.IBillsChangeController;
import com.adp.testadp.dto.DtoChangeReturn;
import com.adp.testadp.enums.CoinsPreference;
import com.adp.testadp.service.ICoinsChangerService;

@RestController
@RequestMapping(path = "/bills")
public class BillsChangeController implements IBillsChangeController{

	@Autowired
	private ICoinsChangerService coinChangerService;
	
	@PutMapping("/{bill}/preference/{coinsPreference}")
	@Override
	public DtoChangeReturn changeBills(@PathVariable Long bill, @PathVariable CoinsPreference coinsPreference) {
		
		return coinChangerService.changeBills(bill, coinsPreference);
	}

}
