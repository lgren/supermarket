/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : supermarket

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 27/04/2018 14:25:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `admin_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `position` int(2) DEFAULT NULL,
  `superior_id` bigint(20) DEFAULT NULL,
  `username` char(16) DEFAULT NULL,
  `password` char(16) DEFAULT NULL,
  `avatar_url` char(254) DEFAULT NULL,
  `nickname` char(20) DEFAULT NULL,
  `real_name` char(50) DEFAULT NULL,
  `gender` int(1) DEFAULT '1',
  `phone` char(11) DEFAULT NULL,
  `email` char(50) DEFAULT NULL,
  `homeAddress` char(100) DEFAULT NULL,
  `liveAddress` char(100) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `wages` decimal(11,3) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
BEGIN;
INSERT INTO `tb_admin` VALUES (1, 0, 0, 'admin', 'admin', NULL, '老板', '杨宏宇', 1, '18428325476', '625552408@qq.com', '四川资阳', '广东深圳', '1996-09-04', 1000000.000);
INSERT INTO `tb_admin` VALUES (2, 1, 1, 'admin11', 'admin11', NULL, '总经理1', '总经理1', 1, '18428372615', '625372618@qq.com', '广东深圳', '广东深圳', '1984-04-22', 300000.000);
INSERT INTO `tb_admin` VALUES (3, 1, 1, 'admin12', 'admin12', NULL, '总经理2', '总经理2', 0, '18434567423', '72615472@qq.com', '浙江杭州', '广东深圳', '1974-04-17', 300000.000);
INSERT INTO `tb_admin` VALUES (4, 2, 2, 'amdin21', 'admin21', NULL, '经理1', '经理1', 0, NULL, NULL, NULL, NULL, NULL, 150000.000);
INSERT INTO `tb_admin` VALUES (5, 3, 3, 'admin22', 'admin22', NULL, '经理2', '经理2', 1, NULL, NULL, NULL, NULL, NULL, 130000.000);
INSERT INTO `tb_admin` VALUES (6, 4, 4, 'admin31', 'admin31', NULL, '组长1', '组长1', 1, NULL, NULL, NULL, NULL, NULL, 22000.000);
INSERT INTO `tb_admin` VALUES (7, 5, 4, 'admin32', 'admin32', NULL, '组长2', '组长2', 0, NULL, NULL, NULL, NULL, NULL, 20000.000);
INSERT INTO `tb_admin` VALUES (8, 6, 5, 'admin33', 'admin33', NULL, '组长3', '组长3', 0, NULL, NULL, NULL, NULL, NULL, 20000.000);
INSERT INTO `tb_admin` VALUES (9, 6, 5, 'admin34', 'admin34', NULL, '组长4', '组长4', 1, NULL, NULL, NULL, NULL, NULL, 20000.000);
INSERT INTO `tb_admin` VALUES (10, 7, 6, 'admin41', 'admin41', NULL, '职员1', '职员1', 1, NULL, NULL, NULL, NULL, NULL, 10000.000);
INSERT INTO `tb_admin` VALUES (11, 8, 6, 'admin42', 'admin42', NULL, '职员2', '职员2', 0, NULL, NULL, NULL, NULL, NULL, 9000.000);
INSERT INTO `tb_admin` VALUES (12, 9, 7, 'admin43', 'admin43', NULL, '职员3', '职员3', 0, NULL, NULL, NULL, NULL, NULL, 8000.000);
INSERT INTO `tb_admin` VALUES (13, 10, 7, 'admin44', 'admin44', NULL, '职员4', '职员4', 0, NULL, NULL, NULL, NULL, NULL, 7000.000);
INSERT INTO `tb_admin` VALUES (14, 10, 8, 'admin45', 'admin45', NULL, '职员5', '职员5', 1, NULL, NULL, NULL, NULL, NULL, 7000.000);
INSERT INTO `tb_admin` VALUES (15, 10, 8, 'admin46', 'admin46', NULL, '职员6', '职员6', 0, NULL, NULL, NULL, NULL, NULL, 7000.000);
INSERT INTO `tb_admin` VALUES (16, 10, 9, 'admin47', 'admin47', NULL, '职员7', '职员7', 0, NULL, NULL, NULL, NULL, NULL, 7000.000);
INSERT INTO `tb_admin` VALUES (17, 10, 9, 'admin48', 'admin48', NULL, '职员8', '职员8', 0, NULL, NULL, NULL, NULL, NULL, 7000.000);
INSERT INTO `tb_admin` VALUES (18, 10, 9, 'admin49', 'admin49', NULL, '职员9', '职员9', 1, NULL, NULL, NULL, NULL, NULL, 7000.000);
COMMIT;

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart` (
  `cart_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cart_goods` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_cart
-- ----------------------------
BEGIN;
INSERT INTO `tb_cart` VALUES (1, 1, 1);
INSERT INTO `tb_cart` VALUES (2, 2, 2);
INSERT INTO `tb_cart` VALUES (3, 3, 3);
INSERT INTO `tb_cart` VALUES (4, 4, 4);
INSERT INTO `tb_cart` VALUES (5, 4, 5);
INSERT INTO `tb_cart` VALUES (6, 4, 6);
INSERT INTO `tb_cart` VALUES (7, 4, 7);
INSERT INTO `tb_cart` VALUES (8, 4, 8);
INSERT INTO `tb_cart` VALUES (9, 4, 9);
INSERT INTO `tb_cart` VALUES (10, 4, 10);
INSERT INTO `tb_cart` VALUES (11, 4, 11);
INSERT INTO `tb_cart` VALUES (16, NULL, 14);
COMMIT;

-- ----------------------------
-- Table structure for tb_cart_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart_goods`;
CREATE TABLE `tb_cart_goods` (
  `cart_goods_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cart_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `cart_goods_time` datetime DEFAULT NULL,
  `number` int(11) DEFAULT '1',
  `price` double(11,3) DEFAULT '0.000',
  PRIMARY KEY (`cart_goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_cart_goods
-- ----------------------------
BEGIN;
INSERT INTO `tb_cart_goods` VALUES (2, 1, 2, NULL, 1, 3.000);
INSERT INTO `tb_cart_goods` VALUES (3, 2, 3, NULL, 1, 4.000);
INSERT INTO `tb_cart_goods` VALUES (4, 3, 2, NULL, 1, 3.200);
INSERT INTO `tb_cart_goods` VALUES (5, 4, 4, NULL, 1, 6.500);
INSERT INTO `tb_cart_goods` VALUES (11, 1, 1, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_collect
-- ----------------------------
DROP TABLE IF EXISTS `tb_collect`;
CREATE TABLE `tb_collect` (
  `collect_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `collect_goods_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`collect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_collect
-- ----------------------------
BEGIN;
INSERT INTO `tb_collect` VALUES (1, 1, 1);
INSERT INTO `tb_collect` VALUES (2, 2, 2);
INSERT INTO `tb_collect` VALUES (3, 3, 3);
INSERT INTO `tb_collect` VALUES (4, 4, 4);
INSERT INTO `tb_collect` VALUES (5, 5, 5);
INSERT INTO `tb_collect` VALUES (6, 6, 3);
INSERT INTO `tb_collect` VALUES (7, 7, 2);
INSERT INTO `tb_collect` VALUES (8, 8, 2);
INSERT INTO `tb_collect` VALUES (9, 9, 4);
INSERT INTO `tb_collect` VALUES (10, 10, 3);
INSERT INTO `tb_collect` VALUES (11, 11, 2);
INSERT INTO `tb_collect` VALUES (16, 14, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_collect_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_collect_goods`;
CREATE TABLE `tb_collect_goods` (
  `collect_goods_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collect_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `collect_goods_time` datetime DEFAULT NULL,
  PRIMARY KEY (`collect_goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_collect_goods
-- ----------------------------
BEGIN;
INSERT INTO `tb_collect_goods` VALUES (2, 1, 3, NULL);
INSERT INTO `tb_collect_goods` VALUES (3, 2, 2, NULL);
INSERT INTO `tb_collect_goods` VALUES (4, 3, 5, NULL);
INSERT INTO `tb_collect_goods` VALUES (5, 4, 2, NULL);
INSERT INTO `tb_collect_goods` VALUES (6, 5, 3, NULL);
INSERT INTO `tb_collect_goods` VALUES (10, 1, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `goods_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) DEFAULT NULL,
  `image_url` char(254) DEFAULT NULL,
  `name` char(50) DEFAULT NULL,
  `type` int(3) DEFAULT '0',
  `price` double(11,3) DEFAULT '0.000',
  `discount` double(4,2) DEFAULT '10.00',
  `show_time` datetime DEFAULT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
BEGIN;
INSERT INTO `tb_goods` VALUES (1, 1, '', '可乐', 1, 3.400, 0.80, NULL);
INSERT INTO `tb_goods` VALUES (2, 1, NULL, '娃哈哈', 1, 4.000, 1.00, NULL);
INSERT INTO `tb_goods` VALUES (3, 1, NULL, '柠檬乐事薯片', 1, 8.900, 1.00, NULL);
INSERT INTO `tb_goods` VALUES (4, 1, NULL, '徐福记沙琪玛', 1, 23.000, 1.00, NULL);
INSERT INTO `tb_goods` VALUES (5, 1, NULL, '海苔味乐事薯片', 1, 14.700, 1.00, NULL);
INSERT INTO `tb_goods` VALUES (6, 2, NULL, '阿迪达斯球衣', 2, 599.000, 1.00, NULL);
INSERT INTO `tb_goods` VALUES (7, 2, NULL, '李宁鞋', 2, 1999.000, 0.90, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `price` double(11,3) DEFAULT NULL,
  `pay_way` int(3) DEFAULT '1',
  `order_time` datetime DEFAULT NULL,
  `send_goods` int(1) DEFAULT '0',
  `send_goods_time` datetime DEFAULT NULL,
  `confirm` int(1) DEFAULT '0',
  `confirm_time` datetime DEFAULT NULL,
  `state` int(2) DEFAULT '0',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_purchased
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchased`;
CREATE TABLE `tb_purchased` (
  `purchased_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `evaluation` int(1) DEFAULT NULL,
  `evaluation_text` char(254) DEFAULT NULL,
  `evaluation_time` datetime DEFAULT NULL,
  `state` int(2) DEFAULT '0',
  PRIMARY KEY (`purchased_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_receiving_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_receiving_address`;
CREATE TABLE `tb_receiving_address` (
  `receiving_address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `receiving_name` char(50) DEFAULT NULL,
  `area` char(30) DEFAULT NULL,
  `address` char(50) DEFAULT NULL,
  `post_code` char(6) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  PRIMARY KEY (`receiving_address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_receiving_address
-- ----------------------------
BEGIN;
INSERT INTO `tb_receiving_address` VALUES (1, 1, '张宏宇', '四川', '成都', '012345', '18274635246');
INSERT INTO `tb_receiving_address` VALUES (2, 2, '李玉红', '四川', '成都', '321456', '18274635249');
INSERT INTO `tb_receiving_address` VALUES (3, 3, '张东升', '广东', '深圳', '754342', '18274635243');
INSERT INTO `tb_receiving_address` VALUES (4, 4, '张世坤', '广东', '深圳', '231419', '18274635246');
INSERT INTO `tb_receiving_address` VALUES (5, 5, '云舍', '广东', '广州', '533468', '18274635241');
INSERT INTO `tb_receiving_address` VALUES (6, 1, '张宏宇', '四川', '广东', '123456', '18274635246');
COMMIT;

-- ----------------------------
-- Table structure for tb_refund
-- ----------------------------
DROP TABLE IF EXISTS `tb_refund`;
CREATE TABLE `tb_refund` (
  `refund_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `purchased_id` bigint(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  `reason` char(254) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  `solve_time` datetime DEFAULT NULL,
  PRIMARY KEY (`refund_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_shop
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop` (
  `shop_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `warehousr_id` bigint(20) DEFAULT NULL,
  `name` char(16) DEFAULT NULL,
  `description` char(254) DEFAULT NULL,
  `show_time` datetime DEFAULT NULL,
  `money` double(11,3) DEFAULT '0.000',
  `state` int(2) unsigned zerofill DEFAULT '00',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------
BEGIN;
INSERT INTO `tb_shop` VALUES (1, 1, 1, '两个人的小店', '卖一些零食', NULL, 90000.000, 01);
INSERT INTO `tb_shop` VALUES (2, 2, 2, '九州小店', '服装', NULL, 190000.000, 01);
INSERT INTO `tb_shop` VALUES (3, 1, NULL, '小小卖部', '卖零食的', NULL, NULL, 01);
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` char(16) NOT NULL,
  `password` char(16) DEFAULT NULL,
  `avatar_url` char(254) DEFAULT NULL,
  `nickname` char(20) DEFAULT NULL,
  `real_name` char(50) DEFAULT NULL,
  `gender` int(1) DEFAULT '1',
  `phone` char(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` char(50) DEFAULT NULL,
  `homeAddress` char(100) DEFAULT NULL,
  `liveAddress` char(100) DEFAULT NULL,
  `money` double(11,3) DEFAULT '0.000',
  `payment_password` char(6) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, 'user', 'user', NULL, '两个人', '陆鸿宇', 1, '17263527163', '1983-04-22', '73625@qq.com', '四川成都', '广东深圳', 4000.000, '888888');
INSERT INTO `tb_user` VALUES (2, 'user1', 'user1', NULL, '用户1', '用户1', 0, NULL, NULL, NULL, NULL, NULL, 0.000, NULL);
INSERT INTO `tb_user` VALUES (3, 'user2', 'user2', NULL, '用户2', '用户2', 0, NULL, NULL, NULL, NULL, NULL, 0.000, NULL);
INSERT INTO `tb_user` VALUES (4, 'user3', 'user3', NULL, '用户3', '用户3', 1, NULL, NULL, NULL, NULL, NULL, 0.000, NULL);
INSERT INTO `tb_user` VALUES (5, 'user4', 'user4', NULL, '用户4', '用户4', 1, NULL, NULL, NULL, NULL, NULL, 0.000, NULL);
INSERT INTO `tb_user` VALUES (6, 'user5', 'user5', NULL, '用户5', '用户5', 0, NULL, NULL, NULL, NULL, NULL, 0.000, NULL);
INSERT INTO `tb_user` VALUES (7, 'user6', 'user6', NULL, '用户6', '用户6', 1, NULL, NULL, NULL, NULL, NULL, 0.000, NULL);
INSERT INTO `tb_user` VALUES (8, 'user7', 'user7', NULL, '用户7', '用户7', 0, NULL, NULL, NULL, NULL, NULL, 0.000, NULL);
INSERT INTO `tb_user` VALUES (9, 'user8', 'user8', NULL, '用户8', '用户8', 0, NULL, NULL, NULL, NULL, NULL, 0.000, NULL);
INSERT INTO `tb_user` VALUES (10, 'user9', 'user9', NULL, '用户9', '用户9', 0, NULL, NULL, NULL, NULL, NULL, 0.000, NULL);
INSERT INTO `tb_user` VALUES (11, 'user10', 'user10', NULL, '用户10', '用户10', 1, NULL, NULL, NULL, NULL, NULL, 0.000, NULL);
INSERT INTO `tb_user` VALUES (14, 'user11', 'user11', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, 0.000, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse`;
CREATE TABLE `tb_warehouse` (
  `warehouse_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_warehouse
-- ----------------------------
BEGIN;
INSERT INTO `tb_warehouse` VALUES (1, 1, 1, 60);
INSERT INTO `tb_warehouse` VALUES (2, 1, 2, 54);
INSERT INTO `tb_warehouse` VALUES (3, 1, 3, 45);
INSERT INTO `tb_warehouse` VALUES (4, 1, 4, 43);
INSERT INTO `tb_warehouse` VALUES (5, 1, 5, 98);
COMMIT;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` char(20) DEFAULT NULL,
  `password` char(24) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='InnoDB free: 6144 kB';

-- ----------------------------
-- Records of test
-- ----------------------------
BEGIN;
INSERT INTO `test` VALUES (1, 'admin', 'admin', 1, 18);
INSERT INTO `test` VALUES (2, 'admin1', 'admin1', 1, 20);
INSERT INTO `test` VALUES (3, 'admin2', 'admin2', 0, 24);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
