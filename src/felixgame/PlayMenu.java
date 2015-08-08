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
 * This class creates the play menu
 * @author Ansh Juneja, last updated May 21, 2015
 * @version 1
 * 
 * Time Spent: 1 minute
 */
public class PlayMenu extends JPanel implements MouseListener
{
  private JLabel newGameButton, loadGameButton, saveGameButton, saveGameAsButton, enlargedNewGameButton, enlargedLoadGameButton, enlargedSaveGameButton,
    enlargedSaveGameAsButton, pressedNewGameButton, pressedLoadGameButton, pressedSaveGameButton, pressedSaveGameAsButton, arrowButton, enlargedArrowButton,
    pressedArrowButton, backgroundLabel;
  private BufferedImage newGameImage, loadGameImage, saveGameImage, saveGameAsImage, enlargedNewGameImage, enlargedLoadGameImage, enlargedSaveGameImage,
    enlargedSaveGameAsImage, pressedNewGameImage, pressedLoadGameImage, pressedSaveGameImage, pressedSaveGameAsImage, arrowImage, enlargedArrowImage, 
    pressedArrowImage, invisibleImage;
  private Component tempComp;
  private boolean tempEntered = true;
  private boolean pressed;
  
  /**
   * Constructor creates the play menu and passes the file path for the play background into the superclass constructor
   */
  public PlayMenu ()
  {
    setLayout (null);
    try
    {
      BufferedImage backgroundImage = ImageIO.read (new File ("assets/Pictures/playbackground.png"));
      arrowImage = ImageIO.read (new File ("assets/Pictures/arrow.png"));
      enlargedArrowImage = ImageIO.read (new File ("assets/Pictures/enlargedarrow.png"));
      pressedArrowImage = ImageIO.read (new File ("assets/Pictures/pressedarrow.png"));
      invisibleImage = ImageIO.read (new File ("assets/Pictures/invisible.png"));
      newGameImage = ImageIO.read (new File ("assets/Pictures/newgame.png"));
      loadGameImage = ImageIO.read (new File ("assets/Pictures/loadgame.png"));
      saveGameImage = ImageIO.read (new File ("assets/Pictures/savegame.png"));
      saveGameAsImage = ImageIO.read (new File ("assets/Pictures/savegameas.png"));
      enlargedNewGameImage = ImageIO.read (new File ("assets/Pictures/enlargednewgame.png"));
      enlargedLoadGameImage = ImageIO.read (new File ("assets/Pictures/enlargedloadgame.png"));
      enlargedSaveGameImage = ImageIO.read (new File ("assets/Pictures/enlargedsavegame.png"));
      enlargedSaveGameAsImage = ImageIO.read (new File ("assets/Pictures/enlargedsavegameas.png"));
      pressedNewGameImage = ImageIO.read (new File ("assets/Pictures/pressednewgame.png"));
      pressedLoadGameImage = ImageIO.read (new File ("assets/Pictures/pressedloadgame.png"));
      pressedSaveGameImage = ImageIO.read (new File ("assets/Pictures/pressedsavegame.png"));
      pressedSaveGameAsImage = ImageIO.read (new File ("assets/Pictures/pressedsavegameas.png"));
      
      backgroundLabel = new JLabel (new ImageIcon (backgroundImage));
      arrowButton = new JLabel (new ImageIcon (arrowImage));
      enlargedArrowButton = new JLabel (new ImageIcon (enlargedArrowImage));
      pressedArrowButton = new JLabel (new ImageIcon (pressedArrowImage));
      newGameButton = new JLabel (new ImageIcon (newGameImage));
      loadGameButton = new JLabel (new ImageIcon (loadGameImage));
      saveGameButton = new JLabel (new ImageIcon (saveGameImage));
      saveGameAsButton = new JLabel (new ImageIcon (saveGameAsImage));
      enlargedNewGameButton = new JLabel (new ImageIcon (enlargedNewGameImage));
      enlargedLoadGameButton = new JLabel (new ImageIcon (enlargedLoadGameImage));
      enlargedSaveGameButton = new JLabel (new ImageIcon (enlargedSaveGameImage));
      enlargedSaveGameAsButton = new JLabel (new ImageIcon (enlargedSaveGameAsImage));
      pressedNewGameButton = new JLabel (new ImageIcon (pressedNewGameImage));
      pressedLoadGameButton = new JLabel (new ImageIcon (pressedLoadGameImage));
      pressedSaveGameButton = new JLabel (new ImageIcon (pressedSaveGameImage));
      pressedSaveGameAsButton = new JLabel (new ImageIcon (pressedSaveGameAsImage));
      
      backgroundLabel.setBounds (0, 0, 900, 600);
      arrowButton.setBounds (20, 10, 90, 80);
      enlargedArrowButton.setBounds (10, 0, 110, 100);
      pressedArrowButton.setBounds (20, 10, 90, 80);
      newGameButton.setBounds (225, 230, 210, 145);
      loadGameButton.setBounds (455, 230, 210, 145);
      saveGameButton.setBounds (25, 250, 185, 125);
      saveGameAsButton.setBounds (685, 250, 185, 125);
      enlargedNewGameButton.setBounds (215, 220, 230, 165);
      enlargedLoadGameButton.setBounds (445, 220, 230, 165);
      enlargedSaveGameButton.setBounds (20, 245, 195, 130);
      enlargedSaveGameAsButton.setBounds (680, 245, 195, 130);
      pressedNewGameButton.setBounds (225, 230, 210, 145);
      pressedLoadGameButton.setBounds (455, 230, 210, 145);
      pressedSaveGameButton.setBounds (20, 245, 190, 130);
      pressedSaveGameAsButton.setBounds (680, 245, 190, 130);
      
      add (newGameButton);
      add (loadGameButton);
      add (saveGameButton);
      add (saveGameAsButton);
      add (enlargedNewGameButton);
      add (enlargedLoadGameButton);
      add (enlargedSaveGameButton);
      add (enlargedSaveGameAsButton);
      add (pressedNewGameButton);
      add (pressedLoadGameButton);
      add (pressedSaveGameButton);
      add (pressedSaveGameAsButton);
      add (arrowButton);
      add (enlargedArrowButton);
      add (pressedArrowButton);
      add (backgroundLabel);
      
      enlargedNewGameButton.setVisible (false);
      enlargedLoadGameButton.setVisible (false);
      enlargedSaveGameButton.setVisible (false);
      enlargedSaveGameAsButton.setVisible (false);
      enlargedArrowButton.setVisible (false);
      pressedNewGameButton.setVisible (false);
      pressedLoadGameButton.setVisible (false);
      pressedSaveGameButton.setVisible (false);
      pressedSaveGameAsButton.setVisible (false);
      pressedArrowButton.setVisible (false);
      
      backgroundLabel.addMouseListener (this);
      arrowButton.addMouseListener (this);
      newGameButton.addMouseListener (this);
      loadGameButton.addMouseListener (this);
      saveGameButton.addMouseListener (this);
      saveGameAsButton.addMouseListener (this);
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
    setSize (900, 600);
    setVisible (true);
  }
  
  public BufferedImage getImage (Component button)
  {
    if (button.equals (newGameButton))
      return newGameImage;
    else if (button.equals (loadGameButton))
      return loadGameImage;
    else if (button.equals (saveGameButton))
      return saveGameImage;
    else if (button.equals (saveGameAsButton))
      return saveGameAsImage;
    else
      return arrowImage;
  }
  
  public Component getButton (Component button, boolean pressedOrEnlarged)
  {
    if (button.equals (newGameButton))
      return pressedOrEnlarged ? pressedNewGameButton : enlargedNewGameButton;
    else if (button.equals (loadGameButton))
      return pressedOrEnlarged ? pressedLoadGameButton : enlargedLoadGameButton;
    else if (button.equals (saveGameButton))
      return pressedOrEnlarged ? pressedSaveGameButton : enlargedSaveGameButton;
    else if (button.equals (saveGameAsButton))
      return pressedOrEnlarged ? pressedSaveGameAsButton : enlargedSaveGameAsButton;
    else
      return pressedOrEnlarged ? pressedArrowButton : enlargedArrowButton;
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
    if (!e.getComponent().equals (backgroundLabel))
    {
      if (!pressed)
      {
        getButton (e.getComponent(), false).setVisible (true);
        ((JLabel) e.getComponent()).setIcon (new ImageIcon (invisibleImage));
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
    if (!e.getComponent().equals (backgroundLabel))
    {
      ((JLabel) e.getComponent()).setIcon (new ImageIcon (getImage (e.getComponent ())));
      getButton (e.getComponent(), false).setVisible (false);
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
    if (!e.getComponent().equals (backgroundLabel))
    {
      pressed = true;
      getButton (e.getComponent(), false).setVisible (false);
      ((JLabel) e.getComponent()).setIcon (new ImageIcon (invisibleImage));
      getButton (e.getComponent(), true).setVisible (true);
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
    if (!e.getComponent().equals (backgroundLabel))
    {
      pressed = false;
      ((JLabel) e.getComponent()).setIcon (new ImageIcon (getImage (e.getComponent ())));
      getButton (e.getComponent(), true).setVisible (false);
      if (tempEntered)
      {
        if (e.getComponent().equals (arrowButton))
          this.setVisible (false);
      }
    }
  }
}