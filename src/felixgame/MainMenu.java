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
 * This class creates and adds functionality to the main menu for the Felix game
 * 
 * @author Ansh Juneja, last updated May 28, 2015
 * @version 3
 * Time Spent: 7 hours
 * <p>
 * <b> Instance Variables </b>
 * <p>
 * <b> background </b> Background picture of main menu label
 * <p>
 * <b> playButton </b> Play button picture label
 * <p>
 * <b> highscoresButton </b> High scores button picture label
 * <p>
 * <b> achievementsButton </b> Achievements button picture label
 * <p>
 * <b> helpButton </b> Help button picture label
 * <p>
 * <b> quitButton </b> Quit button picture label
 * <p>
 * <b> aboutButton </b> About button picture label
 * <p>
 * <b> pressedPlayButton </b> Play button without shadow picture label
 * <p>
 * <b> pressedHighScoresButton </b> High scores button without shadow picture label
 * <p>
 * <b> pressedAchievementsButton </b> Achievements button without shadow picture label
 * <p>
 * <b> pressedHelpButton </b> Help button without shadow picture label
 * <p>
 * <b> pressedQuitButton </b> Quit button without shadow picture label
 * <p>
 * <b> pressedAboutButton </b> About button without shadow picture label
 * <p>
 * <b> enlargedPlayButton </b> Play button enlarged picture label
 * <p>
 * <b> enlargedHighScoresButton </b> High scores button enlarged picture label
 * <p>
 * <b> enlargedAchievementsButton </b> Achievements button enlarged picture label
 * <p>
 * <b> enlargedHelpButton </b> Help button enlarged picture label
 * <p>
 * <b> enlargedQuitButton </b> Quit button enlarged picture label
 * <p>
 * <b> playImage </b> Play button image
 * <p>
 * <b> highscoresImage </b> High scores button image
 * <p>
 * <b> achievementsImage </b> Achievements button image
 * <p>
 * <b> helpImage </b> Help  button image
 * <p>
 * <b> quitImage </b> Quit button image
 * <p>
 * <b> aboutImage </b> About button image
 * <p>
 * <b> pressedPlayImage </b> Pressed play button image
 * <p>
 * <b> pressedHighScoresImage </b> Pressed high scores button image
 * <p>
 * <b> pressedAchievementsImage </b> Pressed achievements button image
 * <p>
 * <b> pressedHelpImage </b> Pressed help button image
 * <p>
 * <b> pressedQuitImage </b> Pressed quit button image
 * <p>
 * <b> pressedAboutImage </b> Pressed about button image
 * <p>
 * <b> enlargedPlayImage </b> Enlarged play button image
 * <p>
 * <b> enlargedHighScoresImage </b> Enlarged high scores button image
 * <p>
 * <b> enlargedAchievementsImage </b> Enlarged achievements button image
 * <p>
 * <b> enlargedHelpImage </b> Enlarged help button image
 * <p>
 * <b> enlargedQuitImage </b> Enlarged quit button image
 * <p>
 * <b> enlargedAboutImage </b> Enlarged about button image
 * <p>
 * <b> invisibleImage </b> Image with no content
 * <p>
 * <b> tempComp </b> temporary Component object used in MouseListener methods to determine button animations
 * <p>
 * <b> tempEntered </b> boolean used in MouseListener methods to determine button animations
 * <p>
 * <b> pressed </b> boolean used in MouseListener methods to determine button animations
 * <p>
 * <b> panel </b> JPanel containing main menu
 * <p>
 * <b> introPanel </b> JPanel containing play menu
 * <p>
 * <b> highScoresPanel </b> JPanel containing high scores menu
 * <p>
 * <b> achievementsPanel </b> JPanel containing achievements menu
 * <p>
 * <b> helpPanel </b> JPanel containing help menu
 * <p>
 * <b> aboutPanel </b> JPanel containing about menu
 * <p>
 * <b> wordSearch </b> JPanel containing word search
 * <p>
 * <b> gameData </b> GameData object containing info about game progress
 * <p>
 * <b> kalimba </b> Music object which plays "Kalimba" song
 * <p>
 * <b> imaginaryFriends </b> Music object which plays "Imaginary Friends" song
 */
