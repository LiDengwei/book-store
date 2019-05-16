-- 长连接
INSERT INTO `pub_authorities`(`authority_id`, `authority_name`, `authority_desc`, `enabled`, `issys`, `create_date`) VALUES (18, 'ROLE_WEB_SOCKET', '{"CN":"长连接","EN":"Long connection"}', 1, 0, NULL);

INSERT INTO `pub_resources`(`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`, `create_date`) VALUES (50, 0, 'ws长连接', 'action', 1, '/web/sockerServer', 'ws长连接', 1, 0, 0, NULL);
INSERT INTO `pub_resources`(`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`, `create_date`) VALUES (51, 0, 'http长连接', 'action', 1, '/web/socketKjs', 'http长连接', 1, 0, 0, NULL);

INSERT INTO `pub_authorities_resources`(`id`, `authority_id`, `resource_id`, `create_date`, `ischecked`) VALUES (50, 18, 50, NULL, 1);

INSERT INTO `pub_roles_authorities`(`role_id`, `authority_id`, `issy`, `create_date`) VALUES (1, 18, 1, NULL);



-- 上传图片
insert into `pub_authorities` (`authority_id`, `authority_name`, `authority_desc`, `enabled`, `issys`) values('19','ROLE_PROBLEM_REPLY','{"CN":"上传图片","EN":"Upload picture"}','1','0');

insert into `pub_resources` (`resource_id`, `parent_id`, `resource_name`, `resource_type`, `priority`, `resource_string`, `resource_desc`, `enabled`, `issys`, `resource_level`) values('52','35','图片上传','action','1','/officialWeb/uploadImage','图片上传','1','0','20');

insert  into `pub_authorities_resources`(`id`,`authority_id`,`resource_id`) values (51,19,51);

insert into `pub_roles_authorities` (`role_id`, `authority_id`, `issy`) values('1','19','1');

insert  into `pub_authorities_resources`(`id`,`authority_id`,`resource_id`) values (52,10,52);
insert  into `pub_authorities_resources`(`id`,`authority_id`,`resource_id`) values (53,11,52);
