package com.leweiyou.shiro.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import com.leweiyou.shiro.entry.SessionUser;


/**
 * 针对Request获取的path跟session中的权限的地址做匹配
 * 
 * @author Zhangweican
 *
 */
public class PermissionsAuthorizationFilter extends AuthorizationFilter {  
  
    public boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws IOException {  
    	
    	HttpServletRequest request = (HttpServletRequest) req;
    	
    	String path = request.getServletPath();
		//shiro管理的session
		SessionUser user = ShiroAuthObjectFactory.getInstance().getSessionUser();
		if(user != null){
			path = delRubbish(path);
			for(String right : user.getRights()){
				if(path.matches(delRubbish(right))){
					return true;
				}
			}
		}else{
			//登陆过滤
			//response.sendRedirect(request.getContextPath());
		}
		return false;
    }
    
    /**
     * 替换反斜杠，并且删除前后无用的反斜杠
     */
    private String delRubbish(String str){
    	str = str.replaceAll("\\\\", "/");
    	if(str.endsWith("/")){
			str = str.substring(0, str.length() - 1);
		}
		if(str.startsWith("/")){
			str = str.substring(1, str.length());
		}
		return str;
    }
} 