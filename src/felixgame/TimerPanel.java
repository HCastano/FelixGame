package felixgame; 

import java.awt.Component;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * This class displays a timer box with the specified size and time to count down from
 * 
 * @author Ansh Juneja last updated June 05, 2015
 * @version 1
 * Time Spent: 1 hour
 * <p>
 * <b> Instance Variables: </b>
 * <p>
 * <b> width </b> Width of timer box
 * <p>
 * <b> height </b> Height of timer box
 * <p>
 * <b> mins </b> Minutes to count down from
 * <p>
 * <b> secs </b> Seconds to count down from
 * <p>
 * <b> elapsed </b> Seconds elapsed since timer inception
 */
public class TimerPanel extends JPanel
{
  private int width, height, mins, secs, elapsed;
  
  /**
   * Constructor which takes width, height, minutes, and seconds as arguments to create timer.
   * @param width  Width of timer box
   * @param height  Height of timer box
   * @param mins  Minutes to start counting down from
   * @param secs  Seconds to start counting down from
   */
  public TimerPanel (int width, int height, int mins, int secs)
  {
    this.width = width;
    this.height = height;
    this.mins = mins;
    this.secs = secs;
    setVisible (true);
    setSize (width, height);
    repaint ();
  }
  
  /**
   * Updates the time of the timer (counts down) and repaints
   */
  public void updateTime ()
  {
    elapsed++;
    if (secs == 0)
    {
      mins--;
      secs = 59;
    }
    else
      secs--;
    repaint ();
  }
  
  /**
   * Repaints the screen with updated time
   * @param g  A reference to a Graphics object
   */
  @Override
  public void paintComponent (Graphics g)
  {
    g.setColor (Color.BLACK);
    g.fillRect (0, 0, width, height);
    g.setFont (new Font ("Bank Gothic", Font.PLAIN, 30));
    if (mins >= 1)
      g.setColor (Color.GREEN);
    else if (secs >= 30)
      g.setColor (Color.YELLOW);
    else
      g.setColor (Color.RED);
    g.drawString (String.format ("%02d", mins), 5, 35);
    g.drawString (":", 45, 35);
    g.drawString (String.format ("%02d", secs), 55, 35);
  }
  
  /**
   * Returns remaining minutes
   * @return remaining minutes
   */
  public int getMins ()
  {
    return mins;
  }
  
  /**
   * Returns remaining seconds
   * @return remaining seconds
   */
  public int getSecs ()
  {
    return secs;
  }
  
  /**
   * Returns elapsed seconds
   * @return elapsed seconds
   */
  public int getElapsed ()
  {
    return elapsed;
  }
  
}