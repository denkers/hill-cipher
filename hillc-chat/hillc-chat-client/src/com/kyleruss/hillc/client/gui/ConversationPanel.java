//-------------------------------------
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems
//-------------------------------------

package com.kyleruss.hillc.client.gui;

import com.kyleruss.hillc.client.ChatConversation;
import com.kyleruss.hillc.client.ChatMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ConversationPanel extends JPanel
{
    private JList chatList;
    private DefaultListModel chatModel;
    private ControlPanel controlPanel;
    private ChatPanel parentPanel;
    
    public ConversationPanel(ChatPanel parentPanel)
    {
        setLayout(new BorderLayout());
        this.parentPanel    =   parentPanel;
        chatModel           =   new DefaultListModel();
        chatList            =   new JList(chatModel);
        controlPanel        =   new ControlPanel();
        
        JPanel titleWrapper =   new JPanel();
        titleWrapper.setBackground(Color.WHITE);
        titleWrapper.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        
        
        add(titleWrapper, BorderLayout.NORTH);
        add(chatList, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    public void addMessage(ChatMessage message)
    {
        chatModel.addElement(message);
    }
    
    private class ControlPanel extends JPanel implements ActionListener
    {
        private JButton sendBtn, leaveBtn;
        private JTextArea chatInputField;
        
        public ControlPanel()
        {
            setPreferredSize(new Dimension(0, 80));
            setLayout(new BorderLayout());
            
            sendBtn         =   new JButton("Send");
            leaveBtn        =   new JButton("Leave");
            chatInputField  =   new JTextArea();
            chatInputField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
            sendBtn.setIcon(new ImageIcon(AppResources.getInstance().getChatImage()));
            leaveBtn.setIcon(new ImageIcon(AppResources.getInstance().getLeaveImage()));
            
            JPanel controlWrapper   =   new JPanel(new GridLayout(2, 1));
            controlWrapper.setPreferredSize(new Dimension(100, 0));
            
            controlWrapper.add(sendBtn);
            controlWrapper.add(leaveBtn);
            
            add(chatInputField, BorderLayout.CENTER);
            add(controlWrapper, BorderLayout.EAST);
            sendBtn.addActionListener(this);
            leaveBtn.addActionListener(this);
        }
        
        private void sendMsg()
        {
            String message                  =   chatInputField.getText();
            ChatConversation conversation   =   parentPanel.getConversation();   

            chatInputField.setText("");
            conversation.sendMessage(message);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Object src  =   e.getSource();
            if(src == sendBtn)
                sendMsg();
            
            else if(src == leaveBtn)
                MainPanel.getInstance().leaveRoom();
        }
    }
}
