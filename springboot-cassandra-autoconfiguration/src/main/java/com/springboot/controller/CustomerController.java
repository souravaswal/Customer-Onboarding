package com.springboot.controller;


import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.exceptions.BadRequestException;
import com.springboot.repository.CassandraRepository;

@RestController
@RequestMapping("/org-onboarding/api/v1")
public class CustomerController {
	private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CassandraRepository cassandraRepository;
	
	@Autowired
	private CustomerMapper customerMapper;

	/***
	 * This is POST API for posting customer information
	 * @param customer
	 * @return customerResponse
	 */
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/Organisation", consumes = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
	public Customer createOrganisation(@Valid @RequestBody Customer customer)
	{
	LOG.info("Entering method createOrganisation");

	CustomerDTO customerDto = customerMapper.controllerToDb(customer);
	
	CustomerDTO customerDtoResponse = cassandraRepository.save(customerDto);
	
	Customer customerResponse = customerMapper.dbToController(customerDtoResponse);
	
	LOG.info("Exiting method createOrganisation");
	
	return customerResponse;

	}
	
	/***
	 * This is GET API for fetching customer information
	 * @param orgGuid
	 * @return customerResponse
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/Organisation/{guid}", produces = {"application/json"}, method = RequestMethod.GET)
	public Customer getOrganisation(@PathVariable(value = "guid") @NonNull final String orgGuid)
	{
	LOG.info("Entering method getOrganisation");
	if (isValidGuid(orgGuid) == false) {
		LOG.error("Org guid is not valid");
		throw new BadRequestException("Org guid is not valid", 400);
	}
	
	CustomerDTO customerDtoResponse = cassandraRepository.findById(orgGuid).get();
	Customer customerResponse = customerMapper.dbToController(customerDtoResponse);
	LOG.info("Exiting method getOrganisation");
	return customerResponse;
	}
	
	/***
	 * This is DELETE API for deleting customer information along a particular OrgGuid
	 * @param orgGuid
	 */
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/Organisation/{guid}", produces = {"application/json"}, method = RequestMethod.DELETE)
	public void deleteOrganisation(@PathVariable(value = "guid") final String orgGuid)
	{
	LOG.info("Entering method deleteOrganisation");
	if (isValidGuid(orgGuid) == false) {
		LOG.error("Org guid is not valid");
		throw new BadRequestException("Org guid is not valid", 400);
	}
	cassandraRepository.deleteById(orgGuid);
	LOG.info("Exiting method deleteOrganisation");
	}
	
	/***
	 * This is PUT API for updating Customer information along a particular OrgGuid
	 * @param customer
	 * @return
	 */
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@RequestMapping(value = "/Organisation/{guid}", produces = {"application/json"}, method = RequestMethod.PUT)
	public Customer updateOrganisation(@Valid @RequestBody Customer customer)
	{
	LOG.info("Entering method updateOrganisation");
	if (isValidGuid(customer.getOrgGuid()) == false) {
		LOG.error("Org guid is not valid");
		throw new BadRequestException("Org guid is not valid", 400);
	}
    CustomerDTO customerDto = customerMapper.controllerToDb(customer);
	CustomerDTO customerDtoResponse = cassandraRepository.save(customerDto);
	Customer customerResponse = customerMapper.dbToController(customerDtoResponse);
	
	LOG.info("Exiting method updateOrganisation");
	return customerResponse;
	}
	
	/**
	 * This method is used to check if incoming guid is valid or not
	 * @param guid
	 * @return
	 */
	private boolean isValidGuid(final String guid) {
		boolean isValid = false;
		try {
			UUID.fromString(guid);
			isValid = true;
		}
		catch(IllegalArgumentException exception) {
		}
		return isValid;
	}
}
