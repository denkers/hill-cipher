//-------------------------------------
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems
//-------------------------------------

package com.kyleruss.hillc.client.message;

import com.kyleruss.hillc.client.ChatMessage;
import com.kyleruss.hillc.client.gui.MainPanel;
import com.kyleruss.jsockchat.client.message.ClientMessageHandler;
import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class JoinRoomMessageHandler implements ClientMessageHandler
{
    @Override
    public void witnessAction(Message message) 
    {
        RequestMessage request      =   ((ResponseMessage) message).getRequestMessage();
        String displayName          =   (String) request.getProperty("display_name");
        String content              =   displayName + " has connected";
        ChatMessage chatMessage     =   new ChatMessage(content);
        MainPanel.getInstance().outputToRoom(message, chatMessage);
    }
    
    
    @Override
    public void performAction(Message message) 
    {
        MainPanel.getInstance().outputToRoom(message, new ChatMessage(message.getDescription()));
    }
}
