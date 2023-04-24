/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : syjlwz

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 24/04/2023 17:48:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户名',
  `userpw` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (19, 'admin', '123456', '李管理', '18899999999');
INSERT INTO `admin` VALUES (22, 'gly', '123456', '张凤霞', '15566666666');

-- ----------------------------
-- Table structure for plinfo
-- ----------------------------
DROP TABLE IF EXISTS `plinfo`;
CREATE TABLE `plinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zpid` int(11) NULL DEFAULT 0,
  `uid` int(11) NULL DEFAULT 0,
  `plnr` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `plsj` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `plhf` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plinfo
-- ----------------------------
INSERT INTO `plinfo` VALUES (1, 1, 1, '这个照片还不错', '2021-12-21 14:54:32', '自己顶一下');
INSERT INTO `plinfo` VALUES (2, 2, 3, '很漂亮的', '2021-12-25 22:52:42', '谢谢你的支持');
INSERT INTO `plinfo` VALUES (3, 3, 3, '拍的真漂亮', '2021-12-25 22:55:50', '感谢你的支持，我会拍更多的');

-- ----------------------------
-- Table structure for uinfo
-- ----------------------------
DROP TABLE IF EXISTS `uinfo`;
CREATE TABLE `uinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userpwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of uinfo
-- ----------------------------
INSERT INTO `uinfo` VALUES (1, 'user01', '123456', '李强', '15566666655', '3225866154@qq.com');
INSERT INTO `uinfo` VALUES (3, 'user02', '123456', '张明', '15525252525', '1111@163.com');

-- ----------------------------
-- Table structure for zpinfo
-- ----------------------------
DROP TABLE IF EXISTS `zpinfo`;
CREATE TABLE `zpinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT 0,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qyid` int(11) NULL DEFAULT 0,
  `ztid` int(11) NULL DEFAULT 0,
  `xsid` int(11) NULL DEFAULT 0,
  `fujian` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cdate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT 0 COMMENT '作品审核状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '作品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zpinfo
-- ----------------------------
INSERT INTO `zpinfo` VALUES (1, 1, '中国江苏南京城市天际线清晨风光', 2, 1, 1, '20211221142944.png', '2021-12-21 14:29:50', 1);
INSERT INTO `zpinfo` VALUES (2, 1, '广州城市风光', 3, 1, 2, '20211221144115.png', '2021-12-21 14:41:17', 1);
INSERT INTO `zpinfo` VALUES (3, 1, '蓝调澳门', 0, 1, 1, '20211221144249.png', '2021-12-21 14:42:51', 1);
INSERT INTO `zpinfo` VALUES (5, 1, '跑步机运动健身', 0, 3, 3, '20211221144942.png', '2021-12-21 14:49:43', 1);
INSERT INTO `zpinfo` VALUES (6, 1, '文艺甜美女生绘画', 0, 3, 2, '20211221145206.png', '2021-12-21 14:52:08', 1);
INSERT INTO `zpinfo` VALUES (7, 1, '九宫格火锅', 0, 2, 1, '20211221145306.png', '2021-12-21 14:53:08', 1);
INSERT INTO `zpinfo` VALUES (8, 3, '舞蹈老师', 0, 3, 3, '20211222151036.jpg', '2021-12-22 15:10:38', 1);
INSERT INTO `zpinfo` VALUES (9, 1, '作品测试', NULL, 3, NULL, '20230424153028.jpg', '2023-04-24 15:30:32', 0);

-- ----------------------------
-- Table structure for zpqy
-- ----------------------------
DROP TABLE IF EXISTS `zpqy`;
CREATE TABLE `zpqy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zpqy
-- ----------------------------
INSERT INTO `zpqy` VALUES (1, '北京');
INSERT INTO `zpqy` VALUES (2, '上海');
INSERT INTO `zpqy` VALUES (3, '广州');

-- ----------------------------
-- Table structure for zptype
-- ----------------------------
DROP TABLE IF EXISTS `zptype`;
CREATE TABLE `zptype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zptype
-- ----------------------------
INSERT INTO `zptype` VALUES (1, '城市风光');
INSERT INTO `zptype` VALUES (2, '美食');
INSERT INTO `zptype` VALUES (3, '人像');

-- ----------------------------
-- Table structure for zpxs
-- ----------------------------
DROP TABLE IF EXISTS `zpxs`;
CREATE TABLE `zpxs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zpxs
-- ----------------------------
INSERT INTO `zpxs` VALUES (1, '100万');
INSERT INTO `zpxs` VALUES (2, '500万');
INSERT INTO `zpxs` VALUES (3, '800万');

SET FOREIGN_KEY_CHECKS = 1;
