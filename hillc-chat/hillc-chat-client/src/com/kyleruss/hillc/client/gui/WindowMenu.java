package com.kyleruss.hillc.client.gui;

import javax.swing.JMenuBar;

public class WindowMenu extends JMenuBar
{
    private static WindowMenu instance;
    
    public static WindowMenu getInstance()
    {
        return instance;
    }
}
