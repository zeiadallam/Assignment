package com.tree.assignment.controller;

import com.tree.assignment.dto.AccountDto;
import com.tree.assignment.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account/")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccount() {
        List<AccountDto> accountDtos = accountService.findAll();
        return ResponseEntity.ok(accountDtos);

    }

    @GetMapping("account-id/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") int id) {
        AccountDto accountDto = accountService.findById(id);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("type/{accountType}")
    public ResponseEntity<List<AccountDto>> getByAccountType(@PathVariable("accountType") String accountType) {
        List<AccountDto> byAccountType = accountService.findByAccountType(accountType);
        return ResponseEntity.ok(byAccountType);

    }
}

