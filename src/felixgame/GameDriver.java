package felixgame; 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class contains all the necessary graphical components
 * for the Felix game. It runs the game, as well as load up
 * and assets which are used, and listen for user input for
 * the gameplay. 
 * 
 * @author H Castano 
 * @version 2 29.5.15
 * Time Spent: 8 hours  
 * 
 * <h2><b> Instance Variables: </b></h2>  
 * <p> 
 * <b> image </b> Reference variable for an Image object. 
 * <p> 
 * <b> second </b> Reference variable for a Graphics object. 
 * <p> 
 * <b> background </b> Reference variable for a background image. 
 * <p> 
 * <b> felixRight </b> 
 * <p> 
 * <b> felixLeft </b>
 * <p> 
 * <b> currFelix </b>
 * <p> 
 * <b> tileDeathScreen </b>
 * <p> 
 * <b> outOfBoundsDeathScreen </b> 
 * <p> 
 * <b> timeDeathScreen </b>
 * <p> 
 * <b> levelSolvedScreen </b>
 * <p> 
 * <b> ocean </b> Reference variable for the Ocean class. 
 * <p> 
 * <b> ocean2 </b> Reference variable for the Ocean class. 
 * <p>
 * <b> felix </b> Reference variable for the Felix class. 
 * <p> 
 * <b> sideBar </b> 
 * <p> 
 * <b> tileArr </b> An instance of the ArrayList class. 
 * <p> 
 * <b> gameMusic </b> An instance of the Music class used to
 *  play the music for the current level. 
 * 
 * <p> 
 * <b> oceanName </b> Used to store the name of the current level. 
 * <p> 
 * <b> maxClues </b> Used to keep count of the amount of clues in a map file.
 */
public class GameDriver extends JFrame implements Runnable, KeyListener, ActionListener{
  
  private Image background, felixRight, felixLeft, currFelix, image,
    tileDeathScreen, outOfBoundsDeathScreen, timeDeathScreen, levelSolvedScreen, pausedScreen;
  
  public static Image[] tiles = new Image [10];
  
  private Graphics second; 
  
  private static Felix felix; 
  private static Ocean ocean; 
  private static Ocean ocean2; 
  private static SideBar sideBar;
  
  private boolean alive = true;
  public static boolean paused;
  
  private ArrayList <Tiles> tileArr = new ArrayList<Tiles>();
  
  public static Music gameMusic;
  
  private static String oceanName;
  private int maxClues, maxAchievements;
  
  private static HighScores highScores;
  
