package com.ping.myCustomer;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 對應hibernate.cfg.xsm檔案,使用mapping對映
 */
@Entity
@Table(name = "Customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cusId;
    @Column(nullable = false)
    private String cusName;
    @Column(unique = true, nullable = false)
    private String cusPhone;
    @Column(nullable = false)
    private String cusAddress;

    public Customer() {
    }

    public int getCusId() {
        return cusId;
    }

    public void cusId(int cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }
}
