package com.arvato.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.arvato.carwleer.AdCrawleerOne;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class Task {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"HH:mm:ss");
	private static Logger logger1 = Logger
			.getLogger("File1");
	private static Logger logger2 = Logger
			.getLogger("File2");
	
	//@Scheduled(fixedRate = 300000)
	public void cheshi() {
		System.out.println("cheshi The time is now "
				+ dateFormat.format(new Date()));
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit");
		logger.setLevel(Level.OFF);
		try{
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true);
		driver.get("http://www.autohome.com.cn/beijing/cheshi/#pvareaid=103402");
		List<WebElement> elementList = driver.findElementsByCssSelector("ul.news_ul li a");
		String[] strs=new String[24];
		String[] strsName=new String[24];
		for(int i=0;i<elementList.size();i++){
			strs[i]=elementList.get(i).getAttribute("href");
			strsName[i]=elementList.get(i).getText();
		}
		
		driver.setJavascriptEnabled(false);
		for(int i=0;i<strs.length;i++){
			String s=strs[i];
			if(s.startsWith("http://www.autohome.com.cn/dealer/")){
				s+=","+strsName[i];
				driver.get(strs[i]);
				s+=","+driver.findElementByCssSelector("div.article-info").getText();
			}else if(s.startsWith("http://www.autohome.com.cn/market/")){
				s+=","+strsName[i];
				driver.get(strs[i]);
				s+=","+driver.findElementByCssSelector("div.article-info").getText();
			}else{
				driver.get(strs[i]);
				s+=","+strsName[i];
				s+=","+"广告页面,"+driver.getCurrentUrl();
			}
			s=(i+1)+","+s;
			logger1.error(s);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//@Scheduled(fixedRate = 300000)
	/*public static void main(String[] s) {
		System.out.println("test The time is now "
				+ dateFormat.format(new Date()));
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit");
		logger.setLevel(Level.OFF);
		try{
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(false);
		driver.get("http://h.pcd.autohome.com.cn/adfront/deliver?psids=1985,1989,1991,1994,1995,1996,825,824&pageid=1&city=110100&ip=123.59.45.110");
		String allString=driver.findElementByTagName("body").getText();
		List<WebElement> elementList = driver.findElementsByTagName("a");
		if(allString.contains(";!")){
			String[] temps=allString.split(";!");
			for(int i=1;i<temps.length;i++){
				//获取Title 和URL参数
				String s1=StringUtils.substring(temps[i], StringUtils.indexOf(temps[i], "d=[")+4,StringUtils.ordinalIndexOf(temps[i], ",", 6)-1);
				//System.out.println(s1);
				String s2=StringUtils.substring(temps[i], StringUtils.ordinalIndexOf(temps[i], ",", 6)+2,StringUtils.ordinalIndexOf(temps[i], ",", 7)-2);
				//System.out.println(s2);
				//获取编号
				String s3=StringUtils.substring(temps[i], StringUtils.indexOf(temps[i], "{},'")+4,StringUtils.ordinalIndexOf(temps[i], ",", 15)-1);
				//System.out.println(s3);
				String s4=StringUtils.substring(temps[i], StringUtils.ordinalIndexOf(temps[i], ",", 16)+2,StringUtils.indexOf(temps[i], "');;"));
				//System.out.println(s4);
			}
		}
		
		
		
		
		System.out.println();
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	

	@Scheduled(fixedRate = 300000)
	public void autohome() {
		System.out.println("autohome The time is now "
				+ dateFormat.format(new Date()));
		try{
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit");
		logger.setLevel(Level.OFF);
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(false);
		driver.get("http://www.autohome.com.cn/includefile/index201404/market/110100.js");
		String jsonc=driver.getPageSource();
		JSONArray arrayMap=JSON.parseArray(jsonc.substring(jsonc.indexOf("["), jsonc.indexOf("]")+1));
		String[] strs=new String[16];
		String[] strsName=new String[16];
		for(int i=0;i<arrayMap.size();i++){
			strs[i]=((Map)arrayMap.get(i)).get("Url").toString();
			strsName[i]=((Map)arrayMap.get(i)).get("Title").toString();
		}

		try{
			driver.setJavascriptEnabled(false);
			driver.get("http://h.pcd.autohome.com.cn/adfront/deliver?psids=1985,1989,1991,1994,1995,1996,825,824&pageid=1&city=110100&ip=123.59.45.110");
			String allString=driver.findElementByTagName("body").getText();
			if(allString.contains(";!")){
				String[] temps=allString.split(";!");
				for(int i=1;i<temps.length;i++){
					//获取Title 和URL参数
					String s1=StringUtils.substring(temps[i], StringUtils.indexOf(temps[i], "d=[")+4,StringUtils.ordinalIndexOf(temps[i], ",", 6)-1);
					//System.out.println(s1);
					String s2=StringUtils.substring(temps[i], StringUtils.ordinalIndexOf(temps[i], ",", 6)+2,StringUtils.ordinalIndexOf(temps[i], ",", 7)-2);
					//System.out.println(s2);
					//获取编号
					String s3=StringUtils.substring(temps[i], StringUtils.indexOf(temps[i], "{},'")+4,StringUtils.ordinalIndexOf(temps[i], ",", 15)-1);
					//System.out.println(s3);
					String s4=StringUtils.substring(temps[i], StringUtils.ordinalIndexOf(temps[i], ",", 16)+2,StringUtils.indexOf(temps[i], "');;"));
					//System.out.println(s4);
					if("s1985".equals(s3)){
						strs[8]=s4+"&u="+s2;
						strsName[8]=s1;
					}else if("s1989".equals(s3)){
						strs[9]=s4+"&u="+s2;
						strsName[8]=s1;
					}else if("s1991".equals(s3)){
						strs[10]=s4+"&u="+s2;
						strsName[10]=s1;
					}else if("s1994".equals(s3)){
						strs[11]=s4+"&u="+s2;
						strsName[11]=s1;
					}else if("s1995".equals(s3)){
						strs[12]=s4+"&u="+s2;
						strsName[12]=s1;
					}else if("s1996".equals(s3)){
						strs[13]=s4+"&u="+s2;
						strsName[13]=s1;
					}else if("jxs_word_01".equals(s3)){
						strs[14]=s4+"&u="+s2;
						strsName[14]=s1;
					}else if("jxs_word_02".equals(s3)){
						strs[15]=s4+"&u="+s2;
						strsName[15]=s1;
					}
				}
			}
			
		/*driver.setJavascriptEnabled(true);
		driver.get("http://www.autohome.com.cn/beijing/");
		List<WebElement> elementList = driver.findElementsByCssSelector("ul#auto-index-market-list li div");
		for(int i=0;i<elementList.size();i++){
			if(!elementList.get(i).toString().contains("/")){
				if(null!=elementList.get(i).getAttribute("id")){
					if("s1985".equals(elementList.get(i).getAttribute("id"))){
						strs[8]=elementList.get(i).findElement(By.tagName("a")).getAttribute("href");
						strsName[8]=elementList.get(i).findElement(By.tagName("a")).getText();
					}else if("s1989".equals(elementList.get(i).getAttribute("id"))){
						strs[9]=elementList.get(i).findElement(By.tagName("a")).getAttribute("href");
						strsName[8]=elementList.get(i).findElement(By.tagName("a")).getText();
					}else if("s1991".equals(elementList.get(i).getAttribute("id"))){
						strs[10]=elementList.get(i).findElement(By.tagName("a")).getAttribute("href");
						strsName[10]=elementList.get(i).findElement(By.tagName("a")).getText();
					}else if("s1994".equals(elementList.get(i).getAttribute("id"))){
						strs[11]=elementList.get(i).findElement(By.tagName("a")).getAttribute("href");
						strsName[11]=elementList.get(i).findElement(By.tagName("a")).getText();
					}else if("s1995".equals(elementList.get(i).getAttribute("id"))){
						strs[12]=elementList.get(i).findElement(By.tagName("a")).getAttribute("href");
						strsName[12]=elementList.get(i).findElement(By.tagName("a")).getText();
					}else if("s1996".equals(elementList.get(i).getAttribute("id"))){
						strs[13]=elementList.get(i).findElement(By.tagName("a")).getAttribute("href");
						strsName[13]=elementList.get(i).findElement(By.tagName("a")).getText();
					}else if("jxs_word_01".equals(elementList.get(i).getAttribute("id"))){
						strs[14]=elementList.get(i).findElement(By.tagName("a")).getAttribute("href");
						strsName[14]=elementList.get(i).findElement(By.tagName("a")).getText();
					}else if("jxs_word_02".equals(elementList.get(i).getAttribute("id"))){
						strs[15]=elementList.get(i).findElement(By.tagName("a")).getAttribute("href");
						strsName[15]=elementList.get(i).findElement(By.tagName("a")).getText();
					}
				}
				
			}
		}*/
		}catch(Exception e){
			System.out.println("抓取北京首頁失敗！");
			//e.printStackTrace();
			//System.out.println(driver.findElementById("auto-index-market-list").toString());
		}
		
		driver.setJavascriptEnabled(false);
		for(int i=0;i<strs.length;i++){
			String s=strs[i];
			if(s.startsWith("http://www.autohome.com.cn/dealer/")){
				s+=","+strsName[i];
				driver.get(strs[i]);
				s+=","+driver.findElementByCssSelector("div.article-info").getText();
			}else if(s.startsWith("http://www.autohome.com.cn/market/")){
				s+=","+strsName[i];
				driver.get(strs[i]);
				s+=","+driver.findElementByCssSelector("div.article-info").getText();
			}else{
				driver.get(strs[i]);
				s+=","+strsName[i];
				//System.out.println();
				s+=","+"广告页面,"+driver.getCurrentUrl();
			}
			s=(i+1)+","+s;
			logger2.error(s);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
