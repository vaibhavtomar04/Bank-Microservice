package com.example.accounts.Service.Impl;

import com.example.accounts.DTO.AccountsDto;
import com.example.accounts.DTO.CustomersDto;
import com.example.accounts.Repo.AccountRepo;
import com.example.accounts.Repo.CustomerRepo;
import com.example.accounts.Service.IAccountsService;
import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.entity.Account;
import com.example.accounts.entity.Customer;
import com.example.accounts.exceptions.CustomerAlreadyExistsExceptions;
import com.example.accounts.exceptions.ResourceNotFoundException;
import com.example.accounts.mapper.AccountsMapper;
import com.example.accounts.mapper.CustomersMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountRepo accountRepo;
    private CustomerRepo customerRepo;
    /**
     *
     * @param customersDto - CustomersDto Object
     */
    @Override
    public void createAccount(CustomersDto customersDto) {
        Customer customer = CustomersMapper.mapToCustomer(customersDto , new Customer());
        Optional<Customer> optionalCustomer = customerRepo.findByMobileNumber(customersDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsExceptions(
                    "Customer with mobile number " + customersDto.getMobileNumber() + " already exists"
            );
        }
        Customer savedCustomer =customerRepo.save(customer);
        accountRepo.save(createNewAccount(savedCustomer));
    }

    /**
     *
     * @param customer - Customer Object
     * @return the new account details
     */
    private Account createNewAccount(Customer customer) {
        Account newaccount = new Account();
        newaccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newaccount.setAccountNumber(randomAccNumber);
        newaccount.setAccountType(AccountsConstants.SAVINGS);
        newaccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newaccount;
    }

    /**
     *
     * @param mobileNumber - Input MobileNumber
     * @return AccountsDetails based on given mobileNumber
     */
    @Override
    public CustomersDto getAccountDetails(String mobileNumber) {
        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Account account = accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );
        CustomersDto customersDto = CustomersMapper.mapToCustomersDto(customer,new CustomersDto());
        customersDto.setAccountDetails(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        return customersDto;
    }

    /**
     *
     * @param customersDto - CustomersDto Object
     * @return boolean indicating account updation sucessful or not.
     */
    @Override
    public boolean updateAccount(CustomersDto customersDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customersDto.getAccountDetails();
        if(accountsDto != null) {
            Account account = accountRepo.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account","AccountNumber",accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccount(accountsDto, account);
            accountRepo.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepo.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer","customerId",customerId.toString())
            );
            CustomersMapper.mapToCustomer(customersDto, customer);
            customerRepo.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     *
     * @param mobileNumber - Input mobileNumber
     * @return boolean indicating account deletion sucessful or not.
     */
    @Override
    public boolean DeleteAccount(String mobileNumber) {
        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        accountRepo.deleteByCustomerId(customer.getCustomerId());
        customerRepo.deleteById(customer.getCustomerId());

        return true;
    }

}
