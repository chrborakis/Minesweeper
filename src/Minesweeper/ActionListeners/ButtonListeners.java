package Minesweeper.ActionListeners;

import Minesweeper.Model.CellState;
import Minesweeper.FactoryDifficulties.*;
import Minesweeper.Components.GameBoard;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

/**
 * Action Listeners for GridLayout
 * @author Xrhstos Mporakhs TP4815
 */
public class ButtonListeners{
    private final IMinesweeper game;
    private final GameBoard board;
    private final GameActions actions;
    
    /**
     * Pass IMinesweeper and GameBoard to access its getter
     * @param game
     * @param board
     */
    public ButtonListeners( IMinesweeper game, GameBoard board){
        this.game = game;
        this.board = board;
        actions = new GameActions();
    }
    
    /**
     * Catch action to each selected button and set its JButton Icon based on a rule - condition
     * - If button contains a mine and isn't clicked yet, sets its icon to a bomb and mark it as clicked
     * - If button doesn't contain a mine and isn't clicked yet, 
     *      sets its icon to an image containing a number based of its adjacent mines and mark it as clicked
     * - If button is left clicked and isn't clicked yet, sets its icon to a flag and mark it as clicked
     *      and for each one pressed, count down each flag placed and
     *      call game.getRemainMines() to set its value to the new count value
     * @param box
     * @param buttons
     * @param row
     * @param col
     * @param MAX_ROWS
     * @param MAX_COLS
     * @param MINES_COUNT
     */
    public void buttonAction( CellState box, JButton[][] buttons, int row, int col, int MAX_ROWS, int MAX_COLS, int MINES_COUNT){
        ActionListener buttonListener;
        buttonListener = (ActionEvent evt) -> {
            JButton selectedBtn = (JButton) evt.getSource();
            for(int r=0; r<MAX_ROWS; r++) {
                for(int c=0; c<MAX_COLS; c++) {
                    if(buttons[r][c] == selectedBtn){
                        if(!box.isClicked()){
                            actions.checkIfOver( board.getTime(), game, box, MAX_ROWS, MAX_COLS, MINES_COUNT);
                        }
                        if(box.isMine() && !box.isClicked()){
                            System.out.printf("Bomb at [%d|%d]%n", r, c);
                            bombCell = new ImageIcon(new ImageIcon("images\\bomb.jpg").getImage().getScaledInstance(buttons[r][c].getWidth(), buttons[r][c].getHeight(), Image.SCALE_SMOOTH));
                            buttons[r][c].setIcon(bombCell);
                            box.isClicked( true);
                            actions.gameOver( board.getTime(), game);
                        }
                        else if(!box.isMine() && !box.isClicked()){
                            switch(box.getNearMines()){
                                case 0:
                                    emptyCell = new ImageIcon(new ImageIcon("images\\empty.jpg").getImage().getScaledInstance(buttons[r][c].getWidth(), buttons[r][c].getHeight(), Image.SCALE_SMOOTH));
                                    buttons[r][c].setIcon(emptyCell);
                                    break;
                                case 1:
                                    nearbyMines = new ImageIcon(new ImageIcon("images\\1.png").getImage().getScaledInstance(buttons[r][c].getWidth(), buttons[r][c].getHeight(), Image.SCALE_SMOOTH));
                                    buttons[r][c].setIcon(nearbyMines);
                                    break;
                                case 2:
                                    nearbyMines = new ImageIcon(new ImageIcon("images\\2.png").getImage().getScaledInstance(buttons[r][c].getWidth(), buttons[r][c].getHeight(), Image.SCALE_SMOOTH));
                                    buttons[r][c].setIcon(nearbyMines);
                                    break;
                                case 3:
                                    nearbyMines = new ImageIcon(new ImageIcon("images\\3.png").getImage().getScaledInstance(buttons[r][c].getWidth(), buttons[r][c].getHeight(), Image.SCALE_SMOOTH));
                                    buttons[r][c].setIcon(nearbyMines);
                                    break;
                                case 4:
                                    nearbyMines = new ImageIcon(new ImageIcon("images\\4.png").getImage().getScaledInstance(buttons[r][c].getWidth(), buttons[r][c].getHeight(), Image.SCALE_SMOOTH));
                                    buttons[r][c].setIcon(nearbyMines);
                                    break;
                                case 5:
                                    nearbyMines = new ImageIcon(new ImageIcon("images\\5.png").getImage().getScaledInstance(buttons[r][c].getWidth(), buttons[r][c].getHeight(), Image.SCALE_SMOOTH));
                                    buttons[r][c].setIcon(nearbyMines);
                                    break;
                                case 6:
                                    nearbyMines = new ImageIcon(new ImageIcon("images\\6.png").getImage().getScaledInstance(buttons[r][c].getWidth(), buttons[r][c].getHeight(), Image.SCALE_SMOOTH));
                                    buttons[r][c].setIcon(nearbyMines);
                                    break;
                                case 7:
                                    nearbyMines = new ImageIcon(new ImageIcon("images\\7.png").getImage().getScaledInstance(buttons[r][c].getWidth(), buttons[r][c].getHeight(), Image.SCALE_SMOOTH));
                                    buttons[r][c].setIcon(nearbyMines);
                                    break;
                                case 8:
                                    nearbyMines = new ImageIcon(new ImageIcon("images\\8.png").getImage().getScaledInstance(buttons[r][c].getWidth(), buttons[r][c].getHeight(), Image.SCALE_SMOOTH));
                                    buttons[r][c].setIcon(nearbyMines);
                                    break;
                            }
                            box.isClicked( true);
                        }
                    }
                }
            }
        };
        
        buttons[row][col].addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    int remainMines = Integer.parseInt(game.getRemain().getText().trim());
                    if(!box.isClicked() && remainMines>0){
                        flag = new ImageIcon(new ImageIcon("images\\flag.png").getImage().getScaledInstance(buttons[row][col].getWidth(), buttons[row][col].getHeight(), Image.SCALE_SMOOTH));
                        buttons[row][col].setIcon(flag);
                        remainMines--;
                        game.getRemain().setText(String.valueOf(remainMines));
                    }else if(!box.isClicked() && remainMines==0){
                        JOptionPane.showMessageDialog(null,"No more flags remaining", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }        
            }               
        });
        buttons[row][col].addActionListener(buttonListener);
    }

    private ImageIcon bombCell, emptyCell, nearbyMines, flag;
}