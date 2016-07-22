//-------------------------------------
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems
//-------------------------------------

package com.kyleruss.hillc.server;

import com.kyleruss.hillc.server.message.ChatMessageHandler;
import com.kyleruss.jsockchat.server.core.ServerManager;
import com.kyleruss.jsockchat.server.io.MessageServer;

public class ChatServer
{
    private ChatServer() {} 
    
    private static void initServer()
    {
        MessageServer.getInstance().setMessageHandler(new ChatMessageHandler());
    }
    
    public static void startServer()
    {
        ServerManager serverManager =   ServerManager.getInstance();
        serverManager.startServers();
    }
    
    public static void main(String[] args)
    {
        initServer();
        startServer();
    }
}
