package felixgame; 
/**
 * This class creates a player for the antarctic level of the Felix game
 * @author Ansh Juneja, last updated June 08, 2015
 * @version 1
 */
public class AntarcticPlayer extends Player
{
  /**
   * Creates an antarctic player with the specified name, minutes, and seconds score
   * @param name name of player
   * @param mins minutes score of player
   * @param secs seconds score of player
   */
  public AntarcticPlayer (String name, int mins, int secs)
  {
    super (name, "Antarctic", mins, secs);
  }
  
  /**
   Creates an antarctic player with the specified name, minutes, and seconds score
   * @param name name of player
   * @param mins minutes score of player
   * @param secs seconds score of player
   */
  public AntarcticPlayer (String name, int totalSecs)
  {
    super (name, "Antarctic", totalSecs);
  }
}