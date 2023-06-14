package com.tree.assignment.services;

import com.tree.assignment.dto.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> findAll();

    AccountDto findById(int id);

    List<AccountDto> findByAccountType(String accountType);
}
