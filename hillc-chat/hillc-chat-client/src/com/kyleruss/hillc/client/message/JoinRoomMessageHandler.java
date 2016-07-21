package com.kyleruss.hillc.client.message;

import com.kyleruss.hillc.client.gui.ChatPanel;
import com.kyleruss.hillc.client.gui.MainPanel;
import com.kyleruss.jsockchat.client.message.ClientMessageHandler;
import com.kyleruss.jsockchat.commons.message.JoinRoomMsgBean;
import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;
import java.util.Date;

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
        ResponseMessage response    =   (ResponseMessage) message;
        JoinRoomMsgBean bean        =   (JoinRoomMsgBean) response.getRequestMessage().getMessageBean();
        String roomName             =   bean.getRoom();
        ChatPanel chatPanel         =   MainPanel.getInstance().getChatPane(roomName);
        chatPanel.showConversationPanel();
        chatPanel.getConvoPanel().addServerMessage(new Date(), message.getDescription());
    }
}
