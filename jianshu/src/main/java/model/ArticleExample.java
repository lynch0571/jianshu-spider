package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCollectionIdIsNull() {
            addCriterion("collection_id is null");
            return (Criteria) this;
        }

        public Criteria andCollectionIdIsNotNull() {
            addCriterion("collection_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionIdEqualTo(Integer value) {
            addCriterion("collection_id =", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdNotEqualTo(Integer value) {
            addCriterion("collection_id <>", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdGreaterThan(Integer value) {
            addCriterion("collection_id >", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("collection_id >=", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdLessThan(Integer value) {
            addCriterion("collection_id <", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdLessThanOrEqualTo(Integer value) {
            addCriterion("collection_id <=", value, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdIn(List<Integer> values) {
            addCriterion("collection_id in", values, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdNotIn(List<Integer> values) {
            addCriterion("collection_id not in", values, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdBetween(Integer value1, Integer value2) {
            addCriterion("collection_id between", value1, value2, "collectionId");
            return (Criteria) this;
        }

        public Criteria andCollectionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("collection_id not between", value1, value2, "collectionId");
            return (Criteria) this;
        }

        public Criteria andArticleUrlIsNull() {
            addCriterion("article_url is null");
            return (Criteria) this;
        }

        public Criteria andArticleUrlIsNotNull() {
            addCriterion("article_url is not null");
            return (Criteria) this;
        }

        public Criteria andArticleUrlEqualTo(String value) {
            addCriterion("article_url =", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlNotEqualTo(String value) {
            addCriterion("article_url <>", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlGreaterThan(String value) {
            addCriterion("article_url >", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlGreaterThanOrEqualTo(String value) {
            addCriterion("article_url >=", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlLessThan(String value) {
            addCriterion("article_url <", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlLessThanOrEqualTo(String value) {
            addCriterion("article_url <=", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlLike(String value) {
            addCriterion("article_url like", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlNotLike(String value) {
            addCriterion("article_url not like", value, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlIn(List<String> values) {
            addCriterion("article_url in", values, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlNotIn(List<String> values) {
            addCriterion("article_url not in", values, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlBetween(String value1, String value2) {
            addCriterion("article_url between", value1, value2, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleUrlNotBetween(String value1, String value2) {
            addCriterion("article_url not between", value1, value2, "articleUrl");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNull() {
            addCriterion("article_title is null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNotNull() {
            addCriterion("article_title is not null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEqualTo(String value) {
            addCriterion("article_title =", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotEqualTo(String value) {
            addCriterion("article_title <>", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThan(String value) {
            addCriterion("article_title >", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThanOrEqualTo(String value) {
            addCriterion("article_title >=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThan(String value) {
            addCriterion("article_title <", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThanOrEqualTo(String value) {
            addCriterion("article_title <=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLike(String value) {
            addCriterion("article_title like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotLike(String value) {
            addCriterion("article_title not like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIn(List<String> values) {
            addCriterion("article_title in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotIn(List<String> values) {
            addCriterion("article_title not in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleBetween(String value1, String value2) {
            addCriterion("article_title between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotBetween(String value1, String value2) {
            addCriterion("article_title not between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("img_url not between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlIsNull() {
            addCriterion("author_url is null");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlIsNotNull() {
            addCriterion("author_url is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlEqualTo(String value) {
            addCriterion("author_url =", value, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlNotEqualTo(String value) {
            addCriterion("author_url <>", value, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlGreaterThan(String value) {
            addCriterion("author_url >", value, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlGreaterThanOrEqualTo(String value) {
            addCriterion("author_url >=", value, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlLessThan(String value) {
            addCriterion("author_url <", value, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlLessThanOrEqualTo(String value) {
            addCriterion("author_url <=", value, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlLike(String value) {
            addCriterion("author_url like", value, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlNotLike(String value) {
            addCriterion("author_url not like", value, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlIn(List<String> values) {
            addCriterion("author_url in", values, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlNotIn(List<String> values) {
            addCriterion("author_url not in", values, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlBetween(String value1, String value2) {
            addCriterion("author_url between", value1, value2, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorUrlNotBetween(String value1, String value2) {
            addCriterion("author_url not between", value1, value2, "authorUrl");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNull() {
            addCriterion("author_name is null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNotNull() {
            addCriterion("author_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameEqualTo(String value) {
            addCriterion("author_name =", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotEqualTo(String value) {
            addCriterion("author_name <>", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThan(String value) {
            addCriterion("author_name >", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThanOrEqualTo(String value) {
            addCriterion("author_name >=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThan(String value) {
            addCriterion("author_name <", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThanOrEqualTo(String value) {
            addCriterion("author_name <=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLike(String value) {
            addCriterion("author_name like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotLike(String value) {
            addCriterion("author_name not like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIn(List<String> values) {
            addCriterion("author_name in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotIn(List<String> values) {
            addCriterion("author_name not in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameBetween(String value1, String value2) {
            addCriterion("author_name between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotBetween(String value1, String value2) {
            addCriterion("author_name not between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeIsNull() {
            addCriterion("published_time is null");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeIsNotNull() {
            addCriterion("published_time is not null");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeEqualTo(Date value) {
            addCriterion("published_time =", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeNotEqualTo(Date value) {
            addCriterion("published_time <>", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeGreaterThan(Date value) {
            addCriterion("published_time >", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("published_time >=", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeLessThan(Date value) {
            addCriterion("published_time <", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeLessThanOrEqualTo(Date value) {
            addCriterion("published_time <=", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeIn(List<Date> values) {
            addCriterion("published_time in", values, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeNotIn(List<Date> values) {
            addCriterion("published_time not in", values, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeBetween(Date value1, Date value2) {
            addCriterion("published_time between", value1, value2, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeNotBetween(Date value1, Date value2) {
            addCriterion("published_time not between", value1, value2, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andReadingAmountIsNull() {
            addCriterion("reading_amount is null");
            return (Criteria) this;
        }

        public Criteria andReadingAmountIsNotNull() {
            addCriterion("reading_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReadingAmountEqualTo(Integer value) {
            addCriterion("reading_amount =", value, "readingAmount");
            return (Criteria) this;
        }

        public Criteria andReadingAmountNotEqualTo(Integer value) {
            addCriterion("reading_amount <>", value, "readingAmount");
            return (Criteria) this;
        }

        public Criteria andReadingAmountGreaterThan(Integer value) {
            addCriterion("reading_amount >", value, "readingAmount");
            return (Criteria) this;
        }

        public Criteria andReadingAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("reading_amount >=", value, "readingAmount");
            return (Criteria) this;
        }

        public Criteria andReadingAmountLessThan(Integer value) {
            addCriterion("reading_amount <", value, "readingAmount");
            return (Criteria) this;
        }

        public Criteria andReadingAmountLessThanOrEqualTo(Integer value) {
            addCriterion("reading_amount <=", value, "readingAmount");
            return (Criteria) this;
        }

        public Criteria andReadingAmountIn(List<Integer> values) {
            addCriterion("reading_amount in", values, "readingAmount");
            return (Criteria) this;
        }

        public Criteria andReadingAmountNotIn(List<Integer> values) {
            addCriterion("reading_amount not in", values, "readingAmount");
            return (Criteria) this;
        }

        public Criteria andReadingAmountBetween(Integer value1, Integer value2) {
            addCriterion("reading_amount between", value1, value2, "readingAmount");
            return (Criteria) this;
        }

        public Criteria andReadingAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("reading_amount not between", value1, value2, "readingAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountIsNull() {
            addCriterion("comment_amount is null");
            return (Criteria) this;
        }

        public Criteria andCommentAmountIsNotNull() {
            addCriterion("comment_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCommentAmountEqualTo(Integer value) {
            addCriterion("comment_amount =", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountNotEqualTo(Integer value) {
            addCriterion("comment_amount <>", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountGreaterThan(Integer value) {
            addCriterion("comment_amount >", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_amount >=", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountLessThan(Integer value) {
            addCriterion("comment_amount <", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountLessThanOrEqualTo(Integer value) {
            addCriterion("comment_amount <=", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountIn(List<Integer> values) {
            addCriterion("comment_amount in", values, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountNotIn(List<Integer> values) {
            addCriterion("comment_amount not in", values, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountBetween(Integer value1, Integer value2) {
            addCriterion("comment_amount between", value1, value2, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_amount not between", value1, value2, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountIsNull() {
            addCriterion("like_amount is null");
            return (Criteria) this;
        }

        public Criteria andLikeAmountIsNotNull() {
            addCriterion("like_amount is not null");
            return (Criteria) this;
        }

        public Criteria andLikeAmountEqualTo(Integer value) {
            addCriterion("like_amount =", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountNotEqualTo(Integer value) {
            addCriterion("like_amount <>", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountGreaterThan(Integer value) {
            addCriterion("like_amount >", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_amount >=", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountLessThan(Integer value) {
            addCriterion("like_amount <", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountLessThanOrEqualTo(Integer value) {
            addCriterion("like_amount <=", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountIn(List<Integer> values) {
            addCriterion("like_amount in", values, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountNotIn(List<Integer> values) {
            addCriterion("like_amount not in", values, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountBetween(Integer value1, Integer value2) {
            addCriterion("like_amount between", value1, value2, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("like_amount not between", value1, value2, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountIsNull() {
            addCriterion("reward_amount is null");
            return (Criteria) this;
        }

        public Criteria andRewardAmountIsNotNull() {
            addCriterion("reward_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRewardAmountEqualTo(Integer value) {
            addCriterion("reward_amount =", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountNotEqualTo(Integer value) {
            addCriterion("reward_amount <>", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountGreaterThan(Integer value) {
            addCriterion("reward_amount >", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("reward_amount >=", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountLessThan(Integer value) {
            addCriterion("reward_amount <", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountLessThanOrEqualTo(Integer value) {
            addCriterion("reward_amount <=", value, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountIn(List<Integer> values) {
            addCriterion("reward_amount in", values, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountNotIn(List<Integer> values) {
            addCriterion("reward_amount not in", values, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountBetween(Integer value1, Integer value2) {
            addCriterion("reward_amount between", value1, value2, "rewardAmount");
            return (Criteria) this;
        }

        public Criteria andRewardAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("reward_amount not between", value1, value2, "rewardAmount");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}