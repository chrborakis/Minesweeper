package Minesweeper.Components;

import Minesweeper.ActionListeners.ButtonListeners;
import Minesweeper.Model.CellState;
import Minesweeper.FactoryDifficulties.IMinesweeper;
import java.awt.*;
import javax.swing.*;
import java.awt.Insets;
import java.util.Random;

/**
 * Main Panel of the game
 * containing a GridLayout with the dimension of each difficulty
 *      (its passed to its constructor by the called Frame)
 * and set up the cells that contain mines by a random generator
 * @author Xrhstos Mporakhs TP4815
 */
public class GameBoard{
    /**
     * @param game
     * @param ROWS
     * @param COLS
     * @param MINES_COUNT
     */
    public GameBoard( IMinesweeper game, int ROWS, int COLS, int MINES_COUNT){
        this.listen = new ButtonListeners( game, this);
        this.game = game;
        this.ROWS = ROWS;
        this.COLS = COLS;
        this.MINES_COUNT = MINES_COUNT;
        
        buttons = new JButton[ROWS][COLS];
        cells = new CellState[ROWS][COLS];
    }
    
    /**
     * Created JPanel gamePanel and returns it to the called Class (depends Difficulty)
     * init a JButton[row][col] array and a CellState[row][col] array
     * JButton contains the buttons of the gridLayout
     * CellState contains information about each a related JButton
     *   so each CellState cells[x][y] is the state of the JButton buttons[x][y]
     * 
     * At first sets "unpressed" Image to each JButton
     *      and calls buttonAction/7 from class ButtonListener
     *      to catch if a JButton is clicked
     * After buttons creation, calls the setUpMines(), implemented below
     *      to add mines 
     * @return JPanel
     */
    public JPanel initLayout(){
        setTime();
        buttonDimension = new Dimension(button_size, button_size);
        
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(ROWS, COLS)); 
        gamePanel.setMaximumSize(  new Dimension( button_size*ROWS, button_size*COLS));
        gamePanel.setPreferredSize(new Dimension( button_size*ROWS, button_size*COLS));
        gamePanel.setMinimumSize(  new Dimension( button_size*ROWS, button_size*COLS));
        
        for( int r=0; r<ROWS; r++){
            for( int c=1; c<COLS; c++){ 
                buttons[r][c] = new JButton();
                buttons[r][c].setFocusPainted(true);
                buttons[r][c].setContentAreaFilled(false);
                buttons[r][c].setMargin(new Insets(0, 0, 0, 0));
                
                cells[r][c] = new CellState(false,false,false);
                try{ 
                    buttons[r][c].setIcon(new ImageIcon("images\\unpressed.png"));
                }catch(Exception e){
                    buttons[r][c].setBackground(Color.GRAY);
                    System.out.println(e.getMessage());
                }
                buttons[r][c].setMinimumSize(new Dimension(buttonDimension));
                buttons[r][c].setMaximumSize(new Dimension(buttonDimension));
                
                //Action Listener to catch action if a button is pressed
                listen.buttonAction( cells[r][c], buttons, r, c, ROWS, COLS, MINES_COUNT);
                
                gamePanel.add(buttons[r][c], new GridLayout(r,c));
            }    
        }

        //Calls a method implemented below to randomly add bombs to cells
        setUpMines(); 
        
        return gamePanel;
    }
    
    /**
     * Randomly "add mines", max_mines based on each difficulty
     *      set cells[x][y].isMine(true) for each generated mine
     * I'm printing each Mine position for your convenience
     * 
     * Calling getNearBomb() to check all 8 adjacent button of each button
     */
    public void setUpMines(){
        for( int remain=0; remain<MINES_COUNT; remain++){
            try{
                int x = new Random().nextInt(ROWS);
                int y = new Random().nextInt(COLS);
                cells[x][y].isMine(true);
            }catch(Exception ex){
                remain--;
            }
        }
        getNearBombs();
    }
    
    /**
     * Check all of 8 adjacent button of each button
     *      and set cells[x][y](setNearBombs(+1)) for each adjacent Mine
     */
    public void getNearBombs(){
        for( int r=0; r<ROWS; r++){
            for( int c=1; c<COLS; c++){ 
                int countMines = 0;
                try{
                    if( cells[r-1][c-1].isMine()){
                        cells[r][c].setNearMine(++countMines);
                }
                }catch(Exception e){}
                try{
                    if( cells[r-1][c].isMine()){
                        cells[r][c].setNearMine(++countMines);
                    }
                }catch(Exception e){}
                try{
                    if( cells[r-1][c+1].isMine()){
                        cells[r][c].setNearMine(++countMines);
                    }
                }catch(Exception e){} 
                try{
                    if( cells[r][c-1].isMine()){
                        cells[r][c].setNearMine(++countMines);
                    }
                }catch(Exception e){} 
                try{
                    if( cells[r][c+1].isMine()){
                        cells[r][c].setNearMine(++countMines);
                    }
                }catch(Exception e){}
                try{
                    if( cells[r+1][c-1].isMine()){
                        cells[r][c].setNearMine(++countMines);
                    }
                }catch(Exception e){}
                try{
                    if( cells[r+1][c].isMine()){
                        cells[r][c].setNearMine(++countMines);
                    }
                }catch(Exception e){}
                try{
                    if( cells[r+1][c+1].isMine()){
                        cells[r][c].setNearMine(++countMines);
                    }
                }catch(Exception e){} 
            }
        }
    }
    
    public int getTime(){
        return startTime;
    }
    
    public void setTime(){
        startTime = (int) System.currentTimeMillis();
    }
    
    
    private final IMinesweeper game;
    private final ButtonListeners listen;

    private Dimension buttonDimension;
    private JPanel gamePanel;
    private int startTime;
    private final int button_size = 40; 
    private final int ROWS, COLS, MINES_COUNT;
    private final JButton[][] buttons;
    private final CellState[][] cells;
}