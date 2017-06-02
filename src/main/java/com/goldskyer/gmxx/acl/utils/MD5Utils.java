/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: gmxx
 * 
 * MD5Util.java File Created at 上午1:18:31
 * 
 * 
 * Copyright 2014 Taobao.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Taobao Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Taobao.com.
 */
package com.goldskyer.gmxx.acl.utils;

import java.security.MessageDigest;

/**
 * MD5工具
 * 
 * @author zhang.li
 * @version 1.0
 * @since 2016-3-20
 */
public class MD5Utils {
	private final static char[] hexDigits = 
		{ '0', '1', '2', '3', '4', '5',  '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' }; 
    
	private static String bytesToHex(byte[] bytes) {  
		StringBuffer sb = new StringBuffer();  
		int t;  
		for (int i = 0; i < 16; i++) {  
		    t = bytes[i];  
		    if (t < 0)  
		        t += 256;  
		    sb.append(hexDigits[(t >>> 4)]);  
		    sb.append(hexDigits[(t % 16)]);  
		}  
		return sb.toString();  
	} 
	
    private static String code(String input, int bit) {  
        try {  
            MessageDigest md = MessageDigest.getInstance(System.getProperty(  
                    "MD5.algorithm", "MD5"));  
            if (bit == 16)  
                return bytesToHex(md.digest(input.getBytes("utf-8")))  
                        .substring(8, 24);  
            return bytesToHex(md.digest(input.getBytes("utf-8")));  
        } catch (Exception e) {  
           return null;
        }  
    }  

	public static String md5Encode16(String input) {
	    return code(input, 16);  
	}  
	
	public static String md5Encode32(String input) {
	    return code(input, 32);  
	}
}