
package com.kyleruss.hillc.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConversationPanel extends JPanel
{
    private JList chatList;
    private DefaultListModel chatModel;
    private JLabel titleLabel;
    private ControlPanel controlPanel;
    
    public ConversationPanel()
    {
        setLayout(new BorderLayout());
        chatModel       =   new DefaultListModel();
        chatList        =   new JList(chatModel);
        controlPanel    =   new ControlPanel();
        titleLabel      =   new JLabel("Chatting with");
        
        JPanel titleWrapper =   new JPanel();
        titleWrapper.setBackground(Color.WHITE);
        titleWrapper.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        titleWrapper.add(titleLabel);
        
        
        add(titleWrapper, BorderLayout.NORTH);
        add(chatList, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
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
            
            JPanel controlWrapper   =   new JPanel(new GridLayout(2, 1));
            controlWrapper.setPreferredSize(new Dimension(100, 0));
            
            controlWrapper.add(sendBtn);
            controlWrapper.add(leaveBtn);
            
            add(chatInputField, BorderLayout.CENTER);
            add(controlWrapper, BorderLayout.EAST);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Object src  =   e.getSource();
        }
    }
}
