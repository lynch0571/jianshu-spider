/**
 * 
 */
package com.lynch.spider.controller;

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

/**
 * @Description TODO
 * @author Lynch
 * @date 2017年3月9日
 */
@RestController
@RequestMapping("c")
public class CollectionController {

    private Logger lg = LoggerFactory.getLogger(CollectionController.class);

    @Value("${spider.collectionIds}")
    private String collectionIds;

    @Value("${spider.myCollectionId}")
    private String myCollectionId;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ResponseDto responseDto;

    @RequestMapping(value = "craw/{id}", method = RequestMethod.GET)
    private ResponseDto crawlMyArticle(@PathVariable("id") String id) {
        long t1 = System.currentTimeMillis();
        if (StringUtils.isEmpty(id)) {
            responseDto.setResult("专题Id为空");
        } else if ("qianzan".equals(id)) {
            id = myCollectionId;
        }
        articleService.doJob(id);
        long t2 = System.currentTimeMillis();
        responseDto.setResult("专题" + id + "爬取完毕!");
        responseDto.setTime((t2 - t1) / 1000.0);
        lg.info(responseDto.toString());
        return responseDto;
    }

}
