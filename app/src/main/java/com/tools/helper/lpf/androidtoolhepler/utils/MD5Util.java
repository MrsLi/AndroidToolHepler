package com.tools.helper.lpf.androidtoolhepler.utils;

import android.annotation.SuppressLint;

import java.security.MessageDigest;

/**
 * MD5加密
 * 
 */
public class MD5Util {
	/**
	 * 加密uid phone4-7+uid+phone8-11
	 * 
	 * @param phone
	 * @param uid
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public final static String codeUid(String phone, String uid) {
		String code = "";
		if (phone != null && phone.length() > 8) {
			uid = phone.substring(3, 7) + uid + phone.substring(7, 11);
		}
		code = MD5(uid).toLowerCase();
		return code;
	}

	/**
	 * md5加密
	 * 
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = s.getBytes("utf-8");
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
