package com.sdp.artgallery.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdp.artgallery.model.Customer;
import com.sdp.artgallery.repository.AdminRepository;
import com.sdp.artgallery.repository.CustomerRepository;
import com.sdp.artgallery.repository.SellerRepository;

@Service
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	

	@Override
	public String addcustomer(Customer c) 
	{
		customerRepository.save(c);
		return "Registration completed";
	}

	@Override
	public String updatecustomer(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer customerbyid(int cid)
	{
		
		Optional<Customer> o= customerRepository.findById(cid);
		if(o.isPresent())
		{
			Customer customer= o.get();
			return customer;
		}
		else
		{
			return null;
		}
	}

	@Override
	public Customer checkcustomerlogin(String email, String password)
	{
		return customerRepository.checkcustomerlogin(email, password);
	}
	

}
