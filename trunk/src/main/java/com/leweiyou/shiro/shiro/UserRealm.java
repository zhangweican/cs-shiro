package com.leweiyou.shiro.shiro;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.leweiyou.shiro.entry.SessionUser;

/**
 * 定义用户校验的Realm
 * 
 * @author Zhangweican
 *
 */
public class UserRealm extends AuthorizingRealm {

	/** 
     * 设定Password校验. 
     */  
    @PostConstruct  
    public void initCredentialsMatcher() {  
    	//该句作用是重写shiro的密码验证，让shiro用我自己的验证  
        setCredentialsMatcher(new CustomCredentialsMatcher());  
    } 
	
	/**
	 * 授权操作
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// String username = (String) getAvailablePrincipal(principals);
		//String username = (String) principals.getPrimaryPrincipal();

		SessionUser user = ShiroAuthObjectFactory.getInstance().getSessionUser();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		authorizationInfo.addRoles(user.getRoleIds());
		authorizationInfo.addStringPermissions(user.getRights());

		return authorizationInfo;
	}

	/**
	 * 身份验证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String account = (String) token.getPrincipal();
		ShiroAuthObject.User user = ShiroAuthObjectFactory.getInstance().getCompareUser(account);
		if (user == null) {
			// 木有找到用户
			throw new AuthenticationException("账号或密码错误");
		}
		
		
		/*
		 * if(Boolean.TRUE.equals(user.getLocked())) { throw new
		 * LockedAccountException(); //帐号锁定 }
		 */

		/**
		 * 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
		 */
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), getName());

		return info;
	}
	
    /** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    } 
}
