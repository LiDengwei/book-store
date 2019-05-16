/*
SQLyog Ultimate v11.24 (64 bit)
MySQL - 5.5.28
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

-- create table `pub_resources` (
-- 	`resource_id` int (10),
-- 	`parent_id` int (10),
-- 	`resource_name` varchar (300),
-- 	`resource_type` varchar (120),
-- 	`priority` int (10),
-- 	`resource_string` varchar (600),
-- 	`resource_desc` varchar (300),
-- 	`enabled` int (10),
-- 	`issys` int (10),
-- 	`resource_level` int (10)
-- );
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('1','0','管理员登录','action','1','/user/login','管理员登录','1','0','0');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('2','0','首页','action','1','/user/index','首页','1','0','0');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('3','0','用户权限菜单','action','1','/user/menu','用户权限菜单','1','0','0');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('4','0','权限管理','action','1','/','权限管理','1','0','1');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('5','4','进入查询管理员页面','action','1','/user/preQueryUserList','进入查询管理员页面','1','0','2');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('6','5','查询管理员列表','action','1','/user/queryUserList','查询管理员列表','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('7','5','进入新增管理员页面','action','1','/user/preCreateUser','进入新增管理员页面','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('8','5','提交新增管理员','action','1','/user/createUser','提交新增管理员','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('9','5','删除管理员','action','1','/user/deleteUser','删除管理员','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('10','5','进入给管理员赋角色页面','action','1','/user/getAllRoles','进入给管理员赋角色页面','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('11','5','保存给管理员赋角色','action','1','/user/giveRoleToUser','保存给管理员赋角色','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('12','4','进入查询资源信息页面','action','1','/resource/preQueryResourceList','进入查询资源信息页面','1','0','2');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('13','12','查询资源列表','action','1','/resource/queryResourceList','查询资源列表','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('14','12','进入新增资源信息页面','action','1','/resource/preCreateResource','进入新增资源信息页面','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('15','12','提交新增资源信息','action','1','/resource/createResource','提交新增资源信息','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('16','12','删除资源信息','action','1','/resource/deleteResources','删除资源信息','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('17','12','查询一级二级资源','action','1','/resource/getAllMenu','查询一级二级资源','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('18','4','进入查新角色信息页面','action','1','/role/preQueryRoleList','进入查新角色信息页面','1','0','2');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('19','18','查询角色列表','action','1','/role/queryRoleList','查询角色列表','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('20','18','进入新增角色页面','action','1','/role/preCreateRole','进入新增角色页面','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('21','18','提交新增角色信息','action','1','/role/createRole','提交新增角色信息','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('22','18','删除角色信息','action','1','/role/deleteRole','删除角色信息','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('23','18','进入给角色赋予权限页面','action','1','/role/getAllAuths','进入给角色赋予权限页面','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('24','18','提交给角色赋权限','action','1','/role/giveAuthToRole','提交给角色赋权限','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('25','4','进入查询权限列表页面','action','1','/auth/preQueryAuthList','进入查询权限列表页面','1','0','2');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('26','25','查询权限列表','action','1','/auth/queryAuthList','查询权限列表','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('27','25','进入新增权限页面','action','1','/auth/preCreateAuth','进入新增权限页面','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('28','25','提交新增权限','action','1','/auth/createAuth','提交新增权限','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('29','25','删除权限','action','1','/auth/deleteAuth','删除权限','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('30','25','进入给权限赋资源的页面','action','1','/auth/getResourceMap','进入给权限赋资源的页面','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('31','25','保存给权限赋资源','action','1','/auth/giveResourcesToAuth','保存给权限赋资源','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('32','4','进入修改密码','action','1','/user/preUpdatePassword','进入修改密码','1','0','2');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('33','32','修改密码','action','1','/user/updatePassword','修改密码','1','0','20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values ('34', '0', '官网公告管理', 'action', '1', '/', '官网公告管理', '1', '0', '1');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values ('35', '34', '进入官网公告查询', 'action', '1', '/officialWeb/preQueryOfficialWebList', '进入官网公告查询', '1', '0', '2');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values ('36', '35', '官网公告查询', 'action', '1', '/officialWeb/queryOfficialWebList', '官网公告查询', '1', '0', '20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values ('37', '35', '进入官网公告新增', 'action', '1', '/officialWeb/preAddOfficialWeb', '进入官网公告新增', '1', '0', '20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values ('38', '35', '官网公告新增', 'action', '1', '/officialWeb/addOfficialWeb', '官网公告新增', '1', '0', '20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values ('39', '35', '进入官网公告修改', 'action', '1', '/officialWeb/preUpdateOfficialWeb', '进入官网公告修改', '1', '0', '20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values ('40', '35', '官网公告修改', 'action', '1', '/officialWeb/updateOfficialWeb', '官网公告修改', '1', '0', '20');
insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values ('41', '35', '进入官网公告查看', 'action', '1', '/officialWeb/preSelectOfficialWeb', '进入官网公告查看', '1', '0', '20');
INSERT INTO `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`, `create_date`) VALUES('49','0','枚举接口获取值','action','1','/public/enumValue','枚举接口获取值','1','0','0',NULL);
