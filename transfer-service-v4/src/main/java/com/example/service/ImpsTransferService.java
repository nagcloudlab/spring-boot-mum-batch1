package com.example.service;

import org.slf4j.Logger;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Service class for handling IMPS transfers between accounts.
 * author: team-2
 * 
 */

@Component
public class ImpsTransferService implements TransferService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger("txr-service");

    private AccountRepository accountRepository;

    public ImpsTransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        logger.info("ImpsTransferService instance created with AccountRepository: {}",
                accountRepository.getClass().getSimpleName());
    }

    @Override
    @Transactional
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        logger.info("Initiating transfer of amount {} from account {} to account {}", amount, fromAccountNumber,
                toAccountNumber);
        // step-1: Load accounts from the repository
        Account fromAccount = accountRepository.findByNumber(fromAccountNumber);
        Account toAccount = accountRepository.findByNumber(toAccountNumber);
        // step-2: check if accounts exist and have sufficient balance
        if (fromAccount == null) {
            logger.error("Source account not found: {}", fromAccountNumber);
            throw new IllegalArgumentException("Source account not found");
        }
        if (toAccount == null) {
            logger.error("Destination account not found: {}", toAccountNumber);
            throw new IllegalArgumentException("Destination account not found");
        }
        if (fromAccount.getBalance() < amount) {
            logger.error(
                    "Insufficient funds in the source account: fromAccount={}, availableBalance={}, requestedAmount={}",
                    fromAccountNumber, fromAccount.getBalance(), amount);
            throw new IllegalArgumentException("Insufficient funds in the source account");
        }
        // step-3: debit from the source account and credit to the destination account
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        logger.info("Debited amount {} from account {}", amount, fromAccountNumber);
        toAccount.setBalance(toAccount.getBalance() + amount);
        logger.info("Credited amount {} to account {}", amount, toAccountNumber);
        // step-4: update the accounts in the repository
        accountRepository.update(fromAccount);

        boolean b=false;
        if(b){
            throw new RuntimeException("Simulated failure after debiting from source account and before crediting to destination account");
        }

        accountRepository.update(toAccount);
        logger.info("Transfer completed successfully: amount {} from account {} to account {}", amount,
                fromAccountNumber,
                toAccountNumber);
    }

}
