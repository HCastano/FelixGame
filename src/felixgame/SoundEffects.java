package felixgame;

import java.io.*;
import sun.audio.*;

/**
 * This class provides a static method to play sound effects
 * @author Ansh Juneja last updated May 28, 2015
 * @version 1
 * Time Spent: 10 minutes
 */
public class SoundEffects
{
  public static void play (String file)
  {
    try
    {
      InputStream in = new FileInputStream ("assets/Sounds/" + file + ".au");
      AudioStream as = new AudioStream (in);
      AudioPlayer.player.start (as);
    }
    catch (Exception e)
    {
    }
  }
}
