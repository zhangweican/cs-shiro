package com.leweiyou.shiro.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Shiro环境变量的获取
 * @author Zhangweican
 *
 */
public class CXT {
	/**
	 * 获取Session
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();  
	}
	
}
