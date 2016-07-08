

package com.kyleruss.hillc.breaker;

import com.kyleruss.hillc.base.CStructure;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class BreakerPanel extends JPanel
{
    private static BreakerPanel instance;
    private ControlPanel controlPanel;
    private OutputPanel outputPanel;
    private BufferedImage execImage, clipboardImage, tickImage, crossImage;
    
    private BreakerPanel()
    {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(700, 450));
        initImages();
        
        controlPanel    =   new ControlPanel();
        outputPanel     =   new OutputPanel();
        
        add(outputPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    private void initImages()
    {
        try
        {
            execImage       =   ImageIO.read(new File("data/images/up.png"));
            clipboardImage  =   ImageIO.read(new File("data/images/clipboard.png"));
            tickImage       =   ImageIO.read(new File("data/images/tick.png"));
            crossImage      =   ImageIO.read(new File("data/images/removeSmallIcon.png"));
        }
        
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "[Error] Failed to load image resources");
        }
    }
    
    private class ControlPanel extends JPanel implements ActionListener
    { 
        private JTextField cipherField;
        private JTextField knownTextField;
        private JButton breakerExecBtn;
        
        public ControlPanel()
        {
            setPreferredSize(new Dimension(0, 80));
            cipherField     =   new JTextField();
            knownTextField  =   new JTextField();
            breakerExecBtn  =   new JButton("Crack");

            knownTextField.setPreferredSize(new Dimension(150, 30));
            cipherField.setPreferredSize(new Dimension(150, 30));
            breakerExecBtn.setIcon(new ImageIcon(execImage));
            
            
            JPanel controlWrapper   =   new JPanel(new GridLayout(2, 2));
            JLabel cipherLabel      =   new JLabel("Cipher text");
            cipherLabel.setIcon(new ImageIcon(clipboardImage));
            
            JLabel knownTextLabel   =   new JLabel("Known text");
            knownTextLabel.setIcon(new ImageIcon(clipboardImage));
            
            controlWrapper.add(cipherLabel);
            controlWrapper.add(cipherField);
            controlWrapper.add(knownTextLabel);
            controlWrapper.add(knownTextField);
            
            JPanel controlInnerWrapper  =   new JPanel(new BorderLayout());
            controlInnerWrapper.add(controlWrapper, BorderLayout.CENTER);
            controlInnerWrapper.add(breakerExecBtn, BorderLayout.EAST);
            
            add(controlInnerWrapper);

            breakerExecBtn.addActionListener(this);
        }
        
        private void executeCrack()
        {
            String cipherText   =   cipherField.getText();
            String knownText    =   knownTextField.getText();
            
            if(validateCipherText(cipherText) && validateKnownText(knownText))
            {
                CStructure cipherStructure                          =   new CStructure(cipherText, 26);
                List<Entry<CStructure, CStructure>> attemptList     =   HillCipherBreaker.breakCipher(cipherStructure, knownText);
                outputPanel.outputModel.setNumRows(0);
                
                for(Entry<CStructure, CStructure> attempt : attemptList)
                    outputPanel.addResultRow(attempt.getValue(), attempt.getKey());
            }
            
            else JOptionPane.showMessageDialog(null, "Invalid input");
        }
        
        private boolean validateCipherText(String cipherText)
        {
            return (cipherText.length() % 2 == 0);
        }
        
        private boolean validateKnownText(String knownText)
        {
            return knownText.length() == 5;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object src  =   e.getSource();
            
            if(src == breakerExecBtn)
                executeCrack();
            
        }
    }
    
    private class OutputPanel extends JPanel implements TableCellRenderer
    {
        private JTable outputTable;
        private JScrollPane outputScroll;
        private DefaultTableModel outputModel;
        
        public OutputPanel()
        {
            setLayout(new BorderLayout());
            outputModel     =   new DefaultTableModel();
            outputTable      =   new JTable(outputModel);
            outputScroll    =   new JScrollPane(outputTable);   
            
            outputModel.addColumn("Status");
            outputModel.addColumn("Decrypted text");
            outputModel.addColumn("Key");
            outputTable.getColumnModel().getColumn(0).setCellRenderer(this);
            outputTable.getColumnModel().getColumn(0).setMaxWidth(100);
            add(outputScroll);
        }
        
        public void addResultRow(CStructure decText, CStructure key)
        {
            String knownText            =   controlPanel.knownTextField.getText();
            boolean containsKnownText   =   decText.getText().contains(knownText);
            ImageIcon statusIcon        =   containsKnownText? new ImageIcon(tickImage) : new ImageIcon(crossImage);
            outputModel.addRow(new Object[] { statusIcon, decText.getText(), key.getText() });
        }


        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
        {
            DefaultTableCellRenderer defaultRenderer    =   new DefaultTableCellRenderer();
            JLabel component         =   (JLabel) defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            ImageIcon statusIcon     =   (ImageIcon) value;
            component.setText("");
            component.setIcon(statusIcon);
            
            return component;
        }
    }
    
    public static BreakerPanel getInstance()
    {
        if(instance == null) instance = new BreakerPanel();
        return instance;
    }
}
