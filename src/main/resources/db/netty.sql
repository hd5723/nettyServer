/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : netty

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 23/07/2024 18:32:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `message_id` bigint NOT NULL COMMENT '消息ID',
  `sender` bigint NULL DEFAULT NULL COMMENT '消息发送人 编号',
  `receiver` bigint NULL DEFAULT NULL COMMENT '消息接收人 编号',
  `message_type` int NULL DEFAULT NULL COMMENT '消息内容类型  1:文本   2:表情包  3:语音  4:图片',
  `message_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '消息内容',
  `action` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '消息动作',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES (111113, 111, 222, 1, 'hello world!', 'send', '2024-07-23 17:53:58', '2024-07-23 17:53:58');
INSERT INTO `t_message` VALUES (111114, 111, 222, 1, 'hello world!', 'send', '2024-07-23 17:54:04', '2024-07-23 17:54:04');
INSERT INTO `t_message` VALUES (1265320424233762816, 111, 2222, 1, 'hello world', 'send', '2024-07-23 14:51:38', '2024-07-23 14:51:38');
INSERT INTO `t_message` VALUES (1265367704668209152, 111, 222, 1, 'hello world!', 'send', '2024-07-23 17:59:30', '2024-07-23 17:59:30');
INSERT INTO `t_message` VALUES (1265370692434526208, 111, 222, 1, 'hello world!', 'send', '2024-07-23 18:11:23', '2024-07-23 18:11:23');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `login_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户昵称',
  `type` tinyint(1) NULL DEFAULT 1 COMMENT '账号类型 1：管理员账号',
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '头像路径',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登陆时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `openid` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `online_status` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT 'offline' COMMENT '在线状态(online;offline)',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `index_login_name`(`login_name` ASC) USING BTREE,
  UNIQUE INDEX `index_phone`(`phonenumber` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1265356516672667648, '坑爹177733', '坑爹', 1, 'ws@163.com', '13337363354', '1', 'http://dummyimage.com/100x100', '1234567', '123456', '0', '0', '', NULL, '2024-07-23 17:14:57', '2024-07-23 17:14:57', 'Ut', 'wxid388645374637948w7q23980', 'onlineStatus');
INSERT INTO `t_user` VALUES (1265357582885715968, '坑爹1553', '坑爹', 1, 'ws@163.com', '13357363354', '1', 'http://dummyimage.com/100x100', '1234567', '123456', '0', '0', '', NULL, '2024-07-23 17:19:17', '2024-07-23 17:19:17', 'Ut', 'wxid388645374637948w7q23980', 'onlineStatus');
INSERT INTO `t_user` VALUES (1815660953219428353, '府于月权', '吴军', 1, 'yiikjs@qq.com', '13876563524', '1', 'http://dummyimage.com/100x100', '1234567', '', '0', '0', '', NULL, NULL, NULL, 'Ut', 'wxid388645374637948w7e93980', 'offline');
INSERT INTO `t_user` VALUES (1815661222070120449, '坑爹', '坑爹', 1, 'ws@163.com', '13323668654', '1', 'http://dummyimage.com/100x100', '1234567', '', '0', '0', '', NULL, NULL, NULL, 'Ut', 'wxid388645374637948w7q23980', 'offline');
INSERT INTO `t_user` VALUES (1815669152534298626, '坑爹11', '坑爹', 1, 'ws@163.com', '13323663654', '1', 'http://dummyimage.com/100x100', '1234567', '', '0', '0', '', NULL, NULL, NULL, 'Ut', 'wxid388645374637948w7q23980', 'offline');
INSERT INTO `t_user` VALUES (1815675624768970753, '坑爹113', '坑爹', 1, 'ws@163.com', '13333663654', '1', 'http://dummyimage.com/100x100', '1234567', '', '0', '0', '', NULL, NULL, NULL, 'Ut', 'wxid388645374637948w7q23980', 'offline');
INSERT INTO `t_user` VALUES (1815676062264238082, '坑爹11333', '坑爹', 1, 'ws@163.com', '13333363654', '1', 'http://dummyimage.com/100x100', '1234567', '', '0', '0', '', NULL, NULL, NULL, 'Ut', 'wxid388645374637948w7q23980', 'offline');
INSERT INTO `t_user` VALUES (1815676523599929346, '坑爹113333', '坑爹', 1, 'ws@163.com', '13333363354', '1', 'http://dummyimage.com/100x100', '1234567', '', '0', '0', '', NULL, NULL, NULL, 'Ut', 'wxid388645374637948w7q23980', 'offline');

SET FOREIGN_KEY_CHECKS = 1;
