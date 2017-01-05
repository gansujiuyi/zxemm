package com.jiuyi.net.socketServer;

import java.util.List;

import xiaoyang.server.core.Server;
import xiaoyang.server.core.ServerFactory;

public class SocketServer {

	public static void main(String[] args)
	  {
	    List<Server> servers = ServerFactory.createServer("config.xml");
	    for (Server server : servers){
	    	server.start();
	    }
	  }
}
