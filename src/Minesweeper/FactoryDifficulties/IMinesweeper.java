package Minesweeper.FactoryDifficulties;
import Minesweeper.Components.GameBoard;
import Minesweeper.Model.Difficulty;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Xrhstos Mporakhs TP4815
 */
public interface IMinesweeper{
    public void initComponents( GameBoard board);
    public void setVisible(Boolean state);
    public JLabel getRemain();
    public Difficulty getDifficulty();
    public JPanel getTime();
}
