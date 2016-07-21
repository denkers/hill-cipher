
package com.kyleruss.hillc.client.message;

import com.kyleruss.jsockchat.commons.io.MessageHandler;
import com.kyleruss.jsockchat.commons.message.ActionHandler;
import com.kyleruss.jsockchat.commons.message.BroadcastMsgBean;
import com.kyleruss.jsockchat.commons.message.DisconnectMsgBean;
import com.kyleruss.jsockchat.commons.message.JoinRoomMsgBean;
import com.kyleruss.jsockchat.commons.message.MessageBean;

public class ChatMessageHandler implements MessageHandler
{
    @Override
    public ActionHandler getActionHandler(MessageBean bean) 
    {
        if(bean instanceof BroadcastMsgBean)
            return new BroadcastMessageHandler();
        
        else if(bean instanceof DisconnectMsgBean)
            return new LeaveRoomMessageHandler();
        
        else if(bean instanceof JoinRoomMsgBean)
            return new JoinRoomMessageHandler();
        
        else return null;
    }
}
