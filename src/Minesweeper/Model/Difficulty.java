package Minesweeper.Model;

/**
 *
 * @author Xrhstos Mporakhs TP4815
 */
public class Difficulty{
    public Difficulty(){}
    public Difficulty( String difficulty){
        switch(difficulty){
            case "Begginer":
                rows = 9;
                cols = 9;
                mines = 10;
                break;
            case "Intermediate":
                rows = 16;
                cols = 16;
                mines = 40;
                break;
            case "Expert":
                rows = 16;
                cols = 30;
                mines = 99;
                break;
        }
        this.difficulty = difficulty;
        toString();
    }
    
    public String getDifficulty(){
        return difficulty;
    } 
    public int getRows(){
        return rows;
    }  
    public int getCols(){
        return cols;
    }  
    public int getMines(){
        return mines;
    }
    
    @Override
    public String toString(){
        return getDifficulty()+"[Rows: "+getRows()+" || Cols: "+getCols()+" || Mines: "+getMines()+"]";
    }
    
    private int rows, cols, mines;
    private String difficulty;
}
