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
import com.microjade.accounts.repository.CustomerRepository;
import com.microjade.accounts.service.IAccountsService;
import com.microjade.accounts.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
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

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = Boolean.FALSE;

        AccountsDto accountsDto = customerDto.getAccountsDto();

        if (Objects.nonNull(accountsDto)){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );

            //Se actualiza accounts con accountsDto
            AccountsMapper.mapToAccounts(accountsDto,accounts);
            accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();

            //Se actualiza customer con customerDto
            customerService.updateCustomer(customerDto,customerId);
            isUpdated = Boolean.TRUE;
        }
        return isUpdated;
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
