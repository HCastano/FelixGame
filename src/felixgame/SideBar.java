package felixgame; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * This class creates a sidebar to display the timer and live information about the game
 * @author Ansh Juneja, last updated June 05, 2015
 * @version 1
 * Time Spent: 2 hours
 * <p>
 * <b> Instance Variables: </b>
 * <p>
 * <b> panel </b> Main JPanel
 * <p>
 * <b> gameTimerPanel </b> Timer box panel
 * <p>
 * <b> timer </b> Timer which fires every second to update timer panel
 * <p>
 * <b> collectedCluesLabel </b> JLabel showing number of collected clues in current level
 * <p>
 * <b> totalMins </b> Total minutes allotment for current level
 * <p>
 * <b> totalSecs </b> Seconds to be added onto total minutes for current level
 * <p>
 * <b> maxClues </b> Total clues in current level
 * <p>
 * <b> collectedClues </b> Collected clues in current level
 * <p>
 * <b> felix </b> Felix from game driver class (used in game)
 * <p>
 * <b> bubbleBody </b> Font used in sidebar
 */
public class SideBar extends JFrame implements ActionListener
{
  private JPanel panel;
  private TimerPanel gameTimerPanel;
  public Timer timer;
  private JLabel collectedCluesLabel, collectedAchievementsLabel;
  private int totalMins, totalSecs, maxClues, collectedClues, maxAchievements, collectedAchievements = 0;
  private Felix felix = GameDriver.getFelix ();
  private Font bubbleBody;
  
  /**
   * Constructor which creates side bar and takes maximum clues parameter as int
   * @param maxClues  Total clue tiles to be collected in current level
   */
  public SideBar (int maxClues, int maxAchievements)
  {
    this.maxClues = maxClues;
    this.maxAchievements = maxAchievements;
    panel = new JPanel ();
    panel.setLayout (null);
    try
    {
      Icon backIcon = new ImageIcon ("assets/Pictures/sidebar1.png");
      JLabel background = new JLabel (backIcon);
      JLabel clueIconLabel = new JLabel (new ImageIcon ("assets/Pictures/Tiles/ClueTile.png"));
      JLabel achievementsIconLabel = new JLabel (new ImageIcon ("assets/Pictures/Tiles/StarFishTile.png"));
      JLabel maxCluesLabel = new JLabel (String.format ("%02d", maxClues));
      JLabel maxAchievementsLabel = new JLabel (String.format ("%02d", maxAchievements));
      JLabel slashLabel = new JLabel ("/");
      JLabel slashLabel2 = new JLabel ("/");
      collectedCluesLabel = new JLabel (String.format ("%02d", collectedClues));
      collectedAchievementsLabel = new JLabel (String.format ("%02d", collectedAchievements));
      bubbleBody = Font.createFont(Font.TRUETYPE_FONT, new File("assets/Fonts/Bobbleboddy.ttf")).deriveFont(35f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/Fonts/Bobbleboddy.ttf")));
      maxCluesLabel.setFont (bubbleBody);
      maxAchievementsLabel.setFont (bubbleBody);
      slashLabel.setFont (bubbleBody);
      slashLabel2.setFont (bubbleBody);
      collectedCluesLabel.setFont (bubbleBody);
      collectedAchievementsLabel.setFont (bubbleBody);
      clueIconLabel.setBounds (5, 75, 50, 50);
      achievementsIconLabel.setBounds (5, 225, 50, 50);
      collectedCluesLabel.setBounds (5, 125, 40, 40);
      collectedAchievementsLabel.setBounds (5, 275, 40, 40);
      slashLabel.setBounds (45, 125, 30, 40);
      maxCluesLabel.setBounds (65, 125, 40, 40);
      maxAchievementsLabel.setBounds (65, 275, 40, 40);
      slashLabel2.setBounds (45, 275, 30, 40);
      background.setBounds (0, 0, 150, 600);
      maxCluesLabel.setForeground (Color.GREEN);
      collectedCluesLabel.setForeground (Color.GREEN);
      slashLabel.setForeground (Color.GREEN);
      maxAchievementsLabel.setForeground (Color.ORANGE);
      collectedAchievementsLabel.setForeground (Color.ORANGE);
      slashLabel2.setForeground (Color.ORANGE);
      panel.add (clueIconLabel);
      panel.add (achievementsIconLabel);
      panel.add (collectedCluesLabel);
      panel.add (slashLabel);
      panel.add (maxCluesLabel);
      panel.add (maxAchievementsLabel);
      panel.add (collectedAchievementsLabel);
      panel.add (slashLabel2);
      panel.add (background);
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
    if (WordSearch.currWord.equals ("Food") || WordSearch.currWord.equals ("Attack"))
    {
      totalMins = 3;
      totalSecs = 0;
    }
    else if (WordSearch.currWord.equals ("Family") || WordSearch.currWord.equals ("Storm"))
    {
      totalMins = 2;
      totalSecs = 30;
    }
    else if (WordSearch.currWord.equals ("Home"))
    {
      totalMins = 2;
      totalSecs = 0;
    }
    else
    {
      totalMins = 1;
      totalSecs = 30;
    }
    
    gameTimerPanel = new TimerPanel (100, 50, totalMins, totalSecs);
    gameTimerPanel.setBounds (0, 0, 100, 50);
    panel.add (gameTimerPanel);
    add (panel);
    timer = new Timer (1000, this);
    timer.setInitialDelay (1000);
    timer.start ();
    setSize (100, 600);
    setResizable (false);
    setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
  }
  
  /**
   * Increments clue counter label
   */
  public void addClue ()
  {
    collectedCluesLabel.setText (String.format ("%02d", ++collectedClues));
  }
  
  public void addAchievement ()
  {
    collectedAchievementsLabel.setText (String.format ("%02d", ++collectedAchievements));
  }
  
  /**
   * Returns whether there is time remaining in current level
   * @return whether there is time remaining in current level
   */
  public boolean timeRemaining ()
  {
    if (gameTimerPanel.getMins() == 0 && gameTimerPanel.getSecs() == 0)
    {
      felix.setDeathState ("Time");
      return false;
    }
    return true;
  }
  
  /**
   * Returns number of collected clues
   * @return number of collected clues
   */
  public int getCollectedClues ()
  {
    return collectedClues;
  }
  
  /**
   * Returns total amount of clues in current level
   * @return total amount of clues in current level
   */
  public int getMaxClues ()
  {
    return maxClues;
  }
  
  /**
   * Returns timer panel for game
   * @return timer panel for game
   */
  public TimerPanel getTimerPanel ()
  {
    return gameTimerPanel;
  }
  
  /**
   * Invoked when action command was fired, updates game timer time
   * @param e ActionEvent pertaining to object on which action command was fired
   */
  @Override
  public void actionPerformed (ActionEvent e)
  {
    if (!GameDriver.paused)
      gameTimerPanel.updateTime ();
  }
}