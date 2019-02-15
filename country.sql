/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50545
Source Host           : 127.0.0.1:3306
Source Database       : country

Target Server Type    : MYSQL
Target Server Version : 50545
File Encoding         : 65001

Date: 2016-09-26 09:50:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `appId` int(11) NOT NULL AUTO_INCREMENT,
  `appName` varchar(4) NOT NULL,
  `appIcon` varchar(30) NOT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES ('1', '借阅管理', '../images/Icon/54.png');
INSERT INTO `app` VALUES ('2', '图书管理', '../images/Icon/53.png');
INSERT INTO `app` VALUES ('3', '读者管理', '../images/Icon/38.png');
INSERT INTO `app` VALUES ('4', '系统管理', '../images/Icon/71.png');

-- ----------------------------
-- Table structure for appauthority
-- ----------------------------
DROP TABLE IF EXISTS `appauthority`;
CREATE TABLE `appauthority` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `appid` int(11) NOT NULL,
  `menuids` varchar(100) DEFAULT NULL,
  `buttonids` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appauthority
-- ----------------------------
INSERT INTO `appauthority` VALUES ('1', '1', '4', '19,22,23,24,25,26', '1,2,3,4,5,6,7,8,9,10,11,');
INSERT INTO `appauthority` VALUES ('2', '2', '1', '1,2,3,5,30', '26,27,28,29,');
INSERT INTO `appauthority` VALUES ('3', '2', '2', '7,8,9,10,11,12,14,15', '19,20,21,22,23,24,25,33,16,18,');
INSERT INTO `appauthority` VALUES ('4', '2', '4', '19,24,25,26,27,28,29', '7,8,9,12,13,14,15,');
INSERT INTO `appauthority` VALUES ('8', '3', '2', '7,8,10,12,14,15', '33,');
INSERT INTO `appauthority` VALUES ('9', '3', '3', '16,17,18', '30,');
INSERT INTO `appauthority` VALUES ('10', '3', '4', '27,28', '');

-- ----------------------------
-- Table structure for bookmanage
-- ----------------------------
DROP TABLE IF EXISTS `bookmanage`;
CREATE TABLE `bookmanage` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `bname` varchar(30) NOT NULL,
  `cid` int(11) NOT NULL,
  `ISBN` varchar(30) NOT NULL,
  `tid` int(11) NOT NULL,
  `bnum` int(11) NOT NULL,
  `author` varchar(20) NOT NULL,
  `press` varchar(10) NOT NULL,
  `price` float(5,0) NOT NULL,
  `baddTime` datetime NOT NULL,
  `uid` int(11) NOT NULL,
  `bookid` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookmanage
