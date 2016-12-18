package main;

import service.ArticleService;


public class MultiThreadRun extends Thread {

	private int collectionId;
	
	public int getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}

	public MultiThreadRun(int collectionId) {
		super();
		this.collectionId = collectionId;
	}

	@Override
	public void run() {
		ArticleService cs=new ArticleService();
		cs.doJob(collectionId);
	}

}
