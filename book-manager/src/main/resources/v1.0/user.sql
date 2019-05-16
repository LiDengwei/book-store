/*
SQLyog Ultimate v11.24 (64 bit)
MySQL - 5.5.28 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

-- create table `pub_users` (
-- 	`user_id` int (10),
-- 	`user_account` varchar (90),
-- 	`user_name` varchar (120),
-- 	`user_password` varchar (300),
-- 	`status` int (10),
-- 	`issys` int (10),
-- 	`user_desc` varchar (300)
-- );
insert into `pub_users` (`user_id`, `user_account`, `user_name`, `user_password`, `status`, `issys`, `user_desc`, `user_language`) values('1','admin','admin','21232f297a57a5a743894a0e4a801fc3','1','1','超级管理员', 'CN');
