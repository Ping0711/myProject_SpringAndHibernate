package com.ping.myCustomer;


import org.springframework.stereotype.Service;

@Service
public interface ICustomer {
    public void register(Customer customer);
}
