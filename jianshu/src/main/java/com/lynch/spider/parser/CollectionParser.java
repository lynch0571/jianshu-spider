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

import com.lynch.spider.model.Collection;
import com.lynch.spider.utils.HtmlUtil;

@Component
public class CollectionParser {

    @Value("${spider.website}")
    private String baseUrl;

    private Logger lg = LoggerFactory.getLogger(CollectionParser.class);

    public Collection getCollectionByElement(Element e) {
        Collection c = new Collection();
        try {
            c.setCreateTime(new Date());
            c.setId(Integer.valueOf(e.select(".follow-btn").attr("props-data-collection-id")));
            c.setArticleCount(Integer.valueOf(e.select(".count a").text().substring(0, e.select(".count a").text().indexOf("篇"))));
            c.setFansCount(getFansCount(e));
            c.setDescription(e.select(".collection-description").text());
            c.setName(e.select("h4 a").text());
            c.setUrl(e.select("h4 a").attr("href"));
            c.setImgUrl(e.select("img").attr("src"));
        } catch (Exception e1) {
            lg.error("HTML Document parse error:{}", e1.getMessage());
            e1.printStackTrace();
        }
        return c;
    }

    private Integer getFansCount(Element e) {
        String str = e.select(".count").text().trim().toLowerCase();
        str = str.substring(str.indexOf("·") + 1, str.indexOf("人"));
        int num = 0;
        try {
            if (str.contains("k")) {
                num = (int) (Float.valueOf(str.substring(0, str.indexOf("k"))) * 1000);
            } else if (str.contains("w")) {
                num = (int) (Float.valueOf(str.substring(0, str.indexOf("w"))) * 10000);
            }
        } catch (NumberFormatException e1) {
            lg.error("Parsing fans count occurred exception:{}", e1.getMessage());
            e1.printStackTrace();
        }
        return num;
    }

    public List<Collection> getCollectionList(int page) {
        String relativeUrl = "/recommendations/collections/" + "?order_by=hot&page=" + page;// 按热度排序
        String url = baseUrl + relativeUrl;
        long start = System.currentTimeMillis();
        Document doc = HtmlUtil.getHtmlDocument(url);
        Elements es = doc.select("#list-container .col-xs-8");
        lg.info("Catched {} elements in {}ms from URL: {}.", es.size(), System.currentTimeMillis() - start, url);
        List<Collection> cs = new ArrayList<Collection>();
        for (int i = 0; i < es.size(); i++) {
            Element e = es.get(i);
            Collection c = getCollectionByElement(e);
            cs.add(c);
        }
        return cs;
    }
}
