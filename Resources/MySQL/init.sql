-- 初始化数据库
DROP SCHEMA IF EXISTS `server_base_dev` ;

CREATE SCHEMA IF NOT EXISTS `server_base_dev` DEFAULT CHARACTER SET utf8mb4 ;

use `server_base_dev`;

-- 初始化用户表

CREATE TABLE IF NOT EXISTS `user`(
	`id` INT(15) NOT NULL AUTO_INCREMENT COMMENT '标识Id',
	`username` VARCHAR(20) NOT NULL COMMENT '用户账号',
    `password` VARCHAR(20) NOT NULL COMMENT '登录密码',
    `email` VARCHAR(50) NOT NULL COMMENT '电子邮件',
    PRIMARY KEY (`id`),
	INDEX `UserIndex` (`username` DESC)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='系统用户表';

-- 初始化留言板

CREATE TABLE IF NOT EXISTS `board`(
    `id` INT(15) NOT NULL AUTO_INCREMENT COMMENT '标识Id',
    `pid` INT(15) COMMENT '父级标识Id',
    `uid` INT(15) NOT NULL COMMENT '用户标识Id',
    `content` VARCHAR(200) NOT NULL COMMENT '用户留言',
    `creattime` DATETIME NOT NULL COMMENT '留言发表时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4 COMMENT='用户留言表';