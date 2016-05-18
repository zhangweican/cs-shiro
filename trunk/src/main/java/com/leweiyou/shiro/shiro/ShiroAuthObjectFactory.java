package com.leweiyou.shiro.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.leweiyou.tools.cfg.EnvUtil;

/**
 * 校验工程类，该类做一个判定，如果没有定义class或者class初始化失败，则使用SimpleShiroAuthObject
 * @author Zhangweican
 *
 */
public class ShiroAuthObjectFactory {
	private static Logger logger = Logger.getLogger(ShiroAuthObjectFactory.class);
	private static Object obj = null;
	
	public static synchronized ShiroAuthObject getInstance(){
		if(obj == null){
			try {
				String authClass = EnvUtil.getValue("shiro.auth.obj.class");
				if(StringUtils.isEmpty(authClass)){
					obj = new SimpleShiroAuthObject();
				}else{
					obj = Class.forName(authClass).newInstance();
				}
			} catch (Exception e) {
				logger.error("shiro.auth.obj.class Error！！", e);
				obj = new SimpleShiroAuthObject();
			}
		}
		return (ShiroAuthObject) obj;
	}
}
