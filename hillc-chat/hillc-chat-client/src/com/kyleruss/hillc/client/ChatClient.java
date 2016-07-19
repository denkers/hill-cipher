
package com.kyleruss.hillc.client;

import com.kyleruss.hillc.client.message.ChatMessageHandler;
import com.kyleruss.hillc.client.gui.ChatWindow;
import com.kyleruss.jsockchat.client.core.ClientConfig;
import com.kyleruss.jsockchat.client.core.ClientManager;
import com.kyleruss.jsockchat.client.core.SocketManager;

public class ChatClient 
{
    private void init()
    {
        Thread connThread   =   SocketManager.getInstance().initSockets(ClientConfig.MSG_SERVER_HOST, ClientConfig.MSG_SERVER_PORT);
        try { connThread.join(); }
        catch(InterruptedException e)
        {
            System.out.println("[Error] " + e.getMessage());
        }
        
        
        ClientManager.getInstance().getMessageListener().setMessageHandler(new ChatMessageHandler());
    }
    
    public void start()
    {
        init();
        ChatWindow.getInstance().display();
    }
    
    public static void main(String[] args)
    {
        ChatClient client   =   new ChatClient();
        client.start();
    }
}
