package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.CollectionService;
import utils.PropertiesUtil;

public class JianShuAppRun {
    private static Logger lg = LoggerFactory.getLogger(JianShuAppRun.class);
    private static String collectionIds = PropertiesUtil.getValueByKey("config/spider.properties", "collectionIds");

    public static void main(String[] args) {
        // crawlArticle();
        crawlCollection();
    }

    private static void crawlCollection() {
        CollectionService cs = new CollectionService();
        cs.doJob();
    }

    private static void crawlArticle() {
        String[] ids = collectionIds.split(",");
        if (ids.length == 0) {
            lg.info("Collection id not set in properties file.");
            return;
        } else {
            lg.info("Collection id:{}", collectionIds);
        }
        for (int i = 0; i < ids.length; i++) {
            ArticleThread thread = new ArticleThread(Integer.valueOf(ids[i]));
            thread.setName("Thread-" + ids[i]);
            thread.start();
        }
    }

}
