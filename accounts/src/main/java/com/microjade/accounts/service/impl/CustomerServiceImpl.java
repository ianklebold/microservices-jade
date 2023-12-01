package com.microjade.accounts.service.impl;

import com.microjade.accounts.dto.CustomerDto;
import com.microjade.accounts.entity.Customer;
import com.microjade.accounts.exception.CustomerAlreadyExistsException;
import com.microjade.accounts.exception.ResourceNotFoundException;
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

    @Override
    public Customer getCustomerByMobile(String mobileNumber) {
        return customerRepository.findCustomerByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
                );
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer","customerId",customerId.toString())
                );
    }

    @Override
    public void updateCustomer(CustomerDto customerDto, Long customerId) {
        Customer customer = getCustomerById(customerId);

        //Se actualiza customer con customerDto
        CustomerMapper.mapToCustomer(customerDto,customer);
        customerRepository.save(customer);
    }
}
