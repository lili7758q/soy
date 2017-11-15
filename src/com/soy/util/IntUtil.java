package com.soy.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

/** 
* @author Soy 
* @date 2017年11月15日 下午5:03:41 
* @version 1.0 
*/
public class IntUtil {
	private static String uMain = "http://s2.vvqz.com:8089/";
	
	public static String post (String url) {
		HttpClient client  = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List <NameValuePair>data = new ArrayList<NameValuePair>();
		data.add(new BasicNameValuePair("username", "hq1234"));
		data.add(new BasicNameValuePair("username", "qazaq"));
		data.add(new BasicNameValuePair("commit", "%B5%C7%C2%BC"));
		String sCharSet = "GB2312";
		
		try {
			post.setEntity(new UrlEncodedFormEntity(data,sCharSet));
			HttpResponse res = client.execute(post);
			if (res !=null) {
				String ret = EntityUtils.toString(res.getEntity());
				if (ret.indexOf("login.php") != -1) {
					HttpGet gt = new HttpGet(uMain+"login/login.php");
					CloseableHttpResponse r = httpClient.execute(gt);
					System.out.println(EntityUtils.toString(gt.getEntity()));
				}
				System.out.println(res.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String []args){
		post(uMain+"passport/deal.php");
	}
}
