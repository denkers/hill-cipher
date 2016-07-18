package com.kyleruss.hillc.client.gui;

import com.kyleruss.hillc.client.Config;
import java.awt.Dimension;
import javax.swing.JPanel;

public class MainPanel extends JPanel
{
    private static MainPanel instance;
    
    private MainPanel()
    {
        setPreferredSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
    }
    
    public static MainPanel getInstance()
    {
        if(instance == null) instance = new MainPanel();
        return instance;
    }
}
