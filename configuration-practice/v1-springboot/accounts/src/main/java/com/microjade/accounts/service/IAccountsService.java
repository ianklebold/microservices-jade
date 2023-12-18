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

    /**
     * Update account boolean.
     *
     * @param customerDto the customer dto
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

}
