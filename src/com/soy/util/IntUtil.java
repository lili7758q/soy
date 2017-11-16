package com.soy.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

/** 
* @author Soy 
* @date 2017年11月15日 下午5:03:41 
* @version 1.0 
*/
public class IntUtil {
	private static String UMAIN = "http://s2.vvqz.com:8089/";
	static CookieStore cookieStore = null;
	static HttpClientContext context = null;
	private static String CHARSET = "GB2312";
	
	public static void  post(String url) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
        
		List <NameValuePair>data = new ArrayList<NameValuePair>();
		data.add(new BasicNameValuePair("username", "hq1234"));
		data.add(new BasicNameValuePair("password", "qazaq"));
		data.add(new BasicNameValuePair("commit", "%B5%C7%C2%BC"));
		
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(data, Consts.UTF_8);
        HttpPost post = new HttpPost(url);  
        post.setEntity(new UrlEncodedFormEntity(data,CHARSET));
        HttpResponse response = client.execute(post);
        printResponse(response,CHARSET);
        System.out.println("--------------");
        
        setCookieStore(response);
        setContext();
        HttpGet get = new HttpGet(UMAIN+"login/login.php");
        HttpResponse res = client.execute(get);
        printResponse(res,CHARSET);
        System.out.println("--------------");
        
        HttpGet get2 = new HttpGet(UMAIN+"index.php");
        HttpResponse res2 = client.execute(get2);
        printResponse(res2,CHARSET);
		
	}
	
	public static void setContext() {
	    System.out.println("----setContext");
	    context = HttpClientContext.create();
	    Registry<CookieSpecProvider> registry = RegistryBuilder
	        .<CookieSpecProvider> create()
	        .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
	        .register(CookieSpecs.BROWSER_COMPATIBILITY,
	            new BrowserCompatSpecFactory()).build();
	    context.setCookieSpecRegistry(registry);
	    context.setCookieStore(cookieStore);
	  }

	  public static void setCookieStore(HttpResponse httpResponse) {
	    System.out.println("----setCookieStore");
	    cookieStore = new BasicCookieStore();
	    // JSESSIONID
	    String setCookie = httpResponse.getFirstHeader("Set-Cookie")
	        .getValue();
	    String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
	        setCookie.indexOf(";"));
	    System.out.println("JSESSIONID:" + JSESSIONID);
	    // 新建一个Cookie
	    BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
	        JSESSIONID);
	    cookie.setVersion(0);
	    cookie.setDomain("127.0.0.1");
	    cookie.setPath("/CwlProClient");
	    // cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
	    // cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
	    // cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
	    // cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
	    cookieStore.addCookie(cookie);
	  }
	  
	  public static void printResponse(HttpResponse httpResponse,String charset)
		      throws Exception {
		    // 获取响应消息实体
		    HttpEntity entity = httpResponse.getEntity();
		    // 响应状态
		    System.out.println("status:" + httpResponse.getStatusLine());
		    System.out.println("headers:");
		    HeaderIterator iterator = httpResponse.headerIterator();
		    while (iterator.hasNext()) {
		      System.out.println("\t" + iterator.next());
		    }
		    // 判断响应实体是否为空
		    if (entity != null) {
		      String responseString = EntityUtils.toString(entity,charset);
		      System.out.println("response length:" + responseString.length());
		      System.out.println("response content:"
		          + responseString.replace("\r\n", ""));
		    }
		  }
	
	public static void main(String []args) throws Exception{
		post(UMAIN+"passport/deal.php");
	}
}
