/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : quickstart

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2015-05-01 15:24:32
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
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_community
-- ----------------------------

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
INSERT INTO `t_task` VALUES ('6', 'hello', 'hello', '1');

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
  `phone` varchar(11) NOT NULL,
  `address` varchar(255) default NULL,
  `header_img_url` varchar(255) default '',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'Admin', '691b14d79bf0fa2215f155235df5e670b64394cc', '7efbd59d9741d34f', 'admin', '2012-06-04 01:00:00', null, '2015-04-30 19:34:19', '13126751501', null, null);
INSERT INTO `t_user` VALUES ('2', 'user', 'kevin', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '6d65d24122c30500', 'user', '2012-06-04 02:00:00', null, '2015-04-30 19:35:51', '13126751502', null, null);
