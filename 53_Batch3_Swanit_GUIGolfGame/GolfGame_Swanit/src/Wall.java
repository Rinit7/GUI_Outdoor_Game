//Author:Swanit Rivankar
//RollNo:2453
//Title: Golf Game
//Start Date: 3rd September 2024
//Modified Date: 22nd September 2024
//Description: This is a Golf Game implemented in Java using Swing for graphical elements. The player controls a golf ball on a 2D course filled with walls and hurdles. The goal is to guide the ball into a red hole by clicking and dragging the mouse to hit the ball in a chosen direction and force. The game tracks the number of strokes (hits) taken to reach the hole, and the player wins when the ball successfully lands in the hole. The game includes buttons for starting, restarting, and exiting, and provides real-time feedback via a scoreboard that displays the current stroke count.
import java.awt.*;

public class Wall {
    public int x, y, width, height;

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
    	g.setColor(new Color(120, 67, 33));
        g.fillRect(x, y, width, height);
    }
}
