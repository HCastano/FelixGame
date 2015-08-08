package felixgame;

public class AtlanticPlayer extends Player
{
  public AtlanticPlayer (String name, int mins, int secs)
  {
    super (name, "Atlantic", mins, secs);
  }
  
  public AtlanticPlayer (String name, int totalSecs)
  {
    super (name, "Atlantic", totalSecs);
  }
}