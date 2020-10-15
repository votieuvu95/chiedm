package vn.vnest.session;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelManager {
	private ConcurrentHashMap<String, MyChannel> channels;

	public ChannelManager() {
		channels = new ConcurrentHashMap<String, MyChannel>();
		ChannelMonitor monitor = new ChannelMonitor(this);
		monitor.start();
	}

	public MyChannel createMyChannel(String id, Channel channel) {
		MyChannel chan = new MyChannel(channel, id);
		channels.put(chan.getId(), chan);
		return chan;
	}

	public MyChannel find(String id) {
		return channels.get(id);
	}
	public void closeChannel(String id) {
		MyChannel chan = find(id);
		if(chan != null) {
			channels.remove(chan.getId());
			chan.destroy();
		}
	}
	public void processTimeOut() {
		Collection<MyChannel> chans = channels.values();
		ArrayList<String> listTobeRemoved = new ArrayList<String>();
		for (MyChannel chan : chans) {
			if (chan.isTimeOut()) {
				listTobeRemoved.add(chan.getId());
				chan.destroy();
				
			}
		}
		for(String s : listTobeRemoved) {
			channels.remove(s);
		}
	}
}

class ChannelMonitor extends Thread {
	ChannelManager manager;

	public ChannelMonitor(ChannelManager m) {
		manager = m;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (Throwable e) {
			}
			manager.processTimeOut();
		}
	}
}