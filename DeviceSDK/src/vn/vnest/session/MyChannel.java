package vn.vnest.session;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class MyChannel {
	private static final Logger log = LogManager.getLogger(MyChannel.class);
	private String id;
	private Channel channel;
	private Session session;
	private long createdTime;
	public MyChannel(Channel chan, String i) {
		id = i;
		channel = chan;
		createdTime = System.currentTimeMillis();
	}
	public boolean isTimeOut() {
		long now = System.currentTimeMillis();
		long timeout = 1000*60*2;
		return now-createdTime>timeout;
	}
	public String getId() {
		return id;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Channel getChannel() {
		return channel;
	}

	public Session getSession() {
		return session;
	}

	public void destroy() {
		try {
			if(session != null) {
				ChannelFuture future = session.flush();
				if(future != null) {
					boolean isDone = future.addListener(ChannelFutureListener.CLOSE)
							.awaitUninterruptibly(1000 * 60 * 2);
					if (isDone) {
						log.info("Close is done!");
						channel.close();
						return;
					}
				}
			}
		} catch (Throwable e) {
			log.info("ERROR", e);
		}
		if (channel != null && (channel.isOpen() || channel.isActive())) {
			log.info("Close is done!");
			channel.close();
		}
	}
}
