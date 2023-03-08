package Minesweeper.Components.Menu;

import Minesweeper.FactoryDifficulties.*;
import Minesweeper.FactoryDifficulties.IMinesweeper;
import Minesweeper.Model.Difficulty;
import Minesweeper.Model.FactoryDifficulty;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Action Listeners for JMenuItems
 * @author Xrhstos Mporakhs TP4815
 */
public class MinesweeperMenuListeners{
    private final IMinesweeper game;
    private final MinesweeperMenu menu;

    /**
     * Pass IMinesweeper and MinesweeperMenu to access its getter
     * @param game
     * @param menu
     */
    public MinesweeperMenuListeners( IMinesweeper game, MinesweeperMenu menu){
        this.game = game;
        this.menu = menu; 
    }

    /**
     * Caught any action in JMenuItems to switch difficulty
     * setVisible(false) the previous one 
     * and prevent reopen the same, if its already displayed
     * @param game
     * @param begginer
     * @param inter
     * @param expert
     */
    public void selectDifficulty( IMinesweeper game, JMenuItem begginer, JMenuItem inter, JMenuItem expert){
        begginer.addActionListener ((ActionEvent e) -> {
            if(!menu.getDifficulty().getDifficulty().equals("Begginer")){
                try {
                    begginer.setOpaque(true);
                    inter.setOpaque(false);
                    expert.setOpaque(false);

                    game.setVisible(false);
                    menu.setDifficulty(new Difficulty("Begginer"));
                    new FactoryDifficulty().getInstance("Begginer");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(MinesweeperMenuListeners.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Begginer is already selected!", "Error Selecting Difficulty", 0);
            }   
        });
        inter.addActionListener ((ActionEvent e) -> {
            if(!menu.getDifficulty().getDifficulty().equals("Intermediate")){
                try {
                    begginer.setOpaque(false);
                    inter.setOpaque(true);
                    expert.setOpaque(false);
                    
                    game.setVisible(false);
                    menu.setDifficulty(new Difficulty("Intermediate"));
                    new FactoryDifficulty().getInstance("Intermediate");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(MinesweeperMenuListeners.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Intermediate is already selected!", "Error Selecting Difficulty", 0);
            }
        });
        expert.addActionListener ((ActionEvent e) -> {
            if(!menu.getDifficulty().getDifficulty().equals("Expert")){
                try {
                    begginer.setOpaque(false);
                    inter.setOpaque(false);
                    expert.setOpaque(true);

                    game.setVisible(false);
                    menu.setDifficulty(new Difficulty("Expert"));
                    new FactoryDifficulty().getInstance("Expert");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(MinesweeperMenuListeners.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Expert is already selected!", "Error Selecting Difficulty", 0);
            }
        });
    }

    /**
     * Catch action in JMenuItem new Game and exit
     * if new Game selected, then start new game with the same difficulty
     * or exit program
     * @param game
     * @param newGame
     * @param exit
     */
    public void otherAction( IMinesweeper game, JMenuItem newGame, JMenuItem credits, JMenuItem exit) {
        newGame.addActionListener((ActionEvent e) -> {
            System.out.println("New Game");
            //h prwti methodos getDifficulty kanei return to antikeimeno Difficulty
            //kai h deuteri methodos kanei return to string onoma tou antikeimenou
            String difficulty = menu.getDifficulty().getDifficulty();
            switch (difficulty) {
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
        });
        
        credits.addActionListener((ActionEvent evt) -> {
            JOptionPane.showMessageDialog(null,"Project 2021-22 Minesweeper\nΧρήστος Μποράκης ΤΠ4815","Credits",JOptionPane.INFORMATION_MESSAGE);
        });
        
        exit.addActionListener((ActionEvent evt) -> {
            String[] options = new String[] {"No", "Exit"};
            int option =  JOptionPane.showOptionDialog(
                    null,
                    "You want to exit Minesweeper?",
                    "Exit",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]
            );
            switch(option){
                case 0:
                    break;
                case 1: System.exit(0);
                break;
            }
        });
    }
}