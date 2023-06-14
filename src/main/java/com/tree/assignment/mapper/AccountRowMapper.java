package com.tree.assignment.mapper;

import com.tree.assignment.dto.AccountDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<AccountDto> {

    @Override
    public AccountDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            AccountDto accountDto = new AccountDto();
            accountDto.setId(rs.getInt("ID"));
            accountDto.setAccount_type(rs.getString("account_type"));
            accountDto.setAccount_number(rs.getString("account_number"));
            return accountDto;

    }
}
