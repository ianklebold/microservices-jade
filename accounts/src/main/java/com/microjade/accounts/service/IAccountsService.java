package com.microjade.accounts.service;

import com.microjade.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Create account.
     *
     * @param customerDto the customer dto
     */
    void createAccount(CustomerDto customerDto);
}
