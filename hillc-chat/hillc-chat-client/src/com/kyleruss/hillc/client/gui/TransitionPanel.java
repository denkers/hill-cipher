//-------------------------------------
//  Kyle Russell
//  AUT University 2016
//  Highly Secured Systems
//-------------------------------------

package com.kyleruss.hillc.client.gui;

import com.kyleruss.hillc.client.Config;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TransitionPanel extends JPanel
{
    private JLabel transitionLabel;
    private ChatPanel parentPanel;
    
    public TransitionPanel(ChatPanel parentPanel)
    {
        this.parentPanel    =   parentPanel;
        transitionLabel     =   new JLabel(new ImageIcon("data/images/loadspinner.gif"));
        add(transitionLabel);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
    }
}
