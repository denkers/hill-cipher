
package com.kyleruss.hillc.client.message;

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
        
        MainPanel.getInstance().outputToRoom(message, bean.getContent(), user);
    }

    @Override
    public void performAction(Message message) {}
}
