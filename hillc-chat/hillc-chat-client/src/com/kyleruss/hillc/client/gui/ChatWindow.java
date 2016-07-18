
package com.kyleruss.hillc.client.gui;

import com.kyleruss.hillc.client.Config;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class ChatWindow 
{
    private static ChatWindow instance;
    private JFrame frame;
    private MainPanel panel;
    
    private ChatWindow()
    {
        initLookAndFeel();
        initFrame();
        initMenu();
    }
    
    private void initLookAndFeel()
    {
        
        try
        {
            UIManager.setLookAndFeel(Config.LOOK_AND_FEEL);
        } 
        
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            System.out.println("[Error] Failed to initialize application look & feel: " + e.getMessage());
        }
    }
    
    private void initMenu()
    {
        WindowMenu menuBar  =   WindowMenu.getInstance(frame);
        frame.setJMenuBar(menuBar);
    }
    
    private void initFrame()
    {
        frame   =   new JFrame(Config.WINDOW_TITLE);
        panel   =   MainPanel.getInstance();
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    public void display()
    {
        frame.setVisible(true);
    }
    
    public static ChatWindow getInstance()
    {
        if(instance == null) instance = new ChatWindow();
        return instance;
    }
}
