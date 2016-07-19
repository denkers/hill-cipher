
package com.kyleruss.hillc.server.message;

import com.kyleruss.jsockchat.commons.io.MessageHandler;
import com.kyleruss.jsockchat.commons.message.ActionHandler;
import com.kyleruss.jsockchat.commons.message.MessageBean;
import com.kyleruss.jsockchat.server.message.DefaultMessageHandler;

public class ChatMessageHandler implements MessageHandler
{
    @Override
    public ActionHandler getActionHandler(MessageBean mb)
    {
        MessageHandler defaultHandler   =   new DefaultMessageHandler();
        return defaultHandler.getActionHandler(mb);
    }
}