public class MainMenu extends JFrame implements MouseListener, KeyListener
{
  private JLabel background, playButton, highscoresButton, achievementsButton, helpButton, quitButton, aboutButton, pressedPlayButton, pressedHighScoresButton, 
    pressedAchievementsButton, pressedHelpButton, pressedQuitButton, pressedAboutButton, enlargedPlayButton, enlargedHighScoresButton, enlargedAchievementsButton,
    enlargedHelpButton, enlargedQuitButton, enlargedAboutButton;
  private BufferedImage playImage, highscoresImage, achievementsImage, helpImage, quitImage, aboutImage, pressedPlayImage, pressedHighScoresImage, 
    pressedAchievementsImage, pressedHelpImage, pressedQuitImage, pressedAboutImage, enlargedPlayImage, enlargedHighScoresImage, enlargedAchievementsImage,
    enlargedHelpImage, enlargedQuitImage, enlargedAboutImage, invisibleImage;
  private Component tempComp;
  private boolean tempEntered = true;
  private boolean pressed;
  private boolean showIntro = true;
  private JPanel panel, introPanel, introPanelTwo, highScoresPanel, achievementsPanel, helpPanel, aboutPanel;
  private String tempPanel;
  public static GameData gameData;
  public static Music menuMusic;
  private static MainMenu mm;
  
