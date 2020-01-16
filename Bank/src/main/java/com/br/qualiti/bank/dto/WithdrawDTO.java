package com.br.qualiti.bank.dto;

public class WithdrawDTO extends AbstractOperationDTO{

	public WithdrawDTO() {
		super();
	}

	public WithdrawDTO(Long customer_id, Long account_id, Double value) {
		super(customer_id, account_id, value);
	}
	
}
