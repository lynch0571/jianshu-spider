package com.lynch.spider.parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lynch.spider.model.Article;
import com.lynch.spider.utils.DateUtil;
import com.lynch.spider.utils.HtmlUtil;

@Component
public class ArticleParser {

    @Value("${spider.website}")
    private String baseUrl;
    
    private Logger lg = LoggerFactory.getLogger(ArticleParser.class);

    public int getAmount(Element e, String type) {
        int amount = 0;
        Elements es = e.select(".meta a");
        for (Element ele : es) {
            if (ele.toString().contains(type)) {
                amount = Integer.valueOf(ele.text().trim());
                break;
            }
        }
        
        Elements es2 = e.select(".meta span");
        for (Element ele : es2) {
            if (ele.toString().contains(type)) {
                amount = Integer.valueOf(ele.text().trim());
                break;
            }
        }
        if (amount == 0) {
            lg.warn("The {} amount is {}", type, amount);
        }
        return amount;
    }

    public Article getArticleByElement(String collectionId, Element e) {
        Article a = new Article();
        a.setCreateTime(new Date());
        a.setCollectionId(collectionId);
        a.setIsCollected(Byte.valueOf("0"));
        a.setArticleUrl(e.select(".title").attr("href"));
        a.setArticleTitle(e.select(".title").text());
        a.setImgUrl(e.select("img").attr("src"));
        a.setAuthorUrl(e.select(".author .name a").attr("href"));
        a.setAuthorName(e.select(".author .name a").text());
        a.setPublishedTime(DateUtil.parseDate(e.select(".time").attr("data-shared-at")));
        a.setReadingAmount(getAmount(e, "ic-list-read"));
        a.setCommentAmount(getAmount(e, "ic-list-comments"));
        a.setLikeAmount(getAmount(e, "ic-list-like"));
        a.setRewardAmount(getAmount(e, "ic-list-money"));
        return a;
    }

    public List<Article> getArticleList(String collectionId, int page) {
        String relativeUrl = "/c/" + collectionId + "?order_by=top&page=" + page;// 按热度排序
        String url = baseUrl + relativeUrl;
        long start = System.currentTimeMillis();
        Document doc = HtmlUtil.getHtmlDocument(url);
        Elements es = doc.select("#list-container li");
        lg.info("Catched {} elements in {}ms from URL: {}.", es.size(), System.currentTimeMillis() - start, url);
        List<Article> cs = new ArrayList<Article>();
        for (int i = 0; i < es.size(); i++) {
            Element e = es.get(i);
            Article c = getArticleByElement(collectionId, e);
            if (c != null && c.getLikeAmount() != null && c.getLikeAmount() >= 1000) {
                cs.add(c);
            } else {
                break;
            }
        }
        return cs;
    }
}