/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50623
 Source Host           : localhost
 Source Database       : messagebox

 Target Server Type    : MySQL
 Target Server Version : 50623
 File Encoding         : utf-8

 Date: 03/16/2015 11:48:58 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ANNOUNCEMENT`
-- ----------------------------
DROP TABLE IF EXISTS `ANNOUNCEMENT`;
CREATE TABLE `ANNOUNCEMENT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` text COLLATE utf8_bin NOT NULL,
  `DATE` datetime NOT NULL,
  `ONLINE` varchar(32) COLLATE utf8_bin NOT NULL,
  `PUBLISHER` varchar(64) COLLATE utf8_bin NOT NULL,
  `TITLE` varchar(255) COLLATE utf8_bin NOT NULL,
  `TYPE` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `ANNOUNCEMENT`
-- ----------------------------
BEGIN;
INSERT INTO `ANNOUNCEMENT` VALUES ('3', 0x61616161, '2015-03-13 21:39:42', 'true', 'admin', 'aaaaa', '1'), ('4', 0x31313131313131310d0a310d0a0d0a310d0a310d0a3131, '2015-03-13 22:46:28', 'true', 'admin', '11111', '1'), ('5', 0x616161616161, '2015-03-13 22:54:36', 'false', 'admin', 'aaaaaa', '1'), ('6', 0x61616161616161, '2015-03-13 23:00:57', 'false', 'admin', 'aaaa', '1'), ('7', 0xe59388e59388e59388e59388e59388, '2015-03-15 10:31:52', 'false', 'admin', '公告', '1'), ('8', 0x717764515763, '2015-03-15 14:50:43', 'true', 'admin', '111111111', '1'), ('9', 0x71776361736361, '2015-03-16 09:48:56', 'true', 'admin', 'qqqqqqqqq', '1'), ('10', 0x7364636463647363534344, '2015-03-16 09:53:21', 'true', 'admin', '没图案云', '1');
COMMIT;

-- ----------------------------
--  Table structure for `GROUP_ANNOUN`
-- ----------------------------
DROP TABLE IF EXISTS `GROUP_ANNOUN`;
CREATE TABLE `GROUP_ANNOUN` (
  `GA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ANNOUN_ID` int(11) DEFAULT NULL,
  `GROUP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`GA_ID`),
  KEY `FK_5hnp4shvwepn9c6fk5jgm8kt9` (`ANNOUN_ID`),
  KEY `FK_db6pu6csg069o083k5sfil3qr` (`GROUP_ID`),
  CONSTRAINT `FK_5hnp4shvwepn9c6fk5jgm8kt9` FOREIGN KEY (`ANNOUN_ID`) REFERENCES `ANNOUNCEMENT` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_db6pu6csg069o083k5sfil3qr` FOREIGN KEY (`GROUP_ID`) REFERENCES `GROUP_INFO` (`GROUP_ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `GROUP_ANNOUN`
-- ----------------------------
BEGIN;
INSERT INTO `GROUP_ANNOUN` VALUES ('3', '8', '20'), ('4', '8', '18'), ('6', '8', '19'), ('7', '10', '19'), ('8', '10', '18'), ('9', '10', '20');
COMMIT;

-- ----------------------------
--  Table structure for `GROUP_INFO`
-- ----------------------------
DROP TABLE IF EXISTS `GROUP_INFO`;
CREATE TABLE `GROUP_INFO` (
  `GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GROUP_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `GROUP_INFO`
-- ----------------------------
BEGIN;
INSERT INTO `GROUP_INFO` VALUES ('18', '学校'), ('19', '省属高校'), ('20', '部属高校');
COMMIT;

-- ----------------------------
--  Table structure for `RECORD`
-- ----------------------------
DROP TABLE IF EXISTS `RECORD`;
CREATE TABLE `RECORD` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ANNOUN_ID` int(11) DEFAULT NULL,
  `U_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_3mkpvsx1p05uresiiqcxjtxkg` (`U_ID`),
  KEY `FK_m12mflg3x77e72727wvavvbat` (`ANNOUN_ID`),
  CONSTRAINT `FK_3mkpvsx1p05uresiiqcxjtxkg` FOREIGN KEY (`U_ID`) REFERENCES `USER_INFO` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_m12mflg3x77e72727wvavvbat` FOREIGN KEY (`ANNOUN_ID`) REFERENCES `ANNOUNCEMENT` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `RECORD`
-- ----------------------------
BEGIN;
INSERT INTO `RECORD` VALUES ('1', '8', 'qwdwedwe');
COMMIT;

-- ----------------------------
--  Table structure for `RELATION`
-- ----------------------------
DROP TABLE IF EXISTS `RELATION`;
CREATE TABLE `RELATION` (
  `RELATION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GROUP_ID` int(11) DEFAULT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`RELATION_ID`),
  KEY `FK_q374h2chm6wrkygh9w4xhlle` (`GROUP_ID`),
  KEY `FK_tb2uoe34mih1ubhl4rgg20482` (`USER_ID`),
  CONSTRAINT `FK_q374h2chm6wrkygh9w4xhlle` FOREIGN KEY (`GROUP_ID`) REFERENCES `GROUP_INFO` (`GROUP_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tb2uoe34mih1ubhl4rgg20482` FOREIGN KEY (`USER_ID`) REFERENCES `USER_INFO` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `RELATION`
-- ----------------------------
BEGIN;
INSERT INTO `RELATION` VALUES ('23', '18', 'qwdwedwe'), ('27', '20', 'qwdwedwe'), ('28', '18', 'qfawefrf'), ('29', '20', 'qfawefrf'), ('30', '19', 'wqweqwe');
COMMIT;

-- ----------------------------
--  Table structure for `USER_INFO`
-- ----------------------------
DROP TABLE IF EXISTS `USER_INFO`;
CREATE TABLE `USER_INFO` (
  `USER_ID` varchar(32) NOT NULL,
  `AREA_CODE` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `DEPARTMENT` varchar(24) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `EMAIL` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `FAX` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `FXMC` varchar(24) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `JOB_TITLE` varchar(24) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `LOGIN_DATE` datetime DEFAULT NULL,
  `MOBILE_PHONE` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `OFFICE_PHONE` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `ORG_CODE` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `ORG_NAME` varchar(24) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `QQ` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `REAL_NAME` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `USER_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `USERNAME` varchar(24) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `USER_INFO`
-- ----------------------------
BEGIN;
INSERT INTO `USER_INFO` VALUES ('qfawefrf', '13', '2015-03-15 16:01:50', '3', null, null, null, null, '2015-03-15 16:01:55', null, null, '23', '2323', null, '23', '2015-03-15 16:02:04', 'school', 'cdasdcasd'), ('qwdwedwe', '13', '2015-03-15 10:03:21', '13e12', null, null, null, '1', '2015-03-15 10:03:28', null, null, '1212', '3dwef', null, '12', '2015-03-15 10:03:41', 'school', 'wwww'), ('wqweqwe', '12', '2015-03-15 10:54:08', 'e', null, null, null, null, '2015-03-15 10:54:12', null, null, '23', '3', null, '23', '2015-03-15 10:54:23', 'school', 'asdfasd');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
