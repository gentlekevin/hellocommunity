/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : quickstart

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2015-05-13 22:20:35
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `id` bigint(20) NOT NULL,
  `name` varchar(50) default NULL,
  `address` varchar(255) default NULL,
  `contacts` varchar(255) default NULL,
  `phone` varchar(11) default NULL,
  `city_id` bigint(20) default NULL,
  `property_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_community
-- ----------------------------
INSERT INTO `t_community` VALUES ('1', '社区1', null, null, null, null, '1');
INSERT INTO `t_community` VALUES ('2', '社区2', null, null, null, null, '1');
INSERT INTO `t_community` VALUES ('3', '社区3', null, null, null, null, '2');

-- ----------------------------
-- Table structure for `t_property`
-- ----------------------------
DROP TABLE IF EXISTS `t_property`;
CREATE TABLE `t_property` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) default NULL,
  `address` varchar(64) default NULL,
  `contacts` varchar(32) default NULL,
  `phone` varchar(11) default NULL,
  `city_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_property
-- ----------------------------
INSERT INTO `t_property` VALUES ('1', 'test1', null, null, null, null);
INSERT INTO `t_property` VALUES ('2', 'test2', null, null, null, null);

-- ----------------------------
-- Table structure for `t_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(128) NOT NULL,
  `description` varchar(255) default NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
INSERT INTO `t_user` VALUES ('38', 'appTest1', 'appTest1', '8fb2cc470514c8c462ed161ef6710a8af66c2120', '16c9345f8360c68a', 'appUser', '2015-05-13 17:46:45', null, '2015-05-13 19:44:40', null, null, '', '1', '1');
INSERT INTO `t_user` VALUES ('39', '物业admin1', null, '91f3f5043ca93ebf064d2e87514ccabcfc94b937', '04c2fe7f59ef9a6b', 'propertyAdmin', '2015-05-13 19:40:01', null, null, null, null, null, null, '1');
INSERT INTO `t_user` VALUES ('40', 'padmin1', null, 'f82a637bf7b10098a1e16b4372c1b5507e32ab93', '84468cab9cbf7567', 'commonAdmin,propertyAdmin', '2015-05-13 19:40:45', null, '2015-05-13 21:32:29', null, null, null, null, '1');
INSERT INTO `t_user` VALUES ('41', 'apptest2', null, 'f82a637bf7b10098a1e16b4372c1b5507e32ab93', '84468cab9cbf7567', 'appUser', '2015-05-13 19:40:45', null, '2015-05-13 19:48:26', null, null, '', '3', '2');
