package com.leweiyou.shiro.shiro;

import org.apache.shiro.SecurityUtils;

import com.leweiyou.shiro.entry.SessionUser;
import com.leweiyou.shiro.util.Commons;

/**
 * 抽出shiro需要的一些公共方法，让子类去实现，这样可以做到把shiro提到上层包里面，而不用放到war层
 * @author Zhangweican
 *
 */
public abstract class ShiroAuthObject {
	
	public static SessionUser getSessionUser(){
		return (SessionUser) SecurityUtils.getSubject().getSession().getAttribute(Commons.SessionAcount);
	}
	
	public static void setSessionUser(SessionUser sessionUser){
		SecurityUtils.getSubject().getSession().setAttribute(Commons.SessionAcount, sessionUser);
	}
	
	/**
	 * 获取需要比对的用户信息，一般从数据库获取正确的用户信息
	 * @return
	 */
	public abstract ShiroAuthObject.User getCompareUser();
	
	public class User{
		private String account;
		private String password;
		
		public User(){
			
		}
		public User(String account,String password){
			this.account = account;
			this.password = password;
		}
		
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
	}
}
