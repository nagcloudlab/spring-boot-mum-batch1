package com.example.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;

import com.example.repository.AccountRepository;
import com.example.repository.JdbcAccountRepository;
import com.example.service.ImpsTransferService;
import com.example.service.TransferService;

@Configuration
@Import(DatasourceConfiguration.class)
public class TransferServiceConfiguration {

    @Bean(name = "jdbcAccountRepository")
    @Description("Configures the JDBC-based AccountRepository.")
    public AccountRepository jdbcAccountRepository(DataSource dataSource) {
        return new JdbcAccountRepository(dataSource);
    }

    @Bean(name = "impsTransferService")
    @Description("Configures the IMPS TransferService implementation.")
    public TransferService impsTransferService(AccountRepository accountRepository) {
        return new ImpsTransferService(accountRepository);
    }

}
