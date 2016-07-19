
package com.kyleruss.hillc.client;

import com.kyleruss.hillc.client.gui.ChatPanel;
import com.kyleruss.jsockchat.client.core.ClientManager;
import com.kyleruss.jsockchat.commons.message.BroadcastMsgBean;
import com.kyleruss.jsockchat.commons.message.RequestMessage;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ChatConversation 
{
    private String displayName;
    private String key;
    private String roomName;
    private ChatPanel parentPanel;
    
    public ChatConversation()
    {
        this("", "", "");
    }
    
    public ChatConversation(String displayName, String key, String roomName)
    {
        this.displayName    =   displayName;
        this.key            =   key;
        this.roomName       =   roomName;
    }

    public String getDisplayName() 
    {
        return displayName;
    }

    public void setDisplayName(String displayName) 
    {
        this.displayName = displayName;
    }

    public String getKey() 
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getRoomName()
    {
        return roomName;
    }

    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }
    
    public void setParentPanel(ChatPanel parentPanel)
    {
        this.parentPanel    =   parentPanel;
    }
    
    public ChatPanel getParentPanel()
    {
        return parentPanel;
    }
    
    public void sendMessage(String message)
    {
        try
        {
            BroadcastMsgBean messageBean    =   new BroadcastMsgBean(roomName, message);
            RequestMessage reqMessage       =   ClientManager.getInstance().prepareMessage(messageBean);
            reqMessage.setProperty("display_name", displayName);
            ClientManager.getInstance().sendRequest(reqMessage);
        }
        
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "[Error] Failed to send message");
        }
    }
}