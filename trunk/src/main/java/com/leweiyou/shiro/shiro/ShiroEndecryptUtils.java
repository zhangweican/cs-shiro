
package com.leweiyou.shiro.shiro; 
import java.security.Key;

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
     * 对密码进行md5加密,并返回密文和salt，包含在User对象中 
     * @param username 用户名 
     * @param password 密码 
     * @return 密文和salt 
     */ 
    public static String md5Password(String username,String password){ 
        //组合username,两次迭代，对密码进行加密 
        String password_cipherText= new Md5Hash(password,username,2).toBase64(); 
        return password_cipherText; 
    } 
    public static void main(String[] args) { 
    	String username = "test2";
        String password = "123456"; 
        String password_cipherText = ShiroEndecryptUtils.md5Password(username, password);
        System.out.println("password: " + password_cipherText);
    } 
}