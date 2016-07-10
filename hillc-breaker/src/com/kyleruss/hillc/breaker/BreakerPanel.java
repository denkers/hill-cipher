

package com.kyleruss.hillc.breaker;

import com.kyleruss.hillc.base.CStructure;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
            
            add(new JLabel("Cipher text"));
            add(cipherField);
            add(new JLabel("Known plain text"));
            add(knownTextField);
            add(breakerExecBtn);
            
            breakerExecBtn.addActionListener(this);
        }
        
        private void executeCrack()
        {
            String cipherText   =   cipherField.getText();
            String knownText    =   knownTextField.getText();
            
            if(validateCipherText(cipherText) && validateKnownText(knownText))
            {
                CStructure cipherStructure                          =   new CStructure(cipherText, 26);
                List<Map.Entry<CStructure, CStructure>> attemptList =   HillCipherBreaker.breakCipher(cipherStructure, knownText);
                DefaultListModel listModel                          =   (DefaultListModel) outputPanel.outputList.getModel();
            
            }
        }
        
        private boolean validateCipherText(String cipherText)
        {
            return (cipherText.length() != 4);
        }
        
        private boolean validateKnownText(String knownText)
        {
            return knownText.length() % 2 == 0;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object src  =   e.getSource();
            
            if(src == breakerExecBtn)
                executeCrack();
            
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
