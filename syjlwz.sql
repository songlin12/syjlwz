# Host: localhost:3309  (Version 5.7.10)
# Date: 2021-12-25 22:57:31
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "admin"
#
create database syjlwz;
use syjlwz;
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '' COMMENT '用户名',
  `userpw` varchar(50) DEFAULT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

#
# Data for table "admin"
#

INSERT INTO `admin` VALUES (19,'admin','123456','李管理','18899999999'),(22,'gly','123456','张凤霞','15566666666');

#
# Structure for table "plinfo"
#

DROP TABLE IF EXISTS `plinfo`;
CREATE TABLE `plinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zpid` int(11) DEFAULT '0',
  `uid` int(11) DEFAULT '0',
  `plnr` varchar(500) DEFAULT NULL,
  `plsj` varchar(20) DEFAULT NULL,
  `plhf` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "plinfo"
#

INSERT INTO `plinfo` VALUES (1,1,1,'这个照片还不错','2021-12-21 14:54:32','自己顶一下'),(2,2,3,'很漂亮的','2021-12-25 22:52:42','谢谢你的支持'),(3,3,3,'拍的真漂亮','2021-12-25 22:55:50','感谢你的支持，我会拍更多的');

#
# Structure for table "uinfo"
#

DROP TABLE IF EXISTS `uinfo`;
CREATE TABLE `uinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `userpwd` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "uinfo"
#

INSERT INTO `uinfo` VALUES (1,'user01','123456','李强','15566666655','3225866154@qq.com'),(3,'user02','123456','张明','15525252525','1111@163.com');

#
# Structure for table "zpinfo"
#

DROP TABLE IF EXISTS `zpinfo`;
CREATE TABLE `zpinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT '0',
  `name` varchar(200) DEFAULT NULL,
  `qyid` int(11) DEFAULT '0',
  `ztid` int(11) DEFAULT '0',
  `xsid` int(11) DEFAULT '0',
  `fujian` varchar(255) DEFAULT NULL,
  `cdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "zpinfo"
#

INSERT INTO `zpinfo` VALUES (1,1,'中国江苏南京城市天际线清晨风光',2,1,1,'20211221142944.png','2021-12-21 14:29:50'),(2,1,'广州城市风光',3,1,2,'20211221144115.png','2021-12-21 14:41:17'),(3,1,'蓝调澳门',0,1,1,'20211221144249.png','2021-12-21 14:42:51'),(5,1,'跑步机运动健身',0,3,3,'20211221144942.png','2021-12-21 14:49:43'),(6,1,'文艺甜美女生绘画',0,3,2,'20211221145206.png','2021-12-21 14:52:08'),(7,1,'九宫格火锅',0,2,1,'20211221145306.png','2021-12-21 14:53:08'),(8,3,'舞蹈老师',0,3,3,'20211222151036.jpg','2021-12-22 15:10:38');

#
# Structure for table "zpqy"
#

DROP TABLE IF EXISTS `zpqy`;
CREATE TABLE `zpqy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "zpqy"
#

INSERT INTO `zpqy` VALUES (1,'北京'),(2,'上海'),(3,'广州');

#
# Structure for table "zptype"
#

DROP TABLE IF EXISTS `zptype`;
CREATE TABLE `zptype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "zptype"
#

INSERT INTO `zptype` VALUES (1,'城市风光'),(2,'美食'),(3,'人像');

#
# Structure for table "zpxs"
#

DROP TABLE IF EXISTS `zpxs`;
CREATE TABLE `zpxs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "zpxs"
#

INSERT INTO `zpxs` VALUES (1,'100万'),(2,'500万'),(3,'800万');
