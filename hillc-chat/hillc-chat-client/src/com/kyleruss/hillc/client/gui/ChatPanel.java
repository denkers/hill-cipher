
package com.kyleruss.hillc.client.gui;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class ChatPanel extends JPanel
{
    public static String CONNECT_CARD       =   "conn_c";
    public static String CONVO_CARD         =   "convo_c";
    public static String TRANSITION_CARD    =   "trans_c";   
    
    private ConnectPanel connectPanel;
    private ConversationPanel convoPanel;
    private TransitionPanel transitionPanel;
    
    public ChatPanel()
    {
        setLayout(new CardLayout());
        connectPanel    =   new ConnectPanel();
        convoPanel      =   new ConversationPanel();
        transitionPanel =   new TransitionPanel();
        
        add(transitionPanel);
        add(convoPanel, CONVO_CARD);
        add(connectPanel, CONNECT_CARD);
    }
    
    public void showConnectPanel()
    {
        changeView(CONNECT_CARD);
    }
    
    public void showConversationPanel()
    {
        changeView(CONVO_CARD);
    }
    
    public void showTransitionPanel()
    {
        changeView(TRANSITION_CARD);
    }
    
    public void changeView(String cardName)
    {
        CardLayout cLayout  =   (CardLayout) getLayout();
        cLayout.show(this, cardName);
    }
}
