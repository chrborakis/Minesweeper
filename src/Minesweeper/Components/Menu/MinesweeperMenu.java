package Minesweeper.Components.Menu;

import Minesweeper.FactoryDifficulties.IMinesweeper;
import Minesweeper.Model.Difficulty;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Initialize MenuBar (same for each difficulty's Frame)
 * @author Xrhstos Mporakhs TP4815
 */
public class MinesweeperMenu{
    private IMinesweeper minesweeper;

    /**
     * Pass IMinesweeper (interface) to handle all of the 3 difficulties Frames
     * @param minesweeper
     */
    public MinesweeperMenu( IMinesweeper minesweeper){
        this.minesweeper = minesweeper;
    }
    
    /**
     * Init Menu items and return it to Minesweeper Frame( beginner/intermediate/expert)
     * and calls methods 
     * selectDifficulty/4, for switch difficulty
     * otherAction/2, for new game or exit game
     * from the Class MinesweeperMenuListener to catch any action to Menu items
     * @return JMenuBar
     */
    public JMenuBar initMenu(){      
        JMenuBar menuBar = new JMenuBar();
        menuBar.add( createGameMenu());

        menuListeners.selectDifficulty( minesweeper,begginer,inter,expert);
        menuListeners.otherAction( minesweeper, newGame, credits, exit);
        
        return menuBar;
    }
    
    /**
     * Creates JMenuItems and JMenu and return it to initMenu method above
     * @return JMenu
     */
    public JMenu createGameMenu(){  
        gameMenu = new JMenu("Game");
        
        newGame = new JMenuItem("New Game");
        begginer = new JMenuItem("Begginer");
        inter = new JMenuItem("Intermediate");
        expert = new JMenuItem("Expert");
        credits = new JMenuItem("Credits");
        exit = new JMenuItem("Exit");
                                       
        gameMenu.add( newGame);
        gameMenu.addSeparator();
        gameMenu.add( setColor(begginer));
        gameMenu.add( setColor(inter));
        gameMenu.add( setColor(expert));
        gameMenu.addSeparator();
        gameMenu.add(credits);
        gameMenu.addSeparator();
        gameMenu.add(exit);
                
        return gameMenu;
    }
    
    /**
     * Sets JMenuItem color to orange and seOpaque to false
     * and when its selected set it to true and all the others to false
     * @param item
     * @return JMenuItem
     */
    public JMenuItem setColor( JMenuItem item){
        item.setBackground(Color.orange);
        item.setOpaque(false);
        return item;
    }

    /**
     * Sets difficulty based on which Frame is visible
     * @param diff
     */
    public void setDifficulty( Difficulty diff){
        this.difficulty = diff;
    }

    /**
     * Gets difficulty based on which Frame is visible
     * @return
     */
    public Difficulty getDifficulty(){
        return difficulty;
    }
    
    private JMenu gameMenu;
    private JMenuItem newGame, begginer, inter, expert, credits, exit;
    private Difficulty difficulty;
    private final MinesweeperMenuListeners menuListeners = new MinesweeperMenuListeners( minesweeper, this);
}
