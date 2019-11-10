package com.excel.model;

import lombok.Data;

@Data
public class Contact {
    private String mobile;
    private String phone1;
    private String phone2;

    public Contact(String mobile, String phone1, String phone2) {
        this.mobile = mobile;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }
}