  /**
   *Initializes all the images, classes and screen properties.  
   */
  public GameDriver(String oceanName){
    setLayout (null);
    this.oceanName = oceanName;
    
    highScores = new HighScores ();
    
    try {
      background = ImageIO.read(new File ("assets/Pictures/" + oceanName + ".png")); 
      
      felixRight = ImageIO.read(new File("assets/Pictures/FelixRight.png")); 
      felixLeft = ImageIO.read(new File ("assets/Pictures/FelixLeft.png"));
      
      tileDeathScreen = ImageIO.read (new File ("assets/Pictures/tiledeathscreen.png"));
      outOfBoundsDeathScreen = ImageIO.read (new File ("assets/Pictures/outofboundsdeathscreen.png"));
      timeDeathScreen = ImageIO.read (new File ("assets/Pictures/timedeathscreen.png"));
      levelSolvedScreen = ImageIO.read (new File ("assets/Pictures/levelsolvedscreen.png"));
      
      tileDeathScreen = ImageIO.read (new File ("assets/Pictures/tiledeathscreen.png"));
      outOfBoundsDeathScreen = ImageIO.read (new File ("assets/Pictures/outofboundsdeathscreen.png"));
      timeDeathScreen = ImageIO.read (new File ("assets/Pictures/timedeathscreen.png"));
      levelSolvedScreen = ImageIO.read (new File ("assets/Pictures/levelsolvedscreen.png"));
      
      pausedScreen = ImageIO.read (new File ("assets/Pictures/gamepausedscreen.png"));
      
      tiles[0] = ImageIO.read(new File ("assets/Pictures/Tiles/ClueTile.png")); 
      tiles[1] = ImageIO.read(new File ("assets/Pictures/Tiles/DeathTile.png")); 
      tiles[2] = ImageIO.read(new File ("assets/Pictures/Tiles/RockTile.png"));
      
      tiles[7] = ImageIO.read(new File ("assets/Pictures/Tiles/BlankTile.png")); 
      tiles[8] = ImageIO.read(new File ("assets/Pictures/Tiles/HeartTile.png")); 
      tiles[9] = ImageIO.read(new File ("assets/Pictures/Tiles/StarfishTile.png")); 
      
      if (oceanName.equals("arctic")){
        tiles[3] = ImageIO.read (new File("assets/Pictures/Tiles/IceTile.png")); 
        tiles[4] = ImageIO.read (new File ("assets/Pictures/Tiles/IceTile.png")); 
        tiles[5] = ImageIO.read(new File ("assets/Pictures/Tiles/IceTile.png"));
        tiles[6] = ImageIO.read(new File ("assets/Pictures/Tiles/IceTile.png"));
      }
      else if (oceanName.equals("antarctic"))
      {
        tiles[3] = ImageIO.read((new File("assets/Pictures/Tiles/IceRockSnow.png"))); 
        tiles[4] = ImageIO.read(new File ("assets/Pictures/Tiles/IceRockTile.png"));
        tiles[5] = ImageIO.read(new File ("assets/Pictures/Tiles/IceRockSnowRight.png"));
        tiles[6] = ImageIO.read (new File ("assets/Pictures/Tiles/IceRockSnowLeft.png")); 
        
        
      }
      else if (oceanName.equals("indian")){
        tiles[3] = ImageIO.read(new File ("assets/Pictures/Tiles/SeaweedTile.png")); 
        tiles[4] = ImageIO.read (new File ("assets/Pictures/Tiles/SeaweedTile.png"));
        tiles[5] = ImageIO.read(new File ("assets/Pictures/Tiles/SeaweedTile.png"));
        tiles[6] = ImageIO.read(new File ("assets/Pictures/Tiles/SeaweedTile.png"));
      }
      else{
        tiles[3] = ImageIO.read(new File ("assets/Pictures/Tiles/SeaweedRock.png")); 
        tiles[4] = ImageIO.read (new File ("assets/Pictures/Tiles/DarkRockTile.png"));
        tiles[5] = ImageIO.read(new File ("assets/Pictures/Tiles/SeaweedRockRight.png"));
        tiles[6] = ImageIO.read(new File ("assets/Pictures/Tiles/SeaweedRockLeft.png"));
      }
      
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
    
    ocean = new Ocean(0, 0); 
    ocean2 = new Ocean (2400, 0); 
    felix = new Felix();
    
    loadMap("assets/Maps/" + oceanName + "Map.txt"); //Load the current map 
    
    currFelix = felixRight; 
    
    //Assigns the current song according to the
    //level chosen 
    String song = "";
    if (oceanName.equals ("atlantic"))
      song = "Origin";
    else if (oceanName.equals ("pacific"))
      song = "Imaginary Friends";
    else if (oceanName.equals ("indian"))
      song = "Piscine Molitor Patel";
    else if (oceanName.equals ("arctic"))
      song = "Devastation And Revenge";
    else if (oceanName.equals("antarctic"))
      song = "Dreamy Flashback";
    else
      song = "The Way";
    
    gameMusic = new Music (song);
    gameMusic.play ();
    
    setTitle("Felix by 3AM Productions");
    addKeyListener(this); 
    setSize(900, 600);  
    setLocationRelativeTo(null); 
    setResizable(false); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    if (!oceanName.equals ("final"))
    {
      sideBar = new SideBar (maxClues, maxAchievements);
      sideBar.setLocation (getX() + getWidth() + 15, getY());
      sideBar.setVisible (true);
    }
    setVisible(true);
  }
  
  /**
   * Loads up the map which will be used for a certain level 
   * 
   * <b> Local Variables: </b>
   * <p> 
   * <b> br </b> Reference variable for the BufferedReader class 
   * <p> 
   * <b> i </b> An int used as a counter  
   * <p> 
   * <b> line </b> A String which holds the line currently being 
   * read from the file
   * <p> 
   * <b> arr </b> Holds the split up tokens for the line currently
   * being read from the file 
   * <p> 
   * <b> tile </b> Used to assign the type of tile 
   * <p> 
   * <b> t </b> Reference variable to the Tiles class 
   * @param filename the name of the file which contains
   * the map that is to be used. 
   * @exception NumberFormatException if the String being 
   * parsed is not an int.
   * @exception IOException if there is a failed or interrupted
   * I/O operation. 
   */
  private void loadMap(String filename) {
    
    try{
      BufferedReader br = new BufferedReader (new FileReader(filename)); 
      int i = 0; 
      while (true){ //Goes through the entire file 
        
        String line = br.readLine(); //Holds the line which is currently being read 
        
        if (line == null){ //Once it hits a null line the reading stops 
          br.close();
          break; 
        }
        
        if (!line.startsWith("#")){ //Ensures that commented lines are not read 
          
          i++; 
          
          String [] arr = line.split(""); //Splits the contents of the line for later use 
          
          for (int j = 0; j < arr.length; j++){ //Goes through the contents of each line 
            
            /*
             * Assigns the correct tile type depending 
             * on what is read from the file. 
             */
            int tile = 0; 
            try{
              tile = Integer.parseInt(arr[j]); 
            }catch (NumberFormatException e){}
            
            if (tile == 7)
              maxClues++;
            
            if (tile == 1)
              maxAchievements++;
            
            Tiles t = new Tiles (j, i, tile); 
            tileArr.add(t); 
            
          }
        }
      }
    }catch(IOException e){
      e.printStackTrace();
    }       
  }
  
  /**
   * <b> Local Variables </b>  
   * <p> 
   * <b> delayTimer </b> 
   * 
   * 
   * Runs the graphical aspects of the program 
   */
  @Override
  public void run() {
    while (felix.getDeathState().equals ("false") && sideBar.timeRemaining()){
      if (!paused)
      {
        Graphics g = getGraphics(); 
        felix.update();
        updateTiles(); 
        ocean.update();
        ocean2.update(); 
        update(g); 
        try
        {
          Thread.sleep(17);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
        if (!oceanName.equals ("final"))
        {
          if (sideBar.getCollectedClues() == sideBar.getMaxClues())
            break;
        }
        else
        {
          if (Tiles.heartFound)
            break;
        }
      }
    }
    gameMusic.stop ();
    if (!oceanName.equals ("final"))
    {
      sideBar.dispose ();
      sideBar.timer.stop ();
    }
    paintEndScreen (getGraphics ());
    Timer delayTimer = new Timer (1000, this);
    delayTimer.setInitialDelay (1000);
    delayTimer.start ();
  }
  
  /**
   * Used to paint the appropriate death screen 
   * in the game 
   * 
   * @param g Reference to the Graphics class
   */
  private void paintEndScreen (Graphics g)
  {
    removeAll ();
    if (felix.getDeathState().equals ("Tile"))
      g.drawImage (tileDeathScreen, 0, 0, this);
    else if (felix.getDeathState().equals ("OutOfBounds"))
      g.drawImage (outOfBoundsDeathScreen, 0, 0, this);
    else if (felix.getDeathState().equals ("Time"))
      g.drawImage (timeDeathScreen, 0, 0, this);
    else
    {
      MainMenu.gameData.addSolvedWord (WordSearch.currWord);
    }
  }
  
  private void paintPauseScreen (Graphics g)
  {
    g.drawImage (pausedScreen, 0, 0, this);
  }
  
  /**
   * Looks at the time the player spent in completing 
   * the level and checks if it's worthy of a high score
   */
  private void checkHighScore ()
  {
    TimerPanel timerPanel = sideBar.getTimerPanel ();
    int time = timerPanel.getElapsed ();
    System.out.println (time);
    int highestTime = 0;
    if (oceanName.equals ("arctic"))
      highestTime = highScores.getArcticPlayers()[9].getTotalSecs ();
    else if (oceanName.equals ("antarctic"))
      highestTime = highScores.getAntarcticPlayers()[9].getTotalSecs ();
    else if (oceanName.equals ("atlantic"))
      highestTime = highScores.getAtlanticPlayers()[9].getTotalSecs ();
    else if (oceanName.equals ("indian"))
      highestTime = highScores.getIndianPlayers()[9].getTotalSecs ();
    else
      highestTime = highScores.getPacificPlayers()[9].getTotalSecs ();
    HighScoresFrame hsf;
    dispose ();
    if (time < highestTime)
    {
      hsf = new HighScoresFrame (time, oceanName);
    }
    else
    {
      JFrame frame = new JFrame ();
      frame.add (new InfoScreen (oceanName + "solved"));
      frame.setVisible (true);
      frame.setSize (900, 600);
      frame.setResizable (false);
      frame.setLocationRelativeTo (null);
    }
  }
  
  /**
   * Used to exit out from the ocean levels
   */
  private void exitGame ()
  {
    dispose ();
    if (oceanName.equals ("final"))
    {
      JFrame frame = new JFrame ();
      frame.setVisible (true);
      frame.setSize (900, 600);
      frame.setResizable (false);
      frame.setLocationRelativeTo (null);
      if (!felix.getDeathState().equals ("false"))
      {
        MainMenu.gameData.setTries (MainMenu.gameData.getTries ()+1);
        if (MainMenu.gameData.getTries () != 3)
        {
          frame.add (new InfoScreen ("finalretry"));
        }
        else
        {
          frame.add (new InfoScreen ("allover"));
        }
      }
      else
      {
        frame.add (new InfoScreen ("finalsolved"));
      }
    }
    else
    {
      if (felix.getDeathState().equals ("false"))
      {
        checkHighScore ();
      }
      else
      {
        MainMenu.gameData.resetAchievements (oceanName);
        WordSearch ws = new WordSearch ();
      }
    }
  }
  
  /**
   * Updates the elements on the screen 
   * @param g Reference to the Graphics class 
   */
  @Override
  public void update(Graphics g) {
    //Double buffering (Kilobolt)
    if (image == null){
      image = createImage(this.getWidth(), this.getHeight());
      second = image.getGraphics();
    }
    
    second.setColor(getBackground()); 
    second.fillRect(0, 0, getWidth(), getHeight());
    second.setColor(getForeground());
    paint(second);
    
    g.drawImage(image, 0, 0, this);
  }
  
  /**
   * Paints elements on the screen 
   * @param g Reference to the Graphics class 
   */
  @Override
  public void paint(Graphics g) {
    g.drawImage(background, ocean.getBgX(), ocean.getBgY(), this); //img, x, y, observer
    g.drawImage(background, ocean2.getBgX(), ocean2.getBgY(), this); 
    g.drawImage(currFelix, felix.getCentreX(), felix.getCentreY()/* + 300*/, this);
    //g.drawRect(felix.getCentreX(), felix.getCentreY(), 100, 100); //Collision box for Felix 
    if (currFelix.equals(felixRight)){
      g.setColor(Color.RED);
      //g.drawRect(felix.getCentreX() + 60, felix.getCentreY() + 25, 15, 30); //Front (Facing Right) 
      
      g.setColor(Color.ORANGE);
      //g.drawRect(felix.getCentreX() + 35, felix.getCentreY() + 10, 30, 15); //Top (Facing Right) 
      
      g.setColor(Color.PINK);
      //g.drawRect(felix.getCentreX() + 5, felix.getCentreY() + 60, 30, 10); //Bottom (Facing Right) 
      
    }else{
      g.setColor(Color.RED);
      //g.drawRect(felix.getCentreX() - 2, felix.getCentreY() + 25, 15, 30); //Front (Facing Left) 
      
      g.setColor(Color.ORANGE);
      //g.drawRect(felix.getCentreX() + 9, felix.getCentreY() + 10, 30, 15); //Top (Facing Left) 
      
      g.setColor(Color.PINK);
      //g.drawRect(felix.getCentreX() + 37, felix.getCentreY() + 60, 30, 10); //Bottom (Facing Left)
      
    }
    
    //g.setColor(Color.black);                
    paintTiles(g); 
  }
  
  /**
   * Updates the tile's position on the screen 
   * via the update method in the Tiles class. 
   */
  private void updateTiles(){
    
    for (int i = 0; i < tileArr.size(); i++){
      Tiles t = (Tiles)tileArr.get(i); 
      t.update();
    }
  }
  
  /**
   * Paints the tiles of the Tiles ArrayList 
   * @param g A reference to a graphics Object. 
   */
  private void paintTiles(Graphics g){
    
    
    for (int i = 0; i < tileArr.size(); i ++){
      Tiles t  = (Tiles)tileArr.get(i); 
      
      if (t.getTileX() > -50 && t.getTileX() < 1000){
        g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);  //Drawing the tiles
      }
    }
  }
  
  
  /**
   * Performs an action according to what key(s) are pressed 
   * @param e Gets the key which is currently being pressed 
   */
  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    
    switch (e.getKeyCode()){
      
      case KeyEvent.VK_RIGHT:  
        felix.moveRight();
        felix.setMovingRight(true); 
        currFelix = felixRight; 
        felix.setFacingRight(true);
        break; 
        
      case KeyEvent.VK_LEFT:
        felix.moveLeft();
        felix.setMovingLeft(true);
        currFelix = felixLeft; 
        felix.setFacingRight(false);
        
        break; 
        
      case KeyEvent.VK_SPACE: 
        felix.jump();
        break; 
        
      case KeyEvent.VK_ESCAPE:
        if (!paused)
      {
        gameMusic.pause ();
        paused = true;
        paintPauseScreen (getGraphics ());
      }
        else
        {
          gameMusic.resume ();
          paused = false;
        }
    }
  }
  
