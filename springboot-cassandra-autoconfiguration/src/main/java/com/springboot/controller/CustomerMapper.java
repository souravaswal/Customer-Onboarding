package com.springboot.controller;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
	public CustomerDTO controllerToDb(Customer customer) {
		CustomerDTO customerdto = new CustomerDTO();
		customerdto.setOrgGuid(customer.getOrgGuid());
		customerdto.setFirstName(customer.getFirstName());
		customerdto.setLastName(customer.getLastName());
		customerdto.setEmailId(customer.getEmailId());
		customerdto.setPhoneNo(customer.getPhoneNo());
		return customerdto;
	}
	
	public Customer dbToController(CustomerDTO customerDto)
    {
        Customer customerRestBean = new Customer();
        customerRestBean.setOrgGuid(customerDto.getOrgGuid());
        customerRestBean.setFirstName(customerDto.getFirstName());
        customerRestBean.setLastName(customerDto.getLastName());
        customerRestBean.setEmailId(customerDto.getEmailId());
        customerRestBean.setPhoneNo(customerDto.getPhoneNo());
        return customerRestBean;
    }

}
