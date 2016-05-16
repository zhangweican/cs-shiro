package com.leweiyou.shiro.shiro;
/*package com.leweiyou.war.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

*//**
 * 基于Memcached的SessionDao
 * @author Zhangweican
 *
 *//*
public class MemcachedSessionDAO extends AbstractSessionDAO {

	private final static Logger log = Logger.getLogger(MemcachedSessionDAO.class.getName());

	private MemcachedClient client;

	public MemcachedSessionDAO(MemcachedClient client) {
		if (client == null) {
			throw new RuntimeException("必须存在memcached客户端实例");
		}
		this.client = client;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		try {
			client.set(sessionId.toString(), (int) session.getTimeout() / 1000, session);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = null;
		try {
			session = client.get(sessionId.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return session;
	}

	@Override
	public void delete(Session session) {
		try {
			client.delete(session.getId().toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		return Collections.emptySet();
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		try {
			client.replace(session.getId().toString(), (int) session.getTimeout() / 1000, session);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}*/