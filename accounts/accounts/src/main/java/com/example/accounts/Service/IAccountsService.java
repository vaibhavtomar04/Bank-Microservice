package com.example.accounts.Service;

import com.example.accounts.DTO.CustomersDto;
import com.example.accounts.entity.Account;
import org.springframework.web.bind.annotation.RequestParam;

public interface IAccountsService {

    /**
     *
     * @param customersDto - CustomersDto Object
     */
    void createAccount(CustomersDto customersDto);
    /**
     *
     * @param mobileNumber - Input MobileNumber
     * @return AccountsDetails based on given mobileNumber
     */

    CustomersDto getAccountDetails(String mobileNumber);
    /**
     *
     * @param customersDto - CustomersDto Object
     * @return boolean indicating account updation sucessful or not.
     */

     boolean updateAccount(CustomersDto customersDto);

    /**
     *
     * @param mobileNumber - Input mobileNumber
     * @return boolean indicating account deletion sucessful or not.
     */

    boolean DeleteAccount(String mobileNumber);

}

