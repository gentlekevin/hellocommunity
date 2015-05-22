/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : quickstart

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2015-05-22 11:03:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_activity`
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
  `id` bigint(20) NOT NULL auto_increment,
  `property_id` bigint(20) NOT NULL,
  `title` varchar(64) default NULL,
  `content` varchar(1024) default NULL,
  `pic` varchar(128) default NULL,
  `publish_date` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_activity
-- ----------------------------
INSERT INTO `t_activity` VALUES ('2', '4', '活动2', ' 活动2', null, '2015-05-21 20:12:04');
INSERT INTO `t_activity` VALUES ('3', '4', 'test', ' test', null, '2015-05-21 20:12:39');

-- ----------------------------
-- Table structure for `t_city`
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `id` bigint(20) NOT NULL default '0',
  `name` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
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
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `address` varchar(255) default NULL,
  `contacts` varchar(255) default NULL,
  `phone` varchar(11) default NULL,
  `city_id` bigint(20) default NULL,
  `property_id` bigint(20) default NULL,
  `add_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_community
-- ----------------------------
INSERT INTO `t_community` VALUES ('11', '物业2社区1', '物业2社区1', '物业2社区1', '物业2社区1', '1', '5', '2015-05-19 13:29:21');
INSERT INTO `t_community` VALUES ('12', '物业test1', '物业test1', '物业test1', '物业test1', '1', '4', '2015-05-21 20:07:00');

-- ----------------------------
-- Table structure for `t_community_activity_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_community_activity_info`;
CREATE TABLE `t_community_activity_info` (
  `id` bigint(20) NOT NULL auto_increment,
  `property_id` bigint(20) NOT NULL,
  `community_id` bigint(20) NOT NULL,
  `activity_id` bigint(20) default NULL,
  `type` varchar(1) NOT NULL,
  `information_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_community_activity_info
-- ----------------------------
INSERT INTO `t_community_activity_info` VALUES ('1', '4', '12', null, '0', '2');
INSERT INTO `t_community_activity_info` VALUES ('2', '4', '12', null, '0', '2');
INSERT INTO `t_community_activity_info` VALUES ('3', '4', '12', '3', '1', null);

-- ----------------------------
-- Table structure for `t_complaint`
-- ----------------------------
DROP TABLE IF EXISTS `t_complaint`;
CREATE TABLE `t_complaint` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) default NULL,
  `user_phone` varchar(11) default NULL,
  `content` varchar(200) NOT NULL,
  `pic` varchar(128) default NULL,
  `handle_person` varchar(32) default NULL,
  `handle_date` timestamp NULL default NULL,
  `status` varchar(1) NOT NULL,
  `complaint_date` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `community_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_complaint
-- ----------------------------

-- ----------------------------
-- Table structure for `t_information`
-- ----------------------------
DROP TABLE IF EXISTS `t_information`;
CREATE TABLE `t_information` (
  `id` bigint(20) NOT NULL auto_increment,
  `property_id` bigint(20) NOT NULL,
  `title` varchar(64) default NULL,
  `content` varchar(1024) default NULL,
  `pic` varchar(128) default NULL,
  `publish_date` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_information
-- ----------------------------
INSERT INTO `t_information` VALUES ('1', '4', '社区资讯', '社区资讯', null, '2015-05-19 18:48:44');
INSERT INTO `t_information` VALUES ('2', '4', 'dddd', 'ddddd', null, '2015-05-21 20:09:26');

-- ----------------------------
-- Table structure for `t_property`
-- ----------------------------
DROP TABLE IF EXISTS `t_property`;
CREATE TABLE `t_property` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(64) default NULL,
  `address` varchar(64) default NULL,
  `contacts` varchar(32) default NULL,
  `phone` varchar(11) default NULL,
  `city_id` bigint(20) default NULL,
  `add_date` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_property
-- ----------------------------
INSERT INTO `t_property` VALUES ('4', '北京物业1', '北京物业1', '北京物业1', '北京物业1', '1', '2015-05-19 13:25:50');
INSERT INTO `t_property` VALUES ('5', '北京物业2', '北京物业2', '北京物业2', '北京物业2', '1', '2015-05-19 13:27:30');

-- ----------------------------
-- Table structure for `t_repair`
-- ----------------------------
DROP TABLE IF EXISTS `t_repair`;
CREATE TABLE `t_repair` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) default NULL,
  `user_phone` varchar(11) default NULL,
  `content` varchar(200) NOT NULL,
  `pic` varchar(128) default NULL,
  `handle_person` varchar(32) default NULL,
  `handle_date` timestamp NULL default NULL,
  `status` varchar(1) NOT NULL,
  `repair_date` timestamp NULL default NULL,
  `address` varchar(256) default NULL,
  `title` varchar(128) default NULL,
  `community_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_repair
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `login_name` varchar(64) default NULL,
  `name` varchar(64) default NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(64) default NULL,
  `roles` varchar(255) default NULL,
  `register_date` timestamp NULL default '0000-00-00 00:00:00',
  `sex` char(1) default NULL,
  `birthday` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `phone` varchar(11) default NULL,
  `address` varchar(255) default NULL,
  `header_img_url` varchar(255) default '',
  `community_id` bigint(20) default NULL,
  `property_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'Admin', '401ea8189b8369703ea00154adef4f65ae060cf0', '34d7b23f0021a90f', 'admin,commonAdmin,propertyAdmin', '2012-06-04 01:00:00', '1', '2015-05-13 21:28:48', '13126751503', 'dasdasd', null, null, null);
INSERT INTO `t_user` VALUES ('38', 'appTest1', 'appTest1', '8fb2cc470514c8c462ed161ef6710a8af66c2120', '16c9345f8360c68a', 'appUser', '2015-05-13 17:46:45', null, '2015-05-20 09:46:16', null, null, '', null, null);
INSERT INTO `t_user` VALUES ('39', '物业admin1', '我是物业2管理员', '91f3f5043ca93ebf064d2e87514ccabcfc94b937', '04c2fe7f59ef9a6b', 'propertyAdmin', '2015-05-13 19:40:01', '1', '2015-05-19 13:30:37', '13126751500', null, null, null, '4');
INSERT INTO `t_user` VALUES ('40', 'padmin1', null, 'f82a637bf7b10098a1e16b4372c1b5507e32ab93', '84468cab9cbf7567', 'commonAdmin,propertyAdmin', '2015-05-13 19:40:45', null, '2015-05-19 13:30:38', null, null, null, null, '4');
INSERT INTO `t_user` VALUES ('41', 'apptest2', null, 'f82a637bf7b10098a1e16b4372c1b5507e32ab93', '84468cab9cbf7567', 'appUser', '2015-05-13 19:40:45', null, '2015-05-20 09:26:41', null, null, '', null, '4');
INSERT INTO `t_user` VALUES ('42', 'eee', null, 'a49387d62fd3c51a0ec882e69e02013635afaf94', 'dd0cfe098c0b688c', 'commonAdmin,propertyAdmin', '2015-05-14 11:00:24', null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('44', 'wuyetest', '我是物业1', '6a0b9b77afcb488ebb8c3e60d93772d2fc25bc69', '2f4047bcaec86adf', 'propertyAdmin', '2015-05-16 10:54:28', '1', '2015-05-19 13:30:42', '13126751501', null, null, null, '4');
