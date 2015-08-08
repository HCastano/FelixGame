package felixgame; 

import java.awt.Image;
import java.awt.Rectangle;

/**
 * This class selects the correct tile type and location
 * when loading up a map. 
 * 
 * @author H Castano 
 * @version 3 05.06.15
 * Time Spent: 5 hours 
 * 
 * <h2><b> Instance Variables: </b></h2>  
 * <p>
 * <b> tileX </b> Location of the tile in the x-direction 
 * <p> 
 * <b> tileY </b> Location of the tile in the y-direction 
 * <p> 
 * <b> speedX </b> Speed on the tile in the x-direction 
 * <p>
 * <b> type </b> Used to reference the type of the tile 
 * <p> 
 * <b> tileImage </b> Reference variable for the tile image 
 * <p> 
 * <b> ocean </b> Reference variable for the Ocean class 
 * <p> 
 * <b> felix </b> Reference variable for the Felix class 
 */
public class Tiles {

 private Image tileImage; 
 private int tileX, tileY, speedX, type; 

 private Ocean ocean = GameDriver.getO1(); 
 private Felix felix = GameDriver.getFelix(); 
 private String oceanName = GameDriver.getOceanName(); 
 public static boolean heartFound;
 private boolean hit; 

 /**
  * Initializes the tiles to the right position 
  * and type. 
  * 
  * @param x the x-coordinate of the tile
  * @param y the y-coordinate of the tile 
  * @param type the type of the tile 
  */
 public Tiles(int x, int y, int type){

  //Makes sure tiles are spaces properly 
  tileX = x*50; 
  tileY = y*50; 

  this.type = type; 




  /*
   * Depending on the type of the tile
   * a different image is loaded up. 
   */
  if (this.type == 5){ //Centre
   tileImage = GameDriver.tiles[4]; 
  }
  else if (this.type == 2){ //Bottom
   tileImage = GameDriver.tiles[2]; 
  }
  else if (this.type == 8){ //Top
   tileImage = GameDriver.tiles[3]; 
  }
  else if (this.type == 7){ //Clue
   tileImage = GameDriver.tiles[0];
  }
  else if (this.type == 9){ //Death
   tileImage = GameDriver.tiles[1]; 
  }
  else if (this.type == 4){ //Left
   tileImage = GameDriver.tiles[6];
  }
  else if (this.type == 6){ //Right
   tileImage = GameDriver.tiles[5]; 
  }
  else if (this.type == 3){ //Heart
   tileImage = GameDriver.tiles[8]; 
  }
  else{
   if (this.type == 1){ //Blank Tile 
    tileImage = GameDriver.tiles[9];   
   }
  }

 }

