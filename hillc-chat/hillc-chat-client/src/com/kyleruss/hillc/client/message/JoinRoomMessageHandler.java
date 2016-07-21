package com.kyleruss.hillc.client.message;

import com.kyleruss.hillc.client.gui.ChatPanel;
import com.kyleruss.hillc.client.gui.MainPanel;
import com.kyleruss.jsockchat.client.message.ClientMessageHandler;
import com.kyleruss.jsockchat.commons.message.JoinRoomMsgBean;
import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import java.util.Date;

public class JoinRoomMessageHandler implements ClientMessageHandler
{
    @Override
    public void witnessAction(Message message) 
    {
        RequestMessage request      =   ((ResponseMessage) message).getRequestMessage();
        String displayName          =   (String) request.getProperty("display_name");
        String content              =   displayName + " has connected";
        showJoinMessage(message, content);
    }
    
    private void showJoinMessage(Message message, String content)
    {
        ResponseMessage response    =   (ResponseMessage) message;
        JoinRoomMsgBean bean        =   (JoinRoomMsgBean) response.getRequestMessage().getMessageBean();
        String roomName             =   bean.getRoom();
        ChatPanel chatPanel         =   MainPanel.getInstance().getChatPane(roomName);
        chatPanel.showConversationPanel();
        chatPanel.getConvoPanel().addServerMessage(new Date(), content);
    }
    
    @Override
    public void performAction(Message message) 
    {
        showJoinMessage(message, message.getDescription());
    }
}
