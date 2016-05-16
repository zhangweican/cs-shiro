package com.leweiyou.shiro.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import com.leweiyou.tools.cfg.EnvUtil;

/**
 * 重写shiroFactoryBean 
 * <br>1.追加了一个判定，是否开启校验
 * <br>2.通过属性filterChainDefinitions 配置的的过滤路径不再生效
 * @author Zhangweican
 *
 */
public class ExtShiroFilterFactoryBean extends ShiroFilterFactoryBean {

	@Override
	public void setFilterChainDefinitions(String definitions) {
		String isValid = EnvUtil.getValue("shiro.is.open.valid");
		if(isValid != null && "false".equals(isValid.trim().toLowerCase())){
			//不做任何处理，相当于没有配置校验规则
			super.setFilterChainDefinitions("/**=anon");
			return;
		}
		
		//从env 环境中获取
		String filterChainDefinitions = EnvUtil.getValue("shiro.filter.chain.definitions",";");
		if(filterChainDefinitions != null){
			definitions += "\n" + filterChainDefinitions.replaceAll(";", "\n");
		}
		super.setFilterChainDefinitions(definitions);
	}
	
}
