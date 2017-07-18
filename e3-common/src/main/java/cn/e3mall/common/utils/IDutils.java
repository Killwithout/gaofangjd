package cn.e3mall.common.utils;

import java.util.Random;

/**
* @author song:
* @version ：2017年6月27日 下午4:36:37
*/
public class IDutils {
	/*
	 * 图片名
	 * */
	public static String genImgeName(){
		
		long millis = System.currentTimeMillis();
		Random random = new Random();
		int end3 = random.nextInt(999);
		String str=millis+String.format("O03d", end3);
		return str;
	}
	/*
	 * 商品id
	 * */
	public static long genItemId(){
		
		long millis = System.currentTimeMillis();
		Random random = new Random();
		int end2 = random.nextInt(99);
		String str=millis+String.format("O2d", end2);
		long id =new Long(str);
		return id;
	}
	

}
