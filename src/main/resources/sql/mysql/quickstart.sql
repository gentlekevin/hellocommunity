/*
Navicat MySQL Data Transfer

Source Server         : hello
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : quickstart

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2015-05-18 17:06:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_activity`
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `property_id` bigint(20) NOT NULL,
  `title` varchar(64) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `pic` varchar(128) DEFAULT NULL,
  `publish_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_activity
-- ----------------------------
INSERT INTO `t_activity` VALUES ('2', '1', '活动2', '      活动2', null, '2015-05-16 18:10:31');
INSERT INTO `t_activity` VALUES ('3', '2', '物业2小区1活动1', ' 物业2小区1活动1', null, '2015-05-18 16:42:03');

-- ----------------------------
-- Table structure for `t_city`
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_city
-- ----------------------------
INSERT INTO `t_city` VALUES ('1', '北京');
INSERT INTO `t_city` VALUES ('2', '上海');
INSERT INTO `t_city` VALUES ('3', '大连');

-- ----------------------------
-- Table structure for `t_community`
-- ----------------------------
DROP TABLE IF EXISTS `t_community`;
CREATE TABLE `t_community` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contacts` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `property_id` bigint(20) DEFAULT NULL,
  `add_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_community
-- ----------------------------
INSERT INTO `t_community` VALUES ('7', '物业1小区1', '物业1小区1', '物业1小区1', '物业1小区1', null, '1', '2015-05-18 13:53:00');
INSERT INTO `t_community` VALUES ('8', '物业1小区2', '物业1小区2', '物业1小区2', '物业1小区2', null, '1', '2015-05-18 13:53:10');
INSERT INTO `t_community` VALUES ('9', '物业2小区1', '物业2小区1', '物业2小区1', '物业2小区1', null, '2', '2015-05-18 16:51:38');

-- ----------------------------
-- Table structure for `t_community_activity_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_community_activity_info`;
CREATE TABLE `t_community_activity_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `property_id` bigint(20) NOT NULL,
  `community_id` bigint(20) NOT NULL,
  `activity_id` bigint(20) DEFAULT NULL,
  `type` varchar(1) NOT NULL,
  `information_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_community_activity_info
-- ----------------------------
INSERT INTO `t_community_activity_info` VALUES ('48', '1', '1', '2', '1', null);
INSERT INTO `t_community_activity_info` VALUES ('49', '1', '6', '2', '1', null);
INSERT INTO `t_community_activity_info` VALUES ('51', '1', '7', null, '0', '2');
INSERT INTO `t_community_activity_info` VALUES ('52', '1', '8', null, '0', '2');
INSERT INTO `t_community_activity_info` VALUES ('53', '2', '9', null, '0', '3');
INSERT INTO `t_community_activity_info` VALUES ('54', '2', '9', '3', '1', null);

-- ----------------------------
-- Table structure for `t_complaint`
-- ----------------------------
DROP TABLE IF EXISTS `t_complaint`;
CREATE TABLE `t_complaint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `user_phone` varchar(11) DEFAULT NULL,
  `content` varchar(200) NOT NULL,
  `pic` varchar(128) DEFAULT NULL,
  `handle_person` varchar(32) DEFAULT NULL,
  `handle_date` timestamp NULL DEFAULT NULL,
  `status` varchar(1) NOT NULL,
  `complaint_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_complaint
-- ----------------------------

-- ----------------------------
-- Table structure for `t_information`
-- ----------------------------
DROP TABLE IF EXISTS `t_information`;
CREATE TABLE `t_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `property_id` bigint(20) NOT NULL,
  `title` varchar(64) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `pic` varchar(128) DEFAULT NULL,
  `publish_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_information
-- ----------------------------
INSERT INTO `t_information` VALUES ('2', '1', '物业1小区1资讯1', '物业1小区1资讯1', null, '2015-05-18 16:39:13');
INSERT INTO `t_information` VALUES ('3', '2', '物业2小区1资讯1', '物业2小区1资讯1', null, '2015-05-18 16:41:08');

-- ----------------------------
-- Table structure for `t_property`
-- ----------------------------
DROP TABLE IF EXISTS `t_property`;
CREATE TABLE `t_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `contacts` varchar(32) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `add_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_property
-- ----------------------------
INSERT INTO `t_property` VALUES ('1', '物业1', '物业1', '物业1', '物业1', '1', '2015-05-18 13:51:21');
INSERT INTO `t_property` VALUES ('2', '物业2', '物业2', '物业2', '物业2', '1', '2015-05-18 13:51:35');

-- ----------------------------
-- Table structure for `t_repair`
-- ----------------------------
DROP TABLE IF EXISTS `t_repair`;
CREATE TABLE `t_repair` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `user_phone` varchar(11) DEFAULT NULL,
  `content` varchar(200) NOT NULL,
  `pic` varchar(128) DEFAULT NULL,
  `handle_person` varchar(32) DEFAULT NULL,
  `handle_date` timestamp NULL DEFAULT NULL,
  `status` varchar(1) NOT NULL,
  `repair_date` timestamp NULL DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `community_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_repair
-- ----------------------------

-- ----------------------------
-- Table structure for `t_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES ('1', 'Study PlayFramework 2.0', 'http://www.playframework.org/', '2');
INSERT INTO `t_task` VALUES ('2', 'Study Grails 2.0', 'http://www.grails.org/', '2');
INSERT INTO `t_task` VALUES ('3', 'Try SpringFuse', 'http://www.springfuse.com/', '2');
INSERT INTO `t_task` VALUES ('4', 'Try Spring Roo', 'http://www.springsource.org/spring-roo', '2');
INSERT INTO `t_task` VALUES ('5', 'Release SpringSide 4.0', 'As soon as posibble.', '2');
INSERT INTO `t_task` VALUES ('6', 'dd', 'dd', '1');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(64) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `register_date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `sex` char(1) DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `header_img_url` varchar(255) DEFAULT '',
  `community_id` bigint(20) DEFAULT NULL,
  `property_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'Admin', '401ea8189b8369703ea00154adef4f65ae060cf0', '34d7b23f0021a90f', 'admin,commonAdmin,propertyAdmin', '2012-06-04 01:00:00', '1', '2015-05-13 21:28:48', '13126751503', 'dasdasd', null, null, null);
INSERT INTO `t_user` VALUES ('38', 'appTest1', 'appTest1', '8fb2cc470514c8c462ed161ef6710a8af66c2120', '16c9345f8360c68a', 'appUser', '2015-05-13 17:46:45', null, '2015-05-18 13:56:17', null, null, '', '7', '1');
INSERT INTO `t_user` VALUES ('41', 'apptest2', null, 'f82a637bf7b10098a1e16b4372c1b5507e32ab93', '84468cab9cbf7567', 'appUser', '2015-05-13 19:40:45', null, '2015-05-18 13:57:07', null, null, '', '8', '1');
INSERT INTO `t_user` VALUES ('45', 'wuye1', null, '525cc99145b0611ff4f4862c4b8e69b753b3bbbe', 'f775f5e95277e531', 'propertyAdmin', '2015-05-18 13:55:07', null, null, null, null, null, null, '1');
INSERT INTO `t_user` VALUES ('46', 'wuye2', '我是物业管理员', '9f92d2fef6ca4e68bfa7e89a5bc03483a6d19cfb', 'ecca6463cbccce73', 'propertyAdmin', '2015-05-18 13:55:23', '1', null, '13126751500', null, null, null, '2');
INSERT INTO `t_user` VALUES ('47', 'sysAdmin', null, 'cc6ff56286622f4c067d6129f95e8c698a5e3cd6', 'cbe5c31200028d9c', 'commonAdmin,propertyAdmin', '2015-05-18 14:08:01', null, null, null, null, null, null, null);
