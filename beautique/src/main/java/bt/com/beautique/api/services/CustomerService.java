package bt.com.beautique.api.services;

import bt.com.beautique.api.dtos.CustomerDTO;
import bt.com.beautique.api.entities.CustomerEntity;

public interface CustomerService {
    CustomerDTO create(CustomerDTO customerDTO);
}
