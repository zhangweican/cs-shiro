package com.leweiyou.shiro.entry;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 针对用户缓存的信息
 * @author Zhangweican
 *
 */
public class SessionUser implements Serializable{
	
	private static final long serialVersionUID = 8541307043821592596L;
	
	//用户本地IP
	private String localIP = null;
	
	//用户ID
	private String userId = null;
	//用户登录账号名
	private String account = null;
	
	//用户所属于的角色集合
	private Set<String> roleIds = new HashSet<String>();
	
	//用户拥有的权限
	private Set<String> rights = new HashSet<String>();
	
	//可扩展属性
	private Map<String,Object> extAttrs = new HashMap<String, Object>();
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Set<String> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Set<String> roleIds) {
		this.roleIds = roleIds;
	}
	public Set<String> getRights() {
		return rights;
	}
	public void setRights(Set<String> rights) {
		this.rights = rights;
	}
	public void addRoleId(String roleId) {
		this.roleIds.add(roleId);
	}
	public Map<String, Object> getExtAttrs() {
		return extAttrs;
	}
	public void setExtAttrs(Map<String, Object> extAttrs) {
		this.extAttrs = extAttrs;
	}
	public String getLocalIP() {
		return localIP;
	}
	public void setLocalIP(String localIP) {
		this.localIP = localIP;
	}
	
	
}