-- ----------------------------
INSERT INTO `bookmanage` VALUES ('1', '由浅入深学java', '1', 'ISBN7-222-05856-2/TP1193', '1', '2', '王五', '中国铁道出版社', '59', '2016-03-20 18:13:56', '1', 'TS001LX0010001');
INSERT INTO `bookmanage` VALUES ('2', 'PB入门教程', '1', 'ISBN978-7-5220-4479-1', '1', '4', '费亚杰', '高等教育出版社', '44', '2016-03-27 19:46:10', '1', 'TS001LX0010002');
INSERT INTO `bookmanage` VALUES ('3', '飘窗', '1', 'ISBN978-7-5407-6974-1', '4', '5', '刘心武', '漓江出版社', '30', '2016-04-08 09:26:04', '3', 'TS002LX0040001');
INSERT INTO `bookmanage` VALUES ('5', '新农村建设和乡村教育', '1', 'ISBN978-7-81133-737-2', '2', '2', '陈尚仁', '哈尔滨工程大学出版社', '30', '2016-04-07 22:10:31', '1', 'TS001LX0020001');
INSERT INTO `bookmanage` VALUES ('6', 'MATLAB数字图像处理', '1', 'ISBN978-7-302-29108-4', '1', '5', '周品', '清华大学出版社', '48', '2016-04-07 22:13:40', '1', 'TS001LX0010003');
INSERT INTO `bookmanage` VALUES ('7', '计算机网络gg', '1', 'ISBN978-7-121-20167-7', '1', '4', '谢希仁', '电子工业出版社', '39', '2016-04-07 22:17:03', '1', 'TS001LX0010004');
INSERT INTO `bookmanage` VALUES ('8', '新时期中小学教师职业道德规范与修养', '1', 'ISBN978-7-5309-5484-3', '2', '5', '肖占鹏', '天津教育出版社', '21', '2016-04-07 22:19:21', '1', 'TS001LX0020002');
INSERT INTO `bookmanage` VALUES ('9', '信息技术', '1', 'ISBN978-7-80767-550-1', '13', '5', '董丽斌', '山西经济出版社', '12', '2016-04-07 22:27:15', '1', 'TS001LX0020003');
INSERT INTO `bookmanage` VALUES ('10', '普通话水平测试', '1', 'ISBN7-100-04658-0', '2', '5', '李东福', '高等印书馆', '13', '2016-04-07 22:29:30', '1', 'TS001LX0020004');
INSERT INTO `bookmanage` VALUES ('11', '申论 山西省公务员录用考试 ', '1', 'ISBN978-7-5115-0966-6', '5', '4', '李永新', '人民日报出版社', '48', '2016-04-07 22:35:20', '1', 'TS001LX0050001');
INSERT INTO `bookmanage` VALUES ('12', '综合素质 教师资格证考试 ', '1', 'ISBN978-7-5110-4479-3', '5', '5', '张跃名', '世界图书出版公司', '45', '2016-04-07 22:37:47', '1', 'TS001LX0050002');
INSERT INTO `bookmanage` VALUES ('13', '教育知识与能力  教师资格证考试  ', '1', 'ISBN978-7-5110-4479-3', '5', '5', '张跃名', '世界图书出版社', '45', '2016-04-07 22:38:48', '1', 'TS001LX0050003');
INSERT INTO `bookmanage` VALUES ('14', '名人成才故事', '1', 'ISBN978-7-81120-807-8', '3', '5', '杨旭', '汕头大学出版社', '10', '2016-04-07 22:41:20', '1', 'TS001LX0030001');
INSERT INTO `bookmanage` VALUES ('15', 'Excel办公高手', '1', 'ISBN978-7-03-031560-1', '7', '3', '胡子平', '科学出版社', '20', '2016-04-07 22:43:05', '1', 'TS001LX0070001');
INSERT INTO `bookmanage` VALUES ('16', '电脑报 2015', '1', 'ISBN978-7-5658-0392-2', '7', '0', '邱玉辉', '汕头大学出版社', '50', '2016-04-07 22:45:16', '1', 'TS001LX0070002');
INSERT INTO `bookmanage` VALUES ('17', 'web程序设计', '1', 'ISBN978-7-302-36143-3', '1', '4', '沈士根 徐晓东', '清华大学出版社', '35', '2016-04-07 22:47:37', '1', 'TS001LX0010005');
INSERT INTO `bookmanage` VALUES ('18', '大学生职业发展与就业指导', '1', 'ISBN978-7-5135-4479-3', '2', '5', '李海星', '山西教育出版社', '33', '2016-04-07 22:49:24', '1', 'TS001LX0020005');
INSERT INTO `bookmanage` VALUES ('19', '人工智能教程', '1', 'ISBN978-7-5260-4479-3', '1', '6', '张仰森 黄改娟', '高等教育出版社', '33', '2016-04-07 22:51:46', '1', 'TS001LX0010006');
INSERT INTO `bookmanage` VALUES ('23', '名人成才故事', '2', 'ISBN978-7-81120-807-8', '3', '5', '杨旭', '汕头大学出版社', '10', '2016-04-07 22:41:20', '3', 'TS002LX0010001');
INSERT INTO `bookmanage` VALUES ('24', 'Excel办公高手', '2', 'ISBN978-7-03-031560-1', '7', '5', '胡子平', '科学出版社', '20', '2016-04-07 22:43:05', '3', 'TS002LX0070001');
INSERT INTO `bookmanage` VALUES ('25', '电脑报 2015', '2', 'ISBN978-7-5658-0392-2', '7', '5', '邱玉辉', '汕头大学出版社', '50', '2016-04-07 22:45:16', '3', 'TS002LX0010002');
INSERT INTO `bookmanage` VALUES ('26', 'web程序设计', '2', 'ISBN978-7-302-36143-3', '1', '5', '沈士根 徐晓东', '清华大学出版社', '35', '2016-04-07 22:47:37', '3', 'TS002LX0010003');
INSERT INTO `bookmanage` VALUES ('27', '大学生职业发展与就业指导', '2', 'ISBN978-7-5135-4479-3', '2', '5', '李海星', '山西教育出版社', '33', '2016-04-07 22:49:24', '3', 'TS002LX0020001');
INSERT INTO `bookmanage` VALUES ('28', '人工智能教程', '2', 'ISBN978-7-5260-4479-3', '1', '5', '张仰森 黄改娟', '高等教育出版社', '33', '2016-04-07 22:51:46', '3', 'TS002LX0010004');
INSERT INTO `bookmanage` VALUES ('30', '福尔摩斯探案全集4', '2', 'ISBN978-7-5442-6427-3', '4', '5', '英 亚瑟·柯南·道尔', '南海出版公司', '14', '2016-04-08 09:30:54', '3', 'TS002LX0040002');
INSERT INTO `bookmanage` VALUES ('31', '狄更斯游记', '2', 'ISBN978-7-5399-7558-0', '4', '5', '狄更斯 刘洋', '江苏凤凰文艺出版社', '39', '2016-04-08 09:34:07', '3', 'TS002LX0040003');
INSERT INTO `bookmanage` VALUES ('32', '海贼王', '1', 'ISBN978-7-5232-6627-3', '12', '5', '张小娴', '高等教育出版社', '20', '2016-04-15 20:41:11', '1', 'TS002LX0120001');
INSERT INTO `bookmanage` VALUES ('33', '明朝那些事', '1', 'ISBN978-7-5232-6427-1', '15', '5', '当时明月', '高等教育出版社', '20', '2016-04-15 19:41:11', '1', 'TS002LX0150001');

