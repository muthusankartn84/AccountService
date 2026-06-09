package com.example.AccountService.mapper;

import com.example.AccountService.Dto.TransactionRequest;
import com.example.AccountService.Entity.Transaction;

public class TransactionMapper {
    public static Transaction map(final TransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setEventId(request.getEventId());
        transaction.setType(request.getType());
        transaction.setAccountId(request.getAccountId());
        transaction.setAmount(request.getAmount());
        transaction.setEventTimestamp(request.getEventTimestamp());
        return transaction;
    }
}
