package com.customer.microservices.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.microservices.config.DatabaseConfig;
import com.customer.microservices.exception.CustomerException;
import com.customer.microservices.model.Customer;
import com.customer.microservices.repository.CustomerRepository;

@Repository
public class CustomerDaoImpl implements CustomerDaoIF {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DatabaseConfig databaseConfig;

	@Override
	public List<Customer> getCustomers() throws CustomerException {
		List<Customer> customerList = customerRepository.findAll();
		return customerList;
	}

	@Override
	public Customer getCustomer(Long customerId) throws CustomerException {
		Customer customer = customerRepository.findOne(customerId);
		return customer;
	}

	@Override
	public Customer saveCustomer(Customer customer) throws CustomerException {
		customerRepository.save(customer);
		return getCustomer(customer.getId());
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		if (getCustomer(customer.getId()) == null)
			return null;

		customerRepository.save(customer);
		return getCustomer(customer.getId());
	}

	@Override
	public Customer deleteCustomer(Long customerId) throws CustomerException {
		Customer customer;
		if (getCustomer(customerId) == null)
			return null;
		customer = getCustomer(customerId);
		customerRepository.delete(customerId);
		return customer;
	}

	@Override
	public Customer getCustomerByEmail(String emailId) throws CustomerException {
		return customerRepository.findByEmail(emailId);
	}

	@Override
	public Customer getCustomerByName(String name) throws CustomerException {
		return customerRepository.findByName(name);
	}

}
