# 秒杀系统 Springboot

秒杀中。。。。

##########################################

登录功能：

1.数据库的设计

create table miaosha_user (id bigint(20), nickname varchar(255) not null, password varchar(32) default null, salt varchar(10) default null, head varchar(128) default null, register_date datetime default null, last_login datetime default null, login_count int(11) default 0, PRIMARY KEY('id'))ENGINE=InnoDB CHARSET=utf8mb4

2.明文密码两次MD5处理

3.JSR303参数校验+全局异常处理器

4.分布式session
