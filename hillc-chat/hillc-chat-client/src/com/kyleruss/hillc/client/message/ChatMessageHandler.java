
package com.kyleruss.hillc.client.message;

import com.kyleruss.jsockchat.commons.io.MessageHandler;
import com.kyleruss.jsockchat.commons.message.ActionHandler;
import com.kyleruss.jsockchat.commons.message.DefaultActionHandler;
import com.kyleruss.jsockchat.commons.message.MessageBean;

public class ChatMessageHandler implements MessageHandler
{
    @Override
    public ActionHandler getActionHandler(MessageBean mb) 
    {
        return new DefaultActionHandler();
    }
}
