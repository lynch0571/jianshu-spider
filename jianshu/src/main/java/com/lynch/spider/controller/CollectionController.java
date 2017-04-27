/**
 * 
 */
package com.lynch.spider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lynch.spider.dto.ResponseDto;
import com.lynch.spider.service.CollectionService;

/**
 * @Description 爬取专题
 * @author Lynch
 * @date 2017年3月9日
 */
@RestController
@RequestMapping("/c/")
public class CollectionController {

    private Logger lg = LoggerFactory.getLogger(CollectionController.class);

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ResponseDto responseDto;

    @RequestMapping(value = "crawl", method = RequestMethod.GET)
    private ResponseDto crawlMyArticle() {
        long t1 = System.currentTimeMillis();
        collectionService.doJob();
        long t2 = System.currentTimeMillis();
        responseDto.setResult("专题爬取完毕!");
        responseDto.setTime((t2 - t1) / 1000.0);
        lg.info(responseDto.toString());
        return responseDto;
    }

}
