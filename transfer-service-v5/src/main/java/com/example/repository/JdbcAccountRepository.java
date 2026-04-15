package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.slf4j.Logger;

import com.example.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * Repository class for managing accounts using JDBC.
 * author: team-1
 * 
 */

@Component
public class JdbcAccountRepository implements AccountRepository {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger("txr-service");

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        logger.info("JdbcAccountRepository initialized with JdbcTemplate: {}", jdbcTemplate);
    }

    public Account findByNumber(String number) {
        logger.info("Finding account by number: {}", number);
        // SQL query to find account by number
        String sql = "SELECT number, holder_name, balance FROM accounts WHERE number = ?";
        return  jdbcTemplate.queryForObject(sql,(rs, rowNum) -> {
            Account account = new Account(
            rs.getString("number"),
            rs.getString("holder_name"),
            rs.getDouble("balance"));
            logger.info("Account found: {}", account);
            return account;
        },number);
    }

    public void update(Account account) {
        logger.info("Updating account: {}", account);
        // SQL query to update account balance
        String sql = "UPDATE accounts SET balance = ? WHERE number = ?";
        jdbcTemplate.update(sql, account.getBalance(), account.getNumber());
        logger.info("Account updated successfully: {}", account);
    }

}
