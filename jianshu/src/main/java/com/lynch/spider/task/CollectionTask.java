/**
 * 
 */
package com.lynch.spider.task;

import com.lynch.spider.service.ArticleService;

/**
 * @Description TODO
 * @author Lynch
 * @date 2017年3月22日
 */
public class CollectionTask implements Runnable {

    private ArticleService articleService;
    private String collectionId;

    public CollectionTask(ArticleService articleService, String collectionId) {
        super();
        this.articleService = articleService;
        this.collectionId = collectionId;
    }

    @Override
    public void run() {
        articleService.doJob(collectionId);
    }

}
