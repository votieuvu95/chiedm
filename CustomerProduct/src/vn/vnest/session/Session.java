package vn.vnest.session;
import static io.netty.buffer.Unpooled.copiedBuffer;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;


public class Session {
	private Channel channel;
	private String sessionId;
	private String userName;
	private String desc;
	private long createdTime;
	private boolean isWriteResponse = false;
	private static final Logger log = LogManager.getLogger(Session.class);
	
	public void setWriteResponse(boolean isWriteResponse) {
		this.isWriteResponse = isWriteResponse;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public void setCreatedTime() {
		this.createdTime = System.currentTimeMillis();
	}
	public Session(Channel channel, String sessionId,
			String userName, String desc) {
		this.channel = channel;
		this.sessionId = sessionId;
		this.userName = userName;
		this.createdTime = System.currentTimeMillis();
		this.desc = desc;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public Channel getChannel() {
		return channel;
	}

	public String getSessionId() {
		return sessionId;
	}
	public String getUserName() {
		return userName;
	}
	public boolean isTimeOut(){
		long now = System.currentTimeMillis();
		long timeout = 1000*60*60*24l; // 24h
		return now-createdTime>timeout;
	}
	public ChannelFuture flush()  throws Throwable {
		try {
			log.info("flush session=" + sessionId +" for " + userName);
			if(channel != null) {
				return writeResponse();
			} else {
				log.info("Channel of session=" + sessionId +" is null");
			}
		} catch (Exception e) {
			log.info("ERROR",e);
		}
		return null;
	}
	private ChannelFuture writeResponse()  throws Throwable {
		// Convert the response content to a ChannelBuffer.
		if (isWriteResponse) {
			channel.close();
			return null;
		}
		String data = new String("SessionClose");
		ByteBuf buf = copiedBuffer(data, CharsetUtil.UTF_8);
		

		FullHttpResponse response;
			response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
					HttpResponseStatus.OK, buf);

		response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");

		response.headers().set(CONTENT_LENGTH, buf.readableBytes());
		log.info("Write to client:" + response.status().toString());
		return channel.writeAndFlush(response);
//		ChannelFuture future = channel.writeAndFlush(response);
//		boolean isDone = future.addListener(ChannelFutureListener.CLOSE).awaitUninterruptibly(1000*60*2);
//		if (isDone) {
//			log.info("Close is done!");
//			channel.close();
//		}
	}
}
