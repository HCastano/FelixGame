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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class creates the high scores menu
 * @author Ansh Juneja, last updated May 21, 2015
 * @version 1
 * 
 * Time Spent: 1 minute
 */
public class HighScoresMenu extends JPanel implements MouseListener
{
  BufferedImage arrowImage, enlargedArrowImage, pressedArrowImage, invisibleImage, pacificImage, enlargedPacificImage, pressedPacificImage,
    antarcticImage, enlargedAntarcticImage, pressedAntarcticImage, indianImage, enlargedIndianImage, pressedIndianImage, arcticImage,
    enlargedArcticImage, pressedArcticImage, atlanticImage, enlargedAtlanticImage, pressedAtlanticImage, resetImage, enlargedResetImage,
    pressedResetImage;
  JLabel arrowButton, enlargedArrowButton, pressedArrowButton, pacificButton, enlargedPacificButton, pressedPacificButton, antarcticButton,
    enlargedAntarcticButton, pressedAntarcticButton, indianButton, enlargedIndianButton, pressedIndianButton, arcticButton, enlargedArcticButton,
    pressedArcticButton, atlanticButton, enlargedAtlanticButton, pressedAtlanticButton, resetButton, enlargedResetButton, pressedResetButton,
    backgroundLabel;
  private Component tempComp;
  private boolean tempEntered = true;
  private boolean pressed;
  private JPanel arcticScoresPanel, antarcticScoresPanel, atlanticScoresPanel, indianScoresPanel, pacificScoresPanel;
  
