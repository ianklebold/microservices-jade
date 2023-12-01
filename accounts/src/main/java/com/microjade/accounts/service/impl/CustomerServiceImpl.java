package com.microjade.accounts.service.impl;

import com.microjade.accounts.dto.CustomerDto;
import com.microjade.accounts.entity.Customer;
import com.microjade.accounts.exception.CustomerAlreadyExistsException;
import com.microjade.accounts.mapper.CustomerMapper;
import com.microjade.accounts.repository.CustomerRepository;
import com.microjade.accounts.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByMobileNumber(customerDto.getMobileNumber());

        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber" + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        return customerRepository.save(customer);
    }
}
