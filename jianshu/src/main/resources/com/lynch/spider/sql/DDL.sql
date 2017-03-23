CREATE TABLE `article` (
    `id` BIGINT (20) NOT NULL AUTO_INCREMENT,
    `collection_id` VARCHAR (32) CHARACTER
SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专题ID',
 `article_url` VARCHAR (128) CHARACTER
SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章URL，相对',
 `article_title` VARCHAR (255) CHARACTER
SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章标题',
 `img_url` VARCHAR (255) CHARACTER
SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图片URL，绝对',
 `author_url` VARCHAR (255) CHARACTER
SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者URL，相对',
 `author_name` VARCHAR (128) CHARACTER
SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者昵称',
 `create_time` datetime NULL DEFAULT NULL COMMENT '爬取时间',
 `published_time` datetime NULL DEFAULT NULL COMMENT '发表时间',
 `reading_amount` INT (11) NULL DEFAULT NULL COMMENT '阅读量',
 `comment_amount` INT (11) NULL DEFAULT NULL COMMENT '评论数',
 `like_amount` INT (11) NULL DEFAULT NULL COMMENT '喜欢数',
 `reward_amount` INT (11) NULL DEFAULT NULL COMMENT '打赏数',
 `is_collected` TINYINT (4) NULL DEFAULT 0 COMMENT '0未收录，1已收录',
 PRIMARY KEY (`id`),
 UNIQUE INDEX `index_article_url` (`article_url`) USING BTREE
) ENGINE = INNODB DEFAULT CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '简书专题表' AUTO_INCREMENT = 3456 ROW_FORMAT = COMPACT;

CREATE TABLE `collection` (
    `id` INT (11) NOT NULL,
    `name` VARCHAR (255) CHARACTER
SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专题名称',
 `url` VARCHAR (50) CHARACTER
SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专题URL',
 `admin` VARCHAR (5000) CHARACTER
SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理员，可能多个',
 `img_url` VARCHAR (255) CHARACTER
SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专题LOGO图片',
 `description` VARCHAR (8000) CHARACTER
SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专题简介',
 `article_count` INT (11) NULL DEFAULT NULL COMMENT '专题收录文章数量',
 `fans_count` INT (11) NULL DEFAULT NULL COMMENT '专题粉丝数量',
 `last_collect_time` datetime NULL DEFAULT NULL COMMENT '专题最近收录文章的时间',
 `create_time` datetime NULL DEFAULT NULL COMMENT '入库时间',
 `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
 PRIMARY KEY (`id`),
 UNIQUE INDEX `index_url` (`url`) USING BTREE
) ENGINE = INNODB DEFAULT CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

