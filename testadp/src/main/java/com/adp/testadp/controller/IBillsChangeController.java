package com.adp.testadp.controller;

import com.adp.testadp.dto.DtoChangeReturn;
import com.adp.testadp.enums.CoinsPreference;

public interface IBillsChangeController {

	public DtoChangeReturn changeBills(Long bill, CoinsPreference coinsPreference);
	
}
