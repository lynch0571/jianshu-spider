package com.lynch.spider.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lynch.spider.dao.CollectionMapper;
import com.lynch.spider.model.Collection;
import com.lynch.spider.model.CollectionExample;
import com.lynch.spider.parser.CollectionParser;

@Service
public class CollectionService {
    private Logger lg = LoggerFactory.getLogger(CollectionService.class);

    @Autowired
    private CollectionParser collectionParser;

    @Autowired
    private CollectionMapper collectionMapper;

    public List<String> getAllIds() {
        return collectionMapper.selectAllIds();
    }
    
    public void doJob() {
        Long start = System.currentTimeMillis();
        lg.info("{} start.", Thread.currentThread().getName());
        int page = 1;
        int i = 0;
        while (i < 5) {
            if (!crawl(page++)) {
                i++;
            }
        }
        Long end = System.currentTimeMillis();
        lg.info("{} end.Totle time:{}s, page:{}", Thread.currentThread().getName(), (end - start) / 1000.0, page);
    }

    private boolean crawl(int page) {
        List<Collection> list = collectionParser.getCollectionList(page);
        if (list.size() == 0) {
            return false;
        }
        for (Collection c : list) {
            CollectionExample example = new CollectionExample();
            example.createCriteria().andUrlEqualTo(c.getUrl());
            List<Collection> collectionList = collectionMapper.selectByExample(example);

            try {
                if (collectionList.size() > 0) {
                    c.setUpdateTime(new Date());
                    if (collectionMapper.updateByExampleSelective(c, example) == 1) {
                        lg.info("Update data successfully.{}", c);
                    } else {
                        lg.info("Failed to update data.{}", c);
                    }
                } else {
                    if (collectionMapper.insert(c) == 1) {
                        lg.info("Insert data successfully.{}", c);
                    } else {
                        lg.error("Failed to insert data.{}", c);
                    }
                }
            } catch (Exception e) {
                lg.error("数据库插入或更新失败!object:{},Message:{}", c, e.getMessage());
                e.printStackTrace();
            }
        }
        return true;
    }
}
