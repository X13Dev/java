package com.example.server.error;

public enum ServerErrorTypeEnum {
    
    USER_ERROR(1000001, "注册信息不能为空！"),

    USER_NAME_NULL_ERROR(1000002, "用户名不能为空！"),

    USER_NAME_LENGTH_ERROR(1000003, "用户名长度在5-20个字符！"),

    USER_NAME_FORMAT_ERROR(1000004, "用户名只能包含英文字母及数字！"),

    USER_NAME_UNIQUE_ERROR(1000005, "用户名已存在！"),

    USER_PWD_NULL_ERROR(1000006, "密码不能为空！"),

    USER_PWD_LENGTH_ERROR(1000007, "密码长度在8-20之间！"),

    USER_PWD_FORMAT_ERROR(1000008, "密码中至少包含一个大写、一个小写、一个数字、一个特殊符号！"),

    USER_EMAIL_NULL_ERROR(1000009, "邮箱不能为空！"),

    USER_EMAIL_FORMAT_ERROR(1000010, "邮箱格式不正确！"),
    
    USER_EMAI_UNIQUE_ERROR(1000011, "邮箱已存在！"),

    USER_USERNAMEEMAIL_NULL_ERROR(1000012, "登录时账号密码必须输入一个");


    /**
     * 错误编码
     */
    private final int errorCode;

    /**
     * 错误描述
     */
    private final String errorDesc;

    /**
     * 构造函数
     *
     * @param errorCode 错误编码
     * @param errorDesc 错误描述
     */
    private ServerErrorTypeEnum(int errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    /**
     * 根据错误编码获取美剧信息
     *
     * @param errorCode 错误编码
     * @return 错误信息
     */
    public static ServerErrorTypeEnum getEnumByKey(int errorCode) {
        for (ServerErrorTypeEnum item : ServerErrorTypeEnum.values()) {
            if (item.getErrorCode() == errorCode) {
                return item;
            }
        }

        return null;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
