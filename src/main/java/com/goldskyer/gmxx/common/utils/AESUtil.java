package com.goldskyer.gmxx.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*******************************************************************************
 * AES加解密算法
 * 
 * @author arix04
 * 
 */

public class AESUtil
{
	public static final String KEY = "asdfghrdfqwertgh";
	private static Log log = LogFactory.getLog(AESUtil.class);

	// 加密
	public static String Encrypt(String sSrc, String sKey)
	{
		try
		{

		if (sKey == null)
		{
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16)
		{
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());

		return Base64.encodeBase64String(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// 解密
	public static String Decrypt(String sSrc, String sKey)
	{
		try
		{
			// 判断Key是否正确
			if (sKey == null)
			{
				log.error("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16)
			{
				log.error("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decodeBase64(sSrc);//先用base64解密
			try
			{
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			}
			catch (Exception e)
			{
				log.error("解密异常，sKey：" + sKey, e);
				return null;
			}
		}
		catch (Exception ex)
		{
			log.error("解密异常，sSrc=" + sSrc + ",sKey=" + sKey, ex);
			return null;
		}
	}

	public static void main(String[] args) throws Exception
	{
		String key = "asdfghrdfqwertgh";
		String en = Encrypt("123", key);
		System.out.println(en);
		String source = Decrypt(en, key);
		System.out.println(source);
	}

}

