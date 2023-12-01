package com.microjade.accounts.service;

import com.microjade.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Create account.
     *
     * @param customerDto the customer dto
     */
    void createAccount(CustomerDto customerDto);


    /**
     * Gets account.
     *
     * @param mobileNumber the mobile number
     * @return the account
     */
    CustomerDto getAccount(String mobileNumber);

}
