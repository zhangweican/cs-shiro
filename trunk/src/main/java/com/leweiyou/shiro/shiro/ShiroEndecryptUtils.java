
package com.leweiyou.shiro.shiro; 
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.Md5Hash;

/** 
* User： cutter.li 
* Date： 2014/6/27 0027 
* Time： 16:49 
* 备注： shiro进行加密解密的工具类封装 
*/ 
public final class ShiroEndecryptUtils { 
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    
	/** 
     * base64进制加密 
     * 
     * @param password 
     * @return 
     */ 
    public static String encrytBase64(String password) { 
        byte[] bytes = password.getBytes(); 
        return Base64.encodeToString(bytes); 
    } 
    /** 
     * base64进制解密 
     * @param cipherText 
     * @return 
     */ 
    public static String decryptBase64(String cipherText) { 
        return Base64.decodeToString(cipherText); 
    } 
    /** 
     * 16进制加密 
     * 
     * @param password 
     * @return 
     */ 
    public static String encrytHex(String password) { 
        byte[] bytes = password.getBytes(); 
        return Hex.encodeToString(bytes); 
    } 
    /** 
     * 16进制解密 
     * @param cipherText 
     * @return 
     */ 
    public static String decryptHex(String cipherText) { 
        return new String(Hex.decode(cipherText)); 
    } 
    public static String generateKey() 
    { 
        AesCipherService aesCipherService=new AesCipherService(); 
        Key key=aesCipherService.generateNewKey(); 
        return Base64.encodeToString(key.getEncoded()); 
    } 
	
	/**
	 * 将字节数组转换为16进制的字符串
	 * @param byteArray 字节数组
	 * @return 16进制的字符串
	 */
	private static String byteArrayToHexString(byte[] byteArray){
		StringBuffer sb = new StringBuffer();
		for(byte byt:byteArray){
			sb.append(byteToHexString(byt));
		}
		return sb.toString();
	}
	/**
	 * 将字节转换为16进制字符串
	 * @param byt 字节
	 * @return 16进制字符串
	 */
	private static String byteToHexString(byte byt) {
		int n = byt;
		if (n < 0)
			n = 256 + n;
		return hexDigits[n/16] + hexDigits[n%16];
	}
	/**
	 * 将摘要信息转换为相应的编码
	 * @param code 编码类型
	 * @param message 摘要信息
	 * @return 相应的编码字符串
	 */
	private static String Encode(String code,String message){
		MessageDigest md;
		String encode = null;
		try {
			md = MessageDigest.getInstance(code);
			encode = byteArrayToHexString(md.digest(message.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encode;
	}
	/**
	 * 将摘要信息转换成MD5编码
	 * @param message 摘要信息
	 * @return MD5编码之后的字符串
	 */
	public static String md5Encode(String message){
		return Encode("MD5",message);
	}
	/**
	 * 将摘要信息转换成SHA编码
	 * @param message 摘要信息
	 * @return SHA编码之后的字符串
	 */
	public static String shaEncode(String message){
		return Encode("SHA",message);
	}
	/**
	 * 将摘要信息转换成SHA-256编码
	 * @param message 摘要信息
	 * @return SHA-256编码之后的字符串
	 */
	public static String sha256Encode(String message){
		return Encode("SHA-256",message);
	}
	/**
	 * 将摘要信息转换成SHA-512编码
	 * @param message 摘要信息
	 * @return SHA-512编码之后的字符串
	 */
	public static String sha512Encode(String message){
		return Encode("SHA-512",message);
	}
	public static void main(String[] args) {
		//对MD5进行验证
		System.out.println("----MD5----");
		System.out.println(ShiroEndecryptUtils.validate("d41d8cd98f00b204e9800998ecf8427e", ShiroEndecryptUtils.md5Encode("")));
		System.out.println(ShiroEndecryptUtils.validate("0cc175b9c0f1b6a831c399e269772661", ShiroEndecryptUtils.md5Encode("a")));
		System.out.println(ShiroEndecryptUtils.validate("900150983cd24fb0d6963f7d28e17f72", ShiroEndecryptUtils.md5Encode("abc")));
		System.out.println(ShiroEndecryptUtils.validate("f96b697d7cb7938d525a2f31aaf161d0", ShiroEndecryptUtils.md5Encode("message digest")));
		System.out.println(ShiroEndecryptUtils.validate("c3fcd3d76192e4007dfb496cca67e13b", ShiroEndecryptUtils.md5Encode("abcdefghijklmnopqrstuvwxyz")));
		System.out.println(ShiroEndecryptUtils.validate("d174ab98d277d9f5a5611c2c9f419d9f", ShiroEndecryptUtils.md5Encode("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")));
		System.out.println(ShiroEndecryptUtils.validate("57edf4a22be3c955ac49da2e2107b67a", ShiroEndecryptUtils.md5Encode("12345678901234567890123456789012345678901234567890123456789012345678901234567890")));
		
		//对SHA进行验证
		System.out.println("----SHA----");
		System.out.println(ShiroEndecryptUtils.validate("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12", ShiroEndecryptUtils.shaEncode("The quick brown fox jumps over the lazy dog")));
		System.out.println(ShiroEndecryptUtils.validate("de9f2c7fd25e1b3afad3e85a0bd17d9b100db4b3", ShiroEndecryptUtils.shaEncode("The quick brown fox jumps over the lazy cog")));
		System.out.println(ShiroEndecryptUtils.validate("da39a3ee5e6b4b0d3255bfef95601890afd80709", ShiroEndecryptUtils.shaEncode("")));
		System.out.println("-----------");
		
		//对于SHA-256，SHA-512请自行验证
		
		//下面显示MD5 SHA SHA-256 SHA-512所生成的长度
		
		System.out.println("--MD5--:"+ShiroEndecryptUtils.md5Encode("123456"));
		System.out.println("--SHA--:"+ShiroEndecryptUtils.shaEncode("123456"));
		System.out.println("SHA-256:"+ShiroEndecryptUtils.sha256Encode("123456"));
		System.out.println("SHA-512:"+ShiroEndecryptUtils.sha512Encode("123456"));
	}
	private static String validate(String code,String tag){
		if(code.equals(tag))
			return "通过验证";
		return "未通过验证";
	} 
}