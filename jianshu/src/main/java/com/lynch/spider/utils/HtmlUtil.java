package com.lynch.spider.utils;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlUtil {
    private static final Logger lg = LoggerFactory.getLogger(HtmlUtil.class);

    public static Document getHtmlDocument(String url) {
        Document doc = null;
        for (int i = 0; i < 3; i++) {
            doc = getDoc(url);
            if (doc != null) {
                break;
            } else {
                lg.warn("The document is null on {} times", i + 1);
                doc = new Document("");
            }
        }
        return doc;
    }

    private static Document getDoc(String url) {
        try {
            return Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
                    .get();
        } catch (SocketTimeoutException se) {
            return null;
        } catch (IOException e) {
            return new Document("");
        }
    }
}
