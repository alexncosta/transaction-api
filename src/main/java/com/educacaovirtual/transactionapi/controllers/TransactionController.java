package com.educacaovirtual.transactionapi.controllers;

import com.educacaovirtual.transactionapi.domain.dtos.TransactionDTO;
import com.educacaovirtual.transactionapi.domain.dtos.TransactionPageResponse;
import com.educacaovirtual.transactionapi.domain.entities.Transaction;
import com.educacaovirtual.transactionapi.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    public final String PAGE_NUMBER = "0";
    public final String PAGE_SIZE = "3";
    public final String SORT_BY = "";
    public final String DIRECTION = "asc";

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transactionDTO));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.findById(id));
    }

    @GetMapping("/allTransactionsPagination")
    public ResponseEntity<TransactionPageResponse> findAllTransactionsWithPagination(
            @RequestParam(defaultValue = PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = PAGE_SIZE, required = false) Integer pageSize
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.findAllTransactionsWithPagination(pageNumber, pageSize));
    }

    @GetMapping("/allTransactionsSort")
    public ResponseEntity<TransactionPageResponse> findAllTransactionsWithPaginationAndSorting(
            @RequestParam(defaultValue = PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = SORT_BY, required = false) String sort,
            @RequestParam(defaultValue = DIRECTION, required = false) String direction
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.findAllTransactionsWithPaginationAndSorting(pageNumber, pageSize, sort, direction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable("id") UUID id, @RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.update(id, transactionDTO));
    }
}
