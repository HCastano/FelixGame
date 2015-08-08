package felixgame; 

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class displays information about a phase of the game and has an arrow button to move forward past the screen
 * 
 * @author Ansh Juneja last updated June 05, 2015
 * @version 1
 * Time Spent: 1.5 hours
 * <p>
 * <b> Instance Variables: </b>
 * <p>
 * <b> arrowImage </b> BufferedImage of arrow button
 * <p>
 * <b> enlargedArrowImage </b> BufferedImage of enlarged arrow button
 * <p>
 * <b> pressedArrowImage </b> BufferedImage of pressed arrow button
 * <p>
 * <b> invisibleImage </b> BufferedImage of image with no content
 * <p>
 * <b> arrowButton </b> JLabel with arrow image icon
 * <p>
 * <b> enlargedArrowButton </b> JLabel with enlarged arrow image icon
 * <p>
 * <b> pressedArrowButton </b> JLabel with pressed arrow image icon
 * <p>
 * <b> infoLabel </b> JLabel with information image icon
 * <p>
 * <b> tempComp </b> temporary Component object used in MouseListener methods to determine button animations
 * <p>
 * <b> tempEntered </b> boolean used in MouseListener methods to determine button animations
 * <p>
 * <b> pressed </b> boolean used in MouseListener methods to determine button animations
 * <p>
 * <b> info </b> String describing information being displayed (and file name)
 */
public class InfoScreen extends JPanel implements MouseListener
{
  private BufferedImage arrowImage, enlargedArrowImage, pressedArrowImage, invisibleImage;
  private JLabel arrowButton, enlargedArrowButton, pressedArrowButton, infoLabel;
  private Component tempComp;
  private boolean tempEntered = true;
  private boolean pressed;
  private String info;
  
