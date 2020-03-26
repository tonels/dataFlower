package com.xwy.poi.entity;

import lombok.Data;

import java.util.List;

/**
 * 每条需求（id, title, content, outline）
 */
@Data
public class Reqbase {

    private String rid;
    private String title;
    private String content;
    private Integer outline;
    private String pid;

    private List<Attach> attachList;
}
