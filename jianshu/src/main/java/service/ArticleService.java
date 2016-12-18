package service;

import java.util.List;

import model.Article;
import model.ArticleExample;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parser.ArticleParser;
import utils.SqlSessionUtil;
import dao.ArticleMapper;

public class ArticleService {
	private Logger lg = LoggerFactory.getLogger(ArticleService.class);
	public void doJob(int articleId) {
		Long start=System.currentTimeMillis();
		lg.info("{} start.",Thread.currentThread().getName());
		//10次未爬到数据，则停止
		int page = 1;
		int i=0;
		while(i<10){
			if(!crawl(articleId,page++)){
				i++;
			}
		}
		Long end=System.currentTimeMillis();
		lg.info("{} end.Totle time:{}s, collectionId:{}, page:{}",Thread.currentThread().getName(),(end-start)/1000.0,articleId,page);
	}
	
	

	private boolean crawl(int articleId,int page) {
		List<Article> as = ArticleParser.getArticleList(articleId, page);
		if(as.size()==0){
			return false;
		}
		SqlSession sqlSession=SqlSessionUtil.getSqlSession();
		ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
		for (Article a : as) {
		    ArticleExample example = new ArticleExample();
			example.createCriteria().andArticleUrlEqualTo(a.getArticleUrl());
			List<Article> collectionList = mapper.selectByExample(example);
			if (collectionList.size() > 0) {
				lg.info("The data already exists.{}", a);
			} else {
				if (mapper.insert(a) == 1) {
					lg.info("Insert data successfully.{}", a);
				} else {
					lg.error("Failed to insert data.{}", a);
				}
			}
		}
		sqlSession.close();
		return true;
	}
}
