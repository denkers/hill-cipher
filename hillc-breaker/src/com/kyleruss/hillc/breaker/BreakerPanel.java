

package com.kyleruss.hillc.breaker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
        private JTextField cipherField;
        private JTextField knownTextField;
        
        public ControlPanel()
        {
            setPreferredSize(new Dimension(0, 80));
            cipherField     =   new JTextField();
            knownTextField  =   new JTextField();
            
            add(new JLabel("Cipher text"));
            add(cipherField);
            add(new JLabel("Known plain text"));
            add(knownTextField);
        }
    }
    
    private class OutputPanel extends JPanel
    {
        private JList outputList;
        private JScrollPane outputScroll;
        
        public OutputPanel()
        {
            setLayout(new BorderLayout());
            outputList      =   new JList();
            outputScroll    =   new JScrollPane(outputList);   
            add(outputScroll);
        }
    }
    
    public static BreakerPanel getInstance()
    {
        if(instance == null) instance = new BreakerPanel();
        return instance;
    }
}
