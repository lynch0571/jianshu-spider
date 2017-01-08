package service;

import java.util.List;

import model.Article;
import model.ArticleExample;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parser.ArticleParser;
import utils.PropertiesUtil;
import utils.SqlSessionUtil;
import dao.ArticleMapper;

public class ArticleService {
    private Logger lg = LoggerFactory.getLogger(ArticleService.class);
    private static String myCollectionId = PropertiesUtil.getValueByKey("config/spider.properties", "myCollectionId");

    public void doJob(int collectionId) {
        Long start = System.currentTimeMillis();
        lg.info("{} start.", Thread.currentThread().getName());
        // 10次未爬到数据，则停止
        int page = 1;
        int i = 0;
        while (i < 10) {
            if (!crawl(collectionId, page++)) {
                i++;
            }
        }
        Long end = System.currentTimeMillis();
        lg.info("{} end.Totle time:{}s, collectionId:{}, page:{}", Thread.currentThread().getName(), (end - start) / 1000.0, collectionId, page);
    }

    private boolean crawl(int collectionId, int page) {
        List<Article> as = ArticleParser.getArticleList(collectionId, page);
        if (as.size() == 0) {
            return false;
        }
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        Byte isCollected = 1;
        for (Article a : as) {
            ArticleExample example = new ArticleExample();
            example.createCriteria().andArticleUrlEqualTo(a.getArticleUrl());
            List<Article> collectionList = mapper.selectByExample(example);

            if (collectionList.size() > 0) {
                lg.info("The data already exist");
//                if (!myCollectionId.equals(collectionId)) {
//                    if (isCollected.equals(collectionList.get(0).getIsCollected())) {
//                        a.setIsCollected(isCollected);
//                        a.setCollectionId(Integer.valueOf(myCollectionId));
//                        if (mapper.updateByExample(a, example) == 1) {
//                            lg.info("Update data successfully.{}", a);
//                        } else {
//                            lg.error("Failed to update data.{}", a);
//                        }
//                    }
//                }
            } else {
                // 千赞专题全部已收录
                if (myCollectionId.equals(collectionId)) {
                    a.setIsCollected(isCollected);
                }
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
