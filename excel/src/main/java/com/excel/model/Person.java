package com.excel.model;

import lombok.Data;

@Data
public class Person {

    private String name;
    private String email;
    private String address;

    public Person(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}



