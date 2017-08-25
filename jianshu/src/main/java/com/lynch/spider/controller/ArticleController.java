/**
 * 
 */
package com.lynch.spider.controller;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lynch.spider.dto.ResponseDto;
import com.lynch.spider.service.ArticleService;
import com.lynch.spider.service.CollectionService;
import com.lynch.spider.task.CollectionTask;

/**
 * @Description 根据专题ID爬取文章
 * @author Lynch
 * @date 2017年3月9日
 */
@RestController
@RequestMapping("/a/")
public class ArticleController {

    private Logger lg = LoggerFactory.getLogger(ArticleController.class);

    @Value("${spider.collectionIds}")
    private String collectionIds;

    @Value("${spider.myCollectionId}")
    private String myCollectionId;

    @Value("${spider.threadSize:20}")
    private int threadSize;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ResponseDto responseDto;

    /**
     * @param idStr
     *            特殊参数:my为千赞专题，all为配置文件中所有的专题
     * @return
     */
    @RequestMapping(value = "crawl/{idStr}", method = RequestMethod.GET)
    private ResponseDto crawlMyArticle(@PathVariable("idStr") String idStr) {
        long t1 = System.currentTimeMillis();
        Object[] ids = null;
        if (StringUtils.isEmpty(idStr)) {
            responseDto.setResult("专题Id为空");
        } else if ("my".equals(idStr)) {
            ids = myCollectionId.split(",");
        } else if ("all".equals(idStr)) {
            ids = collectionIds.split(",");
        } else if ("db".equals(idStr)) {
            List<String> list = collectionService.getAllIds();
            list.remove(myCollectionId);
            list.add(myCollectionId);
            ids = list.toArray();
        }

        ExecutorService pool = Executors.newFixedThreadPool(threadSize);
        for (Object id : ids) {
            pool.execute(new CollectionTask(articleService, id.toString()));
        }
        pool.shutdown();
        while (true) {
            if (pool.isTerminated()) {
                break;
            } else {
                try {
                    lg.warn("线程池还有未执行完毕的线程...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        long t2 = System.currentTimeMillis();
        responseDto.setResult("专题" + Arrays.toString(ids) + "爬取完毕!");
        responseDto.setTime((t2 - t1) / 1000.0);
        lg.info(responseDto.toString());
        lg.info(articleService.getArticleHtml());
        return responseDto;
    }

}
