package com.adp.testadp.validation;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import com.adp.testadp.coins.Vault;
import com.adp.testadp.exception.BillNotAllow;
import com.adp.testadp.exception.CoinsNotEnougth;

@Component
public class BillValidation {

	private Vault vault;

	public BillValidation(Vault vault) {
		this.vault = vault;
	}
	
	public Boolean validateBillAllow(Long bill) {
		vault.getBillsAllow()
				.stream()
				.filter(billAllow -> billAllow.equals(bill))
				.findAny()
				.orElseThrow(() -> new BillNotAllow(bill));
		
		return Boolean.TRUE;
	}

	public Boolean validateCoinsQuantity(Long bill) {
		BigDecimal sum = vault.sumCoinsAvailable();
		
		if (sum.compareTo(new BigDecimal(bill)) < 0) {
			throw new CoinsNotEnougth(sum, bill);
		}
		
		return Boolean.TRUE;
	}

}
