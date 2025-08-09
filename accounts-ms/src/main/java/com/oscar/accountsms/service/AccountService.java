package com.oscar.accountsms.service;

import com.oscar.accountsms.constants.AccountConstants;
import com.oscar.accountsms.dto.AccountDTO;
import com.oscar.accountsms.dto.CustomerDTO;
import com.oscar.accountsms.entity.Account;
import com.oscar.accountsms.entity.Customer;
import com.oscar.accountsms.exception.CustomerAlreadyExistsException;
import com.oscar.accountsms.exception.ResourceNotFoundException;
import com.oscar.accountsms.mapper.AccountMapper;
import com.oscar.accountsms.mapper.CustomerMapper;
import com.oscar.accountsms.repository.AccountRepository;
import com.oscar.accountsms.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber())
                .orElseThrow(() -> new CustomerAlreadyExistsException("Customer already registered with given mobile number"));
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);

        return newAccount;
    }

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return Account details based on a given mobile number
     */
    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", mobileNumber));

        CustomerDTO customerDTO = CustomerMapper.toDTO(customer);
        customerDTO.setAccountDTO(AccountMapper.toDTO(account));

        return customerDTO;
    }

    /**
     *
     * @param customerDTO - CustomerDTO object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountDTO accountDTO = customerDTO.getAccountDTO();
        if (accountDTO != null) {
            Account account = accountRepository.findById(accountDTO.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "account number", accountDTO.getAccountNumber().toString()));
            account = AccountMapper.toEntity(accountDTO);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));
            customer = CustomerMapper.toEntity(customerDTO);
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", mobileNumber));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
