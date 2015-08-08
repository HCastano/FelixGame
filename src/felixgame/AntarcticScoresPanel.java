package felixgame; 

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class AntarcticScoresPanel extends SubMenu
{
  JLabel countLabels[];
  JLabel nameLabels[];
  JLabel minsLabels[];
  JLabel secsLabels[];
  
  public static boolean visible; 
  public AntarcticScoresPanel ()
  {
    super ("antarcticscoresbackground", false);
    drawScores ();
  }
  
  public void drawScores ()
  {
    HighScores hs = new HighScores ();
    String[] names = new String [10];
    int[] mins = new int [10];
    int[] secs = new int [10];
    AntarcticPlayer[] players = hs.getAntarcticPlayers ();
    for (int n = 0 ; n < players.length ; n++)
    {
      names[n] = players[n].getName ();
      mins[n] = players[n].getMins ();
      secs[n] = players[n].getSecs ();
    }
    countLabels = new JLabel [10];
    nameLabels = new JLabel [10];
    minsLabels = new JLabel [10];
    secsLabels = new JLabel [10];
    Font bubbleBody = null;
    try
    {
      bubbleBody = Font.createFont(Font.TRUETYPE_FONT, new File("assets/Fonts/Bobbleboddy.ttf")).deriveFont(50f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/Fonts/Bobbleboddy.ttf")));
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
    int y = 135;
    for (int n = 0 ; n < 10 ; n++)
    {
      countLabels[n] = new JLabel (Integer.toString (n+1));
      nameLabels[n] = new JLabel (names[n]);
      minsLabels[n] = new JLabel (String.format("%02d", mins[n]) + ":");
      secsLabels[n] = new JLabel (String.format("%02d", secs[n]));
      
      countLabels[n].setBounds (100, y, 50, 50);
      nameLabels[n].setBounds (300, y, 300, 50);
      minsLabels[n].setBounds (600, y, 75, 50);
      secsLabels[n].setBounds (670, y, 75, 50);
      
      countLabels[n].setFont (bubbleBody);
      nameLabels[n].setFont (bubbleBody);
      minsLabels[n].setFont (bubbleBody);
      secsLabels[n].setFont (bubbleBody);
      
      countLabels[n].setForeground (Color.BLACK);
      nameLabels[n].setForeground (Color.BLACK);
      minsLabels[n].setForeground (Color.BLACK);
      secsLabels[n].setForeground (Color.BLACK);
      
      add (countLabels[n]);
      add (nameLabels[n]);
      add (minsLabels[n]);
      add (secsLabels[n]);
      
      y+=42;
    }
    addPrintButton ();
    addBackground ();
  }
  
  public void removeScores ()
  {
    for (int n = 0 ; n < 10 ; n++)
    {
      remove (countLabels[n]);
      remove (nameLabels[n]);
      remove (minsLabels[n]);
      remove (secsLabels[n]);
    }
  }
}