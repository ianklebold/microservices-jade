package com.microjade.accounts.service.impl;

import com.microjade.accounts.constants.AccountsConstants;
import com.microjade.accounts.dto.AccountsDto;
import com.microjade.accounts.dto.CustomerDto;
import com.microjade.accounts.entity.Accounts;
import com.microjade.accounts.entity.Customer;
import com.microjade.accounts.exception.ResourceNotFoundException;
import com.microjade.accounts.mapper.AccountsMapper;
import com.microjade.accounts.mapper.CustomerMapper;
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

    @Override
    public CustomerDto getAccount(String mobileNumber) {
        Customer customer = customerService.getCustomerByMobile(mobileNumber);

        Accounts accounts = accountsRepository.findAccountsByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
                );

        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(accountsDto);

        return customerDto;
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
