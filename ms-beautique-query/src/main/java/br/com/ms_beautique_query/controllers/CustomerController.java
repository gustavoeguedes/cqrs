package br.com.ms_beautique_query.controllers;

import br.com.ms_beautique_query.dtos.customers.CustomerDTO;
import br.com.ms_beautique_query.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    ResponseEntity<List<CustomerDTO>> listAllCustomers(){
        List<CustomerDTO> customers = customerService.listAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/by-name/{name}")
    ResponseEntity<List<CustomerDTO>> listByNameLikeIgnoreCase(@PathVariable String name) {
        List<CustomerDTO> customers = customerService.listByNameLikeIgnoreCase(name);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/by-email/{email}")
    ResponseEntity<List<CustomerDTO>> listByEmailLikeIgnoreCase(@PathVariable String email) {
        List<CustomerDTO> customers = customerService.listByEmailLikeIgnoreCase(email);
        return ResponseEntity.ok(customers);
    }
}
