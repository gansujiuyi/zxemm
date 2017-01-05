package com.jiuyi.net.socketServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {

	public static void main(String[] args) {
		// int i = 0;
		while (true) {
			// i++;

			Socket client = null;
			try {
				client = new Socket(InetAddress.getLocalHost(), 13145);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}

			OutputStream out = null;
			InputStream in = null;
			try {
				out = client.getOutputStream();
				// 定义发送的字符串
				String sendString = "<?xml version='1.0' encoding='UTF-8'?><EBANK><HEAD><TranCode>000001</TranCode></HEAD><BODY><IdType>01</IdType><IdNo>620102198712052125</IdNo><RealName>马小勇</RealName></BODY></EBANK>";
				out.write(sendString.getBytes("utf-8"));
				in = client.getInputStream();
				byte[] buf = new byte[512];
				int len;
				while ((len = in.read(buf)) != -1) {
					String rspXml = new String(buf, 0, len, "utf-8");
					System.out.println("响应报文："+rspXml);
				}
			} catch (IOException e) {
				e.printStackTrace();
				try {
					out.close();
					client.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} finally {
				try {
					out.close();
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			// System.out.println(i);
		}
	}
}