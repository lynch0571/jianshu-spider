package com.lynch.spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JianShuAppRun {

    public static void main(String[] args) {
        SpringApplication.run(JianShuAppRun.class, args);
        System.out.println("程序已启动");
        try {
			Document doc=Jsoup.connect("http://localhost:8080/a/crawl/db").get();
			System.out.println(doc);
		} catch (IOException e) {
			System.err.println("调用请求出错");
			e.printStackTrace();
		}
    }

}
