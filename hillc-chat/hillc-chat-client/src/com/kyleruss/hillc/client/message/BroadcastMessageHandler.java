//-------------------------------------
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems
//-------------------------------------

package com.kyleruss.hillc.client.message;

import com.kyleruss.hillc.base.CStructure;
import com.kyleruss.hillc.base.HillCipher;
import com.kyleruss.hillc.client.ChatMessage;
import com.kyleruss.hillc.client.gui.ChatPanel;
import com.kyleruss.hillc.client.gui.MainPanel;
import com.kyleruss.jsockchat.client.message.ClientMessageHandler;
import com.kyleruss.jsockchat.commons.message.BroadcastMsgBean;
import com.kyleruss.jsockchat.commons.message.Message;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import com.kyleruss.jsockchat.commons.message.ResponseMessage;

public class BroadcastMessageHandler implements ClientMessageHandler
{
    @Override
    public void witnessAction(Message message) 
    {
        RequestMessage request  =   ((ResponseMessage) message).getRequestMessage();
        BroadcastMsgBean bean   =   (BroadcastMsgBean) request.getMessageBean();
        String user             =   (String) request.getProperty("display_name");
        
        String cipherText       =   bean.getContent();
        int charSet             =   HillCipher.ALL_CHARS;
        int vecSize             =   HillCipher.LARGE_V;
        CStructure cipherStruc  =   new CStructure(cipherText, charSet, vecSize);
        String room             =   bean.getRoom();
        ChatPanel chatPanel     =   MainPanel.getInstance().getChatPane(room);
        
        if(chatPanel != null)
        {
            String key          =   chatPanel.getConversation().getKey();
            CStructure keyStruc =   new CStructure(key, charSet, vecSize);
            CStructure decStruc =   HillCipher.decrypt(cipherStruc, keyStruc, charSet);
            
            String decText      =   decStruc.getText();
            ChatMessage chatMsg =   new ChatMessage(decText);
            chatMsg.setEncryptedContent(cipherText);
            chatMsg.setUser(user);
            chatMsg.setIsServerMessage(false);
            MainPanel.getInstance().outputToRoom(message, chatMsg);
        }
    }

    @Override
    public void performAction(Message message) {}
}
