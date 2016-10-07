package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.PropertiesUtil;

public class App {
	private static Logger lg = LoggerFactory.getLogger(App.class);
	private static String collectionIds = PropertiesUtil.getValueByKey("config/spider.properties", "collectionIds");
	public static void main(String[] args) {
		String[] ids = collectionIds.split(",");
		if(ids.length==0){
			lg.info("Collection id not set in properties file.");
			return;
		}else{
			lg.info("Collection id:{}",collectionIds);
		}
		for(int i=0;i<ids.length;i++){
			MultiThreadRun thread=new MultiThreadRun(Integer.valueOf(ids[i]));
			thread.setName("Thread-"+ids[i]);
			thread.start();
		}
	}

}
