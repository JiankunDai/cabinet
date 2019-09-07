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

 Date: 07/09/2019 19:47:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for column
-- ----------------------------
DROP TABLE IF EXISTS `column`;
CREATE TABLE `column`  (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `floor` int(30) NULL DEFAULT NULL,
  `col` int(30) NULL DEFAULT NULL,
  `count` int(30) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of column
-- ----------------------------
INSERT INTO `column` VALUES (1, 2, 1, 105);
INSERT INTO `column` VALUES (2, 2, 2, 105);
INSERT INTO `column` VALUES (3, 2, 3, 120);
INSERT INTO `column` VALUES (4, 2, 4, 60);
INSERT INTO `column` VALUES (5, 2, 5, 60);
INSERT INTO `column` VALUES (6, 2, 6, 60);
INSERT INTO `column` VALUES (7, 2, 7, 60);
INSERT INTO `column` VALUES (8, 2, 8, 120);
INSERT INTO `column` VALUES (9, 2, 9, 105);
INSERT INTO `column` VALUES (10, 2, 10, 120);
INSERT INTO `column` VALUES (11, 2, 11, 105);
INSERT INTO `column` VALUES (12, 2, 12, 39);
INSERT INTO `column` VALUES (13, 3, 1, 40);
INSERT INTO `column` VALUES (14, 3, 2, 30);
INSERT INTO `column` VALUES (23, 2, 12, 105);
INSERT INTO `column` VALUES (24, 3, 3, 150);
INSERT INTO `column` VALUES (25, 3, 4, 150);
INSERT INTO `column` VALUES (26, 3, 5, 150);
INSERT INTO `column` VALUES (27, 3, 6, 150);
INSERT INTO `column` VALUES (28, 3, 7, 150);
INSERT INTO `column` VALUES (29, 3, 8, 150);

SET FOREIGN_KEY_CHECKS = 1;
