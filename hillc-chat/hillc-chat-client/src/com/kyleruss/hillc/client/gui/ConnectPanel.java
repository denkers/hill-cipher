package com.kyleruss.hillc.client.gui;

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
        setLayout(new MigLayout("fillx"));
        nickNameField   =   new JTextField();
        roomNameField   =   new JTextField();
        keyField        =   new JTextField();
        connectButton   =   new JButton();

        JLabel nickNameLabel    =   new JLabel("Display name");
        JLabel roomNameLabel    =   new JLabel("Room name");
        JLabel keyLabel         =   new JLabel("Key");
        connectButton.setIcon(new ImageIcon(AppResources.getInstance().getConnectImage()));
        
        add(nickNameLabel);
        add(nickNameField, "wrap");
        add(roomNameLabel);
        add(roomNameField, "wrap");
        add(keyLabel);
        add(keyField, "wrap");
        add(connectButton, "span 2");
    }
}
