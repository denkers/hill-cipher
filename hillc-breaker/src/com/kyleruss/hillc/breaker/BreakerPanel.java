

package com.kyleruss.hillc.breaker;

import com.kyleruss.hillc.base.CStructure;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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

            knownTextField.setPreferredSize(new Dimension(150, 30));
            cipherField.setPreferredSize(new Dimension(150, 30));
            JPanel controlWrapper   =   new JPanel(new GridLayout(2, 2));
            controlWrapper.add(new JLabel("Cipher text"));
            controlWrapper.add(cipherField);
            controlWrapper.add(new JLabel("Known text"));
            controlWrapper.add(knownTextField);
            add(controlWrapper);
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
                DefaultListModel listModel                          =   outputPanel.outputModel;
                listModel.clear();
                
                for(int i = 0; i < attemptList.size(); i++)
                    listModel.add(i, attemptList.get(i).getValue().getText());
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
    
    private class OutputPanel extends JPanel
    {
        private JList outputList;
        private JScrollPane outputScroll;
        private DefaultListModel outputModel;
        
        public OutputPanel()
        {
            setLayout(new BorderLayout());
            outputModel     =   new DefaultListModel();
            outputList      =   new JList(outputModel);
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
