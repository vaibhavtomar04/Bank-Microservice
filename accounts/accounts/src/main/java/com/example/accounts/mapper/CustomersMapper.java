package com.example.accounts.mapper;

import com.example.accounts.DTO.CustomersDto;
import com.example.accounts.entity.Customer;

public class CustomersMapper {

    public static CustomersDto mapToCustomersDto(Customer customer , CustomersDto customersDto) {
        customersDto.setName(customer.getName());
        customersDto.setEmail(customer.getEmail());
        customersDto.setMobileNumber(customer.getMobileNumber());
        return customersDto;
    }

    public static Customer mapToCustomer(CustomersDto customersDto , Customer customer) {
        customer.setName(customersDto.getName());
        customer.setEmail(customersDto.getEmail());
        customer.setMobileNumber(customersDto.getMobileNumber());
        return customer;
    }
}
