package com.example.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.repository.AccountRepository;
import com.example.repository.JdbcAccountRepository;
import com.example.service.ImpsTransferService;
import com.example.service.TransferService;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class TransferServiceConfiguration {

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("mysecretpassword");
        dataSource.setMaximumPoolSize(15);
        return dataSource;
    }

    @Bean
    public AccountRepository jdbAccountRepository(DataSource dataSource) {
        return new JdbcAccountRepository(dataSource);
    }

    @Bean
    public TransferService impsTransferService(AccountRepository accountRepository) {
        return new ImpsTransferService(accountRepository);
    }

}
