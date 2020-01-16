package com.br.qualiti.bank.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.qualiti.bank.dto.AbstractOperationDTO;
import com.br.qualiti.bank.dto.DepositDTO;
import com.br.qualiti.bank.dto.TransferDTO;
import com.br.qualiti.bank.dto.WithdrawDTO;
import com.br.qualiti.bank.exception.ResourceNotFoundException;
import com.br.qualiti.bank.model.Account;
import com.br.qualiti.bank.model.Customer;
import com.br.qualiti.bank.repository.AccountRepository;
import com.br.qualiti.bank.repository.CustomerRepository;

@Service
public class OperationService {
	
	public enum OperationType
	{
		Deposit,
		Withdraw,
		Transfer
	}

	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;
	
	public OperationService(CustomerRepository customerRepository, AccountRepository accountRepository)
	{
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
		
	}
	
	private Account AccountOperation(OperationType operationType, AbstractOperationDTO operationDto)
	{
		Optional<Customer> customer = customerRepository.findById(operationDto.getCustomer_id());
		if(customer.isPresent())
		{
			Account selectAccount = null;
			for (Account account : customer.get().getAccounts()) {
				if(account.getId() == operationDto.getAccount_id())
				{
					selectAccount = account;
					break;
				}
			}
			
			if(selectAccount == null)
			{
				throw new ResourceNotFoundException("Customer", "Client", "Conta não associado ao cliente");	
			}
			if(operationType == OperationType.Deposit)
			{
				selectAccount.setBalance(selectAccount.getBalance() + operationDto.getValue());
			}else if(operationType == OperationType.Withdraw)
			{
				selectAccount.setBalance(selectAccount.getBalance() - operationDto.getValue());
			}
			return accountRepository.save(selectAccount);
		}else
		{
			throw new ResourceNotFoundException("Customer", "Client", "O cliente com id:"+operationDto.getCustomer_id()+" não encontrado");
		}
	}

	public Account deposit(DepositDTO depositDTO) {
		return AccountOperation(OperationType.Deposit, depositDTO);
	}

	public Account withdraw(WithdrawDTO withdrawDTO) {
		return AccountOperation(OperationType.Withdraw, withdrawDTO);
	}
	
	
	public Account transfer(TransferDTO transferDTO) {
		Account sourceAccount = withdraw(new WithdrawDTO(transferDTO.getSource_customer_id(), transferDTO.getSource_account_id(), transferDTO.getValue()));
		Account targetAccount = deposit(new DepositDTO(transferDTO.getTarger_customer_id(), transferDTO.getTarger_account_id(), transferDTO.getValue()));
		return 	sourceAccount;	
	}
	
	
}

