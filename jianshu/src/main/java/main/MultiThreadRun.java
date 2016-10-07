package main;

import service.CollectionService;


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
		CollectionService cs=new CollectionService();
		cs.doJob(collectionId);
	}

}
