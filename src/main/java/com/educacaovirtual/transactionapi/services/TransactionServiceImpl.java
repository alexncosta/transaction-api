package com.educacaovirtual.transactionapi.services;

import com.educacaovirtual.transactionapi.domain.dtos.TransactionDTO;
import com.educacaovirtual.transactionapi.domain.dtos.TransactionPageResponse;
import com.educacaovirtual.transactionapi.domain.entities.Transaction;
import com.educacaovirtual.transactionapi.repositories.TransactionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction save(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDTO, transaction);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(UUID id) {
        return transactionRepository.findById(id).get();
    }

    @Override
    public Transaction update(UUID id, TransactionDTO transactionDTO) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (!transaction.isPresent()) {
            throw new RuntimeException("Transaction not found");
        }
        BeanUtils.copyProperties(transactionDTO, transaction.get());
        return transactionRepository.save(transaction.get());
    }

    @Override
    public TransactionPageResponse findAllTransactionsWithPagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        List<TransactionDTO> listTransactions = getTransactionDTOS(transactions);
        return new TransactionPageResponse(
                listTransactions,
                pageNumber,
                pageSize,
                (int) transactions.getTotalElements(),
                transactions.getTotalPages(),
                transactions.isLast());
    }

    private static List<TransactionDTO> getTransactionDTOS(Page<Transaction> transactions) {
        List<Transaction> transactionsPage = transactions.getContent();
        List<TransactionDTO> listTransactions = new ArrayList<>();
        for (Transaction transaction : transactionsPage) {
            TransactionDTO dto = new TransactionDTO(
                    transaction.getTotal(),
                    transaction.getCreatedAt(),
                    transaction.getCardNumber(),
                    transaction.getCcv(),
                    transaction.getOwner(),
                    transaction.getEin()
            );
            listTransactions.add(dto);
        }
        return listTransactions;
    }

    @Override
    public TransactionPageResponse findAllTransactionsWithPaginationAndSorting(
            Integer pageNumber, Integer pageSize, String sortBy, String direction) {
        Sort sort = sortBy.equalsIgnoreCase("asc") ? Sort.by(direction).ascending() : Sort.by(direction).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        List<TransactionDTO> listTransactions = getTransactionDTOS(transactions);
        return new TransactionPageResponse(
                listTransactions,
                pageNumber,
                pageSize,
                (int) transactions.getTotalElements(),
                transactions.getTotalPages(),
                transactions.isLast());
    }
}
