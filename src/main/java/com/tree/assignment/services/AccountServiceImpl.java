package com.tree.assignment.services;

import com.tree.assignment.dto.AccountDto;
import com.tree.assignment.mapper.AccountRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tree.assignment.utitlity.Constant.SELECT_ALL_SQL;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<AccountDto> findAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new AccountRowMapper());

    }

    @Override
    public List<AccountDto> findByAccountType(String accountType) {
        return jdbcTemplate.query(
                SELECT_ALL_SQL + "  WHERE account_type = ?",
                new Object[]{accountType}, new AccountRowMapper());
    }

    @Override
    public AccountDto findById(int id) {
        return jdbcTemplate.queryForObject(
                SELECT_ALL_SQL + "WHERE ID = ?",
                new Object[]{id}, new AccountRowMapper());

    }


}
