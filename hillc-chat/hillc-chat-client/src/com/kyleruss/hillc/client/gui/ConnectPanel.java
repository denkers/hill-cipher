package com.kyleruss.hillc.client.gui;

import com.kyleruss.hillc.client.Config;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class ConnectPanel extends JPanel
{
    private JTextField nickNameField;
    private JTextField roomNameField;
    private JTextField keyField;
    private JButton connectButton;
    
    public ConnectPanel()
    {
        setBackground(Color.WHITE);
        nickNameField   =   new JTextField();
        roomNameField   =   new JTextField();
        keyField        =   new JTextField();
        connectButton   =   new JButton();

        JLabel nickNameLabel    =   new JLabel("Display name");
        JLabel roomNameLabel    =   new JLabel("Room name");
        JLabel keyLabel         =   new JLabel("Key");
        connectButton.setIcon(new ImageIcon(AppResources.getInstance().getConnectImage()));
        nickNameField.setPreferredSize(new Dimension(120, 25));
        roomNameField.setPreferredSize(new Dimension(120, 25));
        keyField.setPreferredSize(new Dimension(120, 25));
        connectButton.setBorderPainted(false);
        connectButton.setOpaque(false);
        connectButton.setContentAreaFilled(false);
        
        JPanel wrapper  =   new JPanel(new MigLayout("fillx"));
        wrapper.setBackground(Color.WHITE);
        
        wrapper.add(nickNameLabel);
        wrapper.add(nickNameField, "wrap");
        wrapper.add(roomNameLabel);
        wrapper.add(roomNameField, "wrap");
        wrapper.add(keyLabel);
        wrapper.add(keyField, "wrap");
        wrapper.add(connectButton, "span 2");
        
        add(Box.createRigidArea(new Dimension(Config.WINDOW_WIDTH, 100)));
        add(wrapper);
    }
}
