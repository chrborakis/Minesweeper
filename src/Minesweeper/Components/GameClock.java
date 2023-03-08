package Minesweeper.Components;

import java.awt.Dimension;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Xrhstos Mporakhs TP4815
 */
public class GameClock {
    public GameClock(){
    }
    
    public JLabel getTimeLabel() throws InterruptedException{
        JLabel timeLabel = new JLabel();
        timeLabel.setText(String.valueOf(countSeconds++));
        TimeUnit.SECONDS.sleep(1);
   
//        timeLabel.setPreferredSize(new Dimension(100,40));
//        
//        Date startDate = new Date();
//        Date endDate = new Date();
//        int numSeconds = (int)((endDate.getTime() - startDate.getTime()) / 1000);
//        timeLabel.setText(String.valueOf(numSeconds));
//        
//        Timer timer = new Timer(2000, e -> timeLabel.setText(String.valueOf(numSeconds)));
//        timer.setRepeats(false);
//        timer.start();  
        
        return timeLabel;
    }
    
    private int countSeconds = 0;
}
