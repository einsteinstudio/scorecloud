package com.goldskyer.gmxx.mock.controllers;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goldskyer.core.dto.JsonData;
import com.goldskyer.core.utils.Base64Util;
import com.goldskyer.core.utils.IdentityUtil;
import com.goldskyer.core.utils.SignUtil;

@Controller
@RequestMapping("")
public class ToolController {
	private Log log = LogFactory.getLog(ToolController.class);
	
	@RequestMapping(value="/mock/tool.htm")
	public ModelAndView index(String input)
	{
		return new ModelAndView("/mock/tool");
	}
	
	
	@RequestMapping(value="/base64encode", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData base64encode(String input){
		if(StringUtils.isBlank(input))
		{
			return JsonData.failure("输入参数不能为空");
		}		
		return JsonData.success(Base64Util.encodeBase64(input));
    }
	
	@RequestMapping(value="/base64decode", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData base64decode(String input){
		if(StringUtils.isBlank(input))
		{
			return JsonData.failure("输入参数不能为空");
		}
		input = input.trim();
		return JsonData.success(Base64Util.decodeBase64(input));
    }
	
	@RequestMapping(value="/urldecode", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData urldecode(String input){
		if(StringUtils.isBlank(input))
		{
			return JsonData.failure("输入参数不能为空");
		}
		input = input.trim();
		return JsonData.success(URLDecoder.decode(input));
    }
	
	@RequestMapping(value="/urlencode", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData urlencode(String input){
		if(StringUtils.isBlank(input))
		{
			return JsonData.failure("输入参数不能为空");
		}
		return JsonData.success(URLEncoder.encode(input));
    }
	
	@RequestMapping(value="/validateIdentityNo", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData validateIdentityNo(String input){
		if(StringUtils.isBlank(input))
		{
			return JsonData.failure("输入参数不能为空");
		}
		return JsonData.success(String.valueOf(IdentityUtil.verify(input)));
    }
	
	
	@RequestMapping(value="/base64AndHex", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData base64AndHex(String input,String type){
		if(StringUtils.isBlank(input))
		{
			return JsonData.failure("输入参数不能为空");
		}	
		input = input.trim();
		try {
			if(StringUtils.equals("base64", type))
			{
				return JsonData.success(SignUtil.convertBase64ToHex(input));
			}
			if(StringUtils.equals("hex", type))
			{
				return JsonData.success(SignUtil.convertHexToBase64(input));
			}
			else
			{
				return JsonData.failure("输入参数既不是base64格式，又不是16进制格式");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JsonData.failure("转换异常");
		}
		
    }
	
	@RequestMapping(value="/generateSignPair", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData generateSignPair(String type){
		StringBuffer priKey = new StringBuffer();
		StringBuffer pubKey = new StringBuffer();
		if(StringUtils.equals("hex", type))
		{
			SignUtil.genHexRSAKeyPair(priKey, pubKey);
		}
		else if(StringUtils.equals("base64", type))
		{
			SignUtil.genBase64RSAKeyPair(priKey, pubKey);
		}
		else
		{
			return JsonData.failure("所传类型不对");
		}
		JsonData jsonData = JsonData.success();
		jsonData.data.put("pubKey", pubKey.toString());
		jsonData.data.put("priKey", priKey.toString());
		return jsonData;
    }
	
	@RequestMapping(value="/checkSign", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData checkSign(String priKey,String pubKey,String type){
		if(StringUtils.equals("hex", type))
		{
			return JsonData.success(String.valueOf(SignUtil.checkSignPair(pubKey, priKey,"hex")));

		}
		else if(StringUtils.equals("base64", type))
		{
			return JsonData.success(String.valueOf(SignUtil.checkSignPair(pubKey, priKey,"base64")));

		}
		else
		{
			return JsonData.failure("所传类型不对");
		}
    }
	
	@RequestMapping(value="/getSign", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData getSign(String src,String priKey,String type){
		if(StringUtils.equals("base64", type))
		{
			String sign = SignUtil.getBase64RsaSign(priKey, src, "UTF-8");
			if(StringUtils.isBlank(sign))
			{
				return JsonData.failure("生成签名错误");
			}
			return JsonData.success(sign);
		}
		if(StringUtils.equals("hex", type))
		{
			String sign = SignUtil.getHexRsaSign(priKey, src);
			if(StringUtils.isBlank(sign))
			{
				return JsonData.failure("生成签名错误");
			}
			return JsonData.success(sign);
		}
		return JsonData.failure("公私钥格式既不是base64又不是16进制");
    }
	
	@RequestMapping(value="/verifySign", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData verifySign(String src,String pubKey,String sign,String type){
		if(StringUtils.equals("base64", type))
		{
			return JsonData.success(String.valueOf(SignUtil.verifyBase64RsaSign(pubKey, sign, src, "UTF-8")));
		}
		if(StringUtils.equals("hex", type))
		{
			return JsonData.success(String.valueOf(SignUtil.verifyHexRsaSign(pubKey, sign, src)));
		}
		return JsonData.failure("公私钥格式既不是base64又不是16进制");
    }
	
	@RequestMapping(value="/aesDecrypt", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData aesDecrypt(String src,String pubKey,String sign,String type){
		if(StringUtils.equals("base64", type))
		{
			return JsonData.success(String.valueOf(SignUtil.verifyBase64RsaSign(pubKey, sign, src, "UTF-8")));
		}
		if(StringUtils.equals("hex", type))
		{
			return JsonData.success(String.valueOf(SignUtil.verifyHexRsaSign(pubKey, sign, src)));
		}
		return JsonData.failure("公私钥格式既不是base64又不是16进制");
    }
	
	@RequestMapping(value="/aesEncrypt", produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonData aesEncrypt(String src,String pubKey,String sign,String type){
		if(StringUtils.equals("base64", type))
		{
			return JsonData.success(String.valueOf(SignUtil.verifyBase64RsaSign(pubKey, sign, src, "UTF-8")));
		}
		if(StringUtils.equals("hex", type))
		{
			return JsonData.success(String.valueOf(SignUtil.verifyHexRsaSign(pubKey, sign, src)));
		}
		return JsonData.failure("公私钥格式既不是base64又不是16进制");
    }
	
}
