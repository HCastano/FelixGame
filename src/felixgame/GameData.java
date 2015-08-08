package felixgame; 
import java.util.ArrayList;

/**
 * This class contains information about
 * the current game. 
 * 
 * @author Ansh Juneja, last updated June 05, 2015
 * @version 2
 * Time Spent: 1 hour
 * <p>
 * <b> Instance Variables: </b> 
 * <p> 
 * <b> solvedWords </b> An ArrayList of the words which have been solved. 
 * <p>
 * <b> tries </b> Number of tries on final level
 * <p>
 * <b> atlanticAchievements </b> Achievemens objects for the atlantic ocean
 * <p>
 * <b> pacificAchievements </b> Achievements object for the pacific ocean
 * <p>
 * <b> antarcticAchievements </b> Achievements object for the antarctic ocean
 * <p>
 * <b> indianAchievements </b> Achievements object for the indian ocean
 * <p>
 * <b> arcticAchievements </b> Achievements object for the arctic ocean
 */
public class GameData
{
  private ArrayList <String> solvedWords;
  private int tries;
  private Achievements atlanticAchievements, pacificAchievements, antarcticAchievements, indianAchievements, arcticAchievements;
  
  public GameData ()
  {
    solvedWords = new ArrayList <String> ();
    atlanticAchievements = new Achievements (1);
    pacificAchievements = new Achievements (3);
    antarcticAchievements = new Achievements (1);
    indianAchievements = new Achievements (2);
    arcticAchievements = new Achievements (1);
  }
  
  /**
   * Adds a solved word to the ArrayList of solved words
   * 
   * @param word the word to be added to the ArrayList 
   */
  public void addSolvedWord (String word)
  {
    solvedWords.add (word);
  }
  
  /**
   * Adds an achievement to the specified ocean
   * @param oceanName the name of the ocean to which an achievement will be added
   */
  public void addAchievement (String oceanName)
  {
    if (oceanName.equals ("atlantic"))
      atlanticAchievements.addAchievement ();
    else if (oceanName.equals ("pacific"))
      pacificAchievements.addAchievement ();
    else if (oceanName.equals ("indian"))
      indianAchievements.addAchievement ();
    else if (oceanName.equals ("antarctic"))
      antarcticAchievements.addAchievement ();
    else
      arcticAchievements.addAchievement ();
  }
  
  /**
   * Resets the achievements of the specified ocean
   * @param oceanName the name of the ocean to which an achievement will be added
   */
  public void resetAchievements (String oceanName)
  {
    if (oceanName.equals ("atlantic"))
      atlanticAchievements.resetAchievement ();
    else if (oceanName.equals ("pacific"))
      pacificAchievements.resetAchievement ();
    else if (oceanName.equals ("indian"))
      indianAchievements.resetAchievement ();
    else if (oceanName.equals ("antarctic"))
      antarcticAchievements.resetAchievement ();
    else
      arcticAchievements.resetAchievement ();
  }
  
  /**
   * Returns the achievements object of the specified ocean
   * @param oceanName the name of the ocean of which achievements will be returned
   * @return the achievements of the specified ocean
   */
  public Achievements getAchievements (String oceanName)
  {
    if (oceanName.equals ("atlantic"))
      return atlanticAchievements;
    else if (oceanName.equals ("pacific"))
      return pacificAchievements;
    else if (oceanName.equals ("indian"))
      return indianAchievements;
    else if (oceanName.equals ("antarctic"))
      return antarcticAchievements;
    else
      return arcticAchievements;
  }
  
  public int getTries ()
  {
    return tries;
  }
  
  public void setTries (int tries)
  {
    this.tries = tries;
  }
  
  /**
   * Returns an ArrayList of solved words 
   * 
   * @return the ArrayList of solved words 
   */
  public ArrayList <String> getSolvedWords ()
  {
    return solvedWords;
  }
}
