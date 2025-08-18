package bt.com.beautique.api.services.impl;

import bt.com.beautique.api.dtos.CustomerDTO;
import bt.com.beautique.api.entities.CustomerEntity;
import bt.com.beautique.api.repositories.CustomerRepository;
import bt.com.beautique.api.services.CustomerService;
import bt.com.beautique.api.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final ConverterUtil<CustomerEntity, CustomerDTO> converterUtil = new ConverterUtil<>(CustomerEntity.class, CustomerDTO.class);


    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = converterUtil.convertToSource(customerDTO);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return converterUtil.convertToTarget(savedCustomer);
    }




    private CustomerEntity findById(Long id) throws ResponseStatusException {
       return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));


    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDTO updateById(Long id, CustomerDTO customerDTO) {
        CustomerEntity customerToUpdate = findById(id);
        if (customerDTO.getName() != null
                && !Objects.equals(customerToUpdate.getName(), customerDTO.getName())) {
            customerToUpdate.setName(customerDTO.getName());
        }

        if (customerDTO.getEmail() != null
                && !Objects.equals(customerToUpdate.getEmail(), customerDTO.getEmail())) {
            customerToUpdate.setEmail(customerDTO.getEmail());
        }

        if (customerDTO.getPhone() != null
                && !Objects.equals(customerToUpdate.getPhone(), customerDTO.getPhone())) {
            customerToUpdate.setPhone(customerDTO.getPhone());
        }



        CustomerEntity customerUpdated = customerRepository.save(customerToUpdate);

        return converterUtil.convertToTarget(customerUpdated);

    }
}
