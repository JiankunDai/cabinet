/*
 Navicat Premium Data Transfer

 Source Server         : seckill
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : nefulib

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 07/09/2019 19:47:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `config_system_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '配置的id',
  `config_key` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置的key',
  `config_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置的value（时间）',
  PRIMARY KEY (`config_system_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, 'startTime', '2019-09-21 12:00:00');
INSERT INTO `config` VALUES (2, 'endTime', '2019-09-29 12:00:00');

SET FOREIGN_KEY_CHECKS = 1;
