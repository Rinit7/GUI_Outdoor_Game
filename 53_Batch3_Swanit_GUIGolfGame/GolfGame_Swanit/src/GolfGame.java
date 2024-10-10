//Author:Swanit Rivankar
//RollNo:2453
//Title: Golf Game
//Start Date: 3rd September 2024
//Modified Date: 22nd September 2024
//Description: This is a Golf Game implemented in Java using Swing for graphical elements. The player controls a golf ball on a 2D course filled with walls and hurdles. The goal is to guide the ball into a red hole by clicking and dragging the mouse to hit the ball in a chosen direction and force. The game tracks the number of strokes (hits) taken to reach the hole, and the player wins when the ball successfully lands in the hole. The game includes buttons for starting, restarting, and exiting, and provides real-time feedback via a scoreboard that displays the current stroke count.
import javax.swing.*;
import java.awt.*;

public class GolfGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Golf Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            GamePanel gamePanel = new GamePanel();
            frame.add(gamePanel, BorderLayout.CENTER); 

            frame.setSize(835, 633);
            frame.setLocationRelativeTo(null); 
            frame.setVisible(true);
        });
    }
}
