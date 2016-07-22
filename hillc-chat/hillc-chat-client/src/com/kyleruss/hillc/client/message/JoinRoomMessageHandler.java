package com.kyleruss.hillc.client.message;

import com.kyleruss.hillc.client.gui.ChatPanel;
import com.kyleruss.hillc.client.gui.MainPanel;
import com.kyleruss.jsockchat.client.message.ClientMessageHandler;
import com.kyleruss.jsockchat.commons.message.JoinRoomMsgBean;
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
        MainPanel.getInstance().outputToRoom(message, content, null);
    }
    
    
    @Override
    public void performAction(Message message) 
    {
        MainPanel.getInstance().outputToRoom(message, message.getDescription(), null);
    }
}