  /**
   * Constructor creates the high scores menu and passes the file path for the high scores background into the superclass constructor
   */
  public HighScoresMenu ()
  {
    arcticScoresPanel = new ArcticScoresPanel ();
    antarcticScoresPanel = new AntarcticScoresPanel ();
    atlanticScoresPanel = new AtlanticScoresPanel ();
    indianScoresPanel = new IndianScoresPanel ();
    pacificScoresPanel = new PacificScoresPanel ();
    
    setLayout (null);
    try
    {
      BufferedImage backgroundImage = ImageIO.read (new File ("assets/Pictures/hsbackground.png"));
      arrowImage = ImageIO.read (new File ("assets/Pictures/arrow.png"));
      enlargedArrowImage = ImageIO.read (new File ("assets/Pictures/enlargedarrow.png"));
      pressedArrowImage = ImageIO.read (new File ("assets/Pictures/pressedarrow.png"));
      invisibleImage = ImageIO.read (new File ("assets/Pictures/invisible.png"));
      pacificImage = ImageIO.read (new File ("assets/Pictures/pacificscores.png"));
      antarcticImage = ImageIO.read (new File ("assets/Pictures/antarcticscores.png"));
      indianImage = ImageIO.read (new File ("assets/Pictures/indianscores.png"));
      arcticImage = ImageIO.read (new File ("assets/Pictures/arcticscores.png"));
      atlanticImage = ImageIO.read (new File ("assets/Pictures/atlanticscores.png"));
      resetImage = ImageIO.read (new File ("assets/Pictures/resetbutton.png"));
      enlargedResetImage = ImageIO.read (new File ("assets/Pictures/enlargedresetbutton.png"));
      pressedResetImage = ImageIO.read (new File ("assets/Pictures/pressedresetbutton.png"));
      enlargedPacificImage = ImageIO.read (new File ("assets/Pictures/enlargedpacificscores.png"));
      enlargedAntarcticImage = ImageIO.read (new File ("assets/Pictures/enlargedantarcticscores.png"));
      enlargedIndianImage = ImageIO.read (new File ("assets/Pictures/enlargedindianscores.png"));
      enlargedArcticImage = ImageIO.read (new File ("assets/Pictures/enlargedarcticscores.png"));
      enlargedAtlanticImage = ImageIO.read (new File ("assets/Pictures/enlargedatlanticscores.png"));
      pressedPacificImage = ImageIO.read (new File ("assets/Pictures/pressedpacificscores.png"));
      pressedAntarcticImage = ImageIO.read (new File ("assets/Pictures/pressedantarcticscores.png"));
      pressedIndianImage = ImageIO.read (new File ("assets/Pictures/pressedindianscores.png"));
      pressedArcticImage = ImageIO.read (new File ("assets/Pictures/pressedarcticscores.png"));
      pressedAtlanticImage = ImageIO.read (new File ("assets/Pictures/pressedatlanticscores.png"));
      backgroundLabel = new JLabel (new ImageIcon (backgroundImage));
      arrowButton = new JLabel (new ImageIcon (arrowImage));
      enlargedArrowButton = new JLabel (new ImageIcon (enlargedArrowImage));
      pressedArrowButton = new JLabel (new ImageIcon (pressedArrowImage));
      pacificButton = new JLabel (new ImageIcon (pacificImage));
      antarcticButton = new JLabel (new ImageIcon (antarcticImage));
      indianButton = new JLabel (new ImageIcon (indianImage));
      arcticButton = new JLabel (new ImageIcon (arcticImage));
      atlanticButton = new JLabel (new ImageIcon (atlanticImage));
      enlargedPacificButton = new JLabel (new ImageIcon (enlargedPacificImage));
      enlargedAntarcticButton = new JLabel (new ImageIcon (enlargedAntarcticImage));
      enlargedIndianButton = new JLabel (new ImageIcon (enlargedIndianImage));
      enlargedArcticButton = new JLabel (new ImageIcon (enlargedArcticImage));
      enlargedAtlanticButton = new JLabel (new ImageIcon (enlargedAtlanticImage));
      pressedPacificButton = new JLabel (new ImageIcon (pressedPacificImage));
      pressedAntarcticButton = new JLabel (new ImageIcon (pressedAntarcticImage));
      pressedIndianButton = new JLabel (new ImageIcon (pressedIndianImage));
      pressedArcticButton = new JLabel (new ImageIcon (pressedArcticImage));
      pressedAtlanticButton = new JLabel (new ImageIcon (pressedAtlanticImage));
      resetButton = new JLabel (new ImageIcon (resetImage));
      enlargedResetButton = new JLabel (new ImageIcon (enlargedResetImage));
      pressedResetButton = new JLabel (new ImageIcon (pressedResetImage));
      arcticScoresPanel.setBounds (0, 0, 900, 600);
      antarcticScoresPanel.setBounds (0, 0, 900, 600);
      atlanticScoresPanel.setBounds (0, 0, 900, 600);
      indianScoresPanel.setBounds (0, 0, 900, 600);
      pacificScoresPanel.setBounds (0, 0, 900, 600);
      backgroundLabel.setBounds (0, 0, 900, 600);
      arrowButton.setBounds (20, 10, 90, 80);
      enlargedArrowButton.setBounds (10, 0, 110, 100);
      pressedArrowButton.setBounds (20, 10, 90, 80);
      pacificButton.setBounds (352, 230, 210, 145);
      antarcticButton.setBounds (140, 265, 215, 95);
      indianButton.setBounds (560, 265, 215, 95);
      arcticButton.setBounds (30, 275, 110, 80);
      atlanticButton.setBounds (770, 275, 110, 80);
      enlargedPacificButton.setBounds (332, 225, 250, 155);
      enlargedAntarcticButton.setBounds (140, 260, 215, 105);
      enlargedIndianButton.setBounds (550, 255, 235, 115);
      enlargedArcticButton.setBounds (20, 265, 130, 100);
      enlargedAtlanticButton.setBounds (760, 265, 130, 100);
      pressedPacificButton.setBounds (352, 230, 210, 145);
      pressedAntarcticButton.setBounds (140, 265, 215, 95);
      pressedIndianButton.setBounds (560, 265, 215, 95);
      pressedArcticButton.setBounds (30, 275, 110, 80);
      pressedAtlanticButton.setBounds (770, 275, 110, 80);
      resetButton.setBounds (760, 50, 120, 80);
      enlargedResetButton.setBounds (750, 40, 140, 100);
      pressedResetButton.setBounds (760, 50, 120, 80);
      add (arcticScoresPanel);
      add (antarcticScoresPanel);
      add (atlanticScoresPanel);
      add (indianScoresPanel);
      add (pacificScoresPanel);
      add (arrowButton);
      add (enlargedArrowButton);
      add (pressedArrowButton);
      add (pacificButton);
      add (antarcticButton);
      add (indianButton);
      add (arcticButton);
      add (atlanticButton);
      add (enlargedPacificButton);
      add (enlargedAntarcticButton);
      add (enlargedIndianButton);
      add (enlargedArcticButton);
      add (enlargedAtlanticButton);
      add (pressedPacificButton);
      add (pressedAntarcticButton);
      add (pressedIndianButton);
      add (pressedArcticButton);
      add (pressedAtlanticButton);
      add (resetButton);
      add (enlargedResetButton);
      add (pressedResetButton);
      add (backgroundLabel);
      arcticScoresPanel.setVisible (false);
      antarcticScoresPanel.setVisible (false);
      atlanticScoresPanel.setVisible (false);
      indianScoresPanel.setVisible (false);
      pacificScoresPanel.setVisible (false);
      enlargedArrowButton.setVisible (false);
      pressedArrowButton.setVisible (false);
      enlargedPacificButton.setVisible (false);
      enlargedAntarcticButton.setVisible (false);
      enlargedIndianButton.setVisible (false);
      enlargedArcticButton.setVisible (false);
      enlargedAtlanticButton.setVisible (false);
      pressedPacificButton.setVisible (false);
      pressedAntarcticButton.setVisible (false);
      pressedIndianButton.setVisible (false);
      pressedArcticButton.setVisible (false);
      pressedAtlanticButton.setVisible (false);
      enlargedResetButton.setVisible (false);
      pressedResetButton.setVisible (false);
      backgroundLabel.addMouseListener (this);
      arrowButton.addMouseListener (this);
      pacificButton.addMouseListener (this);
      antarcticButton.addMouseListener (this);
      indianButton.addMouseListener (this);
      arcticButton.addMouseListener (this);
      atlanticButton.addMouseListener (this);
      resetButton.addMouseListener (this);
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
    
    setSize (900, 600);
    setVisible (true);
  }
  
  /**
   * Returns pressed or enlarged version of button passed in
   * @param button  button used to determine enlarged or pressed version to return
   * @param pressedOrEnlarged  boolean used to determine whether to return pressed or enlarged button
   * @return pressed or enlarged version of button
   */
  public Component getButton (Component button, boolean pressedOrEnlarged)
  {
    if (button.equals (pacificButton))
      return pressedOrEnlarged ? pressedPacificButton : enlargedPacificButton;
    else if (button.equals (antarcticButton))
      return pressedOrEnlarged ? pressedAntarcticButton : enlargedAntarcticButton;
    else if (button.equals (indianButton))
      return pressedOrEnlarged ? pressedIndianButton : enlargedIndianButton;
    else if (button.equals (arcticButton))
      return pressedOrEnlarged ? pressedArcticButton : enlargedArcticButton;
    else if (button.equals (atlanticButton))
      return pressedOrEnlarged ? pressedAtlanticButton : enlargedAtlanticButton;
    else if (button.equals (resetButton))
      return pressedOrEnlarged ? pressedResetButton : enlargedResetButton;
    else
      return pressedOrEnlarged ? pressedArrowButton : enlargedArrowButton;
  }
  
  /**
   * Returns image of button depending on button passed in (Component)
   * @param button  button used to determine which image to return
   * @return image of button
   */
  public BufferedImage getImage (Component button)
  {
    if (button.equals (pacificButton))
      return pacificImage;
    else if (button.equals (antarcticButton))
      return antarcticImage;
    else if (button.equals (indianButton))
      return indianImage;
    else if (button.equals (arcticButton))
      return arcticImage;
    else if (button.equals (atlanticButton))
      return atlanticImage;
    else if (button.equals (resetButton))
      return resetImage;
    else
      return arrowImage;
  }
  
  public void mouseClicked (MouseEvent e)
  {
  }
  
/**
   * Invoked when the mouse enters a component.
   * 
   * @param e  MouseEvent object pertaining to the component on which mouse entered
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
   * Invoked when the mouse exits a component.
   * 
   * @param e  MouseEvent object pertaining to the component on which mouse exited
   */
  @Override
  public void mouseExited (MouseEvent e)
  {
    if (!e.getComponent().equals (backgroundLabel))
    {
      if (!pressed)
      {
        ((JLabel) e.getComponent()).setIcon (new ImageIcon (getImage (e.getComponent ())));
        getButton (e.getComponent(), false).setVisible (false);
      }
      tempEntered = false; //mouse has left component area
    }
  }
  
  /**
   * Invoked when a mouse button has been pressed on a component.
   * 
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
   * Invoked when a mouse button has been released on a component.
   * 
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
        if (e.getComponent().equals (pacificButton))
        {
          pacificScoresPanel.setVisible (true);
          ((PacificScoresPanel)pacificScoresPanel).removeScores ();
          ((PacificScoresPanel)pacificScoresPanel).drawScores ();
        }
        else if (e.getComponent().equals (antarcticButton))
        {
          antarcticScoresPanel.setVisible (true);
          ((AntarcticScoresPanel)antarcticScoresPanel).removeScores ();
          ((AntarcticScoresPanel)antarcticScoresPanel).drawScores ();
        }
        else if (e.getComponent().equals (indianButton))
        {
          indianScoresPanel.setVisible (true);
          ((IndianScoresPanel)indianScoresPanel).removeScores ();
          ((IndianScoresPanel)indianScoresPanel).drawScores ();
        }
        else if (e.getComponent().equals (arcticButton))
        {
          arcticScoresPanel.setVisible (true);
          ((ArcticScoresPanel)arcticScoresPanel).removeScores ();
          ((ArcticScoresPanel)arcticScoresPanel).drawScores ();
        }
        else if (e.getComponent().equals (atlanticButton))
        {
          atlanticScoresPanel.setVisible (true);
          ((AtlanticScoresPanel)atlanticScoresPanel).removeScores ();
          ((AtlanticScoresPanel)atlanticScoresPanel).drawScores ();
        }
        else if (e.getComponent().equals (resetButton))
          (new HighScores()).resetScores ();
        else
          setVisible (false);
      }
    }
  }
}