//-------------------------------------
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems
//-------------------------------------

package com.kyleruss.hillc.client;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage 
{
    private boolean isServerMessage;
    private String content;
    private String encryptedContent;
    private String user;
    private Date date;
    
    public ChatMessage(String content)
    {
        this.content    =   content;
        isServerMessage =   true;
        user            =   "SERVER";
        date            =   new Date();
    }

    public boolean isIsServerMessage()
    {
        return isServerMessage;
    }

    public void setIsServerMessage(boolean isServerMessage)
    {
        this.isServerMessage = isServerMessage;
    }

    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getEncryptedContent() 
    {
        return encryptedContent;
    }

    public void setEncryptedContent(String encryptedContent) 
    {
        this.encryptedContent = encryptedContent;
    }

    public String getUser() 
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public Date getDate() 
    {
        return date;
    }

    public void setDate(Date date) 
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        String messageStr   =   "[";
        String dateStr      =   new SimpleDateFormat("hh:mm a").format(date);
        messageStr          +=  dateStr + "] ";
        messageStr          +=  user + ": ";
        messageStr          +=  content;
        
        return messageStr;
    }
}
