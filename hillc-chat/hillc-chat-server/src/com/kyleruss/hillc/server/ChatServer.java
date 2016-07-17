
package com.kyleruss.hillc.server;

import com.kyleruss.jsockchat.server.core.ServerManager;

public class ChatServer
{
    private ChatServer() {} 
    
    private static void initServer()
    {
        
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