 /**
  * Updates the positions of the tile on the screen. 
  */
 public void update(){
  speedX = ocean.getSpeedX() * 5;
  tileX+=speedX; 


  Rectangle tile = new Rectangle (getTileX(), getTileY(), 30, 30);
  Rectangle fTop = new Rectangle(0,0,0,0), 
    fBottom = new Rectangle(0,0,0,0), 
    fFront = new Rectangle(0,0,0,0),
    fBack = new Rectangle(0,0,0,0); //Initializing hit boxes


  //The hit boxes are made in the appropriate 
  //direction that Felix is facing 
  if (felix.isFacingRight()){

   fFront = new Rectangle (felix.getCentreX() + 65, felix.getCentreY() + 25, 15, 30);
   //fBack = new Rectangle (felix.getCentreX() - 5, felix.getCentreY() + 35, 10,25);
   fBack = new Rectangle (felix.getCentreX() - 5, felix.getCentreY() + 25, 10,25);
   //fTop = new Rectangle(felix.getCentreX() + 35, felix.getCentreY() + 10, 30, 15);
   fTop = new Rectangle(felix.getCentreX() + 5, felix.getCentreY() + 10, 60, 15);
 
   fBottom = new Rectangle(felix.getCentreX() + 5, felix.getCentreY() + 60, 30, 10);

  }else{


   fFront = new Rectangle (felix.getCentreX() - 5 , felix.getCentreY() + 25, 15, 30);
   //fBack = new Rectangle(felix.getCentreX() + 67, felix.getCentreY() + 35, 10,25);
   fBack = new Rectangle(felix.getCentreX() + 67, felix.getCentreY() + 25, 10,25);
   //fTop = new Rectangle(felix.getCentreX() + 9, felix.getCentreY() + 10, 30, 15);
   fTop = new Rectangle(felix.getCentreX() + 9, felix.getCentreY() + 10, 60, 15);
   fBottom = new Rectangle (felix.getCentreX() + 37, felix.getCentreY() + 60, 30, 10);

  }

  //Prevents Felix from hitting 'inexistent' tiles 
  if (tileImage != null && !tileImage.equals(GameDriver.tiles[7])){

   
   //if(checkTopColl(tile, fTop))
    //topCollision(tile, fTop);
   //if(checkRightColl(tile, fFront))
    //sideCollision(tile, fFront);
   //if (checkLeftColl(tile, fBack))
    //backCollision(tile, fBack); 
   //if (checkBottColl(tile, fBottom));
    //bottomCollision(tile,fBottom); 
   
   if (!checkRightColl(tile, fFront) && !checkLeftColl(tile, fBack))
    topCollision(tile, fTop);
   if (!checkTopColl(tile,fTop)){

    if (checkRightColl(tile, fFront)){
     sideCollision(tile, fFront);
    }else{
     backCollision(tile, fBack); 
    }
   
   
   }
   
   bottomCollision(tile,fBottom); 
    
    
//   topCollision(tile, fTop); 
//   sideCollision(tile, fFront); 
//   backCollision(tile, fBack); 
//   bottomCollision(tile, fBottom);
   


  }

 }


 public boolean checkTopColl(Rectangle r1, Rectangle r2){
  return r1.getBounds().intersects(r2.getBounds()); 
 }
 
 public boolean checkRightColl(Rectangle r1, Rectangle r2){
  return r1.getBounds().intersects(r2.getBounds()); 
 }
 
 public boolean checkLeftColl(Rectangle r1, Rectangle r2){
  return r1.getBounds().intersects(r2.getBounds()); 
 }
 
 public boolean checkBottColl(Rectangle r1, Rectangle r2){
  return r1.getBounds().intersects(r2.getBounds()); 
 }
 
 
 /**
  * Checks if Felix has collided with a tile 
  * from the side. 
  * 
  * http://www.edu4java.com/en/game/game6.html
  * 
  * @param r1 the hit-box surrounding the tile
  * @param r2 the hit-box surrounding Felix 
  */
 public void sideCollision(Rectangle r1, Rectangle r2){


  if (r1.getBounds().intersects(r2.getBounds())){

   System.out.println("S");

   if (type == 9){
    felix.setDeathState ("Tile");
   }
   if (type == 7 || type == 1 || type == 3){
    if (type == 7 && !hit){
     GameDriver.getSideBar().addClue ();
     hit = true; 
    }

    if (type == 1 && !hit)
    { 
     GameDriver.getSideBar().addAchievement ();
     MainMenu.gameData.addAchievement(oceanName);
     hit = true;
    }
    
    if (type == 3 && !hit)
    {
      heartFound = true;
    }

    tileImage = GameDriver.tiles[7]; 


   }

   if (!felix.isTouchingTiles()  && type != 7 
     && type != 1 && type!= 3){


    felix.setSpeedX(0);

    if (felix.isFacingRight()){  //Depending on the dir. he is facing the 'jump' back
     felix.setCentreX(tileX - 80); 
     //felix.setCentreX(tileX - 70); //is changed

    }else{//Facing left 
     //if (!felix.isJumped())
     //felix.setCentreX(tileX + 40); 
     felix.setCentreX(tileX + 45); 
    }

   }else{
    felix.setTouchingTiles(true); 
   }

  }else{
   felix.setTouchingTiles(false);
  }
 }

