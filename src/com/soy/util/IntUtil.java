package com.soy.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static String UMAIN = "";
	private static String CHARSET = "";
	static CookieStore cookieStore = null;
	static HttpClientContext context = null;
	static CloseableHttpClient client;
	
	
	
	public IntUtil (String urlMain,String charset){
		this.client = HttpClients.createDefault();
		this.UMAIN = urlMain;
		this.CHARSET = charset;
	}
	
	/** 
	* @author Soy 
	* @date 2017年11月16日 下午7:06:02 
	* @description  POST
	*/ 
	public HttpResponse post(String url,Map<String,String> paraMap) throws Exception {
		List <NameValuePair>paraList = new ArrayList<NameValuePair>();
		map2ListParam(paraMap,paraList);
		
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paraList,CHARSET);
        HttpPost post = new HttpPost(UMAIN+url);  
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        return response;
		
	}
	
	/** 
	* @author Soy 
	* @date 2017年11月16日 下午7:08:17 
	* @description  GET
	*/ 
	public Map get (String url) throws Exception {
		Map map = new HashMap();
		HttpGet get = new HttpGet(UMAIN+url);
		HttpResponse response = client.execute(get);
		map.put("code", response.getStatusLine().getStatusCode());
		map.put("body", EntityUtils.toString(response.getEntity(),CHARSET));
		get.releaseConnection();
		return map;
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
	  
	  public HttpEntity getResponse(HttpResponse httpResponse,String charset)
		      throws Exception {
		  	Map map = new HashMap();
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
		    return entity;
		  }
	
	  //参数转换
	  public void map2ListParam (Map<String,String> map,List param){
		  for (Map.Entry<String, String> entry : map.entrySet()) {
			  param.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
		  }
	  }
	
	
}
