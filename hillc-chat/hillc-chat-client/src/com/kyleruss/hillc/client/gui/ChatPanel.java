
package com.kyleruss.hillc.client.gui;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class ChatPanel extends JPanel
{
    public static String CONNECT_CARD   =   "conn_c";
    public static String CONVO_CARD     =   "convo_c";
    
    private ConnectPanel connectPanel;
    private ConversationPanel convoPanel;
    
    public ChatPanel()
    {
        setLayout(new CardLayout());
        connectPanel    =   new ConnectPanel();
        convoPanel      =   new ConversationPanel();
        
        add(connectPanel, CONNECT_CARD);
        add(convoPanel, CONVO_CARD);
    }
    
    public void showConnectPanel()
    {
        changeView(CONNECT_CARD);
    }
    
    public void showConversationPanel()
    {
        changeView(CONVO_CARD);
    }
    
    public void changeView(String cardName)
    {
        CardLayout cLayout  =   (CardLayout) getLayout();
        cLayout.show(this, cardName);
    }
}
