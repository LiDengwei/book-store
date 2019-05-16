/*
SQLyog Ultimate v11.24 (64 bit)
MySQL - 5.5.28 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;
----
-- create table `pub_authorities` (
-- 	`authority_id` int (10),
-- 	`authority_name` varchar (120),
-- 	`authority_desc` varchar (300),
-- 	`enabled` int (10),
-- 	`issys` int (10)
-- );

insert into `pub_authorities` VALUES('1','ROLE_PUBLIC','{"CN":"首页","EN":"Index"}','1','0',SYSDATE());
insert into `pub_authorities` values('2','ROLE_MANAGER','{"CN":"管理员信息","EN":"Manager info"}','1','0',SYSDATE());
insert into `pub_authorities` values('3','ROLE_RESOURCE_MANAGER','{"CN":"资源信息","EN":"Resources info"}','1','0',SYSDATE());
insert into `pub_authorities` values('4','ROLE_ROLE_MANAGER','{"CN":"角色列表","EN":"Role list"}','1','0',SYSDATE());
insert into `pub_authorities` values('5','ROLE_AUTH_MANAGER','{"CN":"权限信息","EN":"Authority info"}','1','0',SYSDATE());
insert into `pub_authorities` values('6','ROLE_UPDATE_PASSWORD','{"CN":"修改密码","EN":"Modify password"}','1','0',SYSDATE());
insert into `pub_authorities` values('7','ROLE_AUTHORITY','{"CN":"权限管理","EN":"Authority Manager"}','1','0',SYSDATE());
insert into `pub_authorities` values ('8', 'ROLE_OFFICIAL_WEB_MANAGER', '{"CN":"官网公告","EN":"Offical Notice"}', '1','0',SYSDATE());
insert into `pub_authorities` values ('9', 'ROLE_OFFICIAL_WEB_SELETE', '{"CN":"官网公告查询","EN":"Offical notice query"}', '1','0',SYSDATE());
insert into `pub_authorities` values ('10', 'ROLE_OFFICIAL_WEB_ADD', '{"CN":"官网公告新增","EN":"Offical notice add"}', '1','0',SYSDATE());
insert into `pub_authorities` values ('11', 'ROLE_OFFICIAL_WEB_UPDATE', '{"CN":"官网公告修改","EN":"Offical notice update"}', '1','0',SYSDATE());
insert into `pub_authorities` values ('12', 'ROLE_OFFICIAL_WEB_SELECT', '{"CN":"官网公告查看","EN":"Offical notice check"}', '1','0',SYSDATE());
INSERT INTO `pub_authorities` VALUES('17','ROLE_PUBLIC_ENUM','{"CN":"枚举接口获取值","EN":"Enum get value"}','1','0',SYSDATE());
