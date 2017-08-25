package com.lynch.spider.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lynch.spider.dao.ArticleMapper;
import com.lynch.spider.model.Article;
import com.lynch.spider.model.ArticleExample;
import com.lynch.spider.parser.ArticleParser;

@Service
public class ArticleService {
	private Logger lg = LoggerFactory.getLogger(ArticleService.class);

	@Value("${spider.myCollectionId}")
	private String myCollectionId;

	@Autowired
	private ArticleParser articleParser;

	@Autowired
	private ArticleMapper articleMapper;

	public String getArticleHtml() {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andIsCollectedEqualTo(Byte.valueOf("0"));
		StringBuffer sb = new StringBuffer();
		List<Article> articles = articleMapper.selectByExample(example);
		for (int i = 0; i < articles.size(); i++) {
			sb.append("\n").append("<a href=\"http://www.jianshu.com" + articles.get(i).getArticleUrl() + "\">")
					.append(articles.get(i).getArticleTitle() + "</a><br/>");
		}
		return sb.toString();
	}

	public void doJob(String collectionId) {
		Long start = System.currentTimeMillis();
		lg.info("{} start.", Thread.currentThread().getName());
		int page = 1;
		int i = 0;
		while (i < 5) {
			if (!crawl(collectionId, page++)) {
				i++;
			}
		}
		Long end = System.currentTimeMillis();
		lg.info("{} end.Totle time:{}s, collectionId:{}, page:{}", Thread.currentThread().getName(),
				(end - start) / 1000.0, collectionId, page);
	}

	private boolean crawl(String collectionId, int page) {
		List<Article> as = articleParser.getArticleList(collectionId, page);
		if (as.size() == 0) {
			return false;
		}
		for (Article a : as) {
			ArticleExample example = new ArticleExample();
			example.createCriteria().andArticleUrlEqualTo(a.getArticleUrl());
			List<Article> collectionList = articleMapper.selectByExample(example);

			if (collectionList.size() > 0) {
				if (collectionList.get(0).getIsCollected().equals(Byte.valueOf("1"))) {
					a.setCollectionId(myCollectionId);
				}

				if (myCollectionId.equals(collectionId)
						|| myCollectionId.equals(collectionList.get(0).getCollectionId())) {
					a.setIsCollected(Byte.valueOf("1"));
				}

				if (articleMapper.updateByExampleSelective(a, example) == 1) {
					lg.info("Update data successfully.{}", a);
				} else {
					lg.info("Failed to update data.{}", a);
				}
			} else {
				if (articleMapper.insert(a) == 1) {
					lg.info("Insert data successfully.{}", a);
				} else {
					lg.error("Failed to insert data.{}", a);
				}
			}
		}
		return true;
	}
}
