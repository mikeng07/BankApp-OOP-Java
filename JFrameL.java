/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author tung_nguyen
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JFrameL extends JFrame
{
    
    /** Creates a new instance of JFrameL */
    public JFrameL(String title) {
        super(title);
        FrameListener listener = new FrameListener();
        addWindowListener(listener);
        
    }
    private class FrameListener extends WindowAdapter
    {
    public void windowClosing(WindowEvent e) {
            //This will only be seen on standard output.
       if(!Main.saveflag)
       {
           int again;
           again =JOptionPane.showConfirmDialog(null, "Please save you data before exit! ", "Warning!! ",JOptionPane.YES_NO_OPTION);
           if (again==JOptionPane.YES_OPTION)
                Main.writeFile();
           else if (again==JOptionPane.NO_OPTION)
           {
               System.out.println("WindowListener method called: windowClosed.");
               Main.frame.setVisible(false);
               System.exit(0);
           } 
         
           
       }
       
    }
    }

    public void windowClosed(WindowEvent e) {    }

    public void windowOpened(WindowEvent e) {    }

    public void windowIconified(WindowEvent e) {    }

    public void windowDeiconified(WindowEvent e) {    }

    public void windowActivated(WindowEvent e) {    }

    public void windowDeactivated(WindowEvent e) {    }    
}
