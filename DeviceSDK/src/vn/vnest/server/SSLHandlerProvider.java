package vn.vnest.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.handler.ssl.SslHandler;

public class SSLHandlerProvider {
	private static final Logger log = LogManager.getLogger(SSLHandlerProvider.class);

	private static final String PROTOCOL = "TLS";
	private static final String ALGORITHM_SUN_X509 = "SunX509";
	private static final String ALGORITHM = "ssl.KeyManagerFactory.algorithm";
	private static final String KEYSTORE = "/home/pokecall/webservice/ssl/mysslstore.jks";
	private static final String KEYSTORE_TYPE = "JKS";
	private static final String KEYSTORE_PASSWORD = "hdkhien";
	private static final String CERT_PASSWORD = "hdkhien";
	private static SSLContext serverSSLContext = null;

	public static SslHandler getSSLHandler() {
		SSLEngine sslEngine = null;
		if (serverSSLContext == null) {
			log.error("Server SSL context is null");
			System.exit(-1);
		} else {
			sslEngine = serverSSLContext.createSSLEngine();
			sslEngine.setUseClientMode(false);
			sslEngine.setNeedClientAuth(false);

		}
		return new SslHandler(sslEngine);
	}

	public static SSLContext getServerSSLContext() {
		return serverSSLContext;
	}

	public static void initSSLContext() {

		log.info("Initiating SSL context");
		String algorithm = Security.getProperty(ALGORITHM);
		if (algorithm == null) {
			algorithm = ALGORITHM_SUN_X509;
		}
		KeyStore ks = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(
					SSLHandlerProvider.class.getClassLoader().getResource(KEYSTORE).getFile());
			ks = KeyStore.getInstance(KEYSTORE_TYPE);
			ks.load(inputStream, KEYSTORE_PASSWORD.toCharArray());
		} catch (IOException e) {
			log.info("Cannot load the keystore file", e);
		} catch (CertificateException e) {
			log.info("Cannot get the certificate", e);
		} catch (NoSuchAlgorithmException e) {
			log.info("Somthing wrong with the SSL algorithm", e);
		} catch (KeyStoreException e) {
			log.info("Cannot initialize keystore", e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				log.info("Cannot close keystore file stream ", e);
			}
		}
		try {

			// Set up key manager factory to use our key store
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
			kmf.init(ks, CERT_PASSWORD.toCharArray());
			KeyManager[] keyManagers = kmf.getKeyManagers();
			TrustManager[] trustManagers = null;

			serverSSLContext = SSLContext.getInstance(PROTOCOL);
			serverSSLContext.init(keyManagers, trustManagers, null);

		} catch (Exception e) {
			log.info("Failed to initialize the server-side SSLContext", e);
		}

	}
}
