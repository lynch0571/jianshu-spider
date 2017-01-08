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
        return e.select(".title a").attr("href");
    }

    public static String getArticleTitle(Element e) {
        return e.select(".title a").text();
    }

    public static String getImgUrl(Element e) {
        return e.select("img").attr("src");
    }

    public static String getAuthorUrl(Element e) {
        return e.select(".author-name").attr("href");
    }

    public static String getAuthorName(Element e) {
        return e.select(".author-name").text();
    }

    public static Date getPublishedTime(Element e) {
        return DateUtil.parseDate(e.select(".time").attr("data-shared-at"));
    }

    public static int getReadingAmount(Element e) {
        int amount = 0;
        String type = "阅读";
        Elements es = e.select(".list-footer a");
        for (int i = 0; i < es.size(); i++) {
            String s = es.get(i).text();
            if (s != null && s.contains(type)) {
                s = s.substring(s.indexOf(type) + type.length(), s.length()).trim();
                try {
                    amount = Integer.valueOf(s);
                } catch (NumberFormatException e1) {
                    amount = 0;
                    e1.printStackTrace();
                }
                break;
            }
        }
        return amount;
    }

    public static int getCommentAmount(Element e) {
        int amount = 0;
        String type = "评论";
        Elements es = e.select(".list-footer a");
        for (int i = 0; i < es.size(); i++) {
            String s = es.get(i).text();
            if (s != null && s.contains(type)) {
                s = s.substring(s.indexOf(type) + type.length(), s.length()).trim();
                try {
                    amount = Integer.valueOf(s);
                } catch (NumberFormatException e1) {
                    amount = 0;
                    e1.printStackTrace();
                }
                break;
            }
        }
        return amount;
    }

    public static int getLikeAmount(Element e) {
        int amount = 0;
        String type = "喜欢";
        Elements es = e.select(".list-footer span");
        for (int i = 0; i < es.size(); i++) {
            String s = es.get(i).text();
            if (s != null && s.contains(type)) {
                s = s.substring(s.indexOf(type) + type.length(), s.length()).trim();
                try {
                    amount = Integer.valueOf(s);
                } catch (NumberFormatException e1) {
                    amount = 0;
                    e1.printStackTrace();
                }
                break;
            }
        }
        return amount;
    }

    public static int getRewardAmount(Element e) {
        int amount = 0;
        String type = "打赏";
        Elements es = e.select(".list-footer span");
        for (int i = 0; i < es.size(); i++) {
            String s = es.get(i).text();
            if (s != null && s.contains(type)) {
                s = s.substring(s.indexOf(type) + type.length(), s.length()).trim();
                try {
                    amount = Integer.valueOf(s);
                } catch (NumberFormatException e1) {
                    amount = 0;
                    e1.printStackTrace();
                }
                break;
            }
        }
        return amount;
    }

    public static Article getArticleByElement(int articleId, Element e) {
        Article a = new Article();
        a.setCreateTime(new Date());
        a.setCollectionId(articleId);
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

    public static List<Article> getArticleList(int articleId, int page) {
        String relativeUrl = "/collections/" + articleId + "/notes?order_by=likes_count&page=" + page;// 按热度排序
        String url = baseUrl + relativeUrl;
        Long start = System.currentTimeMillis();
        Document doc = HtmlUtil.getHtmlDocument(url);
        Elements es = doc.select(".article-list li");
        lg.info("Catched {} elements in {}ms from URL: {}.", es.size(), System.currentTimeMillis() - start, url);

        List<Article> cs = new ArrayList<Article>();
        for (int i = 0; i < es.size(); i++) {
            Element e = es.get(i);
            Article c = ArticleParser.getArticleByElement(articleId, e);
            if (c.getLikeAmount() < 1000) {
                break; // 小于1000个赞就结束
            }
            cs.add(c);
        }
        lg.info("Result: {}", cs);
        return cs;
    }
}
