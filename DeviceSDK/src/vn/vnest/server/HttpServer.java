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


import vn.vnest.main.Constant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslHandler;

/**
 * A HTTP server showing how to use the HTTP multipart package for file uploads
 * and decoding post data.
 */
public final class HttpServer {
	private static final Logger log = LogManager.getLogger(HttpServer.class);

	public static void start() throws Exception {

		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);

			b.option(ChannelOption.SO_REUSEADDR, true);
			b.option(ChannelOption.TCP_NODELAY, true);

			b.channel(NioServerSocketChannel.class);
			b.handler(new LoggingHandler(LogLevel.INFO));
			SslHandler sslHandler = null;
//			if (Constant.useSSL) {
//				SSLHandlerProvider.initSSLContext();
//				sslHandler = SSLHandlerProvider.getSSLHandler();
//			}
			b.childHandler(new HttpServerInitializer(sslHandler));

			Channel ch = b.bind(Constant.getPort()).sync().channel();
			ch.closeFuture().sync();
		} catch (Exception e) {
			log.info("ERROR", e);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
