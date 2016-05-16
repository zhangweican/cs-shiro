package com.leweiyou.shiro.shiro;

import com.leweiyou.tools.cfg.EnvUtil;


public class ShiroAuthObjectFactory {
	
	private static Object obj = null;
	
	public static synchronized ShiroAuthObject getInstance(){
		if(obj == null){
			try {
				obj = Class.forName(EnvUtil.getValue("shiro.auth.obj.class")).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (ShiroAuthObject) obj;
	}
}
