package vn.vnest.session;

import io.netty.channel.Channel;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionManager {
	private static final Logger log = LogManager.getLogger(SessionManager.class);

	private ConcurrentHashMap<String, Session> sessions;
	private ConcurrentHashMap<String, String> userNameSessionKeys;
	SessionMonitor monitor;

	public SessionManager() {
		log.info("Create SessionManager");
		sessions = new ConcurrentHashMap<String, Session>();
		userNameSessionKeys = new ConcurrentHashMap<>();
		monitor = new SessionMonitor(this);
		monitor.start();
	}

	public boolean isValidRequest(String userName, String sessionId) {
		String p = userNameSessionKeys.get(userName);
		if(p != null) {
			return p.equalsIgnoreCase(sessionId);
		}
		return false;
	}

	public Session findSession(String sessionId, String phone,
			Channel channel,String desc) {
		if(!sessions.containsKey(sessionId)) {
			return createSession(sessionId, phone, channel, desc);
		}
		return sessions.get(sessionId);
	}

	private Session createSession(String sessionId, String phone,
			Channel channel, String desc) {

		
		Session s = new Session(channel, sessionId, phone, desc);
		sessions.put(sessionId, s);
		userNameSessionKeys.put(phone, sessionId);
		
		log.info("Session size=" + sessions.size());
		return s;
	}

//	public void timeout(String sessionId)  throws Throwable {
//		log.info("Timeout for" + sessionId);
//		if (sessions.containsKey(sessionId)) {
////			Session s = sessions.remove(sessionId);
////			s.destroy();
//			sessions.remove(sessionId);
//		}
//	}

	public void processTimeout() throws Throwable {
		Collection<Session> cs = sessions.values();
		for (Session s : cs) {
			if (s.isTimeOut()) {
				Session sess = sessions.remove(s.getSessionId());
				userNameSessionKeys.remove(sess.getUserName());
//				s.destroy();
			}
		}
	}
	private class SessionMonitor extends Thread {
		SessionManager manager;
		public SessionMonitor(SessionManager manager) {
			this.manager = manager;
		}

		@Override
		public void run() {
			log.info("Start SessionMonitor");
			while (true) {
				try {
					Thread.sleep(1000 * 60);
				} catch (Exception e) {
					log.info("Start SessionMonitor", e);
				}
				try {
					manager.processTimeout();
					
				} catch (Throwable e) {
					log.info("Start SessionMonitor", e);
				}
			}
		}
	}
}


