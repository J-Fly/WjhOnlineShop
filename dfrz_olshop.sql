/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : dfrz_olshop

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2020-09-27 22:47:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_mc`
-- ----------------------------
DROP TABLE IF EXISTS `t_mc`;
CREATE TABLE `t_mc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `price` decimal(16,2) DEFAULT NULL,
  `filename` varchar(300) DEFAULT NULL,
  `filepath` varchar(300) DEFAULT NULL,
  `isdel` int(1) DEFAULT NULL,
  `dcdate` date DEFAULT NULL,
  `maxid` int(11) DEFAULT NULL,
  `minid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1` (`minid`),
  CONSTRAINT `fk1` FOREIGN KEY (`minid`) REFERENCES `t_mc_type` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_mc
-- ----------------------------
INSERT INTO `t_mc` VALUES ('1', '小米9', '小米9是小米公司于2019年2月20日14点在北京工业大学体育馆发布的手机；内部代号是战斗天使。小米9采用圆润的曲面机身，全曲面机身与手掌贴合，颜色有深空灰、全息幻彩紫、全息幻彩蓝；边框厚度3.5毫米，最厚处7.61毫米，手机重173克。', '2499.00', 'temp.jpg', '小米9.jpg', '0', '2020-07-20', '1', '25');
INSERT INTO `t_mc` VALUES ('2', '小米10', '小米10是小米公司旗下的手机，是一部“为了梦想打造的高端旗舰手机”，也是小米十年集大成之作，于2020年2月13日在国内正式发布，于2020年3月27日在海外正式发布。', '2999.00', 'temp.jpg', '小米10.jpg', '0', '2020-07-20', '1', '25');
INSERT INTO `t_mc` VALUES ('3', '小米10Pro', '小米10Pro是小米公司研发的一款手机，于2020年2月13日正式发布。小米10 Pro的正面采用AMOLED曲面原色屏，100%覆盖DCI-P3和sRGB色域，高度162.6毫米，宽度74.8毫米，厚度8.96毫米，重量208克，提供“珍珠白”、“星空蓝”双色可选。', '3999.00', 'temp.jpg', '小米10Pro.jpg', '0', '2020-07-20', '1', '25');
INSERT INTO `t_mc` VALUES ('4', '三星 S20', '三星Galaxy S20是三星在2020年2月12日发布的一款智能手机。Galaxy S20提供遐想灰、浮氧蓝、柔雾粉三种配色。2020年2月12日凌晨正式发布。', '6999.00', 'temp.jpg', '三星S20.jpg', '0', '2020-07-20', '1', '20');
INSERT INTO `t_mc` VALUES ('5', '三星Galaxy S20+', '三星Galaxy S20+采用了6.7英寸Dynamic AMOLED Infinity-O屏幕，支持120Hz刷新率与HDR10+。', '7999.00', '中文temp.jpg', '三星Galaxy S20+.jpg', '0', '2020-07-20', '1', '20');
INSERT INTO `t_mc` VALUES ('6', '小米柔风空调', '小米柔风立式空调是小米商城上架的一款新产品。除了常见的空调功能外，它支持柔风模式、全直流变频设计，拥有新一级能效认证，并且可以接入米家APP，支持小爱同学远程语音智能操控。', '2399.00', 'temp.jpg', '小米柔风空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('7', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('8', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('9', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('10', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('11', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('12', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('13', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('14', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('15', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('16', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('17', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('18', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('19', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('20', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('21', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('22', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('23', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('24', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('25', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('26', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('27', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('28', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('29', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('30', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('31', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('32', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('33', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '1', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('34', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '1', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('35', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '1', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('36', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('37', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '1', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('38', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('39', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '1', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('40', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('41', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '1', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('42', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '0', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('43', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', 'temp.jpg', '米家互联网空调.jpg', '1', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('46', '联想拯救者 R720', '联想笔记本', '4999.00', 'temp.jpg', '20200722202158826_联想拯救者R720.jpg', '1', '2020-07-09', '2', '7');
INSERT INTO `t_mc` VALUES ('47', '联想拯救者 R720', '联想笔记本', '4999.00', 'temp.jpg', '20200722202721424_联想拯救者R720.jpg', '0', '2020-07-09', '2', '7');
INSERT INTO `t_mc` VALUES ('48', '联想拯救者 R720', '联想笔记本', '4999.00', 'temp.jpg', '20200722202721424_联想拯救者R720.jpg', '0', '2020-07-15', '2', '7');
INSERT INTO `t_mc` VALUES ('49', '小米9', '小米9', '2999.00', '', '202007231128385_小米9.jpg', '1', '2020-07-22', '1', '25');
INSERT INTO `t_mc` VALUES ('50', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '2099.00', '', '米家互联网空调.jpg', '1', '2020-07-20', '22', '26');
INSERT INTO `t_mc` VALUES ('51', '小米9', '', '2999.00', '联想拯救者R720.jpg', '20200828202810702_联想拯救者R720.jpg', '1', '2020-08-28', '1', '25');

-- ----------------------------
-- Table structure for `t_mc_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_mc_type`;
CREATE TABLE `t_mc_type` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `classname` varchar(30) NOT NULL,
  `parentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_mc_type
-- ----------------------------
INSERT INTO `t_mc_type` VALUES ('1', '手机', '0');
INSERT INTO `t_mc_type` VALUES ('2', '笔记本', '0');
INSERT INTO `t_mc_type` VALUES ('7', 'Lenovo', '2');
INSERT INTO `t_mc_type` VALUES ('8', '华硕', '2');
INSERT INTO `t_mc_type` VALUES ('19', 'Redmi', '1');
INSERT INTO `t_mc_type` VALUES ('20', '三星', '1');
INSERT INTO `t_mc_type` VALUES ('21', '荣耀', '1');
INSERT INTO `t_mc_type` VALUES ('22', '空调', '0');
INSERT INTO `t_mc_type` VALUES ('23', '美的', '22');
INSERT INTO `t_mc_type` VALUES ('24', '格力', '22');
INSERT INTO `t_mc_type` VALUES ('25', '小米', '1');
INSERT INTO `t_mc_type` VALUES ('26', '米家', '22');
INSERT INTO `t_mc_type` VALUES ('27', '奥克斯', '22');
INSERT INTO `t_mc_type` VALUES ('28', '微星', '1');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `ouser` varchar(30) NOT NULL,
  `odate` datetime DEFAULT NULL,
  `paytype` varchar(30) NOT NULL,
  `sendtype` varchar(30) DEFAULT NULL,
  `mctypesize` int(11) DEFAULT NULL,
  `mcsize` int(11) DEFAULT NULL,
  `totalprice` decimal(16,2) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `msg` varchar(2000) DEFAULT NULL,
  `auser` varchar(30) DEFAULT NULL,
  `adate` datetime DEFAULT NULL,
  `getname` varchar(30) DEFAULT NULL,
  `getaddress` varchar(300) DEFAULT NULL,
  `getpostcode` varchar(10) DEFAULT NULL,
  `getphone` varchar(30) DEFAULT NULL,
  `getemail` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `fk3` (`ouser`),
  CONSTRAINT `fk3` FOREIGN KEY (`ouser`) REFERENCES `t_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('2', '2020080131', '2020-08-08 00:00:00', '否', '否', '2', '4', '14296.00', '1', '无', null, null, '齐远远', '厦门市思明区', '000000', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('3', '2020080131', '2020-08-08 00:51:59', '否', '否', '2', '4', '14296.00', '1', '无', null, null, '齐远远', '厦门市思明区', '000000', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('4', '2020080131', '2020-08-08 23:40:00', '是', '否', '1', '1', '2099.00', '1', '无', '', null, '齐远远', '厦门市思明区', '000000', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('5', '2020080131', '2020-08-08 23:46:40', '是', '否', '1', '1', '2099.00', '1', '无', '', null, '齐远远', '厦门市思明区', '000000', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('6', '2020080131', '2020-08-08 23:56:41', '是', '是', '1', '1', '2099.00', '1', '无', 'admin', '2020-08-10 07:34:47', '齐远远', '厦门市思明区', '000000', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('7', '2020080131', '2020-08-08 23:57:23', '是', '是', '1', '1', '2099.00', '1', '无', 'admin', '2020-08-10 07:30:15', '齐远远', '厦门市思明区', '000000', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('8', '2020080131', '2020-08-08 23:58:50', '是', '是', '1', '1', '2099.00', '1', '无', 'admin', '2020-08-10 09:31:36', '齐远远', '厦门市思明区', '000000', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('9', '2020080131', '2020-08-08 23:59:41', '否', '否', '1', '1', '2099.00', '1', '无', null, null, '齐远远', '厦门市思明区', '000000', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('10', '2020080131', '2020-08-09 00:00:36', '否', '否', '1', '1', '2099.00', '1', '无', null, null, '齐远远', '厦门市思明区', '000000', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('11', '2020080131', '2020-08-09 00:01:17', '否', '否', '1', '1', '2099.00', '1', '无', '', null, '齐远远', '厦门市集美区', '350612', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('12', '2020080120', '2020-08-10 09:40:46', '是', '是', '1', '1', '2099.00', '1', '无', 'admin', '2020-08-24 18:45:57', '测试注册用户', '厦门市集美区', '350601', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('13', '2020080120', '2020-08-10 09:41:05', '否', '否', '1', '1', '7999.00', '1', '无', null, null, '测试注册用户', '厦门市集美区', '350601', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('14', '2020080131', '2020-08-10 09:46:37', '是', '否', '1', '1', '4999.00', '1', '无', 'null', null, '齐远远', '厦门市思明区', '000000', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('15', '2020080131', '2020-08-10 09:48:42', '否', '否', '2', '2', '7098.00', '1', '无', null, null, '齐远远', '厦门市思明区', '000000', '13452663152', '23317599@qq.com');
INSERT INTO `t_order` VALUES ('16', '2020080131', '2020-08-28 19:57:34', '是', '是', '2', '7', '23393.00', '1', '无', 'admin', '2020-08-28 19:58:08', '齐远远', '厦门市思明区', '000000', '13452663152', '1285242825@qq.com');

-- ----------------------------
-- Table structure for `t_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `itemname` varchar(30) NOT NULL,
  `itemdescription` varchar(300) DEFAULT NULL,
  `itemimg` varchar(100) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `price` decimal(16,2) DEFAULT NULL,
  `totalprice` decimal(16,2) DEFAULT NULL,
  PRIMARY KEY (`nid`),
  KEY `fk5` (`orderid`),
  KEY `fk6` (`itemid`),
  CONSTRAINT `fk5` FOREIGN KEY (`orderid`) REFERENCES `t_order` (`orderid`),
  CONSTRAINT `fk6` FOREIGN KEY (`itemid`) REFERENCES `t_mc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES ('1', '2', '5', '三星Galaxy S20+', '三星Galaxy S20+采用了6.7英寸Dynamic AMOLED Infinity-O屏幕，支持120Hz刷新率与HDR10+。', '三星Galaxy S20+.jpg', '1', '7999.00', '7999.00');
INSERT INTO `t_order_item` VALUES ('2', '2', '42', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '3', '2099.00', '6297.00');
INSERT INTO `t_order_item` VALUES ('3', '3', '5', '三星Galaxy S20+', '三星Galaxy S20+采用了6.7英寸Dynamic AMOLED Infinity-O屏幕，支持120Hz刷新率与HDR10+。', '三星Galaxy S20+.jpg', '1', '7999.00', '7999.00');
INSERT INTO `t_order_item` VALUES ('4', '3', '42', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '3', '2099.00', '6297.00');
INSERT INTO `t_order_item` VALUES ('5', '4', '42', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '1', '2099.00', '2099.00');
INSERT INTO `t_order_item` VALUES ('6', '5', '38', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '1', '2099.00', '2099.00');
INSERT INTO `t_order_item` VALUES ('7', '6', '38', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '1', '2099.00', '2099.00');
INSERT INTO `t_order_item` VALUES ('8', '7', '38', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '1', '2099.00', '2099.00');
INSERT INTO `t_order_item` VALUES ('9', '8', '38', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '1', '2099.00', '2099.00');
INSERT INTO `t_order_item` VALUES ('10', '9', '38', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '1', '2099.00', '2099.00');
INSERT INTO `t_order_item` VALUES ('11', '10', '38', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '1', '2099.00', '2099.00');
INSERT INTO `t_order_item` VALUES ('12', '11', '38', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '1', '2099.00', '2099.00');
INSERT INTO `t_order_item` VALUES ('16', '12', '32', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '1', '2099.00', '2099.00');
INSERT INTO `t_order_item` VALUES ('17', '13', '5', '三星Galaxy S20+', '三星Galaxy S20+采用了6.7英寸Dynamic AMOLED Infinity-O屏幕，支持120Hz刷新率与HDR10+。', '三星Galaxy S20+.jpg', '1', '7999.00', '7999.00');
INSERT INTO `t_order_item` VALUES ('19', '14', '48', '联想拯救者 R720', '联想笔记本', '20200722202721424_联想拯救者R720.jpg', '1', '4999.00', '4999.00');
INSERT INTO `t_order_item` VALUES ('20', '15', '42', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '1', '2099.00', '2099.00');
INSERT INTO `t_order_item` VALUES ('21', '15', '47', '联想拯救者 R720', '联想笔记本', '20200722202721424_联想拯救者R720.jpg', '1', '4999.00', '4999.00');
INSERT INTO `t_order_item` VALUES ('22', '16', '48', '联想拯救者 R720', '联想笔记本', '20200722202721424_联想拯救者R720.jpg', '3', '4999.00', '14997.00');
INSERT INTO `t_order_item` VALUES ('23', '16', '42', '米家互联网空调', '米家互联网空调是小米米家发布的一款空调，具有高效制冷/热的功能，同时噪音最低为22分贝，还有强大的变频技术，防霉抑菌滤网以及悬浮式设计。小米为米家互联网空调提供6年的质保。', '米家互联网空调.jpg', '4', '2099.00', '8396.00');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `realname` varchar(30) NOT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  `level` int(1) DEFAULT NULL,
  `regdate` date DEFAULT NULL,
  `islock` int(1) DEFAULT NULL,
  `lastlogin` date DEFAULT NULL,
  `logtime` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('2', 'admin', '123', '超级管理员', '男', '2002-01-01', '1285242825@qq.com', '15422369874', '北京市朝阳区', '000000', '1', '2020-07-31', null, null, '0');
INSERT INTO `t_user` VALUES ('3', '2020073001', '123', '张三', '男', '1999-01-12', '12345678@qq.com', '13655212038', '北京市海淀区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('5', '2020080101', '123', '李四', '男', '2000-02-03', '12345678@163.com', '15966321078', '福建省福州市', '350600', '5', '2020-08-01', '0', null, '0');
INSERT INTO `t_user` VALUES ('6', '20200', '123', '小明', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '0', null, '0');
INSERT INTO `t_user` VALUES ('7', '2020080102', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '0', null, '0');
INSERT INTO `t_user` VALUES ('8', '2020080103', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '0', null, '0');
INSERT INTO `t_user` VALUES ('9', '2020080104', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('10', '2020080105', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('11', '2020080106', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('12', '2020080107', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('13', '2020080108', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '0', null, '0');
INSERT INTO `t_user` VALUES ('14', '2020080109', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('15', '20200801010', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('16', '2020080110', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('17', '2020080111', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('18', '2020080112', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('19', '2020080113', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('20', '2020080114', '123', '测试', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('21', '2020080115', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('22', '2020080116', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '5', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('25', '2020080119', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '0', null, '0');
INSERT INTO `t_user` VALUES ('26', '2020080120', '123', '注册用户', '女', '1999-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '350601', '9', '2020-07-31', '0', null, '0');
INSERT INTO `t_user` VALUES ('27', '2020080121', '123', '注册用户', '女', '1995-05-12', '23317599@qq.com', '18665544122', '厦门市集美区', '123456', '9', '2020-07-31', '0', null, '0');
INSERT INTO `t_user` VALUES ('28', '2020080122', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('29', '2020080123', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('30', '2020080124', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('31', '2020080125', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('32', '2020080126', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('33', '2020080127', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('34', '2020080128', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('35', '2020080129', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('36', '2020080130', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('37', '2020080131', '123456', '齐远远', '女', '1996-05-12', '1285242825@qq.com', '13452663152', '厦门市思明区', '000000', '9', '2020-07-31', null, null, '0');
INSERT INTO `t_user` VALUES ('38', '2020080132', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('39', '2020080133', '123', '注册用户', '男', '1997-05-12', '23317599@qq.com', '13452663152', '厦门市集美区', '000000', '9', '2020-07-31', '1', null, '0');
INSERT INTO `t_user` VALUES ('40', '2020080201', '123', '添加', '女', '2020-07-31', 'test@qq.com', '15966321078', '北京市', '000000', '9', '2020-08-02', '0', null, '0');
INSERT INTO `t_user` VALUES ('41', '2020081101', '123456', '卢本伟', '男', '1999-06-11', '23317599@qq.com', '15486523699', '福建省莆田市', '350600', '9', '2020-08-11', '0', null, '0');
INSERT INTO `t_user` VALUES ('42', '2020081102', '123456', '五五开', '男', '1998-02-05', 'testwwk@qq.com', '13864256696', '厦门市思明区', '350600', '9', '2020-08-11', '0', null, '0');
INSERT INTO `t_user` VALUES ('43', '2020081103', '123456', '刘某', '男', '1998-02-06', 'test@qq.com', '15422369874', '北京市', '350600', '9', '2020-08-11', '0', null, '0');
INSERT INTO `t_user` VALUES ('44', '2020081104', '123456', '周淑怡', '女', '1998-02-06', 'test@qq.com', '18698456632', '北京市', '350600', '9', '2020-08-11', '0', null, '0');
INSERT INTO `t_user` VALUES ('45', '20200804', '123456', '雷军', '男', '2000-02-29', 'test@qq.com', '15644126388', '厦门市思明区', '350600', '9', '2020-08-11', '0', null, '0');