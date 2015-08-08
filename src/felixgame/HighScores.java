package felixgame; 

import java.io.*;

/**
 * This class reads from the high score
 * file and sorts the high scores. 
 * 
 * @author H Castano and A Juneja, last updated June 05, 2015
 * @version 1
 * Time Spent: 1 hour 
 * 
 * <p> 
 * <b> Instance Variables </b> 
 * <p> 
 * <b> players [] </b> An array of Player objects  
 * 
 */
public class HighScores
{
  private ArcticPlayer[] arcticPlayers = new ArcticPlayer [10];
  private AntarcticPlayer[] antarcticPlayers = new AntarcticPlayer [10];
  private AtlanticPlayer[] atlanticPlayers = new AtlanticPlayer [10];
  private IndianPlayer[] indianPlayers = new IndianPlayer [10];
  private PacificPlayer[] pacificPlayers = new PacificPlayer [10];
  
  public HighScores ()
  {
    loadScores ("arctic");
    loadScores ("antarctic");
    loadScores ("atlantic");
    loadScores ("indian");
    loadScores ("pacific");
  }
  
  /**
   * Reads the high scores from the high
   * score file. 
   */
  private void loadScores (String ocn)
  {
    try
    {
      BufferedReader in = new BufferedReader (new FileReader("assets/High Scores/" + ocn + "highscores.felix"));
      String line;
      while (true)
      {
        line = in.readLine ();
        if (line.equals ("START"))
        {
          in.readLine ();
          String name;
          int mins, secs;
          for (int n = 0 ; n < 10 ; n ++)
          {
            name = in.readLine ();
            mins = Integer.parseInt (in.readLine ());
            secs = Integer.parseInt (in.readLine ());
            if (ocn.equals ("arctic"))
              arcticPlayers[n] = new ArcticPlayer (name, mins, secs);
            else if (ocn.equals ("antarctic"))
              antarcticPlayers[n] = new AntarcticPlayer (name, mins, secs);
            else if (ocn.equals ("atlantic"))
              atlanticPlayers[n] = new AtlanticPlayer (name, mins, secs);
            else if (ocn.equals ("indian"))
              indianPlayers[n] = new IndianPlayer (name, mins, secs);
            else
              pacificPlayers[n] = new PacificPlayer (name, mins, secs);
            in.readLine ();
          }
        }
        if (line.equals ("END"))
        {
          in.close();
          break; 
        }
      }
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }  
  }
  
  private void writeScores (String ocn, boolean reset)
  {
    Player[] players;
    int maxMins, maxSecs;
    if (ocn.equals ("arctic"))
    {
      players = arcticPlayers;
      maxMins = 3;
      maxSecs = 0;
    }
    else if (ocn.equals ("antarctic"))
    {
      players = antarcticPlayers;
      maxMins = 2;
      maxSecs = 30;
    }
    else if (ocn.equals ("atlantic"))
    {
      players = atlanticPlayers;
      maxMins = 3;
      maxSecs = 0;
    }
    else if (ocn.equals ("indian"))
    {
      players = indianPlayers;
      maxMins = 2;
      maxSecs = 30;
    }
    else
    {
      players = pacificPlayers;
      maxMins = 2;
      maxSecs = 0;
    }
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter("assets/High Scores/" + ocn + "highscores.felix"));
      out.println ("Felix by 3AMProductions  " + ocn.substring (0, 1).toUpperCase () + ocn.substring (1, ocn.length ())  + " High Scores");
      out.println ();
      out.println ("START");
      out.println ();
      for (int n = 0 ; n < players.length ; n++)
      {
        if (reset)
        {
          out.println ("Player");
          out.println (maxMins);
          out.println (maxSecs);
        }
        else
        {
          out.println (players[n].getName ());
          out.println (players[n].getMins ());
          out.println (players[n].getSecs ());
        }
        out.println ();
      }
      out.println ("END");
      out.close ();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }  
  }
  
  public void resetScores ()
  {
    writeScores ("arctic", true);
    writeScores ("antarctic", true);
    writeScores ("pacific", true);
    writeScores ("indian", true);
    writeScores ("atlantic", true);
  }
  
  public void addPlayer (String ocn, String name, int time)
  {
    if (ocn.equals ("arctic"))
      arcticPlayers[9] = new ArcticPlayer (name, time);
    else if (ocn.equals ("antarctic"))
      antarcticPlayers[9] = new AntarcticPlayer (name, time);
    else if (ocn.equals ("atlantic"))
      atlanticPlayers[9] = new AtlanticPlayer (name, time);
    else if (ocn.equals ("indian"))
      indianPlayers[9] = new IndianPlayer (name, time);
    else
      pacificPlayers[9] = new PacificPlayer (name, time);
    
    sortScores (ocn);
    
    writeScores (ocn, false);
  }
  
  /**
   * Sorts the high scores via Bubble Sort.
   * 
   * @return the sorted high scores 
   */
  private void sortScores(String ocn)
  {
    Player[] players;
    if (ocn.equals ("arctic"))
      players = arcticPlayers;
    else if (ocn.equals ("antarctic"))
      players = antarcticPlayers;
    else if (ocn.equals ("atlantic"))
      players = atlanticPlayers;
    else if (ocn.equals ("indian"))
      players = indianPlayers;
    else
      players = pacificPlayers;
    
    int temp;
    String tempName;
    String tempOcean;
    for (int i = 0; i < players.length - 1; i++)
    {
      for (int j = 1; j < players.length - i; j++)
      {
        if (players[j-1].getTotalSecs () > players[j].getTotalSecs ())
        {
          temp = players[j-1].getTotalSecs ();
          tempName = players[j-1].getName ();
          tempOcean = players[j-1].getOcean ();
          players[j-1].setTotalSecs (players[j].getTotalSecs ());
          players[j-1].setName (players[j].getName ());
          players[j-1].setOcean (players[j].getOcean ());
          players[j].setTotalSecs (temp);
          players[j].setName (tempName);
          players[j].setOcean (tempOcean);
        }
      }
    }
    if (ocn.equals ("arctic"))
      arcticPlayers = (ArcticPlayer[])players;
    else if (ocn.equals ("antarctic"))
      antarcticPlayers = (AntarcticPlayer[])players;
    else if (ocn.equals ("atlantic"))
      atlanticPlayers = (AtlanticPlayer[])players;
    else if (ocn.equals ("indian"))
      indianPlayers = (IndianPlayer[])players;
    else
      pacificPlayers = (PacificPlayer[])players;
  }
  
  /**
   * Returns an array of ArcticPlayer objects 
   * 
   * @return an array of Player objects
   */
  public ArcticPlayer[] getArcticPlayers ()
  {
    return arcticPlayers;
  }
  
  public AntarcticPlayer[] getAntarcticPlayers ()
  {
    return antarcticPlayers;
  }
  
  public AtlanticPlayer[] getAtlanticPlayers ()
  {
    return atlanticPlayers;
  }
  
  public IndianPlayer[] getIndianPlayers ()
  {
    return indianPlayers;
  }
  
  public PacificPlayer[] getPacificPlayers ()
  {
    return pacificPlayers;
  }
}
