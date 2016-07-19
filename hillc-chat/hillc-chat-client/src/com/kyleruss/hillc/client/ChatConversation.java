
package com.kyleruss.hillc.client;

public class ChatConversation 
{
    private String displayName;
    private String key;
    private String roomName;
    
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
    
    
}
