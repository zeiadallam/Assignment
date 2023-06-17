package com.tree.assignment.services;

import com.tree.assignment.dto.StatementDto;
import com.tree.assignment.mapper.StatementRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hsqldb.HsqlException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

import static com.tree.assignment.utitlity.Constant.STATEMENT_SELECT_ALL_SQL;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatementServiceImpl implements StatementService {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<StatementDto> findAll() {
        return jdbcTemplate.query(STATEMENT_SELECT_ALL_SQL, new StatementRowMapper());
    }

    @Override
    public List<StatementDto> findByAccountId(int accountId) {
        List<StatementDto> statementDtos = jdbcTemplate.query(
                STATEMENT_SELECT_ALL_SQL + "  WHERE account_id = ?",
                new Object[]{accountId}, new StatementRowMapper());
        if (!statementDtos.isEmpty()) {
            return statementDtos;
        } else {
            throw new EmptyResultDataAccessException(String.valueOf(accountId), 0);
        }
    }

    @Override
    public List<StatementDto> findByDateRange(String fromDate, String toDate) {
        try {
            LocalDate fromDateValue;
            LocalDate toDateValue;
            if (fromDate == null && toDate == null) {
                LocalDate currentDate = LocalDate.now();
                toDateValue = LocalDate.now();
                // Subtract three months from the current date
                fromDateValue = currentDate.minus(3, ChronoUnit.MONTHS);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
                fromDateValue = LocalDate.parse(fromDate, formatter);
                toDateValue = LocalDate.parse(toDate, formatter);
            }


            String sql = "SELECT * FROM statement " +
                    "WHERE " +
                    "CAST(SUBSTRING(datefield, 7, 4) || '-' || SUBSTRING(datefield, 4, 2) || '-' || " +
                    "SUBSTRING(datefield, 1, 2) AS DATE) " +
                    "BETWEEN DATE  '" + fromDateValue + "'  AND DATE '" + toDateValue + "'";

            List<StatementDto> query = jdbcTemplate.query(sql, new StatementRowMapper());
            if (!query.isEmpty()) {
                return query;
            } else {
                throw new EmptyResultDataAccessException("No result found for period " + fromDate + " to " + toDate, 0);
            }

        } catch (DateTimeParseException e) {
            log.error("error on parse date");
            assert toDate != null;
            throw new DateTimeParseException("dd.MM.yyyy", toDate, 0);
        }

    }

    @Override
    public List<StatementDto> findByAmountRange(String amountFrom, String amountTo) {
        try {
            String sql = "SELECT * FROM statement WHERE CAST(amount AS DECIMAL(20,10)) BETWEEN ? AND ?";
            List<StatementDto> query = jdbcTemplate.query(sql, new Object[]{amountFrom, amountTo},
                    new StatementRowMapper());
            if (!query.isEmpty()) {
                return query;
            } else {
                throw new EmptyResultDataAccessException("No result found for range Amount "
                        + amountFrom + " to " + amountTo, 0);
            }
        } catch (HsqlException e) {
            e.printStackTrace();
            throw new HsqlException(new Throwable(), "one of your amount value not valid", 0);
        }


    }
}
