package felixgame;

public class ArcticPlayer extends Player
{
  public ArcticPlayer (String name, int mins, int secs)
  {
    super (name, "Arctic", mins, secs);
  }
  
  public ArcticPlayer (String name, int totalSecs)
  {
    super (name, "Arctic", totalSecs);
  }
}