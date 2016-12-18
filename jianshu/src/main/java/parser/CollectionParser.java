package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Collection;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.HtmlUtil;
import utils.PropertiesUtil;

public class CollectionParser {

    private static String collectionUrl = PropertiesUtil.getValueByKey("config/spider.properties", "collectionUrl");
    private static String website = PropertiesUtil.getValueByKey("config/spider.properties", "website");
    private static Logger lg = LoggerFactory.getLogger(CollectionParser.class);

    public static Collection getCollectionByElement(Element e) {
        Collection c = new Collection();
        // 获取到专题URL，然后到专题里边爬详细信息
        c.setUrl(getUrl(e));
        c.setName(getName(e));
        Document doc = HtmlUtil.getHtmlDocument(c.getUrl());

        c.setId(getCollectionId(doc));
        c.setAdmin(getAdmin(doc));
        c.setImgUrl(getImgUrl(doc));
        c.setDescription(getDescription(doc));
        c.setArticleCount(getArticleCount(doc));
        c.setFansCount(getFansCount(doc));
        c.setLastCollectTime(getLastCollectTime(doc));
        c.setCreateTime(new Date());
        c.setUpdateTime(new Date());

        return c;
    }

    private static String getUrl(Element e) {
        return website + e.select(".collections-info h5 a").attr("href");
    }

    private static String getName(Element e) {
        return e.select(".collections-info h5 a").text();
    }

    private static Date getLastCollectTime(Document e) {
        Date date = null;
        try {
            String str = e.select(".article-list li").get(0).select(".time").attr("data-shared-at");
            // 2016-12-15T17:57:02+08:00
            str = str.replace("T", " ").substring(0, str.indexOf("+"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(str);
        } catch (Exception e1) {
            lg.error("Exception:", e1);
        }
        return date;
    }

    private static Integer getFansCount(Document e) {
        Elements es = e.select(".follow-group .follow span");
        String str = es.get(es.size() - 1).text().trim().toUpperCase();
        Integer num = null;
        // 精确统计需要请求其他页面
        if (str.contains("K")) {
            num = (int) (Float.valueOf(str.substring(0, str.indexOf("K"))) * 1000);
        } else {
            num = Integer.valueOf(str);
        }
        return num;
    }

    private static Integer getArticleCount(Document e) {
        String str = e.select(".collection-article .author a").get(0).text();
        Integer num = null;
        try {
            num = Integer.valueOf(str.substring(0, str.indexOf("篇")));
        } catch (Exception e2) {
            lg.error("Exception:", e2);
        }
        return num;
    }

    private static String getDescription(Document e) {
        return e.select(".collection-top .description").text();
    }

    private static String getImgUrl(Document e) {
        return e.select(".collection-top .header img ").attr("src");
    }

    private static String getAdmin(Document doc) {
        Elements es = doc.select(".collection-article .author a");
        es.remove(0);
        StringBuilder sb = new StringBuilder();
        for (Element e : es) {
            sb.append(e.text()).append(",").append(e.attr("href")).append("|");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    private static Integer getCollectionId(Document e) {
        String str = e.select(".top-articles a").attr("href");
        return Integer.valueOf(str.substring(str.indexOf("/collections/") + "/collections/".length(), str.indexOf("/notes?")));
    }

    public static List<Collection> getCollectionList(int page) {
        String url = collectionUrl + page;
        Long start = System.currentTimeMillis();
        Document doc = HtmlUtil.getHtmlDocument(url);
        Elements es = doc.select("#all-collections li");
        lg.info("Catched {} elements in {}ms from URL: {}.", es.size(), System.currentTimeMillis() - start, url);

        List<Collection> cs = new ArrayList<Collection>();
        for (int i = 0; i < es.size(); i++) {
            Element e = es.get(i);
            Collection c = CollectionParser.getCollectionByElement(e);
            cs.add(c);
        }
        lg.info("Result: {}", cs);
        return cs;
    }
}
