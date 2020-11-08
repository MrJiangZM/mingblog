/// =====================================================
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(32) DEFAULT NULL,
  `idNo` varchar(18) DEFAULT NULL,
	`staffNo` varchar(18) DEFAULT NULL,
  `pazzword` varchar(64) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `avatar` varchar(32) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `name` varchar(24) DEFAULT NULL,
  `nation` varchar(2) DEFAULT NULL,
  `nativePlace` varchar(12) DEFAULT NULL,
  `nickname` varchar(24) DEFAULT NULL,
  `position` varchar(64) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c4l2vropgww1py9g1713igexl` (`staffNo`),
  UNIQUE KEY `UK_jreodf78a7pl5qidfh43axdfb` (`username`),
  UNIQUE KEY `UK_pbfhfudhm0er4rr7np2l8mtau` (`idNo`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;



/// ============================================================



SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_userId_roleId` (`uid`,`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;




/// ===================================================


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appId` varchar(32) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `name` varchar(90) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_appId_code` (`appId`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;

/// ================================================



SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role_function
-- ----------------------------
DROP TABLE IF EXISTS `role_function`;
CREATE TABLE `role_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_roleId_functionId` (`rid`,`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;


/// =================================================


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for function
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(90) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `appId` varchar(32) DEFAULT NULL,
  `category` varchar(90) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_appId_code` (`appId`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;



/// ================================================================


DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '子系统名称',
  `code` varchar(50) DEFAULT NULL COMMENT '子系统代码',
  `icon` varchar(100) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `idx` int(11) DEFAULT NULL COMMENT '顺序',
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;





