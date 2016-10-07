CREATE TABLE `js_collection` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`collection_id` INT (11) NULL DEFAULT NULL COMMENT '专题编号',
	`article_url` VARCHAR (255) CHARACTER
SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章URL，相对',
 `article_title` VARCHAR (128) CHARACTER
SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
 `img_url` VARCHAR (255) CHARACTER
SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面图片URL，绝对',
 `author_url` VARCHAR (255) CHARACTER
SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者URL，相对',
 `author_name` VARCHAR (128) CHARACTER
SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者昵称',
 `create_time` datetime NULL DEFAULT NULL COMMENT '爬取时间',
 `published_time` datetime NULL DEFAULT NULL COMMENT '发表时间',
 `reading_amount` INT (11) NULL DEFAULT NULL COMMENT '阅读量',
 `comment_amount` INT (11) NULL DEFAULT NULL COMMENT '评论数',
 `like_amount` INT (11) NULL DEFAULT NULL COMMENT '喜欢数',
 `reward_amount` INT (11) NULL DEFAULT NULL COMMENT '打赏数',
 PRIMARY KEY (`id`),
 UNIQUE INDEX `u_article_url` (`article_url`) USING BTREE COMMENT '文章URL为唯一索引'
) ENGINE = INNODB DEFAULT CHARACTER
SET = utf8 COLLATE = utf8_general_ci COMMENT = '简书专题表' AUTO_INCREMENT = 1 ROW_FORMAT = COMPACT;

