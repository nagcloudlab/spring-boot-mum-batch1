package com.example.repository;

import com.example.model.Account;

public interface AccountRepository {
    public Account findByNumber(String number);

    public void update(Account account);
}
