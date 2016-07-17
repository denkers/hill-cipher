package com.kyleruss.hillc.client.gui;

import javax.swing.JPanel;

public class MainPanel extends JPanel
{
    private static MainPanel instance;
    
    public static MainPanel getInstance()
    {
        if(instance == null) instance = new MainPanel();
        return instance;
    }
}
