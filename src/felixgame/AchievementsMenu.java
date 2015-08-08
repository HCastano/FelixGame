package felixgame; 

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This class creates the achievements menu
 * @author Ansh Juneja, last updated June 08, 2015
 * @version 2
 * 
 * Time Spent: 1 hour
 */
public class AchievementsMenu extends SubMenu
{
  /**
   * Constructor creates the achievements menu and passes the file path for the achievements background into the superclass constructor
   */
  public AchievementsMenu ()
  {
    super ("achbackground", false);
    GameData gd = MainMenu.gameData;
    Achievements atlAch = gd.getAchievements ("atlantic");
    Achievements indAch = gd.getAchievements ("indian");
    Achievements pacAch = gd.getAchievements ("pacific");
    Achievements antAch = gd.getAchievements ("antarctic");
    Achievements arctAch = gd.getAchievements ("arctic");
    int atlanticCollected = atlAch.getCollected ();
    int atlanticMax = atlAch.getMax ();
    int indianCollected = indAch.getCollected ();
    int indianMax = indAch.getMax ();
    int pacificCollected = pacAch.getCollected ();
    int pacificMax = pacAch.getMax ();
    int antarcticCollected = antAch.getCollected ();
    int antarcticMax = antAch.getMax ();
    int arcticCollected = arctAch.getCollected ();
    int arcticMax = arctAch.getMax ();
    Font bubbleBody = null;
    Font bubbleBodySmall = null;
    try
    {
      bubbleBody = Font.createFont(Font.TRUETYPE_FONT, new File("assets/Fonts/Bobbleboddy.ttf")).deriveFont(40f);
      bubbleBodySmall = Font.createFont(Font.TRUETYPE_FONT, new File("assets/Fonts/Bobbleboddy.ttf")).deriveFont(35f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/Fonts/Bobbleboddy.ttf")));
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
    JLabel starFishLabel = new JLabel (new ImageIcon ("assets/Pictures/starfish.png"));
    JLabel atlanticTitleLabel = new JLabel ("Atlantic");
    JLabel indianTitleLabel = new JLabel ("Indian");
    JLabel pacificTitleLabel = new JLabel ("Pacific");
    JLabel antarcticTitleLabel = new JLabel ("Antarctic");
    JLabel arcticTitleLabel = new JLabel ("Arctic");
    JLabel atlanticLabel = new JLabel (Integer.toString (atlanticCollected) + "/" + Integer.toString (atlanticMax));
    JLabel indianLabel = new JLabel (Integer.toString (indianCollected) + "/" + Integer.toString (indianMax));
    JLabel pacificLabel = new JLabel (Integer.toString (pacificCollected) + "/" + Integer.toString (pacificMax));
    JLabel antarcticLabel = new JLabel (Integer.toString (antarcticCollected) + "/" + Integer.toString (antarcticMax));
    JLabel arcticLabel = new JLabel (Integer.toString (arcticCollected) + "/" + Integer.toString (arcticMax));
    
    arcticTitleLabel.setFont (bubbleBody);
    antarcticTitleLabel.setFont (bubbleBody);
    pacificTitleLabel.setFont (bubbleBody);
    indianTitleLabel.setFont (bubbleBody);
    atlanticTitleLabel.setFont (bubbleBody);
    
    arcticLabel.setFont (bubbleBodySmall);
    antarcticLabel.setFont (bubbleBodySmall);
    pacificLabel.setFont (bubbleBodySmall);
    indianLabel.setFont (bubbleBodySmall);
    atlanticLabel.setFont (bubbleBodySmall);
    
    antarcticTitleLabel.setForeground (Color.WHITE);
    arcticTitleLabel.setForeground (Color.WHITE);
    pacificTitleLabel.setForeground (Color.WHITE);
    indianTitleLabel.setForeground (Color.WHITE);
    atlanticTitleLabel.setForeground (Color.WHITE);
    
    arcticLabel.setForeground (Color.BLACK);
    antarcticLabel.setForeground (Color.BLACK);
    pacificLabel.setForeground (Color.BLACK);
    indianLabel.setForeground (Color.BLACK);
    atlanticLabel.setForeground (Color.BLACK);
    
    arcticTitleLabel.setBounds (50, 200, 200, 75);
    antarcticTitleLabel.setBounds (200, 200, 200, 75);
    pacificTitleLabel.setBounds (400, 200, 200, 75);
    indianTitleLabel.setBounds (550, 200, 200, 75);
    atlanticTitleLabel.setBounds (700, 200, 200, 75);
    
    arcticLabel.setBounds (80, 235, 100, 75);
    antarcticLabel.setBounds (245, 235, 100, 75);
    pacificLabel.setBounds (430, 235, 700, 75);
    indianLabel.setBounds (575, 235, 100, 75);
    atlanticLabel.setBounds (730, 235, 100, 75);
    
    starFishLabel.setBounds (350, 350, 200, 200);
    
    add (arcticTitleLabel);
    add (antarcticTitleLabel);
    add (pacificTitleLabel);
    add (indianTitleLabel);
    add (atlanticTitleLabel);
    
    add (arcticLabel);
    add (antarcticLabel);
    add (pacificLabel);
    add (indianLabel);
    add (atlanticLabel);
    add (starFishLabel);
    
    addBackground ();
  }
}