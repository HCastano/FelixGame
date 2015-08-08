package felixgame; 
/**
 * 
 * This class controls the actions associated with the main
 * character in the game. It is from here that his movement
 * is controlled, as well as things like figuring out if 
 * he is dead or not. 
 * 
 * 
 * @author H Castano 
 * @version 3 05.06.15
 * Time Spent: 2 hours 
 * 
 * <h2><strong> Instance Variables: </strong></h2> 
 * <p> 
 * <strong> JUMPSPEED </strong> Speed used when jumping 
 * <p> 
 * <strong> MOVESPEED </strong> Speed used when moving from side to side 
 * <p> 
 * <strong> centreX </strong> Centre x-coordinate of the character
 * <p> 
 * <strong> centreY </strong> Centre y-coordinate of the character 
 * <p> 
 * <strong> jumped </strong> Used to determine if the character is jumped or not 
 * <p> 
 * <strong> movingRight </strong> Used to determine if the character is moving 
 *                                                                right or not 
 * <p>  
 * <strong> movingLeft </strong> Used to determine if the character is moving 
 *                                                               left or not 
 * <p>
 * <strong> speedX </strong> Current speed in the x-direction 
 * <p> 
 * <strong> speedY </strong> Current speed in the y-direction 
 * <p> 
 * <strong> o1 </strong> Used to reference the Ocean class 
 * <p> 
 * <strong> o2 </strong> Used to reference the Ocean class 
 * <p> 
 * <strong> onPlatform </strong> Used to check if Felix is on a platform or not
 * <p> 
 * <strong> touchingTiles </strong> Used to check if Felix was previously touching a tile
 * <p> 
 * <strong> deathState </strong> Used to determine how Felix's run has ended 
 */
public class Felix {
  
  private final int JUMPSPEED = -13; 
  private final int MOVESPEED = 2;  
  
  private int centreX, centreY; 
  
  private boolean jumped, movingRight, movingLeft; 
  
  private int speedX, speedY; 
  
  
  private static Ocean o1; 
 private static Ocean o2;
  
  private boolean onPlatform; 
  private boolean touchingTiles;  
  private boolean facingRight; 
  
  private String deathState;
  
  /**
   * Initializes all the private instance variables 
   */
  public Felix(){
    
    centreX = 100; 
    centreY = 480;//ground; 
    
    jumped = false; 
    movingRight = false; 
    movingLeft = false; 
    
    
    speedX = 0; 
    speedY = 1; 
    
    onPlatform = true; 
    touchingTiles = false; 
    facingRight = true; 
    
    deathState = "false"; 
    
    o1 = GameDriver.getO1(); 
    o2= GameDriver.getO2(); 
  }
  
  
  /**
   * Updates character's movement 
   */
  public void update(){
    
    //Background and X movement 
    if (speedX == 0 /* || speedX < 0*/){
      o1.setSpeedX(0); 
      o2.setSpeedX(0); 
    }
    
    if (speedX > 0){
      if (centreX < 200){
        centreX += speedX;
      }
      o1.setSpeedX(-MOVESPEED); 
      o2.setSpeedX(-MOVESPEED); 
    }
    
    
    
    //    if (speedX < 0 && centreX < 400){
    // 
    //      o1.setSpeedX(MOVESPEED); 
    //      o2.setSpeedX(MOVESPEED); 
    //    }
    //    
    //    if (speedX < 0 && centreX > 400){
    //              centreX+=speedX;
    //      o1.setSpeedX(MOVESPEED); 
    //      o2.setSpeedX(MOVESPEED); 
    //    }
    
    
    //Backwards movement 
    if (speedX < 0 ){
      centreX+=speedX;
      o1.setSpeedX(MOVESPEED); 
      o2.setSpeedX(MOVESPEED); 
    }
    
    
    if (jumped || !onPlatform){
      speedY += 1;
    }
    
    centreY += speedY; //Gravity 
    
    
    if (centreX - 50 < 0){ //Prevents left escape
      centreX = 51; 
      o1.setSpeedX(MOVESPEED); 
      o2.setSpeedX(MOVESPEED); 
    }
    
    if (centreX + 135 > 900){ //Prevents right escape 
      centreX = 900-135; 
      o1.setSpeedX(-MOVESPEED); 
      o2.setSpeedX(-MOVESPEED); 
    }
    
    //Death
    if (centreY > 600 || centreY < -125){
      deathState = "OutOfBounds"; 
    }
    
  }
  
  /**
   * Increases the character's speed in
   * the positive x-direction 
   */
  public void moveRight(){
    speedX = MOVESPEED; 
  }
  
  /**
   * Increases the character's speed in
   * the negative x-direction 
   */
  public void moveLeft(){
    speedX = -MOVESPEED; 
  }
  
  /**
   * Stops the character's movement 
   */
  public void stopRight(){
    setMovingRight(false); 
    stop(); 
  }
  
