package com.microjade.accounts.service;

import com.microjade.accounts.dto.CustomerDto;
import com.microjade.accounts.entity.Customer;

public interface ICustomerService {

    Customer createCustomer(CustomerDto customerDto);
}
