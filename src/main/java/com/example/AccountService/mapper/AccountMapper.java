package com.example.AccountService.mapper;

import com.example.AccountService.Dto.TransactionRequest;
import com.example.AccountService.Entity.Account;

import java.math.BigDecimal;

public class AccountMapper {
    public static Account map (final TransactionRequest request) {
        Account account = new Account();
        account.setAccountId(request.getAccountId());
        account.setBalance(BigDecimal.ZERO);
        return account;
    }
}
