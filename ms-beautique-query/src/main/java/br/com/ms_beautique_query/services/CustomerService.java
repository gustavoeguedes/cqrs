package br.com.ms_beautique_query.services;

import br.com.ms_beautique_query.dtos.customers.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> listAllCustomers();
    List<CustomerDTO> listByNameLikeIgnoreCase(String name);
    List<CustomerDTO> listByEmailLikeIgnoreCase(String email);
}
