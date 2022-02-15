package com.example.server.dataObject;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 回复信息类
 */
@Setter
@Getter
@NoArgsConstructor
public class Message {
    /**
     * 标识id
     */
    private Integer id;

    /**
     * 留言标识id
     */
    private Integer bid;

    /**
     * 上一级回复标识Id
     */
    private Integer pid;

    /**
     * 回复用户标识Id
     */
    private Integer uid;

    /**
     * 用户回复信息
     */
    private String content;

    /**
     * 回复时间
     */
    private Date creattime;
}
