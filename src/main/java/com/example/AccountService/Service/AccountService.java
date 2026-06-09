package com.example.AccountService.Service;

import com.example.AccountService.Dto.TransactionRequest;
import com.example.AccountService.Entity.Account;
import com.example.AccountService.Entity.Transaction;
import com.example.AccountService.Repository.AccountRepository;
import com.example.AccountService.Repository.TransactionRepository;
import com.example.AccountService.mapper.AccountMapper;
import com.example.AccountService.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    public AccountService(final AccountRepository accountRepository,final TransactionRepository transactionRepository) {
        this.accountRepository=accountRepository;
        this.transactionRepository=transactionRepository;
    }
    @Transactional
        public void applyTransaction(TransactionRequest request) {
            Account records = AccountMapper.map(request);

            Account account =
                    accountRepository
                            .findByAccountId(request.getAccountId())
                            .orElseGet(() ->
                                    accountRepository.save(records
                                    ));

            if ("CREDIT".equalsIgnoreCase(request.getType())) {

                account.setBalance(
                        account.getBalance().add(request.getAmount()));

            } else {

                account.setBalance(
                        account.getBalance().subtract(request.getAmount()));
            }

            accountRepository.save(account);

            transactionRepository.save(TransactionMapper.map(request));
        }

        public BigDecimal getBalance(String accountId) {

            return accountRepository.findByAccountId(accountId)
                    .map(Account::getBalance)
                    .orElse(BigDecimal.ZERO);
        }
    }
