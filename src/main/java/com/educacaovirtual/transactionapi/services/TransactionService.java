package com.educacaovirtual.transactionapi.services;

import com.educacaovirtual.transactionapi.domain.dtos.TransactionDTO;
import com.educacaovirtual.transactionapi.domain.dtos.TransactionPageResponse;
import com.educacaovirtual.transactionapi.domain.entities.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction save(TransactionDTO transactionDTO);
    List<Transaction> findAll();
    Transaction findById(UUID id);
    Transaction update(UUID id, TransactionDTO transactionDTO);
    TransactionPageResponse findAllTransactionsWithPagination(Integer pageNumber, Integer pageSize);

    TransactionPageResponse findAllTransactionsWithPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String direction);
}
