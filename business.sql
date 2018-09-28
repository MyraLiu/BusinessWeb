/*
Navicat MySQL Data Transfer

Source Server         : bird
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : business

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-09-21 15:45:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for neuedu_cart
-- ----------------------------
DROP TABLE IF EXISTS `neuedu_cart`;
CREATE TABLE `neuedu_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `checked` int(11) DEFAULT NULL COMMENT '是否选择，1=已勾选,0=未勾选',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of neuedu_cart
-- ----------------------------
insert into neuedu_cart (user_id,product_id,quantity,checked,create_time)
    values(30,10003,2,1,now());
-- ----------------------------
-- Table structure for neuedu_category
-- ----------------------------
DROP TABLE IF EXISTS `neuedu_category`;
CREATE TABLE `neuedu_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点，一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常，2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号，同类展示顺序，数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100140 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of neuedu_category
-- ----------------------------
INSERT INTO `neuedu_category` VALUES ('100032', '0', '电子产品', '1', '1', '2018-09-10 19:49:27', '2018-09-10 19:49:27');
INSERT INTO `neuedu_category` VALUES ('100033', '0', '服装鞋帽', '1', '1', '2018-09-10 19:49:35', '2018-09-10 19:49:35');
INSERT INTO `neuedu_category` VALUES ('100034', '0', '图书文献', '1', '1', '2018-09-10 19:49:40', '2018-09-10 19:49:40');
INSERT INTO `neuedu_category` VALUES ('100035', '0', '生活日用', '1', '1', '2018-09-10 19:49:47', '2018-09-10 19:49:47');
INSERT INTO `neuedu_category` VALUES ('100100', '100032', '手机', '1', '1', '2018-09-10 19:53:27', '2018-09-10 19:53:27');
INSERT INTO `neuedu_category` VALUES ('100101', '100032', '电脑', '1', '1', '2018-09-10 19:53:27', '2018-09-10 19:53:27');
INSERT INTO `neuedu_category` VALUES ('100102', '100032', '音响', '1', '1', '2018-09-10 19:53:27', '2018-09-10 19:53:27');
INSERT INTO `neuedu_category` VALUES ('100103', '100032', '液晶显示器', '1', '1', '2018-09-10 19:53:27', '2018-09-10 19:53:27');
INSERT INTO `neuedu_category` VALUES ('100105', '100032', '家用电器', '1', null, null, null);
INSERT INTO `neuedu_category` VALUES ('100106', '100101', 'notepad', '1', null, null, null);
INSERT INTO `neuedu_category` VALUES ('100107', null, '鼠标', '1', null, null, null);
INSERT INTO `neuedu_category` VALUES ('100108', null, 'popo', '1', null, null, null);
INSERT INTO `neuedu_category` VALUES ('100137', '100032', '服务器', '1', null, null, null);
INSERT INTO `neuedu_category` VALUES ('100138', '100032', 'wewe', '1', null, null, null);
INSERT INTO `neuedu_category` VALUES ('100139', '100032', '鼠标', '1', null, null, null);

-- ----------------------------
-- Table structure for neuedu_order
-- ----------------------------
DROP TABLE IF EXISTS `neuedu_order`;
CREATE TABLE `neuedu_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `shipping_id` int(11) DEFAULT NULL,
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实际付款金额，单位是元，保留两位小数',
  `payment_type` int(4) DEFAULT NULL COMMENT '支付类型，1-在线支付',
  `postage` int(10) DEFAULT NULL COMMENT '运费,单位是元',
  `status` int(10) DEFAULT NULL COMMENT '订单状态：0-已取消 10-未付款  20-已付款 40-已发货 50-交易成功 60-交易失败',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



insert into neuedu_order (order_no,user_id,shipping_id,payment,payment_type,postage,status,payment_time,
                          send_time,end_time,close_time,create_time,update_time)
values(10010,30,6,360,1,0,10,now(),null,null,null,now(),null),
  (10011,30,17,70,1,0,10,now(),null,null,null,now(),null),
  (10012,30,6,70,1,0,10,now(),null,null,null,now(),null),
  (10013,30,17,70,1,0,10,now(),null,null,null,now(),null),
  (10014,30,6,70,1,0,10,now(),null,null,null,now(),null),
  (10015,30,17,70,1,0,10,now(),null,null,null,now(),null),
  (10016,30,6,70,1,0,10,now(),null,null,null,now(),null),
  (10017,30,17,140,1,0,10,now(),null,null,null,now(),null);

insert into neuedu_order_item (user_id,order_no,product_id,product_name,product_image,current_unit_price,
quantity,total_price,create_time,update_time)
    values(30,10010,10007 ,"iphone10","1.jpg",60,3, 180,now(),now() ),
      (30,10010,10007 ,"iphone10","1.jpg",60,3, 180,now(),now() ),
      (30,10011,10007 ,"iphone10","1.jpg",70,1, 70,now(),now() ),
  (30,10012,10007 ,"iphone10","1.jpg",70,1, 70,now(),now() ),
  (30,10013,10007 ,"iphone10","1.jpg",70,1, 70,now(),now() ),
  (30,10014,10007 ,"iphone10","1.jpg",70,1, 70,now(),now() ),
  (30,10015,10007 ,"iphone10","1.jpg",70,1, 70,now(),now() ),
  (30,10016,10007 ,"iphone10","1.jpg",70,1, 70,now(),now() ),
(30,10017,10007 ,"iphone10","1.jpg",70,1, 70,now(),now() ),
(30,10017,10007 ,"iphone10","1.jpg",70,1, 70,now(),now() );
-- ----------------------------
-- Records of neuedu_order
-- ----------------------------

-- ----------------------------
-- Table structure for neuedu_order_item
-- ----------------------------
DROP TABLE IF EXISTS `neuedu_order_item`;
CREATE TABLE `neuedu_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `product_image` varchar(500) DEFAULT NULL COMMENT '商品图片地址',
  `current_unit_price` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价,单位是元，保留两位小数',
  `quantity` int(10) DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20,2) DEFAULT NULL COMMENT '商品总价，单位是元，保留两位小数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `order_no_user_id_index` (`user_id`,`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of neuedu_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for neuedu_pay_info
-- ----------------------------
DROP TABLE IF EXISTS `neuedu_pay_info`;
CREATE TABLE `neuedu_pay_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `pay_platform` int(10) DEFAULT NULL COMMENT '支付平台：1-支付宝，2-微信',
  `platform_number` varchar(200) DEFAULT NULL COMMENT '支付宝支付流水号',
  `platform_status` varchar(20) DEFAULT NULL COMMENT '支付宝支付状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of neuedu_pay_info
-- ----------------------------

-- ----------------------------
-- Table structure for neuedu_product
-- ----------------------------
DROP TABLE IF EXISTS `neuedu_product`;
CREATE TABLE `neuedu_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `category_id` int(11) NOT NULL COMMENT '分类id,对应mmall_category表主键',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `subtitle` varchar(200) DEFAULT NULL COMMENT '商品副标题',
  `main_image` varchar(500) DEFAULT NULL COMMENT '产品主图,url相对地址',
  `sub_images` text COMMENT '图片地址，json格式，扩展用',
  `detail` text COMMENT '商品详情',
  `price` decimal(20,2) NOT NULL COMMENT '价格，单位-元保留两位小数',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  `status` int(6) DEFAULT '1' COMMENT '商品状态 1-在售 2-下架 3-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10010 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of neuedu_product
-- ----------------------------
INSERT INTO `neuedu_product` VALUES ('10000', '100100', 'MI6', null, '1.jpg', '1.jpg,2.jpg,3.jpg', 'MI6', '1999.00', '1000', '1', '2018-09-10 19:58:35', '2018-09-10 19:58:35');
INSERT INTO `neuedu_product` VALUES ('10001', '100100', 'MI7', null, '1.jpg', '1.jpg,2.jpg,3.jpg', 'MI7', '2999.00', '1000', '1', '2018-09-10 19:58:35', '2018-09-10 19:58:35');
INSERT INTO `neuedu_product` VALUES ('10002', '100100', 'MI7', null, '1.jpg', '1.jpg,2.jpg,3.jpg', 'MI7', '3999.00', '1000', '1', '2018-09-10 19:58:35', '2018-09-10 19:58:35');
INSERT INTO `neuedu_product` VALUES ('10003', '100101', '华硕k80', null, '1.jpg', '1.jpg,2.jpg,3.jpg', '华硕k80', '1999.00', '1000', '1', '2018-09-10 19:59:59', '2018-09-10 19:59:59');
INSERT INTO `neuedu_product` VALUES ('10004', '100101', '联想e30', null, '1.jpg', '1.jpg,2.jpg,3.jpg', '联想e30', '2999.00', '1000', '1', '2018-09-10 19:59:59', '2018-09-10 19:59:59');
INSERT INTO `neuedu_product` VALUES ('10005', '100101', '惠普s1', null, '1.jpg', '1.jpg,2.jpg,3.jpg', '惠普s1', '3999.00', '1000', '1', '2018-09-10 19:59:59', '2018-09-10 19:59:59');
INSERT INTO `neuedu_product` VALUES ('10006', '100032', 'iphone10', null, '1.jpg', '1.jpg,2.jpg,3.jpg', null, '68888912.00', '1000', '1', '2018-09-18 17:51:26', '2018-09-18 17:51:26');
INSERT INTO `neuedu_product` VALUES ('10007', '100032', 'iphone10', null, '1.jpg', '1.jpg,2.jpg,3.jpg', null, '68.00', '191291', '1', '2018-09-19 09:59:57', '2018-09-19 09:59:57');
INSERT INTO `neuedu_product` VALUES ('10008', '100101', '白纸', null, null, null, null, '1.00', '1000', '1', '2018-09-20 10:19:09', '2018-09-20 10:19:09');
INSERT INTO `neuedu_product` VALUES ('10009', '100101', '红字', null, '9c7ca052-c075-4adc-a3b8-eef7fd6cbe20.png', '9c7ca052-c075-4adc-a3b8-eef7fd6cbe20.png', null, '1222.00', '1000', '1', '2018-09-20 10:20:19', '2018-09-20 10:20:19');

-- ----------------------------
-- Table structure for neuedu_shipping
-- ----------------------------
DROP TABLE IF EXISTS `neuedu_shipping`;
CREATE TABLE `neuedu_shipping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货姓名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货固定电话',
  `receiver_mobile` varchar(20) DEFAULT NULL COMMENT '收货移动电话',
  `receiver_province` varchar(20) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(20) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮编',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of neuedu_shipping
-- ----------------------------

-- ----------------------------
-- Table structure for neuedu_user
-- ----------------------------
DROP TABLE IF EXISTS `neuedu_user`;
CREATE TABLE `neuedu_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `role` int(4) NOT NULL COMMENT '角色0-管理员，1-普通用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `token` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of neuedu_user
-- ----------------------------
INSERT INTO `neuedu_user` VALUES ('21', 'admin', '123456', 'admin@193.com', '12908762345', '爸爸生日', '1966-3-2', '1', '2018-09-03 13:56:56', '2018-09-03 13:56:56', 'ebc3d96a0d8a0e2a6b64a92b6888ae56');
INSERT INTO `neuedu_user` VALUES ('22', 'xixi', '96e79218965eb72c92a549dd5a330112', '2312@163.com', null, '学校', '大学', '1', '2018-09-04 17:24:27', '2018-09-04 17:24:27', null);
INSERT INTO `neuedu_user` VALUES ('23', 'xix2i', '202cb962ac59075b964b07152d234b70', 'qqqq@193.com', '11111', '***', '1212', '1', '2018-09-04 17:26:26', '2018-09-04 17:26:26', null);
INSERT INTO `neuedu_user` VALUES ('24', 'admin2', 'admin', 'admin2@163.com', '13122211212', 'xxx', 'xxx', '1', '2018-09-06 10:29:22', '2018-09-06 10:29:22', '7b25bb89d809355de0cae69972bc124f');
INSERT INTO `neuedu_user` VALUES ('25', 'admin1', 'e00cf25ad42683b3df678c61f42c6bda', 'admin1@163.com', '13122211212', 'xxx', 'xxx', '1', '2018-09-06 10:53:19', '2018-09-06 10:53:19', 'd41d8cd98f00b204e9800998ecf8427e');
INSERT INTO `neuedu_user` VALUES ('26', 'admin3', '32cacb2f994f6b42183a1300d9a3e8d6', 'admin3@163.com', '13122211212', 'xxx', 'xxx', '0', '2018-09-06 15:15:48', '2018-09-06 15:15:48', '7b25bb89d809355de0cae69972bc124f');
INSERT INTO `neuedu_user` VALUES ('28', 'hipo', '123456', 'hipo@qq.com', '109876543211', 'xxx', 'xxxx', '1', '2018-09-08 16:34:07', '2018-09-08 16:34:07', null);
INSERT INTO `neuedu_user` VALUES ('30', '李旭', '96e79218965eb72c92a549dd5a330112', '1211@qq.com', '12121212121', 'ssss', 'ssss', '0', '2018-09-17 18:54:41', '2018-09-17 18:54:41', '7b25bb89d809355de0cae69972bc124f');
INSERT INTO `neuedu_user` VALUES ('31', '李旭1', 'e10adc3949ba59abbe56e057f20f883e', '1211@qq.com', '12121212121', 'ssss', 'ssss', '1', '2018-09-17 18:54:55', '2018-09-17 18:54:55', null);
INSERT INTO `neuedu_user` VALUES ('32', '李旭122', 'e10adc3949ba59abbe56e057f20f883e', '1211@qq.com', '12121212121', 'ssss', 'ssss', '1', '2018-09-19 09:14:58', '2018-09-19 09:14:58', null);
INSERT INTO `neuedu_user` VALUES ('33', '李旭12', 'e10adc3949ba59abbe56e057f20f883e', '12112@qq.com', '12121212121', 'ssss', 'ssss', '1', '2018-09-19 09:15:06', '2018-09-19 09:15:06', null);
INSERT INTO `neuedu_user` VALUES ('34', '李旭333', 'e10adc3949ba59abbe56e057f20f883e', '12112@qq.com', '12121212121', 'ssss', 'ssss', '1', '2018-09-19 09:17:38', '2018-09-19 09:17:38', null);
INSERT INTO `neuedu_user` VALUES ('35', '李旭3', 'e10adc3949ba59abbe56e057f20f883e', '12113@qq.com', '12121212121', 'ssss', 'ssss', '1', '2018-09-19 09:54:58', '2018-09-19 09:54:58', '7b25bb89d809355de0cae69972bc124f');
INSERT INTO `neuedu_user` VALUES ('36', '李旭4', 'e10adc3949ba59abbe56e057f20f883e', '12114@qq.com', '12121212121', 'ssss', 'ssss', '1', '2018-09-20 08:07:16', '2018-09-20 08:07:16', '');
