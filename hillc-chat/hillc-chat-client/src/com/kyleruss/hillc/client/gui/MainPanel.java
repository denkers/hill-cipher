package com.kyleruss.hillc.client.gui;

import com.kyleruss.hillc.client.Config;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class MainPanel extends JPanel
{
    private static MainPanel instance;
    private ConnectPanel connectPanel;
    
    private MainPanel()
    {
        setPreferredSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        connectPanel    =   new ConnectPanel();
        add(connectPanel);
    }
    
    public static MainPanel getInstance()
    {
        if(instance == null) instance = new MainPanel();
        return instance;
    }
}
