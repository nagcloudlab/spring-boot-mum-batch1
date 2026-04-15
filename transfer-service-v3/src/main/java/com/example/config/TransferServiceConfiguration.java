package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.repository.AccountRepository;
import com.example.repository.JdbcAccountRepository;
import com.example.service.ImpsTransferService;
import com.example.service.TransferService;

@Configuration
public class TransferServiceConfiguration {

    @Bean
    public AccountRepository jdbAccountRepository() {
        return new JdbcAccountRepository();
    }

    @Bean
    public TransferService impsTransferService(AccountRepository accountRepository) {
        return new ImpsTransferService(accountRepository);
    }

}
