package com.fanbo.javase.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class MyLinuxShellUtil {
	
	public static String execShell(String host, String user, String psw, String shellScript) {
		InputStream in = null;
		String result = "";
		try {
			Connection conn = new Connection(host);
			conn.connect();// 链接
			boolean login = conn.authenticateWithPassword(user, psw); // 认证

			if (login) {
				Session openSession = conn.openSession();
				Session session = openSession; // 打开一个会话
				session.execCommand(shellScript);

				in = session.getStdout();

				byte[] buf = new byte[1024];
				StringBuffer sb = new StringBuffer();
				try {
					while (in.read(buf) != -1) {
						sb.append(new String(buf, Charset.defaultCharset()
								.toString()));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				result = sb.toString();

				session.close();
				conn.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result;
	}
}
