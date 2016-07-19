package com.kyleruss.hillc.client.gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class WindowMenu extends JMenuBar implements ActionListener
{
    private static WindowMenu instance;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenu chatMenu;
    private JMenuItem exitItem, miniItem, maxItem;
    private JMenuItem aboutItem;
    private JMenuItem newChatItem, leaveChatItem;
    private JFrame frame;
    
    private WindowMenu(JFrame frame)
    {
        this.frame      =   frame;
        fileMenu        =   new JMenu("File");
        helpMenu        =   new JMenu("Help");
        chatMenu        =   new JMenu("Chat");
        
        exitItem        =   new JMenuItem("Exit");
        miniItem        =   new JMenuItem("Minimize");
        maxItem         =   new JMenuItem("Maximize");
        aboutItem       =   new JMenuItem("About author");
        newChatItem     =   new JMenuItem("New chat");
        leaveChatItem   =   new JMenuItem("Leave chat");
        
        fileMenu.add(miniItem);
        fileMenu.add(maxItem);
        fileMenu.add(exitItem);
        helpMenu.add(aboutItem);
        chatMenu.add(newChatItem);
        chatMenu.add(leaveChatItem);
        
        add(fileMenu);
        add(chatMenu);
        add(helpMenu);
        
        exitItem.addActionListener(this);
        miniItem.addActionListener(this);
        maxItem.addActionListener(this);
        aboutItem.addActionListener(this);
        newChatItem.addActionListener(this);
        leaveChatItem.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object src =   e.getSource();
        
        if(src == exitItem)
            System.exit(0);
        
        else if(src == miniItem)
            frame.setState(Frame.ICONIFIED);
        
        else if(src == maxItem)
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        else if(src == aboutItem)
            JOptionPane.showMessageDialog(null, "Kyle Russell\nAUT University 2016\ngithub.com/denkers/hill-cipher");
    }
    
    public static WindowMenu getInstance(JFrame frame)
    {
        if(instance == null) instance = new WindowMenu(frame);
        return instance;
    }
    
    public static WindowMenu getInstance()
    {
        return instance;
    }
}