 public void backCollision(Rectangle r1, Rectangle r2){

  if (r1.getBounds().intersects(r2.getBounds())){

   System.out.println("L");

   if (type == 9){
    felix.setDeathState ("Tile");
   }
   if (type == 7 || type == 1 || type == 3){
    if (type == 7 && !hit){
     GameDriver.getSideBar().addClue ();
     hit = true; 
    }

    if (type == 1 && !hit)
    {
     GameDriver.getSideBar().addAchievement ();
     MainMenu.gameData.addAchievement(oceanName);
     hit = true;
    }

    tileImage = GameDriver.tiles[7]; 


   }

   if (!felix.isTouchingTiles()  && type != 7 
     && type != 1 && type != 3){


    felix.setSpeedX(0);

    if (felix.isFacingRight()){  //Depending on the dir. he is facing the 'jump' back
     felix.setCentreX(tileX + 45); 
     

    }else{//Facing left 
     
     felix.setCentreX(tileX - 80); 
    }

   }else{
    felix.setTouchingTiles(true); 
   }

  }else{
   felix.setTouchingTiles(false);
  }
  

 }

 /**
  * Check if Felix has collided with a tile 
  * from the bottom. 
  * 
  * @param r1 the hit-box surrounding the tile 
  * @param r2 the hit-box under Felix
  */
 public void bottomCollision(Rectangle r1, Rectangle r2){

  if (r1.getBounds().intersects(r2.getBounds())){

   //System.out.println("B");

   if (this.type == 9){
    felix.setDeathState ("Tile");
   }
   if (type == 7 || type == 1 || type == 3){
    if (type == 7 && !hit){
     GameDriver.getSideBar().addClue ();
     hit = true; 
    }

    if (type == 1 && !hit){
     GameDriver.getSideBar().addAchievement ();
     MainMenu.gameData.addAchievement(oceanName);
     hit = true;
    }

    tileImage = GameDriver.tiles[7]; 
   }

   if (type != 7 && type != 1 && type != 3){

    felix.setCentreY(tileY - 70);
    felix.setSpeedY(0);
    felix.setJumped(false);
    felix.setOnPlatform (true);
   }
  }
  else{
   felix.setOnPlatform (false);
  }
 }

 /**
  * Checks if Felix has collided with a tile
  * with his head. 
  * @param r1 the hit-box surrounding the tile
  * @param r2 the hit-box on top of Felix 
  */
 public void topCollision(Rectangle r1, Rectangle r2){

  if (r1.getBounds().intersects(r2.getBounds())){

   System.out.println("T");

   if (this.type == 9){
    felix.setDeathState ("Tile");
   }
   if (this.type == 7 || this.type == 1 || this.type == 3){
    if (type == 7 && !hit){
     GameDriver.getSideBar().addClue ();
     hit = true;
    }

    if (type == 1 && !hit){
     GameDriver.getSideBar().addAchievement ();
     MainMenu.gameData.addAchievement(oceanName);
     hit = true;
    }

    tileImage = GameDriver.tiles[7]; 

   }

   if (type != 7 && type != 1 && type !=3/*&& !felix.isTouchingTiles ()*/){
    felix.setTouchingTiles (true);
    felix.setCentreY(tileY + 30);
    //felix.setSpeedY (10);
    felix.setSpeedY(1);
   }
  }
  //     else{
  //       felix.setTouchingTiles (false);
  //     }
 }


 //Accessor and Mutator Methods 

 /**
  * The x-component of the tile's location. 
  * @return the x-component of the tile's location. 
  */
 public int getTileX() {
  return tileX;
 }

 /**
  * Sets the tile's new x-coordinate
  * @param tileX the tile's new x-coordinate 
  */
 public void setTileX(int tileX) {
  this.tileX = tileX;
 }

 /**
  * Returns the tile's y-coordinate 
  * @return the tile's y-coordinate 
  */
 public int getTileY() {
  return tileY;
 }

 /**
  * Sets the tile's new y-coordinate 
  * @param tileY the tile's new y-coordinate 
  */
 public void setTileY(int tileY) {
  this.tileY = tileY;
 }

 /**
  * Returns the image of the tile 
  * @return the image of the tile 
  */
 public Image getTileImage() {
  return tileImage;
 }

 /**
  * Sets the new image for the tile 
  * @param tileImage the new image for the tile 
  */
 public void setTileImage(Image tileImage) {
  this.tileImage = tileImage;
 }
}
