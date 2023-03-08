package Minesweeper.Model;
/**
 * State for each JButton of the GridLayout and 
 * its objects are stored in a CellState[][]
 * CellState[][] index equals to JButton[][] index
 * 
 * Information is about if a JButton
 *      -isClicked, true if these Button is clicked once, to prevent re-click
 *      -isMine, true if it contains a mine
 *      -isFlagged, if its JButton there was a right-click action
 *      -nearBomb, total of adjacent mines, 8 max
 * 
 * @author Xrhstos Mporakhs TP4815
 */
public class CellState{
    private boolean isClicked;
    private boolean isMine;
    private boolean isFlagged;
    private int nearMines;
    
    public CellState(boolean isClicked, boolean isMine, boolean isFlagged){
        this.isClicked = isClicked;
        this.isMine = isMine;
        this.isFlagged = isFlagged;
    }    
    
    public boolean isClicked(){
        return isClicked;
    }
    public void isClicked( boolean state){
        this.isClicked = state;
    }
    public boolean isMine(){
        return isMine;
    }
    public void isMine( boolean state){
        this.isMine = state;
    }
    public boolean isFlagged(){
        return isFlagged;
    }
    public void setFlagged( boolean state){
        this.isFlagged = state;
    }
    public int getNearMines(){
        return nearMines;
    }
    public void setNearMine( int nearMines){
        this.nearMines = nearMines;
    }
}
