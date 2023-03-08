package Minesweeper.ActionListeners;

import Minesweeper.Model.CellState;
import Minesweeper.FactoryDifficulties.*;
import javax.swing.JOptionPane;

/**
 * @author Xrhstos Mporakhs TP4815
 */
public class GameActions {
    private int countClicked = 0;
    public GameActions(){}
    
    /**
     * Count each button that is clicked only ONCE
     * and if the count is equals to totalCells - totalMines
     * then the player has won, and its asked if he wants to replay or exit
     * @param startTime
     * @param game
     * @param box
     * @param MAX_ROW
     * @param MAX_COL
     * @param MINES_COUNT
     */
    public void checkIfOver( int startTime, IMinesweeper game, CellState box, int MAX_ROW, int MAX_COL, int MINES_COUNT){
        countClicked++;
        if(countClicked == (MAX_ROW*MAX_COL)-MINES_COUNT){
            long finish = System.currentTimeMillis();
            long duration = (finish - startTime)/1000;
            String[] options = new String[] {"Retry?", "Exit"};
            int option =  JOptionPane.showOptionDialog(
                null,
                "You Won!!\nGame Duration: "+duration+"secs.\nYou want to play again?",
                "Congratulations",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]
                );
            switch(option){
                default:
                    restartGame( game);
                    break;
                case 1: System.exit(0);
            } 
        }
    }
         
    /**
     * If game is Over, display a JOptionPane to give options to retry or exit
     * @param startTime
     * @param game
     */
    public void gameOver( int startTime, IMinesweeper game){
        long finish = System.currentTimeMillis();
        long duration = (finish - startTime)/1000;
        String[] options = new String[] {"Retry?", "Exit"};
        int option =  JOptionPane.showOptionDialog(
                null,
                "You Lost.\n Game Duration: "+duration+"secs.\nYou want to play again?",
                "Game Over",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]
            );
        switch(option){
            default:
                restartGame( game);
                break;
            case 1: System.exit(0);
            }    
    }   
    
    /**
     * Restart Game of the same difficulty
     * @param game
     */
    public void restartGame( IMinesweeper game){
        System.out.println("\nNew Game");
        //h prwti methodos getDifficulty kanei return to antikeimeno Difficulty
        //kai h deuteri methodos kanei return to string onoma tou antikeimenou
        String difficulty = game.getDifficulty().getDifficulty();
        switch(difficulty) {
            case "Begginer":
                game.setVisible(false);
                new MinesweeperBegginer();
                break;
            case "Intermediate":
                game.setVisible(false);
                new MinesweeperIntermediate();
                break;
            case "Expert":
                game.setVisible(false);
                new MinesweeperExpert();
                break;
        }
    }
}