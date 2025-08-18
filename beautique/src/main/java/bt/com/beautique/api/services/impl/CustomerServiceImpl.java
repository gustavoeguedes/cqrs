package bt.com.beautique.api.services.impl;

import bt.com.beautique.api.dtos.CustomerDTO;
import bt.com.beautique.api.entities.CustomerEntity;
import bt.com.beautique.api.repositories.CustomerRepository;
import bt.com.beautique.api.services.CustomerService;
import bt.com.beautique.api.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
