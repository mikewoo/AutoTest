/*
Navicat MySQL Data Transfer

Source Server         : 192.168.33.100
Source Server Version : 50635
Source Host           : 192.168.33.100:3306
Source Database       : testcase

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-12-26 16:57:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for add_user_case
-- ----------------------------
DROP TABLE IF EXISTS `add_user_case`;
CREATE TABLE `add_user_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别（0：男，1：女）',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `permission` tinyint(4) NOT NULL COMMENT '权限',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  `expected` varchar(50) NOT NULL COMMENT '期望结果（true或者false）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of add_user_case
-- ----------------------------

-- ----------------------------
-- Table structure for get_user_info_case
-- ----------------------------
DROP TABLE IF EXISTS `get_user_info_case`;
CREATE TABLE `get_user_info_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `expected` varchar(50) NOT NULL COMMENT '期望结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of get_user_info_case
-- ----------------------------

-- ----------------------------
-- Table structure for get_user_list_case
-- ----------------------------
DROP TABLE IF EXISTS `get_user_list_case`;
CREATE TABLE `get_user_list_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别（0：男，1：女）',
  `expected` varchar(50) NOT NULL COMMENT '期望结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of get_user_list_case
-- ----------------------------

-- ----------------------------
-- Table structure for login_case
-- ----------------------------
DROP TABLE IF EXISTS `login_case`;
CREATE TABLE `login_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `expected` varchar(50) NOT NULL COMMENT '期望结果（true或者false）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_case
-- ----------------------------

-- ----------------------------
-- Table structure for update_user_info_case
-- ----------------------------
DROP TABLE IF EXISTS `update_user_info_case`;
CREATE TABLE `update_user_info_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别（0：男，1：女）',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `permission` tinyint(4) DEFAULT NULL COMMENT '权限（0：管理员，1：普通用户）',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '是否删除（0：未删除，1：已删除）',
  `expected` varchar(50) NOT NULL COMMENT '期望结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of update_user_info_case
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别（0：男，1：女）',
  `permission` tinyint(4) NOT NULL DEFAULT '1' COMMENT '权限（0：管理员，1：普通用户）',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '是否删除（0：未删除，1：已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
