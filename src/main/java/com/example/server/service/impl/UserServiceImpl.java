package com.example.server.service.impl;

import com.example.server.api.UserAPI;
import com.example.server.dao.UserDAO;
import com.example.server.dataObject.User;
import com.example.server.model.Result;
import com.example.server.service.UserService;
import com.example.server.util.ClassExamine;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * User Service Implementation
 */
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public Result<User> register(User user) {
		Result<User> result = new Result<>();
		// 先去数据库找用户名是否存在
		User getUser = userDAO.getByUsername(user.getUsername());
		if (getUser != null) {
			result.setResultFailed("该用户名已存在！");
			return result;
		}
		// 加密储存用户的密码
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		// 存入数据库
		userDAO.add(user);
		// 返回用户数据，成功消息
		result.setResultSuccess("注册用户成功！", user);
		return result;
	}

	@Override
	public Result<User> login(User user) {
		Result<User> result = new Result<>();
		// 去数据库查找用户
		User getUser = userDAO.getByUsername(user.getUsername());
		if (getUser == null) {
			result.setResultFailed("用户不存在！");
			return result;
		}
		// 比对密码（数据库取出用户的密码是加密的，因此要把前端传来的用户密码加密再比对）
		if (!getUser.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
			result.setResultFailed("用户名或者密码错误！");
			return result;
		}
		// 设定登录成功消息以及用户信息
		result.setResultSuccess("登录成功！", getUser);
		return result;
	}

	@Override
	public Result<User> update(User user) throws Exception {
		Result<User> result = new Result<>();
		// 去数据库查找用户
		User getUser = userDAO.getById(user.getId());
		if (getUser == null) {
			result.setResultFailed("用户不存在！");
			return result;
		}
		// 检测传来的对象里面字段值是否为空，若是就用数据库里面的对象相应字段值补上
		if (!StringUtils.isEmpty(user.getPassword())) {
			// 加密储存
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		}
		// 对象互补
		ClassExamine.objectOverlap(user, getUser);
		// 存入数据库
		userDAO.update(user);
		result.setResultSuccess("修改用户成功！", user);
		return result;
	}

	@Override
	public Result<User> isLogin(HttpSession session) {
		Result<User> result = new Result<>();
		// 从session中取出用户信息
		User sessionUser = (User) session.getAttribute(UserAPI.SESSION_NAME);
		// 若session中没有用户信息这说明用户未登录
		if (sessionUser == null) {
			result.setResultFailed("用户未登录！");
			return result;
		}
		// 登录了则去数据库取出信息进行比对
		User getUser = userDAO.getById(sessionUser.getId());
		// 如果session用户找不到对应的数据库中的用户或者找出的用户密码和session中用户不一致则说明session中用户信息无效
		if (getUser == null || !getUser.getPassword().equals(sessionUser.getPassword())) {
			result.setResultFailed("用户信息无效！");
			return result;
		}
		result.setResultSuccess("用户已登录！", getUser);
		return result;
	}

}