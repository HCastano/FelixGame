package felixgame; 

import java.io.*;
import javax.sound.sampled.*;

/**
 * This class plays and stops a music file
 * @author Ansh Juneja last updated May 28, 2015
 * @version 1
 * Time Spent: 30 minutes
 * 
 * <b> Instance Variables </b> 
 * <p> 
 * <b> clip </b> 
 * <p> 
 * <b> file </b> 
 * 
 */
public class Music
{
  private Clip clip;
  private String file;
  private long time;
  
  /**
   * Initializes the Music class
   * with a specific song. 
   * @param file the song to be used 
   */
  public Music (String file)
  {
    this.file = file;
  }
  
  /**
   * Plays the correct song for the scenario. 
   */
  public void play ()
  {
    try
    {
      File soundFile = new File("assets/Sounds/" + file + ".au");
      AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
      DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
      clip = (Clip) AudioSystem.getLine(info);
      clip.open(sound);
      clip.setLoopPoints (0, -1);
      clip.loop (Clip.LOOP_CONTINUOUSLY);
    }
    catch(Exception e)
    {
      e.printStackTrace ();
    }
  }
  
  
 public void pause ()
 {
   time = clip.getMicrosecondPosition ();
   clip.stop ();
 }
 
 public void resume ()
 {
   clip.setMicrosecondPosition (time);
   clip.start ();
 }
  
  /**
   * Stops the song from playing. 
   * 
   */
  public void stop ()
  {
    try
    {
      clip.stop ();
      clip.close ();
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
  }
}
