package com.example.server.dao;

import com.example.server.dataObject.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDao类
 */
@Mapper
public interface UserDAO {

    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return 新增成功记录条数
     */
    int add(User user);

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @return 修改成功记录条数
     */
    int update(User user);

    /**
     * 根据id获取用户
     *
     * @param id 用户id
     * @return 用户对象
     */
    User getById(Integer id);

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户对象
     */
    User getByUsername(String username);

    /**
     * 检查用户名是否已经被占用
     *
     * @param username 用户名
     * @return 占用:1 未占用:0
     */
    int checkByUserName(String username);

    /**
     * 检查邮箱是否已经被占用
     *
     * @param email 邮箱
     * @return 占用:1 未占用:0
     */
    int checkByEmail(String email);

}