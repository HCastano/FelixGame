package felixgame; 

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Component;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * This class draws and implements a word search with the hidden words "ATTACK", "HOME", "FAMILY", "FOOD", "STORM"
 * 
 * @author Ansh Juneja last updated May 21, 2015
 * @version 2
 * Time Spent: 5 hours
 * <p>
 * <b> Instance Variables: </b>
 * <p>
 * <b> wordSearch </b> 2D array with each element representing a cell of the wordsearch
 * <p>
 * <b> wordSearchRed </b> 2D array with each element representing a red (selected) cell of the wordsearch
 * <p>
 * <b> wordSearchGrey </b> 2D array with each element representing a grey (solved) cell of the wordsearch
 * <p>
 * <b> selectedCells </b> ArrayList containing Strings representing coordinates of the selected cells in the wordsearch
 * <p>
 * <b> attackLine </b> Line to cross out "ATTACK" in word list when word is found
 * <p>
 * <b> foodLine </b> Line to cross out "FOOD" in word list when word is found
 * <p>
 * <b> stormLine </b> Line to cross out "STORM" in word list when word is found
 * <p>
 * <b> homeLine </b> Line to cross out "HOME" in word list when word is found
 * <p>
 * <b> familyLine </b> Line to cross out "FAMILY" in word list when word is found
 */
public class WordSearch extends JFrame implements MouseListener, ActionListener
{
  BufferedImage arrowImage, enlargedArrowImage, pressedArrowImage, invisibleImage;
  JLabel arrowButton, enlargedArrowButton, pressedArrowButton;
  private Component tempComp;
  private boolean tempEntered = true;
  private boolean pressed;
  private JPanel panel;
  private Cell [][] wordSearch = new Cell [10][10];
  private ArrayList <Cell> selectedCells;
  private JLabel attackLine, foodLine, stormLine, homeLine, familyLine;
  private String level;
  public static String currWord;
  private Timer delayTimer;
  public static Music wordSearchMusic;
  
