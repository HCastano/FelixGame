package felixgame; 
/**
 * This class contains information about a player with a high score
 * @author Ansh Juneja last updated June 05, 2015
 * @version 1
 * Time Spent: 15 minutes
 * <p>
 * <b> Instance Variables: </b>
 * <p>
 * <b> name </b> String containing name of player
 * <p>
 * <b> mins </b> Minutes that player took to complete level
 * <p>
 * <b> secs </b> Seconds upon the minutes that player took to complete level
 */
public class Player
{
  private String name, ocean;
  private int mins, secs, totalSecs;
  
  /**
   * Constructor which takes name, minutes, and seconds as parameters to create new player
   */
  public Player (String name, String ocean, int mins, int secs)
  {
    this.name = name;
    this.ocean = ocean;
    this.mins = mins;
    this.secs = secs;
    totalSecs = mins * 60 + secs;
  }
  
  public Player (String name, String ocean, int totalSecs)
  {
    this.name = name;
    this.ocean = ocean;
    this.totalSecs = totalSecs;
    mins = totalSecs / 60;
    secs = totalSecs % 60;
  }
  
  /**
   * Returns name of player
   * @return name of player
   */
  public String getName ()
  {
    return name;
  }
  
  public String getOcean ()
  {
    return ocean;
  }
  
  public void setOcean (String ocean)
  {
    this.ocean = ocean;
  }
  
  public void setName (String name)
  {
    this.name = name;
  }
  
  /**
   * Returns minutes score of player
   * @return minutes score of player
   */
  public int getMins ()
  {
    return mins;
  }
  
  /**
   * Returns seconds score of player
   * @return seconds score of player
   */
  public int getSecs ()
  {
    return secs;
  }
  
  /**
   * Returns total seconds of player score
   * @return total seconds of player score
   */
  public int getTotalSecs ()
  {
    return totalSecs;
  }
  
  public void setTotalSecs (int totalSecs)
  {
    this.totalSecs = totalSecs;
    mins = totalSecs / 60;
    secs = totalSecs % 60;
  }
}