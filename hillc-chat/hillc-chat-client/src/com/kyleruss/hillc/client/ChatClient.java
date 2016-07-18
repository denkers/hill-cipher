
package com.kyleruss.hillc.client;

import com.kyleruss.hillc.client.gui.ChatWindow;

public class ChatClient 
{
    private void init()
    {
        
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
