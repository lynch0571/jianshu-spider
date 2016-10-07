package main;

import java.util.List;

import model.Collection;
import model.CollectionExample;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parser.CollectionParser;
import utils.PropertiesUtil;
import utils.SqlSessionUtil;
import dao.CollectionMapper;

public class App {
	private static Logger lg = LoggerFactory.getLogger(App.class);
	private static String collectionIds = PropertiesUtil.getValueByKey("config/spider.properties", "collectionIds");
	public static void main(String[] args) {
		String[] ids = collectionIds.split(",");
		
		for(int i=0;i<ids.length;i++){
			MultiThreadRun thread=new MultiThreadRun(Integer.valueOf(ids[i]));
			thread.setName("Thread-"+ids[i]);
			thread.start();
		}
	}



	public static void doJob(int collectionId) {
		Long start=System.currentTimeMillis();
		lg.info("{} start.",Thread.currentThread().getName());
		//10次未爬到数据，则停止
		int page = 100;
		int i=0;
		while(i<10){
			if(!crawl(collectionId,page++)){
				i++;
			}
		}
		Long end=System.currentTimeMillis();
		lg.info("{} end.Totle time:{}s, collectionId:{}, page:{}",Thread.currentThread().getName(),(end-start)/1000.0,collectionId,page);
	}
	
	

	private static boolean crawl(int collectionId,int page) {
		List<Collection> cs = CollectionParser.getCollectionArticleList(collectionId, page);
		if(cs.size()==0){
			return false;
		}
		SqlSession sqlSession=SqlSessionUtil.getSqlSession();
		CollectionMapper mapper = sqlSession.getMapper(CollectionMapper.class);
		for (Collection c : cs) {
			CollectionExample example = new CollectionExample();
			example.createCriteria().andArticleUrlEqualTo(c.getArticleUrl());
			List<Collection> collectionList = mapper.selectByExample(example);
			if (collectionList.size() > 0) {
				lg.info("The data already exists.{}", c);
			} else {
				if (mapper.insert(c) == 1) {
					lg.info("Insert data successfully.{}", c);
				} else {
					lg.error("Failed to insert data.{}", c);
				}
			}
		}
		sqlSession.close();
		return true;
	}

}
