package com.example.server.service;

import com.example.server.dataObject.User;
import com.example.server.model.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * User Service Interface
 */
@Service
public interface UserService {

	/**
	 * 用户注册
	 *
	 * @param user 用户对象
	 * @return 注册结果
	 */
	Result<User> register(User user);

	/**
	 * 用户登录
	 *
	 * @param user 用户对象
	 * @return 登录结果
	 */
	Result<User> login(User user);

	/**
	 * 修改用户信息
	 *
	 * @param user 用户对象
	 * @return 修改结果
	 */
	Result<User> update(User user) throws Exception;

	/**
	 * 判断用户是否登录
	 *
	 * @param session 传入请求session
	 * @return 返回结果，若用户已登录则返回用户信息
	 */
	Result<User> isLogin(HttpSession session);
    
}
