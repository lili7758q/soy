package com.soy.run;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.soy.util.Const;
import com.soy.util.IntUtil;

/** 
* @author Soy 
* @date 2017��11��16�� ����7:15:44 
* @version 1.0 
*/
public class testRun {
	static String urlMain = "http://s2.vvqz.com:8089/";
	static String charset = Const.GB2321;
	static Logger log = Logger.getLogger(testRun.class);
	
	public static void main (String args[]) throws Exception{
		IntUtil util = new IntUtil(urlMain, charset) ;
		log.error("test!!!!!!");
		//��½
		Map loginMap = new HashMap();
		loginMap.put("username", "hq1234");
		loginMap.put("password", "qazaq");
		loginMap.put("commit", "%B5%C7%C2%BC");
		HttpResponse loginRes = util.post("passport/deal.php", loginMap);
		int StatusCode = loginRes.getStatusLine().getStatusCode();
		if (StatusCode != 200) {
			if(StatusCode == 404){
				System.out.println("������վʧ�ܣ�");
			}else if (StatusCode == 302){
				System.out.println("�˺Ż������벻��ȷ��");
			}
			return;
		}
		System.out.println(new Date());
		//������ҳ
		Map map = util.get("login/login.php");
		if((int)map.get("code")!= 200){
			System.out.println("��½ʧ�ܣ�");
			return;
		}
		System.out.println(new Date());
		for(int n = 10; n >0;n--){
			Map indexMap = util.get("index.php");
			System.out.println("__________"+n+"__________");
			System.out.println((int)indexMap.get("code"));
			System.out.println(new Date());
		}

	}

}
