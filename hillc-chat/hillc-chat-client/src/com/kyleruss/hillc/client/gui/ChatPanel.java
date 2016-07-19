
package com.kyleruss.hillc.client.gui;

import com.kyleruss.hillc.client.ChatConversation;
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
    private ChatConversation conversation;
    
    public ChatPanel()
    {
        setLayout(new CardLayout());
        connectPanel    =   new ConnectPanel(this);
        convoPanel      =   new ConversationPanel(this);
        transitionPanel =   new TransitionPanel(this);
        conversation    =   new ChatConversation();
        conversation.setParentPanel(this);
        
        add(connectPanel, CONNECT_CARD);
        add(convoPanel, CONVO_CARD);
        add(transitionPanel, TRANSITION_CARD);
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