  /**
   * Constructor which creates and adds all the menu images to the main frame. It also adds mouse listeners to the button images.
   * <p>
   * <b> Local Variables: </b>
   * <p>
   * <b> backgroundImage </b> Background image
   */
  public MainMenu (boolean createGameData, GameData gd)
  {
    super ("Felix by 3AM Productions");
    SplashScreen ss = new SplashScreen ();
    
    if (createGameData)
      gameData = new GameData ();
    else
    {
      gameData = gd;
      showIntro = false;
    }
    panel = new JPanel ();
    introPanel = new InfoScreen ("introone");
    introPanelTwo = new InfoScreen ("introtwo");
    highScoresPanel = new HighScoresMenu ();
    achievementsPanel = new AchievementsMenu ();
    helpPanel = new HelpMenu ();
    aboutPanel = new AboutMenu ();
    
    
    menuMusic = new Music ("The Builder");
    
    panel.setLayout (null);
    try
    {
      BufferedImage backgroundImage = ImageIO.read (new File ("assets/Pictures/background.png"));
      playImage = ImageIO.read (new File ("assets/Pictures/play.png"));
      highscoresImage = ImageIO.read (new File ("assets/Pictures/highscores.png"));
      achievementsImage = ImageIO.read (new File ("assets/Pictures/achievements.png"));
      helpImage = ImageIO.read (new File ("assets/Pictures/help.png"));
      quitImage = ImageIO.read (new File ("assets/Pictures/quit.png"));
      aboutImage = ImageIO.read (new File ("assets/Pictures/about.png"));
      pressedPlayImage = ImageIO.read (new File ("assets/Pictures/pressedplay.png"));
      pressedHighScoresImage = ImageIO.read (new File ("assets/Pictures/pressedhighscores.png"));
      pressedAchievementsImage = ImageIO.read (new File ("assets/Pictures/pressedachievements.png"));
      pressedHelpImage = ImageIO.read (new File ("assets/Pictures/pressedhelp.png"));
      pressedQuitImage = ImageIO.read (new File ("assets/Pictures/pressedquit.png"));
      pressedAboutImage = ImageIO.read (new File ("assets/Pictures/pressedabout.png"));
      invisibleImage = ImageIO.read (new File ("assets/Pictures/invisible.png"));
      enlargedPlayImage = ImageIO.read (new File ("assets/Pictures/enlargedplay.png"));
      enlargedHighScoresImage = ImageIO.read (new File ("assets/Pictures/enlargedhighscores.png"));
      enlargedAchievementsImage = ImageIO.read (new File ("assets/Pictures/enlargedachievements.png"));
      enlargedHelpImage = ImageIO.read (new File ("assets/Pictures/enlargedhelp.png"));
      enlargedQuitImage = ImageIO.read (new File ("assets/Pictures/enlargedquit.png"));
      enlargedAboutImage = ImageIO.read (new File ("assets/Pictures/enlargedabout.png"));
      
      background = new JLabel (new ImageIcon (backgroundImage));
      playButton = new JLabel (new ImageIcon (playImage));
      highscoresButton = new JLabel (new ImageIcon (highscoresImage));
      achievementsButton = new JLabel (new ImageIcon (achievementsImage));
      helpButton = new JLabel (new ImageIcon (helpImage));
      quitButton = new JLabel (new ImageIcon (quitImage));
      aboutButton = new JLabel (new ImageIcon (aboutImage));
      pressedPlayButton = new JLabel (new ImageIcon (pressedPlayImage));
      pressedHighScoresButton = new JLabel (new ImageIcon (pressedHighScoresImage));
      pressedAchievementsButton = new JLabel (new ImageIcon (pressedAchievementsImage));
      pressedHelpButton = new JLabel (new ImageIcon (pressedHelpImage));
      pressedQuitButton = new JLabel (new ImageIcon (pressedQuitImage));
      pressedAboutButton = new JLabel (new ImageIcon (pressedAboutImage));
      enlargedPlayButton = new JLabel (new ImageIcon (enlargedPlayImage));
      enlargedHighScoresButton = new JLabel (new ImageIcon (enlargedHighScoresImage));
      enlargedAchievementsButton = new JLabel (new ImageIcon (enlargedAchievementsImage));
      enlargedHelpButton = new JLabel (new ImageIcon (enlargedHelpImage));
      enlargedQuitButton = new JLabel (new ImageIcon (enlargedQuitImage));
      enlargedAboutButton = new JLabel (new ImageIcon (enlargedAboutImage));
      
      background.setBounds (0, 0, 900, 600);
      playButton.setBounds (352, 230, 210, 145);
      highscoresButton.setBounds (140, 265, 215, 95);
      achievementsButton.setBounds (560, 265, 215, 95);
      helpButton.setBounds (30, 275, 110, 80);
      quitButton.setBounds (770, 275, 110, 80);
      aboutButton.setBounds (800, 15, 70, 70);
      pressedPlayButton.setBounds (352, 230, 210, 145);
      pressedHighScoresButton.setBounds (140, 265, 215, 95);
      pressedAchievementsButton.setBounds (560, 265, 215, 95);
      pressedHelpButton.setBounds (30, 275, 110, 80);
      pressedQuitButton.setBounds (770, 275, 110, 80);
      pressedAboutButton.setBounds (800, 15, 70, 70);
      enlargedPlayButton.setBounds (332, 225, 250, 155);
      enlargedHighScoresButton.setBounds (140, 260, 215, 105);
      enlargedAchievementsButton.setBounds (550, 255, 235, 115);
      enlargedHelpButton.setBounds (20, 265, 130, 100);
      enlargedQuitButton.setBounds (760, 265, 130, 100);
      enlargedAboutButton.setBounds (800, 15, 70, 70);
      
      panel.add (playButton);
      panel.add (highscoresButton);
      panel.add (achievementsButton);
      panel.add (helpButton);
      panel.add (quitButton);
      panel.add (aboutButton);
      panel.add (pressedQuitButton);
      panel.add (pressedPlayButton);
      panel.add (pressedHighScoresButton);
      panel.add (pressedAchievementsButton);
      panel.add (pressedHelpButton);
      panel.add (pressedAboutButton);
      panel.add (enlargedPlayButton);
      panel.add (enlargedHighScoresButton);
      panel.add (enlargedAchievementsButton);
      panel.add (enlargedHelpButton);
      panel.add (enlargedQuitButton);
      panel.add (enlargedAboutButton);
      panel.add (background);
      
      pressedPlayButton.setVisible (false);
      pressedHighScoresButton.setVisible (false);
      pressedAchievementsButton.setVisible (false);
      pressedHelpButton.setVisible (false);
      pressedQuitButton.setVisible (false);
      pressedAboutButton.setVisible (false);
      enlargedPlayButton.setVisible (false);
      enlargedHighScoresButton.setVisible (false);
      enlargedAchievementsButton.setVisible (false);
      enlargedHelpButton.setVisible (false);
      enlargedQuitButton.setVisible (false);
      enlargedAboutButton.setVisible (false);
      
      quitButton.addMouseListener (this);
      playButton.addMouseListener (this);
      highscoresButton.addMouseListener (this);
      achievementsButton.addMouseListener (this);
      helpButton.addMouseListener (this);
      aboutButton.addMouseListener (this);
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
    
    add (introPanel);
    add (highScoresPanel);
    add (achievementsPanel);
    add (helpPanel);
    add (aboutPanel);
    add (panel);
    
    introPanel.setVisible (false);
    highScoresPanel.setVisible (false);
    achievementsPanel.setVisible (false);
    helpPanel.setVisible (false);
    aboutPanel.setVisible (false);
    
    menuMusic.play ();
    
    addKeyListener (this);
    
    ss.dispose ();
    setSize (900, 600);
    setResizable (false);
    setLocationRelativeTo (this);
    setVisible (true);
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  }
  
  /**
   * Returns pressed or enlarged version of button passed in
   * @param button  button used to determine enlarged or pressed version to return
   * @param pressedOrEnlarged  boolean used to determine whether to return pressed or enlarged button
   * @return pressed or enlarged version of button
   */
  public Component getButton (Component button, boolean pressedOrEnlarged)
  {
    if (button.equals (playButton))
      return pressedOrEnlarged ? pressedPlayButton : enlargedPlayButton;
    else if (button.equals (highscoresButton))
      return pressedOrEnlarged ? pressedHighScoresButton : enlargedHighScoresButton;
    else if (button.equals (achievementsButton))
      return pressedOrEnlarged ? pressedAchievementsButton : enlargedAchievementsButton;
    else if (button.equals (helpButton))
      return pressedOrEnlarged ? pressedHelpButton : enlargedHelpButton;
    else if (button.equals (aboutButton))
      return pressedOrEnlarged ? pressedAboutButton : enlargedAboutButton;
    else
      return pressedOrEnlarged ? pressedQuitButton : enlargedQuitButton;
  }
  
  /**
   * Returns image of button depending on button passed in (Component)
   * @param button  button used to determine which image to return
   * @return image of button
   */
  public BufferedImage getImage (Component button)
  {
    if (button.equals (playButton))
      return playImage;
    else if (button.equals (highscoresButton))
      return highscoresImage;
    else if (button.equals (achievementsButton))
      return achievementsImage;
    else if (button.equals (helpButton))
      return helpImage;
    else if (button.equals (aboutButton))
      return aboutImage;
    else
      return quitImage;
  }
  
  public JPanel getPanel (String name)
  {
    if (name.equals ("achievements"))
      return achievementsPanel;
    else if (name.equals ("highscores"))
      return highScoresPanel;
    else
      return aboutPanel;
  }
  
  public static MainMenu getMainMenu ()
  {
    return mm;
  }
  
  /**
   * Invoked when the mouse button has been clicked (pressed and released) on a component.
   * 
   * @param e  MouseEvent object pertaining to the component on which mouse button was clicked
   */
  @Override
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
    if (!pressed)
    {
      getButton (e.getComponent(), false).setVisible (true);
      ((JLabel) e.getComponent()).setIcon (new ImageIcon (invisibleImage));
    }
    if (e.getComponent().equals (tempComp)) //mouse has entered pressed component's area
      tempEntered = true;
  }
  
