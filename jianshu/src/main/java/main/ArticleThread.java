package main;

import service.ArticleService;


public class ArticleThread extends Thread {

	private int collectionId;
	
	public int getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}

	public ArticleThread(int collectionId) {
		super();
		this.collectionId = collectionId;
	}

	@Override
	public void run() {
		ArticleService cs=new ArticleService();
		cs.doJob(collectionId);
	}

}
