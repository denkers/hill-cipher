//-------------------------------------
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems
//-------------------------------------

package com.kyleruss.hillc.client;

import com.kyleruss.hillc.client.message.ChatMessageHandler;
import com.kyleruss.hillc.client.gui.ChatWindow;
import com.kyleruss.jsockchat.client.core.ClientConfig;
import com.kyleruss.jsockchat.client.core.ClientManager;
import com.kyleruss.jsockchat.client.core.SocketManager;
import com.kyleruss.jsockchat.commons.message.MessageBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import java.io.IOException;

public class ChatClient 
{
    private static ChatClient instance;
    
    private void init()
    {
        SocketManager.getInstance().initSockets(ClientConfig.MSG_SERVER_HOST, ClientConfig.MSG_SERVER_PORT);
        ClientManager.getInstance().getMessageListener().setMessageHandler(new ChatMessageHandler());
    }
    
    public void sendMessage(String name, MessageBean bean) throws IOException
    {
        RequestMessage reqMessage       =   ClientManager.getInstance().prepareMessage(bean);
        reqMessage.setProperty("display_name", name);
        ClientManager.getInstance().sendRequest(reqMessage);
    }
    
    public void start()
    {
        init();
        ChatWindow.getInstance().display();
    }
    
    public static ChatClient getInstance()
    {
        if(instance == null) instance = new ChatClient();
        return instance;
    }
    
    public static void main(String[] args)
    {
        ChatClient client   =   getInstance();
        client.start();
    }
}
