

package com.kyleruss.hillc.breaker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JPanel;

public class BreakerPanel extends JPanel
{
    private static BreakerPanel instance;
    private ControlPanel controlPanel;
    private OutputPanel outputPanel;
    
    private BreakerPanel()
    {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(700, 450));
        
        controlPanel    =   new ControlPanel();
        outputPanel     =   new OutputPanel();
        
        add(outputPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    private class ControlPanel extends JPanel
    {
        public ControlPanel()
        {
            
        }
    }
    
    private class OutputPanel extends JPanel
    {
        public OutputPanel()
        {
            
        }
    }
    
    public static BreakerPanel getInstance()
    {
        if(instance == null) instance = new BreakerPanel();
        return instance;
    }
}
