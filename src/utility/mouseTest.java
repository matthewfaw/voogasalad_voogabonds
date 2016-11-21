package utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Point;

public class mouseTest extends JFrame
{
Robot r;

    public mouseTest()
    {
        createAndShowGUI();
    }
    
    private void createAndShowGUI()
    {
        setTitle("Move Cursor with Keyboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // hide the visibility
        setUndecorated(true);
        setOpacity(0f);
        setVisible(true);
        
        // Create Robot object
        try
        {
        r=new Robot();
        }catch(Exception e){}
        
        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e)
            {
                // If there occured an exception
                // while creating Robot object, r is null
                // then go back
                if(r==null) return;
                
                // Get global current cursor location
                Point p=MouseInfo.getPointerInfo().getLocation();
                
                switch(e.getKeyCode())
                {
                    case KeyEvent.VK_UP: r.mouseMove(p.x,p.y-5); break;
                    case KeyEvent.VK_DOWN: r.mouseMove(p.x,p.y+5); break;
                    case KeyEvent.VK_LEFT: r.mouseMove(p.x-5,p.y); break;
                    case KeyEvent.VK_RIGHT: r.mouseMove(p.x+5,p.y); break;
                    // left click
                    case KeyEvent.VK_ENTER: r.mousePress(MouseEvent.BUTTON1_MASK); r.mouseRelease(MouseEvent.BUTTON1_MASK);
                }
            }
        });
    }
    
    public static void main(String args[])
    {
        new mouseTest();
    }
}