package felixgame;

public class PacificPlayer extends Player
{
  public PacificPlayer (String name, int mins, int secs)
  {
    super (name, "Pacific", mins, secs);
  }
  
  public PacificPlayer (String name, int totalSecs)
  {
    super (name, "Pacific", totalSecs);
  }
}