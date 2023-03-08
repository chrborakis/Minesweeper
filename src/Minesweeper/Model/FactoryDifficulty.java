package Minesweeper.Model;

import Minesweeper.FactoryDifficulties.*;
import Minesweeper.FactoryDifficulties.IMinesweeper;

/**
 *
 * @author Xrhstos Mporakhs TP4815
 */
public class FactoryDifficulty{
    public IMinesweeper getInstance(String selection) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        switch(selection) {
            case "Begginer":
                return new MinesweeperBegginer();
            case "Intermediate":
                return new MinesweeperIntermediate();
            case "Expert":
                return new MinesweeperExpert();
        }
        return null;
    }
}


