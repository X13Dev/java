package com.example.server.dataObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 留言增强类
 */
@Setter
@Getter
@NoArgsConstructor
public class BoardFull extends Board{
    
    private String username;
}
