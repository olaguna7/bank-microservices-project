package com.oscar.accountsms.service;

import com.oscar.accountsms.dto.CustomerDTO;

public interface IAccountService {

    /**
     *
     * @param customer - CustomerDTO object
     */
    void createAccount(CustomerDTO customer);

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return Account Details based on a given mobile number
     */
    CustomerDTO fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDTO - CustomerDTO object
     * @return boolean indicating if the update of the Account details is successful or not
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);

}
