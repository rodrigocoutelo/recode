package com.br.qualiti.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.qualiti.bank.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
