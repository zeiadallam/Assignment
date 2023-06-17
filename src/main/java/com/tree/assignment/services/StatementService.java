package com.tree.assignment.services;

import com.tree.assignment.dto.StatementDto;

import java.util.List;

public interface StatementService {
    List<StatementDto> findAll();

    List<StatementDto> findByAccountId(int accountId);

    List<StatementDto> findByDateRange(String fromDate, String toDate);

    List<StatementDto> findByAmountRange(String amountFrom, String amountTo);
}