  /**
   * Performs an action according to what key was released 
   * @param e Gets the value of the key that was released 
   */
  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
    switch (e.getKeyCode()){
      
      case KeyEvent.VK_RIGHT:
        felix.setMovingRight(false); 
        felix.stop();
        break;
        
      case KeyEvent.VK_LEFT:
        felix.setMovingLeft(false);
        felix.stop(); 
        break;
        
      case KeyEvent.VK_SPACE:
        break;
        
    }
    
  }
  
  /**
   * Invoked when a key has been typed 
   * @param e Gets the value of the key that has been typed 
   */
  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }
  
  public static String getOceanName ()
  {
    return oceanName;
  }
  
  /**
   * Returns an instance of the Ocean class 
   * @return an instance of the Ocean class 
   */
  public static Ocean getO1(){
    return ocean; 
  }
  
  /**
   * Returns an instance of the Ocean class 
   * @return an instance of the Ocean class
   */
  public static Ocean getO2(){
    return ocean2; 
  }
  
  /**
   * Returns an instance of the Felix class 
   * @return an instance of the Felix class 
   */
  public static Felix getFelix(){
    return felix; 
  }
  
  public static SideBar getSideBar(){
    return sideBar;
  }
  
  public static HighScores getHighScores (){
    return highScores;
  }
  
  @Override
  public void actionPerformed (ActionEvent e)
  {
    ((Timer) e.getSource()).stop ();
    exitGame ();
  }
}
