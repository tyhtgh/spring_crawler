package com.arvato.carwleer;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class AdCrawleer extends BreadthCrawler {
	private static Logger logger = Logger
			.getLogger("File1");
	public AdCrawleer(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		/* start page */
		//this.addSeed("http://h.pcd.autohome.com.cn/adfront/deliver?psids=1985&pageid=1&city=110100");
		/* fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml */
		//this.addRegex("http://h.pcd.autohome.com.cn/adfront/deliver?psids=1985&pageid=1&city=110100?a=");
		/* do not fetch jpg|png|gif */
		this.addRegex("-.*\\.(jpg|png|gif).*");
		/* do not fetch url contains # */
		this.addRegex("-.*#.*");
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		Document doc = page.getDoc();
		String str=doc.getElementsByTag("body").toString();
		System.out.println(page.getUrl());
		logger.error("--------");
		next.add("http://h.pcd.autohome.com.cn/adfront/deliver?psids=825&pageid=1&city=110100");
		next.add("http://h.pcd.autohome.com.cn/adfront/deliver?psids=825&pageid=1&city=110102");
		/*String title = page.select("ul[id=auto-index-market-list]>li")
						.toString();
		System.out.println("title:\n" + title);*/
		String url = page.getUrl();
	}

/*	public static void main(String[] args) throws Exception {
		for(int i=0;i<100;i++){
			AdCrawleer crawler = new AdCrawleer("crawl", true);
			//for (int i = 0; i < 1; i++) {
			crawler.addSeed("http://www.cnblogs.com/weixing/p/4096791.html?");
				//System.out.println(i);
			//}
			//crawler.setExecuteInterval(1000);
			crawler.setThreads(1);
			//crawler.setTopN(6);
			crawler.start(2);
			//Thread.sleep(5000);
		}
	}*/
}
