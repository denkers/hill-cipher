
package com.kyleruss.hillc.client.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class AppResources 
{
    private static AppResources instance;
    private BufferedImage connectImage, chatImage;
    private BufferedImage groupImage, connImage, lockImage;
    private BufferedImage leaveImage, chatMultiImage, addImage;
   
    private AppResources()
    {
        initImageResources();
    }
    
    private void initImageResources()
    {
        try
        {
            connectImage    =   ImageIO.read(new File("data/images/connect_button.png"));
            groupImage      =   ImageIO.read(new File("data/images/group_icon.png"));
            connImage       =   ImageIO.read(new File("data/images/internet_icon.png"));
            lockImage       =   ImageIO.read(new File("data/images/lock.png"));
            chatImage       =   ImageIO.read(new File("data/images/pm_image.png"));
            leaveImage      =   ImageIO.read(new File("data/images/leave_room.png"));
            chatMultiImage  =   ImageIO.read(new File("data/images/bubbles-alt.png"));
            addImage        =   ImageIO.read(new File("data/images/addSmallIcon.png"));
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

    public BufferedImage getGroupImage() 
    {
        return groupImage;
    }

    public BufferedImage getConnImage() 
    {
        return connImage;
    }

    public BufferedImage getLockImage() 
    {
        return lockImage;
    }
    
    public BufferedImage getChatImage()
    {
        return chatImage;
    }

    public BufferedImage getLeaveImage() 
    {
        return leaveImage;
    }

    public BufferedImage getChatMultiImage() 
    {
        return chatMultiImage;
    }
    
    public BufferedImage getAddImage()
    {
        return addImage;
    }
    
    public static AppResources getInstance()
    {
        if(instance == null) instance = new AppResources();
        return instance;
    }
}
