package com.educacaovirtual.transactionapi.domain.dtos;

import com.educacaovirtual.transactionapi.domain.entities.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public record TransactionPageResponse(List<TransactionDTO> transactionsDTO,
                                      Integer pagNumber,
                                      Integer pageSize,
                                      int totalElements,
                                      int totalPages,
                                      boolean isLast) {
}
