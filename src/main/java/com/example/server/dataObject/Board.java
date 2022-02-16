package com.example.server.dataObject;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 留言板类
 */
@Setter
@Getter
@NoArgsConstructor
public class Board {
    /**
     * 留言板id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 用户留言
     */
    private String content;

    /**
     * 留言时间
     */
    private Date creattime;
}
