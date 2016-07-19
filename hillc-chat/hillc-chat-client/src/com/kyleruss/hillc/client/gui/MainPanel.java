package com.kyleruss.hillc.client.gui;

import com.kyleruss.hillc.client.Config;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainPanel extends JPanel implements ActionListener
{
    private static MainPanel instance;
    private JTabbedPane chatTabPane;
    private Map<String, ChatPanel> chatPanes;
    private JButton addTabBtn;
    
    private MainPanel()
    {
        setPreferredSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        setLayout(new BorderLayout());
        chatTabPane =   new JTabbedPane();
        chatPanes   =   new LinkedHashMap<>();
        addTabBtn   =   new JButton(new ImageIcon(AppResources.getInstance().getAddImage()));
        addTabBtn.setBorderPainted(false);
        
        add(chatTabPane);
        chatTabPane.addTab("", null);
        chatTabPane.setTabComponentAt(0, addTabBtn);
        addTabBtn.addActionListener(this);
        addChatRoom();
    }
    
    public void setRoomName(String name, ChatPanel panel)
    {
        chatTabPane.add(name, panel);
    }
    
    public void addChatRoom()
    {
        ChatPanel panel =   new ChatPanel();
        int numTabs     =   chatTabPane.getTabCount();
        int index       =   numTabs - 1;
        chatTabPane.add(panel, index);
        chatTabPane.setIconAt(index, new ImageIcon(AppResources.getInstance().getChatMultiImage()));
        chatTabPane.setTitleAt(index, "New chat");
        chatTabPane.setSelectedIndex(index);
    }
    
    public ChatPanel getChatPane(String name)
    {
        return chatPanes.get(name);
    }
    
    public void removeChatPane(String name)
    {
        int index   =   chatTabPane.indexOfTab(name);
        if(index != -1)
        {
            chatPanes.remove(name);
            chatTabPane.remove(index);
        }
    }
    
    public Map<String, ChatPanel> getChatPanes()
    {
        return chatPanes;
    }
    
    public static MainPanel getInstance()
    {
        if(instance == null) instance = new MainPanel();
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == addTabBtn)
        addChatRoom();
    }
}
