package vn.vnest.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslHandler;
import vn.vnest.main.Contanst;

public class HttpServer {
	
	private static final Logger log = LogManager.getLogger(HttpServer.class);
	
	public static void start() throws Exception {
		
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
		ServerBootstrap server = new ServerBootstrap();
		server.group(bossGroup, workerGroup);
		server.channel(NioServerSocketChannel.class);
		SslHandler sslHandler = null;
		
		server.childHandler(new HttpServerInitializer(sslHandler));
		
		Channel ch = server.bind(Contanst.getPort()).sync().channel();
		ch.closeFuture().sync();
		}
		catch (Exception e) {
			log.info("ERROR" +  e.getMessage());
		}
	}

}
