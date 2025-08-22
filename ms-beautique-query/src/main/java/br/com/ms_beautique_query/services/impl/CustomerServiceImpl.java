package br.com.ms_beautique_query.services.impl;

import br.com.ms_beautique_query.dtos.customers.CustomerDTO;
import br.com.ms_beautique_query.repositories.CustomerRepository;
import br.com.ms_beautique_query.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> listAllCustomers() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving customers: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CustomerDTO> listByNameLikeIgnoreCase(String name) {
        try {
            return customerRepository.findByNameLikeIgnoreCase(name);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving customers: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CustomerDTO> listByEmailLikeIgnoreCase(String email) {
        try {
            return customerRepository.findByEmailLikeIgnoreCase(email);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving customers: " + e.getMessage(), e);
        }
    }
}
