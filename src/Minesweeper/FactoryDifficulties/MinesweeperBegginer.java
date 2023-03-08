package Minesweeper.FactoryDifficulties;

import Minesweeper.Components.GameBoard;
import Minesweeper.Components.GameClock;
import Minesweeper.Components.Menu.MinesweeperMenu;
import Minesweeper.Model.Difficulty;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 * @author Xrhstos Mporakhs TP4815
 */
public class MinesweeperBegginer extends JFrame implements IMinesweeper{
    /**
     * In Minesweeper Constructor is set the current difficulty of the game
     * and init cols/rows/mines by the difficulty
     * flags(right click) is set equal to mines count
     */
    public MinesweeperBegginer(){
        super("Minesweeper - Begginer");
        gameMenu.setDifficulty(new Difficulty("Begginer"));
        System.out.println("Minesweeper: "+gameMenu.getDifficulty());
        
        COLS = gameMenu.getDifficulty().getCols();
        ROWS = gameMenu.getDifficulty().getRows();
        MINES_COUNT = gameMenu.getDifficulty().getMines();
        remainFlags = MINES_COUNT;
        
        clock = new GameClock();
        GameBoard board = new GameBoard( this, ROWS, COLS, MINES_COUNT);
        initComponents( board);
    }
    
    /**
     * Init the main components of the frame
     * such as panels and labels
     * A menu bar for difficulty options/new game/exit is created in class MinesweeperMenu
     * The layout of the main Panel of the frame is GridLayout
     * and its called an instance of the class GameBoard to init it
     * @param board
     */
    @Override
    public void initComponents( GameBoard board){
        setPreferredSize( new Dimension(345,500));
        setJMenuBar( gameMenu.initMenu());        //Menu for Game Options
        
        allPanel = new JPanel(new BorderLayout());
        
        topPanel = new JPanel();
        topPanel.setMaximumSize(new Dimension(650,80));
        timeLabel = new JLabel();
        topPanel.add(timeLabel);
        
        gamePanel = new JPanel();    //init the mainPanel in the class GameBoard
        gamePanel = board.initLayout(); 
        
        bottomPanel = new JPanel();
        bottomPanel.setMaximumSize(new Dimension(650,80));
        flagsText = new JLabel("Flags: ");
        flagsLabel = new JLabel(String.valueOf(remainFlags));
        bottomPanel.add(flagsText);
        bottomPanel.add(flagsLabel);
        
        allPanel.add(topPanel, BorderLayout.NORTH);
        allPanel.add(gamePanel, BorderLayout.CENTER);
        allPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(allPanel);
        
        this.setLocation(300,250);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * This method handles the visibility of each difficulty's Frame
     * from another classes, if another difficulty is selected
     * @param state true false
     */
    @Override
    public void setVisible(Boolean state) {
        if(state){
            setVisible(true);
        }else{
            setVisible(false);
        }
    }
    
    /**
     * Get remainFlags panel to reset its value from ButtonListeners class
     * @return JLabel
     */
    @Override
    public JLabel getRemain(){
        return flagsLabel;
    }
    
    /**
     * Get current Difficulty of the game
     * @return Difficulty
     */
    @Override
    public Difficulty getDifficulty(){
        return gameMenu.getDifficulty();
    }
    
    @Override
    public JPanel getTime(){
        try {
            clock.getTimeLabel();
        } catch (InterruptedException ex) {
            Logger.getLogger(MinesweeperIntermediate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return topPanel;
    }
    
    private final MinesweeperMenu gameMenu = new MinesweeperMenu(this);
    private GameClock clock;
    
    private final int ROWS, COLS;
    private final int MINES_COUNT;
    private int remainFlags;
    
    private JPanel allPanel, topPanel, gamePanel, bottomPanel;
    private JLabel timeLabel, flagsLabel, flagsText;
}