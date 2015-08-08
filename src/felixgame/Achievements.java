package felixgame; 

/**
 * This class stores achievements for an ocean level in the Felix game
 * @author Ansh Juneja, last updated June 08, 2015
 * @version 1
 * 
 * Time Spent: 20 minutes
 * <b> Instance Variables: </b>
 * <p>
 * <b> max </b> maximum achievements for a level
 * <p>
 * <b> collected </b> collected achievements for a level
 */
public class Achievements
{
  private int max, collected;
  
  /**
   * Creates Achievements with the specified maximum achievements
   * @param max maximum achievements
   */
  public Achievements (int max)
  {
    this.max = max;
  }
  
  /**
   * Increments collected achievements
   */
  public void addAchievement ()
  {
    collected++;
  }
  
  /**
   * Resets collected achievements
   */
  public void resetAchievement ()
  {
    collected = 0;
  }
  
  /**
   * Returns collected achievements
   * @return collected achievements
   */
  public int getCollected ()
  {
    return collected;
  }
  
  /**
   * Returns max achievements
   * @return max achievements
   */
  public int getMax ()
  {
    return max;
  }
}