package parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Collection;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.DateUtil;
import utils.HtmlUtil;
import utils.PropertiesUtil;

public class CollectionParser {
	
	private static String baseUrl = PropertiesUtil.getValueByKey("config/spider.properties", "website");
	private static Logger lg = LoggerFactory.getLogger(CollectionParser.class);

	/*
	 * <li class="have-img"> <a class="wrap-img" href="/p/0ec4e764c5f0"><img
	 * src=
	 * "http://upload-images.jianshu.io/upload_images/2659780-08003cc010b9e732.jpg?imageMogr2/auto-orient/strip%7CimageView2/1/w/300/h/300"
	 * alt="300"></a> <div> <p class="list-top"> <a
	 * class="author-name blue-link" target="_blank"
	 * href="/users/f4a514ec24b5">王诗文circle</a> <em>·</em> <span class="time"
	 * data-shared-at="2016-10-06T10:48:08+08:00"></span> </p> <h4
	 * class="title"><a target="_blank"
	 * href="/p/0ec4e764c5f0">恋爱，除了上床，你还有件更重要的事得做！</a></h4> <div
	 * class="list-footer"> <a target="_blank" href="/p/0ec4e764c5f0"> 阅读 1170
	 * </a> <a target="_blank" href="/p/0ec4e764c5f0#comments"> · 评论 20 </a>
	 * <span> · 喜欢 31</span> <span> · 打赏 3</span> · <a class="info" title=
	 * "<img alt='Tiny' class='loader-tiny' src='http://baijii-common.b0.upaiyun.com/loaders/tiny.gif'>"
	 * data-html="true" data-collection-note-id="3330610"
	 * data-url="/collection_notes/3330610" href="javascript:void(null)"><i
	 * class="fa fa-info-circle"></i></a> </div> </div> </li>
	 */

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
		String type="阅读";
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
		String type="评论";
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
		String type="喜欢";
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
		String type="打赏";
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

	public static Collection getCollectionByElement(int collectionId,Element e){
		Collection c=new Collection();
		c.setCreateTime(new Date());
		c.setCollectionId(collectionId);
		c.setArticleUrl(getArticleUrl(e));
		c.setArticleTitle(getArticleTitle(e));
		c.setImgUrl(getImgUrl(e));
		c.setAuthorUrl(getAuthorUrl(e));
		c.setAuthorName(getAuthorName(e));
		c.setPublishedTime(getPublishedTime(e));
		c.setReadingAmount(getReadingAmount(e));
		c.setCommentAmount(getCommentAmount(e));
		c.setLikeAmount(getLikeAmount(e));
		c.setRewardAmount(getRewardAmount(e));
		return c;
	}
	
	public static List<Collection> getCollectionArticleList(int collectionId,int page) {
		String relativeUrl = "/collections/"+collectionId+"/notes?page="+page;
		String url= baseUrl + relativeUrl;
		Long start=System.currentTimeMillis();
		Document doc = HtmlUtil.getHtmlDocument(url);
		Elements es = doc.select(".article-list li");
		lg.info("Catched {} elements in {}ms from URL: {}.",es.size(),System.currentTimeMillis()-start,url);
		
		List<Collection> cs = new ArrayList<Collection>();
		for (int i = 0; i < es.size(); i++) {
			Element e = es.get(i);
			Collection c = CollectionParser.getCollectionByElement(collectionId,e);
			cs.add(c);
		}
		lg.info("Result: {}",cs);
		return cs;
	}
}
