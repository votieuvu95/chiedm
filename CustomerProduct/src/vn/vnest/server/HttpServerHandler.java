/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package vn.vnest.server;

import static io.netty.buffer.Unpooled.copiedBuffer;
import static io.netty.handler.codec.http. HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http. HttpHeaderNames.CONTENT_TYPE;

import java.net.InetSocketAddress;
//import java.util.List;
//import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
//import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;
import vn.vnest.httphandler.TopicManager;
import vn.vnest.main.Constant;
import vn.vnest.main.HTTPServer;
import vn.vnest.session.MyChannel;
import vn.vnest.session.Session;

public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

	private static final Logger log = LogManager.getLogger(HttpServerHandler.class);

	private String sessionId = "";
	private Session session;
	private String contentStr = "";
	private MyChannel myChan;
	private String topic = "/";
	private String dataRes;
	private HttpMethod method = null;
	private HttpHeaders headers = null;
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
	}

	private void closeChannel() {
		log.info("Close channel of " + sessionId);
		if (myChan != null) {
			HTTPServer.getChannelManager().closeChannel(myChan.getId());
			myChan = null;
		}
	}

	private boolean checkAuthentication(DefaultHttpRequest request) throws Exception {
		// String value = request.headers().get(
		// io.netty.handler.codec.http.HttpHeaders.Names.AUTHORIZATION);
		// log.info("AUTHORIZATION:" + value);
		return true;
	}
	private boolean decodeData(String data, String ip, Channel channel) throws Exception {
		if (data != null) {
			try {
				log.info("Request info: value=" + data + ", host=" + ip + ", topic=" + topic);
				dataRes = new TopicManager().process(headers,method, topic, data);
				writeResponse(channel, false);

			} catch (Throwable e) {
				log.info("ERROR", e);
				dataRes = "ERROR:" + e.getMessage();
				writeResponse(channel, true);
			}
		} else {
			dataRes = "Invalid Data!";
			writeResponse(channel, true);
		}
		return true;
	}

	boolean checkAuthentication = false;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String ip = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress();
		int port = ((InetSocketAddress) ctx.channel().remoteAddress()).getPort();

		String id = ip + ":" + port + "-" + Constant.makeUniqueId();
		myChan = HTTPServer.getChannelManager().createMyChannel(id, ctx.channel());
		super.channelActive(ctx);
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		try {
			String ip = ((InetSocketAddress) ctx.channel().remoteAddress()).getAddress().getHostAddress();
//			InetAddress IP=InetAddress.getLocalHost();

			if (msg instanceof DefaultHttpRequest) {
				DefaultHttpRequest d = (DefaultHttpRequest) msg;
				headers = d.headers();
				topic = d.uri();
				checkAuthentication = checkAuthentication(d);
				method = d.method();
			} else if (msg instanceof HttpContent) {
				HttpContent cont = (HttpContent) msg;
				ByteBuf content = cont.content();
				contentStr += content.toString(CharsetUtil.UTF_8);
			}
			if (msg instanceof LastHttpContent) {
				// log.info("ContentStr=" + contentStr);
				if (!checkAuthentication) {
					dataRes = "Wrong authentication!";
					writeResponse(ctx.channel(), true);
				} else {
					decodeData(contentStr, ip, ctx.channel());
				}

			}
		} catch (Throwable e) {
			log.info("ERROR", e);
		}
	}

	private void writeResponse(Channel channel,
			boolean isPlainText/* , boolean isCloseChannel */) throws Exception {
		if (dataRes != null) {
			ByteBuf buf = copiedBuffer(dataRes, CharsetUtil.UTF_8);
			log.info("Write:" + dataRes);
			if (session != null) {
				session.setWriteResponse(true);
			}
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
			HttpHeaders headers = new DefaultHttpHeaders();
			headers.set(CONTENT_TYPE, "");
			if (isPlainText) {
				response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
			} else {
				response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
			}
			response.headers().set(CONTENT_LENGTH, buf.readableBytes());
			log.info("Write to client:" + response.status().toString());
			channel.writeAndFlush(response);
		}
		closeChannel();

	}

	// private void writeResponse(Channel channel) throws Exception {
	// closeChannel();
	// }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.info("ERROR", cause);
		ctx.channel().close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("Channel inactive!");
		if (myChan != null) {
			closeChannel();
		}
		super.channelInactive(ctx);
	}
}
