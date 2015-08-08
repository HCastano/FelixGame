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

public class HighScoresFrame extends JFrame implements MouseListener
{
  private int time;
  private String ocean;
  private BufferedImage arrowImage, enlargedArrowImage, pressedArrowImage, invisibleImage;
  private JLabel arrowButton, enlargedArrowButton, pressedArrowButton, emptyNameWarning;
  private JTextField nameField;
  private Component tempComp;
  private boolean tempEntered = true;
  private boolean pressed;
  private JPanel panel;
  private HighScores highScores = GameDriver.getHighScores ();
  
  public HighScoresFrame (int time, String ocean)
  {
    super ("Felix by 3AM Productions");
    this.time = time;
    this.ocean = ocean;
    
    panel = new JPanel ();
    panel.setLayout (null);
    try
    {
      nameField = new JTextField (20);
      BufferedImage backgroundImage = ImageIO.read (new File ("assets/Pictures/mainback.png"));
      arrowImage = ImageIO.read (new File ("assets/Pictures/highforwardarrow.png"));
      enlargedArrowImage = ImageIO.read (new File ("assets/Pictures/highenlargedforwardarrow.png"));
      pressedArrowImage = ImageIO.read (new File ("assets/Pictures/highpressedforwardarrow.png"));
      invisibleImage = ImageIO.read (new File ("assets/Pictures/invisible.png"));
      JLabel backgroundLabel = new JLabel (new ImageIcon ("assets/Pictures/newscorebackground.png"));
      emptyNameWarning = new JLabel (new ImageIcon ("assets/Pictures/enteraname.png"));
      arrowButton = new JLabel (new ImageIcon (arrowImage));
      enlargedArrowButton = new JLabel (new ImageIcon (enlargedArrowImage));
      pressedArrowButton = new JLabel (new ImageIcon (pressedArrowImage));
      nameField.setBounds (185, 125, 200, 25);
      emptyNameWarning.setBounds (205, 155, 150, 25);
      backgroundLabel.setBounds (0, 0, 450, 300);
      arrowButton.setBounds (335, 185, 85, 75);
      enlargedArrowButton.setBounds (325, 175, 105, 95);
      pressedArrowButton.setBounds (335, 185, 85, 75);
      add (nameField);
      add (emptyNameWarning);
      add (arrowButton);
      add (enlargedArrowButton);
      add (pressedArrowButton);
      add (backgroundLabel);
      emptyNameWarning.setVisible (false);
      enlargedArrowButton.setVisible (false);
      pressedArrowButton.setVisible (false);
      backgroundLabel.addMouseListener (this);
      arrowButton.addMouseListener (this);
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
    setSize (450, 300);
    setLocationRelativeTo (null);
    setVisible (true);
    setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
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
        if (!nameField.getText().equals (""))
        {
          highScores.addPlayer (ocean, nameField.getText (), time);
          dispose ();
          JFrame frame = new JFrame ();
          frame.add (new InfoScreen (ocean + "solved"));
          frame.setVisible (true);
          frame.setSize (900, 600);
          frame.setResizable (false);
          frame.setLocationRelativeTo (null);
        }
        else
        {
          emptyNameWarning.setVisible (true);
        }
      }
    }
  }
}