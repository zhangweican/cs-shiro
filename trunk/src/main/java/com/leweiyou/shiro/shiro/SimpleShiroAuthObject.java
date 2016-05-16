package com.leweiyou.shiro.shiro;

public class SimpleShiroAuthObject extends ShiroAuthObject {

	@Override
	public User getCompareUser() {
		return new User("admin",ShiroEndecryptUtils.md5Password("admin", "123456"));
	}

}
