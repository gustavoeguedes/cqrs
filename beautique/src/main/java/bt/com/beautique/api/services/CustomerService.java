package bt.com.beautique.api.services;

import bt.com.beautique.api.dtos.CustomerDTO;
import bt.com.beautique.api.entities.CustomerEntity;
import org.springframework.web.server.ResponseStatusException;

public interface CustomerService {
    CustomerDTO create(CustomerDTO customerDTO);
    CustomerDTO updateById(Long id, CustomerDTO customerDTO);
    void deleteById(Long id);

}
