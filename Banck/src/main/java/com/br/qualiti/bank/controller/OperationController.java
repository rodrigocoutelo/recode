package com.br.qualiti.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.qualiti.bank.dto.DepositDTO;
import com.br.qualiti.bank.dto.TransferDTO;
import com.br.qualiti.bank.dto.WithdrawDTO;
import com.br.qualiti.bank.exception.ResourceNotFoundException;
import com.br.qualiti.bank.model.Account;
import com.br.qualiti.bank.service.OperationService;

@RestController()
@RequestMapping("/api/v1/operation")
public class OperationController {

	private OperationService operationService;
	
	OperationController(OperationService operationService) {
		this.operationService = operationService;
	}
	
	@PostMapping(value="/deposit")
	public ResponseEntity deposit(@RequestBody DepositDTO depositDTO) {
		try {
			Account account = operationService.deposit(depositDTO);
			return ResponseEntity.ok().body(account);
		}catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(value="/withdraw")
	public ResponseEntity withdraw(@RequestBody WithdrawDTO withdrawDTO) {
		try {
			Account account = operationService.withdraw(withdrawDTO);
			return ResponseEntity.ok().body(account);
		}catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(value="/transfer")
	public ResponseEntity transfer(@RequestBody TransferDTO transferDTO) {
		try {
			Account account = operationService.transfer(transferDTO);
			return ResponseEntity.ok().body(account);
		}catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
