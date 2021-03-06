package com.leweiyou.shiro.shiro;

/**
 * 定义一个简单的shiro校验类
 * @author Zhangweican
 *
 */
public class SimpleShiroAuthObject extends ShiroAuthObject {

	@Override
	public ShiroAuthObject.User getCompareUser(String account) {
		return new User("admin",ShiroEndecryptUtils.md5Encode("123456"));
	}

	@Override
	public String encryptMatcherPassword(String account, String password) {
		return ShiroEndecryptUtils.md5Encode(password);
	}

}