  /**
   * Invoked when the mouse exits a component.
   * 
   * @param e  MouseEvent object pertaining to the component on which mouse exited
   */
  @Override
  public void mouseExited (MouseEvent e)
  {
    boolean notOnMain = introPanel.isVisible ()  || highScoresPanel.isVisible () || achievementsPanel.isVisible () || helpPanel.isVisible () || aboutPanel.isVisible ();
    if (!pressed && !notOnMain)
    {
      ((JLabel) e.getComponent()).setIcon (new ImageIcon (getImage (e.getComponent ())));
      getButton (e.getComponent(), false).setVisible (false);
    }
    tempEntered = false; //mouse has left component area
  }
  
  /**
   * Invoked when a mouse button has been pressed on a component.
   * 
   * @param e  MouseEvent object pertaining to the component on which mouse button was pressed
   */
  @Override
  public void mousePressed (MouseEvent e)
  {
    pressed = true;
    getButton (e.getComponent(), false).setVisible (false);
    ((JLabel) e.getComponent()).setIcon (new ImageIcon (invisibleImage));
    getButton (e.getComponent(), true).setVisible (true);
    tempComp = e.getComponent (); //component user has pressed on
    tempEntered = true; //mouse is in component area
  }
  
  /**
   * Invoked when a mouse button has been released on a component.
   * 
   * @param e  MouseEvent object pertaining to the component on which mouse button was released
   */
  @Override
  public void mouseReleased (MouseEvent e)
  {
    pressed = false;
    ((JLabel) e.getComponent()).setIcon (new ImageIcon (getImage (e.getComponent ())));
    getButton (e.getComponent(), true).setVisible (false);
    if (tempEntered)
    {
      if (e.getComponent().equals (highscoresButton))
        highScoresPanel.setVisible (true);
      else if (e.getComponent().equals (achievementsButton))
        achievementsPanel.setVisible (true);
      else if (e.getComponent().equals (helpButton))
      {
        Runtime run = Runtime.getRuntime(); 
        try
        {
          Runtime.getRuntime().exec("hh.exe assets/Help/FelixHelpMenu.chm"); 
        }
        catch(Exception ex)
        {
          ex.printStackTrace(); 
        }
      }
      else if (e.getComponent().equals (aboutButton))
        aboutPanel.setVisible (true);
      else if (e.getComponent().equals (playButton))
      {
        if (showIntro)
          introPanel.setVisible (true);
        else
        {
          menuMusic.stop ();
          dispose ();
          WordSearch ws = new WordSearch ();
        }
      }
      else
        System.exit (0);
    }
  }
  
