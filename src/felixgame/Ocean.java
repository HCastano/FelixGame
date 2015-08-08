package felixgame; 
/**
 * This class is the superclass to the 5 oceans used in this game. It keeps track of the movement of the ocean as Felix moves across the screen.
 * 
 * @author Hernando Castano, last updated May 28, 2015
 * Version 3
 * <p>
 * <b> bgX </b> Stores x dimension of background
 * <p>
 * <b> bgY </b> Stores y dimension of background
 * <p>
 * <b> speedX </b> Stores speed of x dimension of background
 */
public class Ocean {

 private int bgX, bgY, speedX;
 
 /**
  * Constructor with two parameters to set the x and y dimensions of the background. Intitilizes speedX to 0.
  * 
  * @param x  x dimension of background, bgX
  * @param y  y dimension of background, bgY
  */
 public Ocean(int x, int y){
  
  bgX = x; 
  bgY = y; 
  speedX = 0;
 }
 
 /**
  * Updates x dimension of background according to speed
  */
 public void update(){
  
  bgX += speedX; 
  
  //Right now the image that I'm using
  //is 2160 x 480
  
  /*
   * This will allow the background to 'loop'
   * in a sense. If the left side scrolls too far
   * down, it will get moved to the very right. 
   */
  if (bgX <= -2400) //Check and make sure that the player can go backwards too 
   bgX+=4800;//4096;bgX+=4320; 
   
//  if(bgX >=2160) //Check, the character shouldn't be going back after a certain point 
//   bgX-=4096; 
  if (bgX >=2400)
   bgX-=4800; 
 }

 /**
  * Returns x dimension of background
  * 
  * @return bgX  x dimension of background
  */
 public int getBgX() {
  return bgX;
 }

 /**
  * Sets x dimension of background
  * 
  * @param bgX  new x dimension of background
  */
 public void setBgX(int bgX) {
  this.bgX = bgX;
 }
 
 /**
  * Returns y dimension of background
  * 
  * @return bgY  y dimension of background
  */
 public int getBgY() {
  return bgY;
 }

 /**
  * Sets y dimension of background
  * 
  * @param bgY  new y dimension of background
  */
 public void setBgY(int bgY) {
  this.bgY = bgY;
 }

 /**
  * Gets speed of the x dimension of the background
  * 
  * @return speedX  x speed of background
  */
 public int getSpeedX() {
  return speedX;
 }

 /**
  * Sets speed of the x dimension of the background
  * 
  * @param speedX  new x speed of background
  */
 public void setSpeedX(int speedX) {
  this.speedX = speedX;
 }
}
