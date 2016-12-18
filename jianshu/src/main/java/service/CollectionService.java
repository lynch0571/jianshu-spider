package service;

import java.util.List;

import model.Collection;
import model.CollectionExample;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parser.CollectionParser;
import utils.SqlSessionUtil;
import dao.CollectionMapper;

public class CollectionService {
    private Logger lg = LoggerFactory.getLogger(CollectionService.class);

    public void doJob() {
        Long start = System.currentTimeMillis();
        lg.info("{} start.", Thread.currentThread().getName());
        // 10次未爬到数据，则停止
        int page = 1;
        int i = 0;
        while (i < 10) {
            if (!crawl(page++)) {
                i++;
            }
        }
        Long end = System.currentTimeMillis();
        lg.info("{} end.Totle time:{}s, page:{}", Thread.currentThread().getName(), (end - start) / 1000.0, page);
    }

    private boolean crawl(int page) {
        List<Collection> as = CollectionParser.getCollectionList(page);
        if (as.size() == 0) {
            return false;
        }
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CollectionMapper mapper = sqlSession.getMapper(CollectionMapper.class);
        for (Collection a : as) {
            CollectionExample example = new CollectionExample();
            example.createCriteria().andIdEqualTo(a.getId());
            List<Collection> collectionList = mapper.selectByExample(example);
            if (collectionList.size() > 0) {
                if (mapper.updateByExample(a, example) == 1) {
                    lg.info("Update data successfully.{}", a);
                } else {
                    lg.error("Failed to update data.{}", a);
                }
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
