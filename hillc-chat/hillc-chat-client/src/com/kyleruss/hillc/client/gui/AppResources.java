
package com.kyleruss.hillc.client.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class AppResources 
{
    private static AppResources instance;
    private BufferedImage connectImage;
   
    private AppResources()
    {
        initImageResources();
    }
    
    private void initImageResources()
    {
        try
        {
            connectImage    =   ImageIO.read(new File("data/images/connect_button.png"));
        }
        
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "[Error] Failed to load image resources");
        }
    }
    
    public BufferedImage getConnectImage()
    {
        return connectImage;
    }
    
    public static AppResources getInstance()
    {
        if(instance == null) instance = new AppResources();
        return instance;
    }
}