  /**
   * Stops the character's movement
   * in the negative x-direction 
   */
  public void stopLeft(){
    setMovingLeft(false); 
    stop(); 
  }
  
  /**
   * Stops the character's movement, or moves them 
   * according to the direction they are switching to
   */
  public void stop(){
    if (isMovingRight() == false && isMovingLeft() == false) {
      speedX = 0;
    }
    
    if (isMovingRight() == false && isMovingLeft() == true) {
      moveLeft();
    }
    
    
    if (isMovingRight() == true && isMovingLeft() == false) {
      moveRight();
    }
  }
  
  /**
   * Makes the character jump
   */
  public void jump(){
    // if (!jumped){
    speedY = JUMPSPEED; 
    jumped = true; 
    //}
  }
  
  
  //Accessor and Mutator methods 
  
  /**
   * Returns the character's centre x-coordinate
   * @return the character's centre x-coordinate 
   */
  public int getCentreX() {
    return centreX;
  }
  
  /**
   * Sets the character's centre x-coordinate 
   * @param centreX the characters' new centre x-coordinate 
   */
  public void setCentreX(int centreX) {
    this.centreX = centreX;
  }
  
  /**
   * Returns the character's centre y-coordinate 
   *@return the character's centre y-coordinate 
   */
  public int getCentreY() {
    return centreY;
  }
  
  /**
   * Sets the character's centre y-coordinate 
   * @param centreY the character's new centre y-coordinate
   */
  public void setCentreY(int centreY) {
    this.centreY = centreY;
  }
  
  /**
   * Returns whether or not the character is jumped 
   * @return jump state of character 
   */
  public boolean isJumped() {
    return jumped;
  }
  
  /**
   * Sets whether or not the character is jumped 
   * @param jumped whether or not the character is jumped 
   */
  public void setJumped(boolean jumped) {
    this.jumped = jumped;
  }
  
  /**
   * Returns the character's speed in the x-direction 
   * @return the character's speed in the x-direction 
   */
  public int getSpeedX() {
    return speedX;
  }
  
  /**
   * Sets the character's speed in the x-direction 
   * @param speedX the chacter's new speed in the x-direction 
   */
  public void setSpeedX(int speedX) {
    this.speedX = speedX;
  }
  
  /**
   * Returns the character's speed in the y-direction 
   * @return the character's speed in the y-direction 
   */
  public int getSpeedY() {
    return speedY;
  }
  
  /**
   * Sets the character's speed in the y-direction 
   * @param speedY the character's new speed in the y-direction 
   */
  public void setSpeedY(int speedY) {
    this.speedY = speedY;
  }
  
  /**
   * Returns whether or not the character is moving right 
   * @return whether or not the character is moving right 
   */
  public boolean isMovingRight() {
    return movingRight;
  }
  
  /**
   * Sets whether or not the character is moving right 
   * @param movingRight sets whether or not the character is moving right 
   */
  public void setMovingRight(boolean movingRight) {
    this.movingRight = movingRight;
  }
  
  /**
   * Returns whether or no the character is moving left 
   * @return whether or not the character is moving left 
   */
  public boolean isMovingLeft() {
    return movingLeft;
  }
  
  /**
   * Sets whether or not the character is moving left 
   * @param movingLeft sets whether or not the character is moving left 
   */
  public void setMovingLeft(boolean movingLeft) {
    this.movingLeft = movingLeft;
  }
  
  /**
   * Returns whether or not the character is on a platform 
   * @return whether or not the character is on a platform 
   */
  public boolean isOnPlatform (){
    return onPlatform;
  }
  
  /**
   * Sets whether or not the character is on a platform 
   * @param onPlatform sets 
   */
  public void setOnPlatform (boolean onPlatform){
    this.onPlatform = onPlatform;
  }
  
  /**
   * Returns whether or not the character has 
   * been touching a tile
   * @return whether or not the character has 
   * been touching a tile 
   */
  public boolean isTouchingTiles() {
    return touchingTiles;
  }
  
  /**
   * Sets whether or not the character is touching
   * a tile
   * @param touchingTiles whether or not the character
   * is touching a tile
   */
  public void setTouchingTiles(boolean touchingTiles) {
    this.touchingTiles = touchingTiles;
  }
  
  /**
   * Returns whether or not Felix is facing right
   * @return whether or not Felix is facing right
   */
  public boolean isFacingRight() {
    return facingRight;
  }
  
  /**
   * Sets whether or not Felix is facing right
   * @param facingRight whether or not Felix is facing right
   */
  public void setFacingRight(boolean facingRight) {
    this.facingRight = facingRight;
  }
  
  /**
   * Returns the type of death which has occurred
   * @return the type of death which has occurred
   */
  public String getDeathState() {
    return deathState;
  }
  
  /**
   * Sets the type of death which has occurred
   * @param deathState the type of death which as occurred
   */
  public void setDeathState(String deathState) {
    this.deathState = deathState;
  }
}
