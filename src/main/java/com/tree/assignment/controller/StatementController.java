package com.tree.assignment.controller;

import com.tree.assignment.dto.StatementDto;
import com.tree.assignment.services.StatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statement/")
public class StatementController {

    private final StatementService statementService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StatementDto>> getAllStatementByAccountId(@PathVariable int id) {
        List<StatementDto> accountDtos = statementService.findByAccountId(id);
        return ResponseEntity.ok(accountDtos);

    }

    @GetMapping("/date-range")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StatementDto>> getStatementDateRange(
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate) {
        List<StatementDto> statementDtoList = statementService.findByDateRange(fromDate, toDate);
        return ResponseEntity.ok(statementDtoList);
    }

    @GetMapping("/amount-range")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<StatementDto>> getStatementByAmountRange(
            @RequestParam(value = "fromAmount") String fromAmount,
            @RequestParam(value = "toAmount") String toAmount) {
        List<StatementDto> statementDtoList = statementService.findByAmountRange(fromAmount, toAmount);
        return ResponseEntity.ok(statementDtoList);
    }

}