  /**
   * Contructor which creates background with information image based on string parameter passed containing file name
   * of information screen image
   * @param info  String with file name of information screen image
   */
  public InfoScreen (String info)
  {
    this.info = info;
    setLayout (null);
    try
    {
      BufferedImage backgroundImage = ImageIO.read (new File ("assets/Pictures/mainback.png"));
      arrowImage = ImageIO.read (new File ("assets/Pictures/forwardarrow.png"));
      BufferedImage infoImage = ImageIO.read (new File ("assets/Pictures/" + info + ".png"));
      enlargedArrowImage = ImageIO.read (new File ("assets/Pictures/enlargedforwardarrow.png"));
      pressedArrowImage = ImageIO.read (new File ("assets/Pictures/pressedforwardarrow.png"));
      invisibleImage = ImageIO.read (new File ("assets/Pictures/invisible.png"));
      JLabel backgroundLabel = new JLabel (new ImageIcon (backgroundImage));
      infoLabel = new JLabel (new ImageIcon (infoImage));
      arrowButton = new JLabel (new ImageIcon (arrowImage));
      enlargedArrowButton = new JLabel (new ImageIcon (enlargedArrowImage));
      pressedArrowButton = new JLabel (new ImageIcon (pressedArrowImage));
      backgroundLabel.setBounds (0, 0, 900, 600);
      arrowButton.setBounds (790, 490, 90, 80);
      enlargedArrowButton.setBounds (780, 480, 110, 100);
      pressedArrowButton.setBounds (790, 490, 90, 80);
      infoLabel.setBounds (0, 0, 900, 600);
      add (arrowButton);
      add (enlargedArrowButton);
      add (pressedArrowButton);
      add (infoLabel);
      add (backgroundLabel);
      enlargedArrowButton.setVisible (false);
      pressedArrowButton.setVisible (false);
      backgroundLabel.addMouseListener (this);
      arrowButton.addMouseListener (this);
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
    
    setSize (900, 600);
    setVisible (true);
  }
  
  /**
   * Invoked when the mouse is clicked on a component
   * @param e  MouseEvent object pertaining to the component on which mouse button was clicked
   */
  @Override
  public void mouseClicked (MouseEvent e) {}
  
  /**
   * Invoked when the mouse enters a component; displays an enlarged version of the button
   * @param e  MouseEvent object pertaining to the component on which mouse enters
   */
  @Override
  public void mouseEntered (MouseEvent e)
  {
    if (e.getComponent().equals (arrowButton))
    {
      if (!pressed)
      {
        enlargedArrowButton.setVisible (true);
        arrowButton.setIcon (new ImageIcon (invisibleImage));
      }
      if (e.getComponent().equals (tempComp)) //mouse has entered pressed component's area
        tempEntered = true;
    }
  }
  
  /**
   * Invoked when the mouse exits a component; returns the button to normal size
   * @param e  MouseEvent object pertaining to the component on which mouse exits
   */
  @Override
  public void mouseExited (MouseEvent e)
  {
    if (e.getComponent().equals (arrowButton))
    {
      if (!pressed)
      {
        ((JLabel)e.getComponent()).setIcon (new ImageIcon (arrowImage));
        enlargedArrowButton.setVisible (false);
      }
      tempEntered = false; //mouse has left component area
    }
  }
  
  /**
   * Invoked when the mouse presses on a component; displays a pressed version of the button
   * @param e  MouseEvent object pertaining to the component on which mouse button was pressed
   */
  @Override
  public void mousePressed (MouseEvent e)
  {
    if (e.getComponent().equals (arrowButton))
    {
      pressed = true;
      enlargedArrowButton.setVisible (false);
      ((JLabel) e.getComponent()).setIcon (new ImageIcon (invisibleImage));
      pressedArrowButton.setVisible (true);
      tempComp = e.getComponent (); //component user has pressed on
      tempEntered = true; //mouse is in component area
    }
  }
  
  public String getInfo ()
  {
    return info;
  }
  
  public void setInfo (String newInfo)
  {
    info = newInfo;
    infoLabel.setIcon (new ImageIcon ("assets/Pictures/" + newInfo + ".png"));
  }
  
  /**
   * Invoked when the mouse is released on a component; returns to main menu or displays normal version of button again
   * @param e  MouseEvent object pertaining to the component on which mouse button was released
   */
  @Override
  public void mouseReleased (MouseEvent e)
  {
    if (e.getComponent().equals (arrowButton))
    {
      pressed = false;
      arrowButton.setIcon (new ImageIcon (arrowImage));
      pressedArrowButton.setVisible (false);
      if (tempEntered)
      {
        if (info.equals ("introone"))
        {
          setInfo ("introfelix");
        }
        else if (info.equals ("introfelix"))
        {
          setInfo ("introtwo");
        }
        else if (info.equals ("introtwo"))
        {
          MainMenu.menuMusic.stop ();
          SwingUtilities.getWindowAncestor (this).dispose ();
          WordSearch ws = new WordSearch ();
        }
        else if (info.endsWith ("solved") && !(info.equals ("finalsolved")))
        {
          if (MainMenu.gameData.getSolvedWords().size() == 5)
          {
            System.out.println ("final");
            setInfo ("finalintro");
          }
          else
          {
        	  SwingUtilities.getWindowAncestor (this).dispose ();
            WordSearch ws = new WordSearch ();
          }
        }
        else if (info.equals ("finalintro"))
        {
          SwingUtilities.getWindowAncestor (this).dispose ();
          Thread thread = new Thread (new GameDriver ("final"));
          thread.start ();
        }
        else if (info.equals ("finalretry"))
        {
          SwingUtilities.getWindowAncestor (this).dispose ();
          Thread thread = new Thread (new GameDriver ("final"));
          thread.start ();
        }
        else if (info.equals ("allover") || info.equals ("finalsolved"))
        {
          SwingUtilities.getWindowAncestor (this).dispose ();
          Tiles.heartFound = false;
          MainMenu mm = new MainMenu (true, null);
        }
        else
        {
          setVisible (false);
          WordSearch.wordSearchMusic.stop ();
          SwingUtilities.getWindowAncestor (this).dispose ();
          Thread thread = new Thread (new GameDriver (info.substring (0, info.indexOf ("intro"))));
          thread.start ();
        }
      }
    }
  }
}