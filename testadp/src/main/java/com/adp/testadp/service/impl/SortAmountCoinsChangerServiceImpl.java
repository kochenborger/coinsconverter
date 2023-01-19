package com.adp.testadp.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import com.adp.testadp.coins.Vault;
import com.adp.testadp.dto.DtoChangeReturn;
import com.adp.testadp.enums.CoinsPreference;
import com.adp.testadp.service.ICoinsChangerService;
import com.adp.testadp.service.factory.PreferencesCoinFactory;
import com.adp.testadp.validation.BillValidation;

@Service
public class SortAmountCoinsChangerServiceImpl implements ICoinsChangerService{

	private Vault vault;

	private BillValidation coinValidation;
	
	private PreferencesCoinFactory preferencesCoinFactory;
	
	public SortAmountCoinsChangerServiceImpl(Vault vault, BillValidation coinValidation, PreferencesCoinFactory preferencesCoinFactory) {
		this.vault = vault;
		this.coinValidation = coinValidation;
		this.preferencesCoinFactory = preferencesCoinFactory;
	}
	
	@Override
	public DtoChangeReturn changeBills(Long bill, CoinsPreference coinsPreference) {
		
		//Initial validations
		coinValidation.validateBillAllow(bill);
		coinValidation.validateCoinsQuantity(bill);
		
		DtoChangeReturn returnValues = new DtoChangeReturn();
		
		
		List<BigDecimal> sortedCoins = preferencesCoinFactory.sortCoinsByPreference(coinsPreference);
		
		
		BigDecimal sumCoins = new BigDecimal("0.00");
		for (BigDecimal coin : sortedCoins) {
		
			BigDecimal remainingValue = new BigDecimal(bill).subtract(sumCoins);
			BigDecimal coinsNecessary = remainingValue.divideToIntegralValue(coin);
			
			Long qtdCoinsAvailable = vault.getCoins().get(coin);

			if (coinsNecessary.compareTo(new BigDecimal(qtdCoinsAvailable)) <= 0) {
				//if the quantity necessary is less or equals than  the available quantity of coins
				vault.subCoins(coin, coinsNecessary.longValue());
				sumCoins = coin.multiply(coinsNecessary).add(sumCoins);
				returnValues.getCoinsUsed().put(coin, coinsNecessary.intValue());
				
				break;
			} else {
				//if the quantity necessary is bigger than the available quantity, is necessary look to other coin
				vault.subCoins(coin, qtdCoinsAvailable);
				sumCoins = coin.multiply(new BigDecimal(qtdCoinsAvailable)).add(sumCoins);
				returnValues.getCoinsUsed().put(coin, qtdCoinsAvailable.intValue());
			}
			
		}
		
		returnValues.setCoinsAvailable(vault.getCoins());
		return returnValues;
	}

}
