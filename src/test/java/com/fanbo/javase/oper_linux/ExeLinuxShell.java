package com.fanbo.javase.oper_linux;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ExeLinuxShell {

	private Connection conn;
	private String ipAddr;		//IP地址
	private String charset = Charset.defaultCharset().toString();	//	编码
	private String userName;	//用户名
	private String password;	//密码
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("执行shell脚本中...");
		
		ExeLinuxShell tool = new ExeLinuxShell("192.168.1.202", "root", "genekangadmin", "utf-8");

		String result = tool.exec("/home/genekang-db/genekang_mysql_db/hd_blood_pressure_data/hd_blood_pressure_data.sh");		
		System.out.print(result);
		
		System.out.println("shell脚本执行结束...");

	}
	
	public ExeLinuxShell(String ipAddr, String userName, String password,
			String charset) {
		this.ipAddr = ipAddr;
		this.userName = userName;
		this.password = password;
		if (charset != null) {
			this.charset = charset;
		}
	}

	public boolean login() throws IOException {
		conn = new Connection(ipAddr);
		conn.connect(); // 连接
		return conn.authenticateWithPassword(userName, password); // 认证
	}

	public String exec(String cmds) {
		InputStream in = null;
		String result = "";
		try {
			if (this.login()) {
				Session session = conn.openSession(); // 打开一个会话
				session.execCommand(cmds);

				in = session.getStdout();
				result = this.processStdout(in, this.charset);
				session.close();
				conn.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public String processStdout(InputStream in, String charset) {

		byte[] buf = new byte[1024];
		StringBuffer sb = new StringBuffer();
		try {
			while (in.read(buf) != -1) {
				sb.append(new String(buf, charset));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}


}
