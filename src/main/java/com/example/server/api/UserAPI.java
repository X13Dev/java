package com.example.server.api;

import com.example.server.dataObject.User;
import com.example.server.error.ServerErrorTypeEnum;
import com.example.server.error.ServerException;
import com.example.server.model.Result;
import com.example.server.service.UserService;
import com.example.server.util.RegexUtils;
import com.example.server.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserAPI {

    /**
     * session
     */
    public static final String SESSION_NAME = "userInfo";

    /**
     * user service
     */
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user    传入注册用户信息
     * @param errors  Validation的校验错误存放对象
     * @param request 请求对象，用于操作session
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody @Valid User user, BindingResult errors, HttpServletRequest request) throws ServerException {
        Result<User> result;
        if (user == null) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_ERROR.getErrorDesc());
            return result;
        }

        // 用户名校验 5-20且只能为数字和字母且唯一
        if (StringUtils.stringIsEmptyOrNull(user.getUsername())) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_NAME_NULL_ERROR.getErrorDesc());
            return result;
        }

        String strUsername = user.getUsername(); 
        if (strUsername.length() < 5 || strUsername.length() > 20) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_NAME_LENGTH_ERROR.getErrorDesc());
            return result;
        }

        String regexUN = "^[a-z0-9A-Z]+$";
        boolean nameRegex = RegexUtils.genericMatcher(regexUN, strUsername);
        if (!nameRegex){
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_NAME_FORMAT_ERROR.getErrorDesc());
            return result;
        }

        int checkUN = userService.checkByUserName(strUsername);
        if (checkUN == 1) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_NAME_UNIQUE_ERROR.getErrorDesc());
            return result;
        }

        // 密码校验 8-20 至少包含一个大写、一个小写、一个数字、一个特殊符号
        if (StringUtils.stringIsEmptyOrNull(user.getPassword())) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_PWD_NULL_ERROR.getErrorDesc());
            return result;
        }

        String pwd = user.getPassword();
        if (pwd.length() <8 || pwd.length() > 20){
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_PWD_LENGTH_ERROR.getErrorDesc());
            return result;

        }

        String regexPWD = "^(?![A-z0-9]+$)(?=.[^%&',;=?$\\x22])(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,20}$";
        boolean pwdRegex = RegexUtils.genericMatcher(regexPWD, pwd);
        if (!pwdRegex){
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_PWD_FORMAT_ERROR.getErrorDesc());
            return result;
        }

        // 邮箱校验 
        if (StringUtils.stringIsEmptyOrNull(user.getEmail())) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_EMAIL_NULL_ERROR.getErrorDesc());
            return result;
        }

        String strEmail = user.getEmail();
        String regexEmail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        boolean emailRegex = RegexUtils.genericMatcher(regexEmail, strEmail);
        if (!emailRegex){
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_EMAIL_FORMAT_ERROR.getErrorDesc());
            return result;
        }

        int checkEmail = userService.checkByEmail(strEmail);
        if (checkEmail == 1) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_EMAI_UNIQUE_ERROR.getErrorDesc());
            return result;
        }
       
        // 写的有问题暂时不用
        // // 如果校验有错，返回登录失败以及错误信息 
        // if (errors.hasErrors()) {
        //     result = new Result<>();
        //     result.setResultFailed(errors.getFieldError().getDefaultMessage());
        //     return result;
        // }
        // 调用注册服务
        result = userService.register(user);
        // 如果注册成功，则设定session
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 用户登录
     *
     * @param user    传入登录用户信息
     * @param errors  Validation的校验错误存放对象
     * @param request 请求对象，用于操作session
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody @Valid User user, BindingResult errors, HttpServletRequest request) throws ServerException{
        Result<User> result;

        if (user == null) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_ERROR.getErrorDesc());
            return result;
        }

        String username = user.getUsername();
        String email = user.getEmail();
        String pwd = user.getPassword();
        if (StringUtils.stringIsEmptyOrNull(username) && StringUtils.stringIsEmptyOrNull(email)) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_USERNAMEEMAIL_NULL_ERROR.getErrorDesc());
            return result;
        }

        if (StringUtils.stringIsEmptyOrNull(pwd)) {
            result = new Result<>();
            result.setResultFailed(ServerErrorTypeEnum.USER_PWD_NULL_ERROR.getErrorDesc());
            return result;
        }
        // 写的有问题暂时不用
        // // 如果校验有错，返回登录失败以及错误信息
        // if (errors.hasErrors()) {
        //     result = new Result<>();
        //     result.setResultFailed(errors.getFieldError().getDefaultMessage());
        //     return result;
        // }
        // 调用登录服务
        result = userService.login(user);
        // 如果登录成功，则设定session
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 判断用户是否登录
     *
     * @param request 请求对象，从中获取session里面的用户信息以判断用户是否登录
     * @return 结果对象，已经登录则结果为成功，且数据体为用户信息；否则结果为失败，数据体为空
     */
    @GetMapping("/islogin")
    public Result<User> isLogin(HttpServletRequest request) {
        // 传入session到用户服务层
        return userService.isLogin(request.getSession());
    }

    /**
     * 用户信息修改
     *
     * @param user    修改后用户信息对象
     * @param request 请求对象，用于操作session
     * @return 修改结果
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public Result<User> update(@RequestBody User user, HttpServletRequest request) throws Exception {
        Result<User> result = new Result<>();
        HttpSession session = request.getSession();
        // 检查session中的用户（即当前登录用户）是否和当前被修改用户一致
        User sessionUser = (User) session.getAttribute(SESSION_NAME);
        if (sessionUser.getId() != user.getId()) {
            result.setResultFailed("当前登录用户和被修改用户不一致，终止！");
            return result;
        }
        result = userService.update(user);
        // 修改成功则刷新session信息
        if (result.isSuccess()) {
            session.setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 用户登出
     *
     * @param request 请求，用于操作session
     * @return 结果对象
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        Result result = new Result();
        // 用户登出很简单，就是把session里面的用户信息设为null即可
        request.getSession().setAttribute(SESSION_NAME, null);
        result.setResultSuccess("用户退出登录成功！", null);
        return result;
    }

}