  /**
   * Creates an instance of Main Menu
   * @param args  Contains command lines as array of String objects
   */
  public static void main (String[] args)
  {
    mm = new MainMenu (true, null);
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    
    switch (e.getKeyCode()){
      case KeyEvent.VK_A: 
        if (panel.isVisible ())
      {
        panel.setVisible (false);
        tempPanel = "achievements";
        achievementsPanel.setVisible (true);
      }
        break;
      case KeyEvent.VK_S: 
        if (panel.isVisible ())
      {
        panel.setVisible (false);
        tempPanel = "highscores";
        highScoresPanel.setVisible (true);
      }
        break;
      case KeyEvent.VK_I: 
        if (panel.isVisible ())
      {
        panel.setVisible (false);
        tempPanel = "about";
        aboutPanel.setVisible (true);
      }
        break;
      case KeyEvent.VK_P: 
        if (panel.isVisible ())
      {
        introPanel.setVisible (true);
      }
        break;
      case KeyEvent.VK_Q: 
        if (panel.isVisible ())
      {
        System.exit (0);
      }
        break;
      case KeyEvent.VK_ENTER: 
        if (introPanel.isVisible ())
      {
        if (((InfoScreen)introPanel).getInfo ().equals ("introone"))
          ((InfoScreen)introPanel).setInfo ("introfelix");
        else if (((InfoScreen)introPanel).getInfo ().equals ("introfelix"))
          ((InfoScreen)introPanel).setInfo ("introtwo");
        else
        {
          menuMusic.stop ();
          dispose ();
          WordSearch ws = new WordSearch ();
        }
      }
        break;
      case KeyEvent.VK_H: 
        try
      {
        Runtime.getRuntime().exec("hh.exe assets/Help/FelixHelpMenu.chm"); 
      }
        catch (Exception ee)
        {
        }
        break;
      case KeyEvent.VK_B: 
        if (!panel.isVisible ())
      {
        getPanel(tempPanel).setVisible (false);
        panel.setVisible (true);
      }
        break;
    }
    
  }
  
  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }
}
