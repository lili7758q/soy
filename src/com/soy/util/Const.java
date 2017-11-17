package com.soy.util;
/** 
* @author Soy 
* @date 2017年11月16日 下午3:53:03 
* @version 1.0 
* @description 常量
*/
public class Const {
	/*常用返回码*/
	public static final String CODE_SUCCESSED = "0";
	
	/*Http状态码*/
	public static final int CODE_OK = 200;
	public static final int CODE_BAD_GATEWAY = 502;
	public static final int CODE_NOT_FOUND = 404;
	public static final int CODE_MOVE = 302; //账号密码不正确
	
	/*编码*/
	public static final String UTF_8 = "utf-8";
	public static final String GB2321 = "gb2312";
	
	/*
	 * 拍东西结果，没有枚举值就直接返回一个字符串
	 */
	public static final String SELL_0  = "0";//成功
	public static final String SELL_4  = "4";//您的拍卖物品个数已达上限,请取出已过期的物品！
	public static final String SELL_5  = "5";//该物品不可交易
	public static final String SELL_10 = "10";//您的背包中没有足够的物品可以拍卖！
	public static final String SELL_11 = "11";//此物品已经在拍卖所，请取回后再继续拍卖！
	public static final String SELL_12 = "12";//您操作太快，请稍候再试！
	
	/*
	 * 买东西结果，没有枚举值就直接返回一个字符串
	 */
	public static final String BUY_0  = "0";//成功
	public static final String BUY_3  = "3";//购买物品数量太多！
	public static final String BUY_4  = "4";//包裹空间不足！
	public static final String BUY_5  = "5";//您操作太快！
	public static final String BUY_7  = "7";//该物品不是卖给你的哦！
	public static final String BUY_8  = "8";//您太慢了，东西卖完了！
	public static final String BUY_10 = "10";//您的金币不够！
	
	/*
	 * 进化结果，没有枚举值就直接返回一个字符串
	 */
	public static final String JH_0  = "0";//进化失败！
	public static final String JH_1  = "1";//恭喜您，宝贝进化成功!!
	public static final String JH_2  = "2";//缺少进化必须品！
	public static final String JH_3  = "3";//宝宝的等级太低，还不能进化！
	public static final String JH_4  = "4";//宝贝暂时还不能进化到更高级
	public static final String JH_5  = "5";//您没有足够金币进行进化！
	public static final String JH_6  = "6";//您已经达到最大的进化次数了！
	
	/*
	 * 合成结果，没有枚举值就直接返回一个字符串
	 * 传参不含type1
	 * 带装备合，加参数type=do&type1=check
	 * 不加护宠类，加参数type1=check
	 */
	public static final String HC_0 = "";//合成失败！！
	public static final String HC_1 = "";//您没有相应的宠物
	public static final String HC_2 = "";//这两个宠物好像不能合成噢!
	public static final String HC_3 = "";//您的金币不足，不能合成!
	public static final String HC_5 = "";//成功
	public static final String HC_6 = "";//失败
	public static final String HC_10 = "";//数据读取失败!
	public static final String HC_11 = "";//冷却时间未到！
	public static final String HC_15 = "";//宠物成长未达到合成条件哦！
	public static final String HC_20 = "";//对不起，您的背包中没有此物品!
	public static final String HC_200 = "";//未添加护宠类道具
	public static final String HC_1000 = "";//宠物有装备
	
	/*物品pids*/
	public static final String pids_94   = "94";//进化之书
	public static final String pids_95   = "95";//高进
	public static final String pids_107  = "107";//妖泪
	public static final String pids_747  = "747";//超进
	public static final String pids_748  = "748";//A丹
	public static final String pids_749  = "749";//B丹
	public static final String pids_1221 = "1221";//玉露
	public static final String pids_1222 = "1222";//天仙玉露
	
}
