/*
SQLyog Ultimate v11.24 (64 bit)
MySQL - 5.5.28 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

-- create table `pub_roles` (
-- 	`role_id` int (10),
-- 	`role_name` varchar (300),
-- 	`role_desc` varchar (300),
-- 	`enabled` int (10),
-- 	`issys` int (10)
-- );
insert into `pub_roles` (`role_id`, `role_name`, `role_desc`, `enabled`, `issys`) values('1','ROLE_SUPER_MANAGER','超级管理员','1','0');
