/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : liftu

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 04/09/2020 20:22:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` int(0) NOT NULL,
  `post_id` int(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parent_comment_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for posts
-- ----------------------------
DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `status` tinyint(0) NULL DEFAULT NULL,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of posts
-- ----------------------------
INSERT INTO `posts` VALUES (17, 'xin chào các bạn  của tôi hôm này     trời khá là đẹp 2222', 'xin-chao-cac-ban-cua-toi-hom-nay-troi-kha-la-dep-2222', 'day la content22222', 0, '2020-09-04 16:42:07', '2020-09-04 16:42:07', 1, NULL, NULL);
INSERT INTO `posts` VALUES (18, 'xin chào các bạn  của tôi hôm này     trời khá là đẹp 2222', 'xin-chao-cac-ban-cua-toi-hom-nay-troi-kha-la-dep-2222', 'day la content22222', 0, '2020-09-04 16:42:45', '2020-09-04 16:42:45', 1, NULL, NULL);
INSERT INTO `posts` VALUES (19, 'xin chào các bạn  của tôi hôm này     trời khá là đẹp 2222', 'xin-chao-cac-ban-cua-toi-hom-nay-troi-kha-la-dep-2222', 'day la content22222', 0, '2020-09-04 16:43:13', '2020-09-04 16:43:13', 1, NULL, NULL);
INSERT INTO `posts` VALUES (20, 'xin chào các bạn  của tôi hôm này     trời khá là đẹp 2222', 'xin-chao-cac-ban-cua-toi-hom-nay-troi-kha-la-dep-2222', 'day la content22222', 0, '2020-09-04 16:43:31', '2020-09-04 16:43:31', 1, NULL, NULL);
INSERT INTO `posts` VALUES (21, 'xin chào các bạn  của tôi hôm này     trời khá là đẹp 2222', 'xin-chao-cac-ban-cua-toi-hom-nay-troi-kha-la-dep-2222', 'day la content22222', 0, '2020-09-04 16:48:06', '2020-09-04 16:48:06', 1, NULL, NULL);
INSERT INTO `posts` VALUES (22, 'xin chào các bạn  của tôi hôm này     trời khá là đẹp 2222', 'xin-chao-cac-ban-cua-toi-hom-nay-troi-kha-la-dep-2222', 'day la content22222', 0, '2020-09-04 16:48:19', '2020-09-04 16:48:19', 1, NULL, NULL);
INSERT INTO `posts` VALUES (23, 'xin chào các bạn  của tôi hôm này     trời khá là đẹp 2222', 'xin-chao-cac-ban-cua-toi-hom-nay-troi-kha-la-dep-2222', 'day la content22222', 0, '2020-09-04 16:48:57', '2020-09-04 16:48:57', 1, NULL, NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(0) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` tinyint(0) NULL DEFAULT NULL,
  `created_at` datetime(0) NULL DEFAULT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `display_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', '$2a$10$hysYUmRihqlw6igNqX2yKOEg.7PYXxfKzeQs5ZQN2rigJ7wK.AzyG', 0, '2020-08-23 23:42:57', '2020-08-23 23:43:00', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
