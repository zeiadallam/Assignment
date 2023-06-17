package com.tree.assignment.mapper;

import com.tree.assignment.dto.StatementDto;
import org.springframework.jdbc.core.RowMapper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatementRowMapper implements RowMapper<StatementDto> {

    @Override
    public StatementDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        StatementDto accountDto = new StatementDto();
        accountDto.setId(rs.getInt("ID"));
        accountDto.setAmount(rs.getString("amount"));
        accountDto.setAccountId(hashAccountId(rs.getInt("account_id")));
        accountDto.setDateField(rs.getString("datefield"));
        return accountDto;

    }

    private String hashAccountId(int id) {
        try {

            // Create SHA-256 MessageDigest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Convert the ID to a string and get its bytes
            byte[] idBytes = String.valueOf(id).getBytes(StandardCharsets.UTF_8);
            // Calculate the hash value
            byte[] hashBytes = digest.digest(idBytes);
            // Convert the hash value to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
        return null;
    }
}
