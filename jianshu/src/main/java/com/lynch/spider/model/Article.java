package com.lynch.spider.model;

import java.util.Date;

public class Article {
    private Long id;

    private String collectionId;

    private String articleUrl;

    private String articleTitle;

    private String imgUrl;

    private String authorUrl;

    private String authorName;

    private Date createTime;

    private Date publishedTime;

    private Integer readingAmount;

    private Integer commentAmount;

    private Integer likeAmount;

    private Integer rewardAmount;

    private Byte isCollected;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    public Integer getReadingAmount() {
        return readingAmount;
    }

    public void setReadingAmount(Integer readingAmount) {
        this.readingAmount = readingAmount;
    }

    public Integer getCommentAmount() {
        return commentAmount;
    }

    public void setCommentAmount(Integer commentAmount) {
        this.commentAmount = commentAmount;
    }

    public Integer getLikeAmount() {
        return likeAmount;
    }

    public void setLikeAmount(Integer likeAmount) {
        this.likeAmount = likeAmount;
    }

    public Integer getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Integer rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public Byte getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(Byte isCollected) {
        this.isCollected = isCollected;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", collectionId=" + collectionId + ", articleUrl=" + articleUrl + ", articleTitle=" + articleTitle + ", imgUrl=" + imgUrl
                + ", authorUrl=" + authorUrl + ", authorName=" + authorName + ", createTime=" + createTime + ", publishedTime=" + publishedTime
                + ", readingAmount=" + readingAmount + ", commentAmount=" + commentAmount + ", likeAmount=" + likeAmount + ", rewardAmount=" + rewardAmount
                + ", isCollected=" + isCollected + "]";
    }
    
    
}