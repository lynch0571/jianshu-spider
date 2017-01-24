package main;

import service.ArticleService;


public class ArticleThread extends Thread {

	private String collectionId;
	
	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public ArticleThread(String collectionId) {
		super();
		this.collectionId = collectionId;
	}

	@Override
	public void run() {
		ArticleService cs=new ArticleService();
		cs.doJob(collectionId);
	}

}
