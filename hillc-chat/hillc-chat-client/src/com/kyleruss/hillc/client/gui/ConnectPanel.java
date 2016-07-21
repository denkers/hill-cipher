package com.kyleruss.hillc.client.gui;

import com.kyleruss.hillc.client.ChatClient;
import com.kyleruss.hillc.client.ChatConversation;
import com.kyleruss.hillc.client.Config;
import com.kyleruss.jsockchat.commons.message.JoinRoomMsgBean;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class ConnectPanel extends JPanel implements ActionListener
{
    private JTextField nickNameField;
    private JTextField roomNameField;
    private JTextField keyField;
    private JButton connectButton;
    private ChatPanel parentPanel;
    
    public ConnectPanel(ChatPanel parentPanel)
    {
        setBackground(Color.WHITE);
        this.parentPanel    =   parentPanel;
        nickNameField       =   new JTextField();
        roomNameField       =   new JTextField();
        keyField            =   new JTextField();
        connectButton       =   new JButton();

        JLabel nickNameLabel    =   new JLabel("Display name");
        JLabel roomNameLabel    =   new JLabel("Room name");
        JLabel keyLabel         =   new JLabel("Key");
        AppResources resources  =   AppResources.getInstance();
        nickNameLabel.setIcon(new ImageIcon(resources.getGroupImage()));
        roomNameLabel.setIcon(new ImageIcon(resources.getConnImage()));
        keyLabel.setIcon(new ImageIcon(resources.getLockImage()));
        
        connectButton.setIcon(new ImageIcon(resources.getConnectImage()));
        nickNameField.setPreferredSize(new Dimension(120, 25));
        roomNameField.setPreferredSize(new Dimension(120, 25));
        keyField.setPreferredSize(new Dimension(120, 25));
        connectButton.setBorderPainted(false);
        connectButton.setOpaque(false);
        connectButton.setContentAreaFilled(false);
        
        JPanel wrapper  =   new JPanel(new MigLayout("fillx"));
        wrapper.setBackground(Color.WHITE);
        
        wrapper.add(nickNameLabel);
        wrapper.add(nickNameField, "wrap");
        wrapper.add(roomNameLabel);
        wrapper.add(roomNameField, "wrap");
        wrapper.add(keyLabel);
        wrapper.add(keyField, "wrap");
        wrapper.add(connectButton, "span 2");
        
        add(Box.createRigidArea(new Dimension(Config.WINDOW_WIDTH, 100)));
        add(wrapper);
        connectButton.addActionListener(this);
    }
    
    public void connectToRoom()
    {
        String displayName  =   nickNameField.getText();
        String roomName     =   roomNameField.getText();
        String key          =   keyField.getText();
        
        try
        {
            JoinRoomMsgBean bean    =   new JoinRoomMsgBean(roomName, null);
            ChatClient.getInstance().sendMessage(displayName, bean);
            parentPanel.showTransitionPanel();
            
            
            MainPanel.getInstance().setChatPane(roomName, parentPanel);
            JTabbedPane tabPane =   MainPanel.getInstance().getChatTabPane();
            tabPane.setTitleAt(tabPane.getSelectedIndex(), roomName);
            
            ChatConversation conversation   =   parentPanel.getConversation();
            conversation.setDisplayName(displayName);
            conversation.setRoomName(roomName);
            conversation.setKey(key);
        }
        
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "[Error] Failed to connect");
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object src  =   e.getSource();
        if(src == connectButton)
            connectToRoom();
    }
}
