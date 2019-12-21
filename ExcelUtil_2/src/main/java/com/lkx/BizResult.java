package com.lkx;

import lombok.Data;

import java.util.List;

@Data
public class BizResult<T> {

    private String code;

    private String msg;

    private T data;

    private List<T> list;


}
