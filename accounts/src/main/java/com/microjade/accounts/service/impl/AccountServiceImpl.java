package com.microjade.accounts.service.impl;

import com.microjade.accounts.constants.AccountsConstants;
import com.microjade.accounts.dto.CustomerDto;
import com.microjade.accounts.entity.Accounts;
import com.microjade.accounts.entity.Customer;
import com.microjade.accounts.repository.AccountsRepository;
import com.microjade.accounts.service.IAccountsService;
import com.microjade.accounts.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private ICustomerService customerService;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer savedCustomer = customerService.createCustomer(customerDto);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 10000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedBy("Anonymous");
        newAccount.setCreatedAt(LocalDateTime.now());
        return newAccount;
    }


}
