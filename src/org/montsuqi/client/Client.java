package org.montsuqi.client;

import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.montsuqi.util.Logger;
import org.montsuqi.util.OptionParser;

public class Client implements Runnable {

	static final int PORT_GLTERM = 8000;
	private static final String CLIENT_VERSION = "0.0";

	private int portNumber;
	private String host;
	private String cache;
	private String user;
	private String pass;

	private String currentApplication;

	// if USE_SSL
	//private String key;
	//private String cert;
	private boolean useSSL;
	private boolean verify;
	//private String CApath;
	//private String CAfile;

	private Protocol protocol;
	private Logger logger;

	public String getCacheFileName(String name) {
		String sep = System.getProperty("path.separator");
		StringBuffer buf = new StringBuffer();
		buf.append(cache);
		buf.append(sep);
		buf.append(host);
		buf.append(sep);
		buf.append(portNumber);
		buf.append(sep);
		buf.append(name);
		return buf.toString();
	}
	
	private String[] parseOptions(String[] args) {
		OptionParser options = new OptionParser();

		options.add("port", "�ݡ����ֹ�", PORT_GLTERM);
		options.add("host", "�ۥ���̾", "localhost");
		options.add("cache", "����å���ǥ��쥯�ȥ�̾", "cache");
		options.add("user", "�桼��̾", System.getProperty("user.name"));
		options.add("pass", "�ѥ����", "");
		options.add("v1", "�ǡ��������ץ�ȥ���С������ 1 ��Ȥ�", true);
		options.add("v2", "�ǡ��������ץ�ȥ���С������ 2 ��Ȥ�", true);

		options.add("useSSL", "SSL", false);
		//options.add("key", "���ե�����̾(pem)", null);
		//options.add("cert", "������ե�����̾(pem)", null);
		//options.add("ssl", "SSL��Ȥ�", false);
		options.add("verifypeer", "���饤����Ⱦ�����θ��ڤ�Ԥ�", false);
		//options.add("CApath", "CA������ؤΥѥ�", null);
		//options.add("CAfile", "CA������ե�����", null);

		String[] files = options.parse(Client.class.getName(), args);

		portNumber = ((Integer)options.getValue("port")).intValue();
		host = (String)options.getValue("host");
		cache = (String)options.getValue("cache");
		user = (String)options.getValue("user");
		pass = (String)options.getValue("pass");

		useSSL = ((Boolean)options.getValue("useSSL")).booleanValue();

		if (useSSL) {
			//key = (String)options.getValue("key");
			//cert = (String)options.getValue("cert");
			//useSSL = ((Boolean)options.getValue("ssl")).booleanValue();
			verify = ((Boolean)options.getValue("verifypeer")).booleanValue();
			//CApath = options.getValue("CApath");
			//CAfile = options.getValue("CAfile");
		}

		return files;
	}

	public Client(String[] args) {
		logger = Logger.getLogger(Client.class);

		String[] files = parseOptions(args);

		if (files.length > 0) {
			currentApplication = files[0];
		} else {
			currentApplication = "demo";
		}
		
		Socket s = null;
		try {
			s = new Socket(host, portNumber);
			// if USE_SSL
			if (useSSL) {
				SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
				SSLSocket ssl = (SSLSocket)factory.createSocket(s, host, portNumber, true);
				/* key, cert, capath, cafile */
				ssl.setNeedClientAuth(verify);
				s = ssl;
			}
			logger.info("socket: {0}", s);
			
			protocol = new Protocol(this, s);
			logger.info(protocol.toString());
		} catch (IOException e) {
			logger.fatal(e);
			System.exit(0);
		}
		if (protocol == null) {
			logger.fatal("cannot connect");
		}
	}
		

	public void run() {
		try {
			logger.info("sendConnect({0}, {1}, {2})...", new Object[] { user, pass, currentApplication });
			protocol.sendConnect(user, pass, currentApplication);
			logger.info("done.");
			while (true) {
				logger.info("checkScreens(true)");
				protocol.checkScreens(true);
				logger.info("getScreenData()");
				protocol.getScreenData();
			}
		} catch (IOException e) {
			logger.fatal(e);
		}
	}
		
	public static void main(String[] args) {
		showBannar();
		Client client = new Client(args);
		client.run();
		client.exitSystem();
	}

	public void finalize() {
		if (protocol != null) {
			exitSystem();
		}
	}
	
	public void exitSystem() {
		try {
			protocol.sendPacketClass(PacketClass.END);
			protocol.close();
			System.exit(0);
		} catch (IOException e) {
			logger.fatal(e);
		}
	}

	protected static void showBannar() {
		System.out.println("glclient java ver " + CLIENT_VERSION);
		System.out.println("Copyright (c) 1998-1999 Masami Ogoshi <ogochan@nurs.or.jp>");
		System.out.println("              2000-2002 Masami Ogoshi & JMA.");
	}
}