  /**
   * Constructor which defines the wordSearch arrays by reading from a text file, creates and adds the background and lines of the words, and draws the
   * word search by calling the drawWordSearch () method. It also sets the size of the panel to 900x600 and sets it to visible.
   * <p>
   * <b> Local Variables: </b>
   * <p>
   * <b> wordSearchLetters </b> 2D array containing characters of the word search read from the text file
   * <p>
   * <b> input </b> BufferedReader object used to read from text file
   * <p>
   * <b> line </b> Icon object containing image of line used to cross out words when they are found
   * <p>
   * <b> background </b> Icon object containing image of background
   * <p>
   * <b> backgroundLabel </b> JLabel object with background icon
   */
  public WordSearch ()
  {
    super ("Felix by 3AM Productions");
    panel = new JPanel ();
    setLayout (null);
    
    panel.setLayout (null);
    
    wordSearchMusic = new Music ("Anamalie");
    
    selectedCells = new ArrayList <Cell> ();
    
    char[][] wordSearchLetters = new char[10][10];
    BufferedReader input;
    try
    {
      input = new BufferedReader (new FileReader ("assets/wordsearch.txt"));
      for (int row = 0 ; row < 10 ; row++)
      {
        for (int column = 0; column < 10 ; column++)
        {
          wordSearchLetters [row][column] = (char) input.read ();
        }
        input.readLine ();
      }
      input.close ();
      for (int row = 0 ; row < 10 ; row++)
      {
        int column = 0;
        for (char letter : wordSearchLetters[row])
        {
          wordSearch [row][column] = new Cell (Character.toString (letter), row, column);
          column++;
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace ();
    }
    
    try
    {
      arrowImage = ImageIO.read (new File ("assets/Pictures/arrow.png"));
      enlargedArrowImage = ImageIO.read (new File ("assets/Pictures/enlargedarrow.png"));
      pressedArrowImage = ImageIO.read (new File ("assets/Pictures/pressedarrowlight.png"));
      invisibleImage = ImageIO.read (new File ("assets/Pictures/invisible.png"));
      
      arrowButton = new JLabel (new ImageIcon (arrowImage));
      enlargedArrowButton = new JLabel (new ImageIcon (enlargedArrowImage));
      pressedArrowButton = new JLabel (new ImageIcon (pressedArrowImage));
      
      arrowButton.setBounds (20, 515, 90, 80);
      enlargedArrowButton.setBounds (10, 505, 110, 100);
      pressedArrowButton.setBounds (20, 515, 90, 80);
      
      panel.add (arrowButton);
      panel.add (enlargedArrowButton);
      panel.add (pressedArrowButton);
      
      enlargedArrowButton.setVisible (false);
      pressedArrowButton.setVisible (false);
      arrowButton.addMouseListener (this);
    }
    catch (Exception e)
    {
    }
    
    Icon line = new ImageIcon ("assets/Pictures/line.png");
    
    attackLine = new JLabel (line);
    foodLine = new JLabel (line);
    stormLine = new JLabel (line);
    homeLine = new JLabel (line);
    familyLine = new JLabel (line);
    
    panel.add (attackLine);
    panel.add (foodLine);
    panel.add (stormLine);
    panel.add (homeLine);
    panel.add (familyLine);
    
    attackLine.setBounds (633, 175, 150, 5);
    foodLine.setBounds (633, 232, 150, 5);
    homeLine.setBounds (633, 289, 150, 5);
    familyLine.setBounds (633, 346, 150, 5);
    stormLine.setBounds (633, 403, 150, 5);
    
    attackLine.setVisible (false);
    foodLine.setVisible (false);
    homeLine.setVisible (false);
    familyLine.setVisible (false);
    stormLine.setVisible (false);
    
    Icon background = new ImageIcon ("assets/Pictures/wordsearchbackground.png");
    JLabel backgroundLabel = new JLabel (background);
    backgroundLabel.setBounds (0, 0, 900, 600);
    
    drawWordSearch ();
    disableFoundWords ();
    
    panel.setBounds (0, 0, 900, 600);
    
    panel.add (backgroundLabel);
    add (panel);
    
    wordSearchMusic.play ();
    
    setSize (900, 630);
    setResizable (false);
    setLocationRelativeTo (this);
    if (MainMenu.gameData.getSolvedWords().size () == 5)
    {
      add (new InfoScreen ("finalocean"));
    }
    setVisible (true);
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  }
  
  private void disableFoundWords ()
  {
    for (String word: MainMenu.gameData.getSolvedWords ())
    {
      disableWord (word);
    }
  }
  
  /**
   * Draws word search and also adds red/grey word search cells however sets their visibility to false. Also adds mouse listeners to cells of regular and
   * red word search.
   * <p>
   * <b> Local Variables: </b>
   * <p>
   * <b> x </b> x component of cell placements
   * <p>
   * <b> y </b> y component of cell placements
   */
  private void drawWordSearch ()
  {
    int x = 10;
    int y = 10;
    for (int row = 0 ; row < 10 ; row++)
    {
      for (int col = 0 ; col < 10 ; col++)
      {
        wordSearch [row][col].setBounds (x, y, 50, 50);
        panel.add (wordSearch[row][col]);
        wordSearch[row][col].addMouseListener (this);
        x+=50;
      }
      y+=50;
      x=10;
    }
  }
  
  /**
   * Returns position of given element in given 2D array as an array containing row and column
   * @param search  2D array which is to be searched
   * @param element  element to be found in 2D array
   * @return {row,col}  row and column of element in 2D array
   * <p>
   * <b> Local variables: </b>
   * <p>
   * <b> row </b>  row in 2D array of element to be found, intitialized as -1
   * <p>
   * <b> col </b>  column in 2D array of element to be found, initiialized as -1
   */
  private int[] elementPosition (Cell search[][], Cell element)
  {
    int row = -1;
    int col = -1;
    for (int i = 0; i < search.length; i++) 
    { 
      for (int j = 0; j < search[i].length; j++) 
      { 
        if (search[i][j].equals (element)) 
        {
          row = i;
          col = j;
        }
      }
    }
    return new int[] {row, col};
  }
  
  /**
   * Greys out solved words in wordsearch, crosses them out in the word list, and returns the solved word, or "None" if none found
   * @return solved word or "None"
   */
  private String wordFound ()
  {
    String letters = "";
    if (selectedCells.size() == 6)
    {
      for (Cell cell : selectedCells)
        letters += cell.getLetter ();
      if (letters.contains ("A") && letters.contains ("T") && letters.contains ("C") && letters.contains ("K"))
        return "Attack";
      if (letters.contains ("F") && letters.contains ("A") && letters.contains ("M") && letters.contains ("I") && letters.contains ("L")
            && letters.contains ("Y"))
        return "Family";
    }
    if (selectedCells.size () == 4)
    {
      for (Cell cell : selectedCells)
        letters += cell.getLetter ();
      if (letters.contains ("H") && letters.contains ("O") && letters.contains ("M") && letters.contains ("E"))
        return "Home";
      if (letters.contains ("F") && letters.contains ("O") && letters.contains ("D"))
        return "Food";
    }
    if (selectedCells.size () == 5)
    {
      for (Cell cell : selectedCells)
        letters += cell.getLetter ();
      if (letters.contains ("S") && letters.contains ("T") && letters.contains ("O") && letters.contains ("R") && letters.contains ("M"))
        return "Storm";
    }
    return "None";
  }
  
  private void disableWord (String word)
  {
    if (word.equals ("Family"))
    {
      familyLine.setVisible (true);
      for (int n = 4 ; n <= 9 ; n++)
      {
        selectedCells.remove ("4" + Integer.toString (n));
        wordSearch[4][n].setDisabled ();
        wordSearch[4][n].removeMouseListener (this);
      }
    }
    else if (word.equals ("Home"))
    {
      homeLine.setVisible (true);
      for (int n = 0 ; n <= 3 ; n++)
      {
        selectedCells.remove (Integer.toString (n) + "9");
        wordSearch[n][9].setDisabled ();
        wordSearch[n][9].removeMouseListener (this);
      }
    }
    else if (word.equals ("Storm"))
    {
      stormLine.setVisible (true);
      for (int n = 1 ; n <= 5 ; n++)
      {
        selectedCells.remove (Integer.toString (n) + "2");
        wordSearch[n][2].setDisabled ();
        wordSearch[n][2].removeMouseListener (this);
      }
    }
    else if (word.equals ("Food"))
    {
      foodLine.setVisible (true);
      for (int n = 5 ; n <= 8 ; n++)
      {
        selectedCells.remove (Integer.toString (n) + "3");
        wordSearch[n][3].setDisabled ();
        wordSearch[n][3].removeMouseListener (this);
      }
    }
    else
    {
      attackLine.setVisible (true);
      for (int n = 0 ; n <= 5 ; n++)
      {
        selectedCells.remove ("9" + Integer.toString (n));
        wordSearch[9][n].setDisabled ();
        wordSearch[9][n].removeMouseListener (this);
      }
    }
  }
  
  /**
   * checks if given cell of word search is a valid selection (if it is adjacent to an already selected cell if it is not the first one)
   * @param cell  the cell to be checked for validity
   * @return whether the cell is valid (true) or not (false)
   * <b> Local Variables: </b>
   * <p>
   * <b> row </b> row in wordsearch 2d array of cell passed in
   * <p>
   * <b> col </b> col in wordsearch 2d array of cell passed in
   */
  private boolean validSelection (Cell cell)
  {
    int row = cell.getRow ();
    int col = cell.getColumn ();
    if (selectedCells.size () == 0 || (row != 9 && wordSearch[row+1][col].isSelected ()) || ( col != 9 && wordSearch[row][col+1].isSelected ())
          || (row != 0 && wordSearch[row-1][col].isSelected ()) || (col != 0 && wordSearch[row][col-1].isSelected ()))
      return true;
    return false;
  }
  
  public void enterOcean ()
  {
    panel.setVisible (false);
    add (new InfoScreen (level));
  }
  
  /**
   * Invoked when the mouse is clicked on a component; depending on cell clicked, sets its visibility to false and displays different state of cell
   * (either black or red)
   * @param e  MouseEvent object pertaining to the component on which mouse button was clicked
   */
  @Override
  public void mouseClicked (MouseEvent e)
  {
    if (!e.getComponent().equals (arrowButton))
    {
      Cell cell = (Cell) e.getComponent ();
      if (cell.isSelected ())
      {
        cell.setSelected (false);
        selectedCells.remove (cell);
      }
      else
      {
        if (validSelection (cell))
        {
          cell.setSelected (true);
          selectedCells.add (cell);
        }
      }
      String word = wordFound ();
      if (!word.equals ("None"))
      {
        currWord = word;
        if (word.equals ("Home"))
        {
          level = "pacificintro";
        }
        if (word.equals ("Family"))
        {
          level = "indianintro";
        }
        if (word.equals ("Storm"))
        {
          level = "antarcticintro";
        }
        if (word.equals ("Food"))
        {
          level = "atlanticintro";
        }
        if (word.equals ("Attack"))
        {
          level = "arcticintro";
        }
        delayTimer = new Timer (150, this);
        delayTimer.setInitialDelay (150);
        delayTimer.start ();
      }
    }
  }
  
  /**
   * Invoked when mouse enters a component
   * @param e  MouseEvent object pertaining to the component on which mouse entered
   */
  @Override
  public void mouseEntered (MouseEvent e)
  {
    if (e.getComponent().equals (arrowButton))
    {
      if (!pressed)
      {
        enlargedArrowButton.setVisible (true);
        arrowButton.setIcon (new ImageIcon (invisibleImage));
      }
      if (e.getComponent().equals (tempComp)) //mouse has entered pressed component's area
        tempEntered = true;
    }
  }
  
  /**
   * Invoked when mouse exits a component
   * @param e  MouseEvent object pertaining to the component on which mouse exited
   */
  @Override
  public void mouseExited (MouseEvent e)
  {
    if (e.getComponent().equals (arrowButton))
    {
      if (!pressed)
      {
        ((JLabel)e.getComponent()).setIcon (new ImageIcon (arrowImage));
        enlargedArrowButton.setVisible (false);
      }
      tempEntered = false; //mouse has left component area
    }
  }
  
  /**
   * Invoked when mouse presses on a component
   * @param e  MouseEvent object pertaining to the component on which mouse button was pressed
   */
  @Override
  public void mousePressed (MouseEvent e)
  {
    if (e.getComponent().equals (arrowButton))
    {
      pressed = true;
      enlargedArrowButton.setVisible (false);
      ((JLabel) e.getComponent()).setIcon (new ImageIcon (invisibleImage));
      pressedArrowButton.setVisible (true);
      tempComp = e.getComponent (); //component user has pressed on
      tempEntered = true; //mouse is in component area
    }
  }
  
  /**
   * Invoked when mouse releases on a component
   * @param e  MouseEvent object pertaining to the component on which mouse button was released
   */
  @Override
  public void mouseReleased (MouseEvent e)
  {
    if (e.getComponent().equals (arrowButton))
    {
      pressed = false;
      arrowButton.setIcon (new ImageIcon (arrowImage));
      pressedArrowButton.setVisible (false);
      if (tempEntered)
      {
        dispose ();
        wordSearchMusic.stop ();
        MainMenu mm = new MainMenu (false, MainMenu.gameData);
      }
    }
  }
  
  @Override
  public void actionPerformed (ActionEvent e)
  {
    delayTimer.stop ();
    enterOcean ();
  }
}
