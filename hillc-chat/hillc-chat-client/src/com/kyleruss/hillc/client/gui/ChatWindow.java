
package com.kyleruss.hillc.client.gui;

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
    }
    
    private void initLookAndFeel()
    {
        
        try
        {
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel");
        } 
        
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            System.out.println("[Error] Failed to initialize application look & feel: " + e.getMessage());
        }
    }

    
    private void initFrame()
    {
        frame   =   new JFrame("Hill Cipher Breaker - Kyle Russell 2016");
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
