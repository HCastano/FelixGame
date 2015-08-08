package felixgame; 

import javax.swing.*;

/**
 * This class is used for creating the word search graphics. 
 * 
 * @author Ansh Juneja, last updated June 01, 2015
 * @version 1
 * Time Spent: 30 minutes 
 * 
 * <b> Instance Variables </b> 
 * <p> 
 * <b> letter </b> letter of cell
 * <p> 
 * <b> row </b> row of cell
 * <p> 
 * <b> column </b> column of cell
 * <p> 
 * <b> selected </b> whether cells is selected
 * <p> 
 * <b> icon</b> icon of cell
 * <p> 
 * <b> selectedIcon </b> selected icon of cell
 * <p> 
 * <b> disabledIcon </b> disabled icon of cell
 * <p> 
 */
public class Cell extends JLabel
{
  private String letter;
  private int row, column;
  private boolean selected;
  private Icon icon, selectedIcon, disabledIcon;

  /**
   * Initializes all the instance variables
   * 
   * @param letter the letter in the cell
   * @param row the row of the letter in the cell
   * @param column the column of the letter in the cell 
   */
  public Cell (String letter, int row, int column)
  {
    this.letter = letter;
    this.row = row;
    this.column = column;
    icon = new ImageIcon ("assets/Pictures/cells/" + letter + ".png");
    selectedIcon = new ImageIcon ("assets/Pictures/cells/red" + letter + ".png");
    disabledIcon = new ImageIcon ("assets/Pictures/cells/grey" + letter + ".png");
    setIcon (icon);
  }
  
  /**
   * Changes the colour of the letter when it is 
   * selected by the user 
   * 
   * @param selected whether or not the cell has been
   * selected by the user 
   */
  public void setSelected (boolean selected)
  {
    if (selected)
    {
      setIcon (selectedIcon);
      this.selected = true;
    }
    else
    {
      setIcon (icon);
      this.selected = false;
    }
  }
  
  /**
   * Sets the icon of the cell to 
   * the right disabled state 
   */
  public void setDisabled ()
  {
    setIcon (disabledIcon);
  }
  
  /**
   * Returns the letter selected
   * @return the letter selected 
   */
  public String getLetter ()
  {
    return letter;
  }
  
  /**
   * Returns whether or not a cell
   * has been selected 
   * 
   * @return whether or not a cell has 
   * been selected 
   */
  public boolean isSelected ()
  {
    return selected;
  }
  
  /**
   * Returns the row of a cell
   * @return the row of a cell
   */
  public int getRow ()
  {
    return row;
  }
  
  /**
   * Returns the column of a cell
   * @return the column of a cell 
   */
  public int getColumn ()
  {
    return column;
  }
}
