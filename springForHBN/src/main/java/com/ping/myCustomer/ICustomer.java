package com.ping.myCustomer;


import org.springframework.stereotype.Service;

@Service
public interface ICustomer {
    public void register(Customer customer);
    public Customer findByID(int cusId);
    public boolean checkCus(Customer customer);
    public Customer signIn(Customer customer);
}
