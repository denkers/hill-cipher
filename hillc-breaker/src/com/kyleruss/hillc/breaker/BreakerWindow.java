
package com.kyleruss.hillc.breaker;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BreakerWindow
{
    private static BreakerWindow instance;
    private JFrame frame;
    
    private BreakerWindow()
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    public void display()
    {
        frame.setVisible(true);
    }
    
    public static BreakerWindow getInstance()
    {
        if(instance == null) instance = new BreakerWindow();
        return instance;
    }
    
    public static void main(String[] args)
    {
        BreakerWindow window    =   BreakerWindow.getInstance();
        window.display();
    }
}
