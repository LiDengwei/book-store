
/*Table structure for table `pub_authorities` */

CREATE TABLE `pub_authorities` (
  `authority_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `authority_name` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '权限名称',
  `authority_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `enabled` int(10) NOT NULL COMMENT '是否禁用 0 禁用 1正常',
  `issys` int(10) NOT NULL COMMENT '是否是超级用户 0不是 1是',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

/*Table structure for table `pub_resources` */

CREATE TABLE `pub_resources` (
  `resource_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `parent_id` int(10) DEFAULT NULL COMMENT '一级菜单ID 0 表示当前菜单为一级菜单',
  `resource_name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '资源名称',
  `resource_type` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '资源类型',
  `priority` int(10) NOT NULL COMMENT '资源优先权',
  `resource_string` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '资源地址',
  `resource_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '资源描述',
  `enabled` int(10) NOT NULL COMMENT '是否被禁用 0 禁用 1正常',
  `issys` int(10) NOT NULL COMMENT '是否是超级权限 0不是 1是',
  `resource_level` int(10) DEFAULT NULL COMMENT '资源级别',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=367 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限资源表';

/*Table structure for table `pub_authorities_resources` */

CREATE TABLE `pub_authorities_resources` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `authority_id` int(10) NOT NULL COMMENT '权限ID',
  `resource_id` int(10) NOT NULL COMMENT '资源ID',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
  `ischecked` int(11) DEFAULT '1' COMMENT '是否被选中 0,未选中 1,选中',
  PRIMARY KEY (`id`),
  KEY `fk_res_aut` (`authority_id`),
  KEY `fk_res` (`resource_id`),
  CONSTRAINT `fk_res` FOREIGN KEY (`resource_id`) REFERENCES `pub_resources` (`resource_id`),
  CONSTRAINT `fk_res_aut` FOREIGN KEY (`authority_id`) REFERENCES `pub_authorities` (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=409 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限和资源关联表';

/*Table structure for table `pub_roles` */

CREATE TABLE `pub_roles` (
  `role_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `role_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '角色备注',
  `enabled` int(10) NOT NULL COMMENT '是否被禁用 0 禁用 1正常',
  `issys` int(10) NOT NULL COMMENT '是否是超级权限 0不是 1是',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

/*Table structure for table `pub_roles_authorities` */

CREATE TABLE `pub_roles_authorities` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_id` int(10) NOT NULL COMMENT '角色ID',
  `authority_id` int(10) NOT NULL COMMENT '权限id',
  `issy` tinyint(1) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `fk_aut_role` (`role_id`),
  KEY `fk_aut` (`authority_id`),
  CONSTRAINT `fk_aut` FOREIGN KEY (`authority_id`) REFERENCES `pub_authorities` (`authority_id`),
  CONSTRAINT `fk_aut_role` FOREIGN KEY (`role_id`) REFERENCES `pub_roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=795 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色和权限关联表';

/*Table structure for table `pub_users` */

CREATE TABLE `pub_users` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_account` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '用户',
  `user_name` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `user_password` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `status` int(10) NOT NULL COMMENT '是否可用 0 禁用 1 正常',
  `issys` int(10) NOT NULL COMMENT '是否超级管理 0不是 1是',
  `user_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `user_language` varchar(10) COLLATE utf8_bin DEFAULT 'CN' COMMENT '用户语言字段,CN:中文,EN:英文',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

/*Table structure for table `pub_users_roles` */

CREATE TABLE `pub_users_roles` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  `role_id` int(10) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `fk_user` (`user_id`),
  KEY `fk_role` (`role_id`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `pub_roles` (`role_id`),
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `pub_users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色和用户关联表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
