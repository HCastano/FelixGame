package felixgame; 

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class provides a template for all the sub-menus in the game (High Scores, Achievements, Help, About).
 * @author Ansh Juneja, last updated May 21, 2015
 * @version 1
 * 
 * Time Spent: 1.5 hours
 * <p>
 * <b> Instance Variables: </b>
 * <p>
 * <b> arrowImage </b> BufferedImage for the arrow button
 * <p>
 * <b> enlargedArrowImage </b> BufferedImage for the enlarged arrow button
 * <p>
 * <b> pressedArrowImage </b> BufferedImage for the pressed arrow button
 * <p>
 * <b> invisibleImage </b> BufferedImage for invisible image
 * <p>
 * <b> arrowButton </b> JLabel with arrowImage icon
 * <p>
 * <b> enlargedArrowButton </b> JLabel with enlargedArrowImage icon
 * <p>
 * <b> pressedArrowButton </b> JLabel with pressedArrowImage icon
 * <p>
 * <b> tempComp </b> Component object used in mouse listener methods to determine button animations
 * <p>
 * <b> tempEntered </b> boolean used in mouse listener methods to determine button animations
 * <p>
 * <b> pressed </b> boolean used in mouse listener methods to determine button animations
 */
public class SubMenu extends JPanel implements MouseListener
{
  BufferedImage arrowImage, enlargedArrowImage, pressedArrowImage, invisibleImage, printImage, enlargedPrintImage, pressedPrintImage;
  JLabel arrowButton, enlargedArrowButton, pressedArrowButton, printButton, enlargedPrintButton, pressedPrintButton, backgroundLabel;
  private Component tempComp;
  private boolean tempEntered = true;
  private boolean pressed;
  
  /**
   * Constructor which takes String parameter to determine file path of background to be shown in sub menu. It adds and displays the background and the
   * return arrow button.
   * @param backgroundPath  String containing file path to background image of sub menu
   * <p>
   * <b> backgroundImage </b> BufferedImage containing background image of sub menu
   * <p>
   * <b> backgroundLabel </b> JLabel containing background image as icon
   */
  public SubMenu (String backgroundPath, boolean addFirst)
  {
    setLayout (null);
    try
    {
      BufferedImage backgroundImage = ImageIO.read (new File ("assets/Pictures/" + backgroundPath + ".png"));
      printImage = ImageIO.read (new File ("assets/Pictures/printbutton.png"));
      enlargedPrintImage = ImageIO.read (new File ("assets/Pictures/enlargedprintbutton.png"));
      pressedPrintImage = ImageIO.read (new File ("assets/Pictures/pressedprintbutton.png"));
      arrowImage = ImageIO.read (new File ("assets/Pictures/arrow.png"));
      enlargedArrowImage = ImageIO.read (new File ("assets/Pictures/enlargedarrow.png"));
      pressedArrowImage = ImageIO.read (new File ("assets/Pictures/pressedarrow.png"));
      invisibleImage = ImageIO.read (new File ("assets/Pictures/invisible.png"));
      backgroundLabel = new JLabel (new ImageIcon (backgroundImage));
      arrowButton = new JLabel (new ImageIcon (arrowImage));
      enlargedArrowButton = new JLabel (new ImageIcon (enlargedArrowImage));
      pressedArrowButton = new JLabel (new ImageIcon (pressedArrowImage));
      printButton = new JLabel (new ImageIcon (printImage));
      enlargedPrintButton = new JLabel (new ImageIcon (enlargedPrintImage));
      pressedPrintButton = new JLabel (new ImageIcon (pressedPrintImage));
      backgroundLabel.setBounds (0, 0, 900, 600);
      arrowButton.setBounds (20, 10, 90, 80);
      enlargedArrowButton.setBounds (10, 0, 110, 100);
      pressedArrowButton.setBounds (20, 10, 90, 80);
      printButton.setBounds (800, 15, 70, 70);
      enlargedPrintButton.setBounds (790, 5, 90, 90);
      pressedPrintButton.setBounds (800, 15, 70, 70);
      add (arrowButton);
      add (enlargedArrowButton);
      add (pressedArrowButton);
      if (addFirst)
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
  
  public void addBackground ()
  {
    add (backgroundLabel);
  }
  
  public void addPrintButton ()
  {
    add (printButton);
    add (enlargedPrintButton);
    add (pressedPrintButton);
    enlargedPrintButton.setVisible (false);
    pressedPrintButton.setVisible (false);
    printButton.addMouseListener (this);
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
    if (e.getComponent().equals (printButton))
    {
      if (!pressed)
      {
        enlargedPrintButton.setVisible (true);
        printButton.setIcon (new ImageIcon (invisibleImage));
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
    if (e.getComponent().equals (printButton))
    {
      if (!pressed)
      {
        ((JLabel)e.getComponent()).setIcon (new ImageIcon (printImage));
        enlargedPrintButton.setVisible (false);
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
    if (e.getComponent().equals (printButton))
    {
      pressed = true;
      enlargedPrintButton.setVisible (false);
      ((JLabel) e.getComponent()).setIcon (new ImageIcon (invisibleImage));
      pressedPrintButton.setVisible (true);
      tempComp = e.getComponent (); //component user has pressed on
      tempEntered = true; //mouse is in component area
    }
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
        this.setVisible (false);
        if (GameDriver.paused)
        {
          GameDriver.paused = false;
          GameDriver.gameMusic.resume ();
        }
      }
    }
    if (e.getComponent().equals (printButton))
    {
      pressed = false;
      printButton.setIcon (new ImageIcon (printImage));
      pressedPrintButton.setVisible (false);
      if (tempEntered)
      {
    	  String ocean = ""; 
          if (ArcticScoresPanel.visible){
            ocean = "Arctic"; 
          }
          else if (AntarcticScoresPanel.visible){
            ocean = "Antarctic"; 
          }
          else if(AtlanticScoresPanel.visible){
            ocean = "Atlantic"; 
          }
          else if (PacificScoresPanel.visible){
            ocean = "Pacific"; 
          }
          else{
            ocean = "Indian";
          }
          
          PrintHighScores print = new PrintHighScores(ocean); 
      }
    }
  } 
}
