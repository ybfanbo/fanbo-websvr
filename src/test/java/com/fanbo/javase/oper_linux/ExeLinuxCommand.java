package com.fanbo.javase.oper_linux;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExeLinuxCommand {

	public static void main(String args[]) {
		//String exec = exec("192.168.1.201", "root", "genekangadmin", 22, "date;");
		//String result = exec("120.26.59.226","root","CloudGK006",22,"python /mnt/gaic_file/moniter.py -i job-0000000055B9DEA20000E4F000000133");
		//String result = exec("120.26.59.226","root","CloudGK006",22,"python /mnt/gaic_file/show_all_task.py");
		//System.out.println(result);

		
		//String command = "perl /mnt/GAIC/Report/generate_info.pl   -outdir  /mnt/GAIC/import/  -sample  WGC045693D_combined";
		String command = "cal";
		String result = exec("112.74.206.50","root","CloudGK006",22,command);
		System.out.println(result);	
	}

	public static String exec(String host, String user, String psw, int port, String command) {
		String result = "";
		Session session = null;
		ChannelExec openChannel = null;
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(user, host, port);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(psw);
			session.connect();
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand(command);
			int exitStatus = openChannel.getExitStatus();
			System.out.println(exitStatus);
			openChannel.connect();
			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String buf = null;
			while ((buf = reader.readLine()) != null) {
				result += new String(buf.getBytes("UTF-8"), "UTF-8");
			}
		} catch (JSchException | IOException e) {
			result += e.getMessage();
		} finally {
			if (openChannel != null && !openChannel.isClosed()) {
				openChannel.disconnect();
			}
			if (session != null && session.isConnected()) {
				session.disconnect();
			}
		}
		return result;
	}

}
