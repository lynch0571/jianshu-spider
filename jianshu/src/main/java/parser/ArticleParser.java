package parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Article;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.DateUtil;
import utils.HtmlUtil;
import utils.PropertiesUtil;

public class ArticleParser {

    private static String baseUrl = PropertiesUtil.getValueByKey("config/spider.properties", "website");
    private static Logger lg = LoggerFactory.getLogger(ArticleParser.class);

    public static String getArticleUrl(Element e) {
        return e.select(".title").attr("href");
    }

    public static String getArticleTitle(Element e) {
        return e.select(".title").text();
    }

    public static String getImgUrl(Element e) {
        return e.select("img").attr("src");
    }

    public static String getAuthorUrl(Element e) {
        return e.select(".author .name a").attr("href");
    }

    public static String getAuthorName(Element e) {
        return e.select(".author .name a").text();
    }

    public static Date getPublishedTime(Element e) {
        return DateUtil.parseDate(e.select(".time").attr("data-shared-at"));
    }

    public static int getReadingAmount(Element e) {
        int amount = 0;
        String type = "ic-list-read";
        Elements es = e.select(".meta a");
        for (Element ele : es) {
            if (ele.toString().contains(type)) {
                amount = Integer.valueOf(ele.text());
                break;
            }
        }
        return amount;
    }

    public static int getCommentAmount(Element e) {
        int amount = 0;
        String type = "ic-list-comments";
        Elements es = e.select(".meta a");
        for (Element ele : es) {
            if (ele.toString().contains(type)) {
                amount = Integer.valueOf(ele.text());
                break;
            }
        }
        return amount;
    }

    public static int getLikeAmount(Element e) {
        int amount = 0;
        String type = "ic-list-like";
        Elements es = e.select(".meta a");
        for (Element ele : es) {
            if (ele.toString().contains(type)) {
                amount = Integer.valueOf(ele.text());
                break;
            }
        }
        return amount;
    }

    public static int getRewardAmount(Element e) {
        int amount = 0;
        String type = "ic-list-money";
        Elements es = e.select(".meta a");
        for (Element ele : es) {
            if (ele.toString().contains(type)) {
                amount = Integer.valueOf(ele.text());
                break;
            }
        }
        return amount;
    }

    public static Article getArticleByElement(String collectionId, Element e) {
        Article a = new Article();
        a.setCreateTime(new Date());
        a.setCollectionId(collectionId);
        a.setArticleUrl(getArticleUrl(e));
        a.setArticleTitle(getArticleTitle(e));
        a.setImgUrl(getImgUrl(e));
        a.setAuthorUrl(getAuthorUrl(e));
        a.setAuthorName(getAuthorName(e));
        a.setPublishedTime(getPublishedTime(e));
        a.setReadingAmount(getReadingAmount(e));
        a.setCommentAmount(getCommentAmount(e));
        a.setLikeAmount(getLikeAmount(e));
        a.setRewardAmount(getRewardAmount(e));
        return a;
    }

    public static List<Article> getArticleList(String collectionId, int page) {
        String relativeUrl = "/c/" + collectionId + "?order_by=top&page=" + page;// 按热度排序
        String url = baseUrl + relativeUrl;
        Long start = System.currentTimeMillis();
        Document doc = HtmlUtil.getHtmlDocument(url);
        Elements es = doc.select("#list-container li");
        lg.info("Catched {} elements in {}ms from URL: {}.", es.size(), System.currentTimeMillis() - start, url);

        List<Article> cs = new ArrayList<Article>();
        for (int i = 0; i < es.size(); i++) {
            Element e = es.get(i);
            Article c = ArticleParser.getArticleByElement(collectionId, e);
            if (c.getLikeAmount() < 1000) {
                break; // 小于1000个赞就结束
            }
            cs.add(c);
        }
        lg.info("Result: {}", cs);
        return cs;
    }
}
