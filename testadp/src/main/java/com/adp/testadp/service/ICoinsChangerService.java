package com.adp.testadp.service;

import com.adp.testadp.dto.DtoChangeReturn;
import com.adp.testadp.enums.CoinsPreference;

public interface ICoinsChangerService {

	public DtoChangeReturn changeBills(Long bill, CoinsPreference coinsPreference);
	
}
