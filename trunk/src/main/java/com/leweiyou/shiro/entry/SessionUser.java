package com.leweiyou.shiro.entry;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 针对用户缓存的信息
 * @author Zhangweican
 *
 */
public class SessionUser implements Serializable{
	
	private static final long serialVersionUID = 8541307043821592596L;
	private String userId = null;
	private String account = null;
	private Set<String> roleIds = new HashSet<String>();
	private Set<String> rights = new HashSet<String>();
	private String tree = null;
	
	
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
	public String getTree() {
		return tree;
	}
	public void addRoleId(String roleId) {
		this.roleIds.add(roleId);
	}
	
	
}
