package com.excel.model;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String userPass;

    public User(String userId, String userPass) {
        this.userId = userId;
        this.userPass = userPass;
    }
}