-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booktype
-- ----------------------------
INSERT INTO `booktype` VALUES ('1', '计算机');
INSERT INTO `booktype` VALUES ('2', '教育');
INSERT INTO `booktype` VALUES ('3', '散文');
INSERT INTO `booktype` VALUES ('4', '小说');
INSERT INTO `booktype` VALUES ('5', '专业考试');
INSERT INTO `booktype` VALUES ('7', '生活');
INSERT INTO `booktype` VALUES ('10', '医疗');
INSERT INTO `booktype` VALUES ('11', '美术');
INSERT INTO `booktype` VALUES ('12', '动漫');
INSERT INTO `booktype` VALUES ('13', '科技');
INSERT INTO `booktype` VALUES ('15', '历史');

-- ----------------------------
-- Table structure for borbooks
-- ----------------------------
DROP TABLE IF EXISTS `borbooks`;
CREATE TABLE `borbooks` (
  `jid` int(11) NOT NULL AUTO_INCREMENT,
  `btype` int(2) NOT NULL,
  `userid` varchar(255) NOT NULL,
  `bid` int(11) NOT NULL,
  `jianTime` datetime NOT NULL,
  `huanTime` datetime DEFAULT NULL,
  `yingTime` datetime NOT NULL,
  `cid` int(11) NOT NULL,
  PRIMARY KEY (`jid`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borbooks
-- ----------------------------
INSERT INTO `borbooks` VALUES ('1', '0', '1-0003', '2', '2016-04-01 22:15:42', '2016-05-18 12:12:20', '2016-05-01 22:15:42', '1');
INSERT INTO `borbooks` VALUES ('3', '0', '1-0001', '19', '2016-04-08 12:53:00', '2016-07-26 11:17:33', '2016-08-05 13:39:49', '1');
INSERT INTO `borbooks` VALUES ('4', '0', '1-0002', '7', '2016-04-08 12:55:47', '2016-04-19 12:46:02', '2016-05-08 12:55:47', '1');
INSERT INTO `borbooks` VALUES ('7', '0', '1-0002', '6', '2016-04-08 13:00:07', '2016-04-27 17:44:58', '2016-05-08 13:00:07', '1');
INSERT INTO `borbooks` VALUES ('8', '0', '1-0002', '12', '2016-04-08 13:00:43', '2016-04-08 13:12:47', '2016-05-08 13:00:43', '1');
INSERT INTO `borbooks` VALUES ('10', '0', '1-0001', '17', '2016-04-08 13:06:50', '2016-04-27 17:45:04', '2016-05-08 13:15:10', '1');
INSERT INTO `borbooks` VALUES ('13', '0', '1-0002', '10', '2016-04-08 16:47:17', '2016-07-05 13:40:48', '2016-05-08 16:47:17', '1');
INSERT INTO `borbooks` VALUES ('18', '1', '2-0001', '16', '2016-04-12 21:44:02', null, '2016-05-12 21:44:02', '2');
INSERT INTO `borbooks` VALUES ('21', '0', '1-0001', '32', '2016-04-15 22:23:49', '2016-07-05 13:40:04', '2016-05-15 22:23:49', '1');
INSERT INTO `borbooks` VALUES ('23', '0', '1-0001', '16', '2016-04-27 17:42:33', '2016-04-27 17:42:40', '2016-05-27 17:42:33', '1');
INSERT INTO `borbooks` VALUES ('24', '0', '1-0003', '3', '2016-05-11 22:14:45', '2016-07-26 11:18:23', '2016-07-02 15:14:19', '1');
INSERT INTO `borbooks` VALUES ('25', '0', '1-0003', '2', '2016-05-11 22:15:42', '2016-06-02 15:15:11', '2016-07-02 15:14:54', '1');
INSERT INTO `borbooks` VALUES ('26', '2', '1-0003', '32', '2016-06-02 15:17:36', null, '2016-08-26 11:18:26', '1');
INSERT INTO `borbooks` VALUES ('27', '0', '1-0003', '34', '2016-06-02 15:26:42', '2016-07-05 13:40:31', '2016-07-02 15:26:42', '1');
INSERT INTO `borbooks` VALUES ('28', '1', '1-0001', '1', '2016-07-05 13:41:20', null, '2016-08-05 13:41:20', '1');
INSERT INTO `borbooks` VALUES ('29', '1', '1-0001', '1', '2016-07-05 13:41:52', null, '2016-08-05 13:41:52', '1');

-- ----------------------------
-- Table structure for button
-- ----------------------------
DROP TABLE IF EXISTS `button`;
CREATE TABLE `button` (
  `btnid` int(11) NOT NULL AUTO_INCREMENT,
  `btnname` varchar(10) NOT NULL,
  `btnicon` varchar(30) NOT NULL,
  `btnhandler` varchar(20) NOT NULL,
  `menuid` int(11) NOT NULL,
  PRIMARY KEY (`btnid`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of button
-- ----------------------------
INSERT INTO `button` VALUES ('1', '新增', '../images/Icon/11.png', 'add()', '22');
INSERT INTO `button` VALUES ('2', '修改', '../images/Icon/33.png', 'edit()', '22');
INSERT INTO `button` VALUES ('3', '删除', '../images/Icon/12.png', 'del()', '22');
INSERT INTO `button` VALUES ('4', '新增', '../images/Icon/11.png', 'add()', '23');
INSERT INTO `button` VALUES ('5', '修改', '../images/Icon/33.png', 'edit()', '23');
INSERT INTO `button` VALUES ('6', '删除', '../images/Icon/12.png', 'del()', '23');
INSERT INTO `button` VALUES ('7', '新增', '../images/Icon/11.png', 'add()', '24');
INSERT INTO `button` VALUES ('8', '修改', '../images/Icon/33.png', 'edit()', '24');
INSERT INTO `button` VALUES ('9', '删除', '../images/Icon/12.png', 'del()', '24');
INSERT INTO `button` VALUES ('10', '新增', '../images/Icon/11.png', 'add()', '26');
INSERT INTO `button` VALUES ('11', '删除', '../images/Icon/12.png', 'del()', '26');
INSERT INTO `button` VALUES ('12', '编辑', '../images/Icon/24.png', 'edit()', '28');
INSERT INTO `button` VALUES ('13', '新增', '../images/Icon/11.png', 'add()', '29');
INSERT INTO `button` VALUES ('14', '修改', '../images/Icon/33.png', 'edit()', '29');
INSERT INTO `button` VALUES ('15', '删除', '../images/Icon/12.png', 'del()', '29');
INSERT INTO `button` VALUES ('16', '新增', '../images/Icon/11.png', 'add()', '11');
INSERT INTO `button` VALUES ('18', '删除', '../images/Icon/12.png', 'del()', '11');
INSERT INTO `button` VALUES ('19', '修改', '../images/Icon/33.png', 'edit()', '8');
INSERT INTO `button` VALUES ('20', '删除', '../images/Icon/12.png', 'del()', '8');
INSERT INTO `button` VALUES ('21', '新增', '../images/Icon/11.png', 'add()', '9');
INSERT INTO `button` VALUES ('22', '修改', '../images/Icon/33.png', 'edit()', '9');
INSERT INTO `button` VALUES ('23', '删除', '../images/Icon/12.png', 'del()', '9');
INSERT INTO `button` VALUES ('24', '上传', '../images/Icon/3.png', 'add()', '15');
INSERT INTO `button` VALUES ('25', '删除', '../images/Icon/12.png', 'del()', '15');
INSERT INTO `button` VALUES ('26', '借书', '../images/Icon/11.png', 'add()', '2');
INSERT INTO `button` VALUES ('27', '新增', '../images/Icon/11.png', 'add()', '30');
INSERT INTO `button` VALUES ('28', '修改', '../images/Icon/33.png', 'edit()', '30');
INSERT INTO `button` VALUES ('29', '删除', '../images/Icon/12.png', 'del()', '30');
INSERT INTO `button` VALUES ('30', '修改', '../images/Icon/33.png', 'edit()', '17');
INSERT INTO `button` VALUES ('33', '高级搜索', '../images/Icon/95.gif', 'show()', '8');
INSERT INTO `button` VALUES ('34', '权限设置', '../images/Icon/91.png', 'authority()', '23');

-- ----------------------------
-- Table structure for compensate
-- ----------------------------
DROP TABLE IF EXISTS `compensate`;
CREATE TABLE `compensate` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `userid` varchar(6) NOT NULL,
  `bid` int(11) NOT NULL,
  `guaTime` datetime NOT NULL,
  `peiTime` datetime DEFAULT NULL,
  `yprice` float(5,0) NOT NULL,
  `sprice` float(5,0) DEFAULT NULL,
  `bookid` varchar(15) NOT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of compensate
-- ----------------------------
INSERT INTO `compensate` VALUES ('1', '1', '1-0001', '3', '2016-04-08 16:25:36', '2016-04-28 21:22:57', '39', '39', 'TS002LX0040001');
INSERT INTO `compensate` VALUES ('4', '1', '1-0003', '5', '2016-04-28 22:06:40', '2016-04-28 22:06:40', '30', '34', 'TS001LX0020001');
INSERT INTO `compensate` VALUES ('7', '1', '1-0003', '7', '2016-06-02 15:21:17', '2016-06-02 15:22:03', '39', '20', 'TS001LX0010004');

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(30) NOT NULL,
  `caddTime` datetime NOT NULL,
  `caddress` varchar(30) NOT NULL,
  `cphone` varchar(12) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of country
-- ----------------------------
INSERT INTO `country` VALUES ('1', '马坪头村', '2016-03-20 14:56:07', '山西省晋城市泽州县川底乡马坪头村', '0356-3333333');
INSERT INTO `country` VALUES ('2', '沙沟村', '2016-03-12 18:42:40', '山西省晋城市泽州县川底乡沙沟村', '0356-3434355');
INSERT INTO `country` VALUES ('3', '下麓村', '2016-03-13 11:06:46', '山西省晋城市泽州县川底乡下麓村', '11111111111');
INSERT INTO `country` VALUES ('4', '晓庄村', '2016-06-02 15:40:49', '草你谨记好不', '123456789');

-- ----------------------------
-- Table structure for ebook
-- ----------------------------
DROP TABLE IF EXISTS `ebook`;
CREATE TABLE `ebook` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `bname` varchar(30) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ebook
-- ----------------------------
INSERT INTO `ebook` VALUES ('35', 'CSS完全参考手册3.0', '1', '/up_ebook\\admin\\2016-04-15\\22_49.chm', '1', '2016-04-15 22:49:18', 'chm');
INSERT INTO `ebook` VALUES ('36', 'java+web', '1', '/up_ebook\\admin\\2016-04-15\\22_50.CHM', '1', '2016-04-15 22:50:08', 'CHM');
INSERT INTO `ebook` VALUES ('37', '网页制作完全手册', '1', '/up_ebook\\admin\\2016-04-15\\22_50.CHM', '1', '2016-04-15 22:50:36', 'CHM');
INSERT INTO `ebook` VALUES ('38', 'MySQL 5.1参考手册_中文帮助文档', '1', '/up_ebook\\admin\\2016-04-15\\22_51.chm', '1', '2016-04-15 22:51:56', 'chm');
INSERT INTO `ebook` VALUES ('39', '《JSP设计（第三版）》英文版', '1', '/up_ebook\\admin\\2016-04-15\\22_52.chm', '1', '2016-04-15 22:52:22', 'chm');
INSERT INTO `ebook` VALUES ('42', '网页制作精华', '2', '/up_ebook\\张三\\2016-05-09\\16_35.chm', '3', '2016-05-09 16:35:22', 'chm');
INSERT INTO `ebook` VALUES ('43', 'SQL参考手册', '2', '/up_ebook\\张三\\2016-05-09\\16_36.chm', '3', '2016-05-09 16:36:45', 'chm');

-- ----------------------------
-- Table structure for libraryname
-- ----------------------------
DROP TABLE IF EXISTS `libraryname`;
CREATE TABLE `libraryname` (
  `lnid` int(11) NOT NULL AUTO_INCREMENT,
  `lname` varchar(20) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `colbook` varchar(10) DEFAULT NULL,
  `userNum` int(11) DEFAULT NULL,
  `area` varchar(8) DEFAULT NULL,
  `intro` text,
  `lphone` varchar(12) DEFAULT NULL,
  `laddress` varchar(30) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`lnid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of libraryname
-- ----------------------------
INSERT INTO `libraryname` VALUES ('9', '川底村', '4', '0', '0', '1', '1', '1', '1', '3');
INSERT INTO `libraryname` VALUES ('13', '马坪头图书馆', '1', '20', '3', '340', '马坪头图书馆响应国家的政策，建立了乡村图书馆。这个图书馆即可以丰富他们的业余生活，培养文化素养，也解决了孩子看书难，借书难的问题。村里的人们都争先来借书，顿时连空气都变得儒雅了。人们也更加的有涵养了。马坪头图书馆响应国家的政策，建立了乡村图书馆。这个图书馆即可以丰富他们的业余生活，培养文化素养，也解决了孩子看书难，借书难的问题。村里的人们都争先来借书，顿时连空气都变得儒雅了。人们也更加的有涵养了。马坪头图书馆响应国家的政策，建立了乡村图书馆。这个图书馆即可以丰富他们的业余生活，培养文化素养，也解决了孩子看书难，借书难的问题。村里的人们都争先来借书，顿时连空气都变得儒雅了。人们也更加的有涵养了。马坪头图书馆响应国家的政策，建立了乡村图书馆。这个图书馆即可以丰富他们的业余生活，培养文化素养，也解决了孩子看书难，借书难的问题。村里的人们都争先来借书，顿时连空气都变得儒雅了。人们也更加的有涵养了。马坪头图书馆响应国家的政策，建立了乡村图书馆。这个图书馆即可以丰富他们的业余生活，培养文化素养，也解决了孩子看书难，借书难的问题。村里的人们都争先来借书，顿时连空气都变得儒雅了。人们也更加的有涵养了。', '13835645041', '山西省晋城市泽州县', '1');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(10) NOT NULL,
  `menuIcon` varchar(40) NOT NULL,
  `pid` int(11) NOT NULL,
  `appid` int(11) NOT NULL,
  `menuUrl` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '借阅管理', '../images/Icon/25.png', '0', '1', null);
INSERT INTO `menu` VALUES ('2', '借书', '../images/Icon/28.png', '1', '1', 'bor/addBor.jsp');
INSERT INTO `menu` VALUES ('3', '借阅信息', '../images/Icon/27.png', '1', '1', 'bor/bor_jy.jsp');
INSERT INTO `menu` VALUES ('5', '还书记录', '../images/Icon/25.png', '1', '1', 'bor/bor_gh.jsp');
INSERT INTO `menu` VALUES ('7', '图书管理', '../images/Icon/54.png', '0', '2', '');
INSERT INTO `menu` VALUES ('8', '库存图书', '../images/Icon/53.png', '7', '2', 'cty/cty_BookManage.jsp?menuId=8');
INSERT INTO `menu` VALUES ('9', '图书采购', '../images/Icon/81.png', '7', '2', 'cty/cty_BookManage.jsp?menuId=9');
INSERT INTO `menu` VALUES ('10', '分类查找', '../images/Icon/28.png', '0', '2', '');
INSERT INTO `menu` VALUES ('11', '图书类型', '../images/Icon/97.png', '10', '2', 'cty/cty_type.jsp');
INSERT INTO `menu` VALUES ('12', '类型查找', '../images/Icon/75.png', '10', '2', 'cty/findType.jsp');
INSERT INTO `menu` VALUES ('14', '图书资源', '../images/Icon/39.png', '0', '2', '');
INSERT INTO `menu` VALUES ('15', '图书资源', '../images/Icon/39.png', '14', '2', 'cty/cty_Ebook.jsp');
INSERT INTO `menu` VALUES ('16', '读者管理', '../images/Icon/23.png', '0', '3', null);
INSERT INTO `menu` VALUES ('17', '个人信息', '../images/Icon/93.png', '16', '3', 'person/person.jsp');
INSERT INTO `menu` VALUES ('18', '借阅历史', '../images/Icon/33.png', '16', '3', 'person/per_jy.jsp');
INSERT INTO `menu` VALUES ('19', '帐户管理', '../images/Icon/28.png', '0', '4', '');
INSERT INTO `menu` VALUES ('22', '乡村管理', '../images/Icon/63.png', '19', '4', 'sys/sys_country.jsp');
INSERT INTO `menu` VALUES ('23', '角色管理', '../images/Icon/67.png', '19', '4', 'sys/sys_role.jsp');
INSERT INTO `menu` VALUES ('24', '人员管理', '../images/Icon/37.png', '19', '4', 'sys/sys_user.jsp');
INSERT INTO `menu` VALUES ('25', '消息管理', '../images/Icon/26.png', '0', '4', '');
INSERT INTO `menu` VALUES ('26', '系统公告', '../images/Icon/28.png', '25', '4', 'sys/sys_Pn.jsp');
INSERT INTO `menu` VALUES ('27', '馆藏设置', '../images/Icon/26.png', '0', '4', '');
INSERT INTO `menu` VALUES ('28', '本馆信息', '../images/Icon/55.png', '27', '4', 'sys/sys_Lbyname.jsp');
INSERT INTO `menu` VALUES ('29', '借阅规则', '../images/Icon/23.png', '27', '4', 'sys/sys_rule.jsp');
INSERT INTO `menu` VALUES ('30', '挂失赔偿', '../images/Icon/91.png', '1', '1', 'bor/bor_com.jsp');

-- ----------------------------
-- Table structure for pubnotice
-- ----------------------------
DROP TABLE IF EXISTS `pubnotice`;
CREATE TABLE `pubnotice` (
  `pnid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(60) NOT NULL,
  `content` text NOT NULL,
  `pntime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `uid` int(11) NOT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`pnid`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pubnotice
-- ----------------------------
INSERT INTO `pubnotice` VALUES ('29', '图书馆升级', '因版本问题，需要升级', '2016-04-07 21:07:23', '1', '23');
INSERT INTO `pubnotice` VALUES ('36', '搬迁公告', '从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。从今起，图书馆搬至广场周围。', '2016-04-07 21:46:49', '1', '6');
INSERT INTO `pubnotice` VALUES ('37', '5uhtj5ti夜uyeruytiuiu ', '是对', '2016-06-02 15:56:26', '1', '4');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(10) NOT NULL,
  `addTime` datetime NOT NULL,
  `rdesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员', '2016-03-15 20:01:39', '用于管理系统操作');
INSERT INTO `role` VALUES ('2', '图书管理员', '2016-03-15 20:05:05', '用于管理图书馆的日常操作');
INSERT INTO `role` VALUES ('3', '读者', '2016-03-15 20:07:42', '用于查看图书，所借书籍，修改自己的信息');

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `ruid` int(11) NOT NULL AUTO_INCREMENT,
  `runame` varchar(20) NOT NULL,
  `ruday` varchar(3) NOT NULL,
  `runum` int(11) NOT NULL,
  `renew` int(11) NOT NULL,
  `userid` varchar(6) NOT NULL,
  `rutime` datetime NOT NULL,
  PRIMARY KEY (`ruid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rule
-- ----------------------------
INSERT INTO `rule` VALUES ('1', '借阅规则2', '30', '5', '1', 'admin', '2016-03-19 11:54:27');
INSERT INTO `rule` VALUES ('2', '借阅规则1', '30', '5', '1', 'admin', '2016-03-19 18:41:22');
INSERT INTO `rule` VALUES ('3', '借阅规则3', '40', '22', '3', '1-0001', '2016-04-07 21:59:26');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(6) NOT NULL,
  `uname` varchar(10) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `cid` int(11) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `userPhone` varchar(11) NOT NULL,
  `uaddTime` datetime NOT NULL,
  `rid` int(11) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '1-0001', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', '女', '13456788984', '2016-03-13 20:43:24', '1', '12121@qq.com', '山西');
INSERT INTO `users` VALUES ('2', '1-0002', '原梅梅', 'e10adc3949ba59abbe56e057f20f883e', '1', '女', '13456788984', '2016-03-13 20:43:24', '3', '12121@qq.com', '山西');
INSERT INTO `users` VALUES ('3', '2-0001', '张三', 'e10adc3949ba59abbe56e057f20f883e', '2', '女', '13456788984', '2016-03-13 20:43:24', '2', '12121@qq.com', '山西');
INSERT INTO `users` VALUES ('4', '1-0003', '邓晓苗', 'e10adc3949ba59abbe56e057f20f883e', '1', '女', '13456788984', '2016-03-13 20:43:24', '2', '12121@qq.com', '山西');
INSERT INTO `users` VALUES ('13', '3-0001', 'yuan', 'e10adc3949ba59abbe56e057f20f883e', '3', '男', '7', '2016-03-17 13:37:27', '2', '7', '7');
INSERT INTO `users` VALUES ('14', '3-0002', 'zhang', 'e10adc3949ba59abbe56e057f20f883e', '3', '男', '1111', '2016-03-22 12:00:02', '3', '1', '1');
INSERT INTO `users` VALUES ('15', '2-0002', '周宁', 'e10adc3949ba59abbe56e057f20f883e', '2', '男', '1', '2016-04-04 10:23:29', '3', '1', '1');
INSERT INTO `users` VALUES ('16', '1-0004', '原浩亮', 'e10adc3949ba59abbe56e057f20f883e', '1', '男', '12345678', '2016-06-02 15:31:59', '3', '1234567890', '反反复复反反复复反反复复反反复复反反复复吩咐');
