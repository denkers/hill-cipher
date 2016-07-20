package com.kyleruss.hillc.client.message;

import com.kyleruss.jsockchat.client.message.ClientMessageHandler;
import com.kyleruss.jsockchat.commons.message.Message;

public class JoinRoomMessageHandler implements ClientMessageHandler
{
    @Override
    public void witnessAction(Message message) 
    {
        System.out.println("[JoinRoom witness action] " + message.getDescription());
    }

    @Override
    public void performAction(Message message) 
    {
        System.out.println("[JoinRoom action] " + message.getDescription());
    }
}
