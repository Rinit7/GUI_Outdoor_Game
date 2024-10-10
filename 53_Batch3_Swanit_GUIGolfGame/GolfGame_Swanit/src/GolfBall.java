//Author:Swanit Rivankar
//RollNo:2453
//Title: Golf Game
//Start Date: 3rd September 2024
//Modified Date: 22nd September 2024
//Description: This is a Golf Game implemented in Java using Swing for graphical elements. The player controls a golf ball on a 2D course filled with walls and hurdles. The goal is to guide the ball into a red hole by clicking and dragging the mouse to hit the ball in a chosen direction and force. The game tracks the number of strokes (hits) taken to reach the hole, and the player wins when the ball successfully lands in the hole. The game includes buttons for starting, restarting, and exiting, and provides real-time feedback via a scoreboard that displays the current stroke count.
import java.awt.*;
import java.util.ArrayList;

public class GolfBall {
    public int x, y;
    private double velocityX, velocityY;
    private double friction;
    private int strokes; // Strokes counter

    public GolfBall(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
        this.friction = 0.96; // Friction factor to reduce speed
        this.strokes = 0; 
    }

    public void hit(int startX, int startY, int targetX, int targetY) {
        // Calculate velocity based on drag distance
        int deltaX = targetX - startX;
        int deltaY = targetY - startY;
        velocityX = deltaX / 8.0;
        velocityY = deltaY / 8.0;
        strokes++;
    }

    public void move(ArrayList<Wall> walls) {
        velocityX *= friction;
        velocityY *= friction;

        int newX = x + (int) velocityX;
        int newY = y + (int) velocityY;

        boolean collided = false;

        for (Wall wall : walls) {
            if (newX < wall.x + wall.width && newX + 20 > wall.x && newY < wall.y + wall.height && newY + 20 > wall.y) {
                collided = true;
                
                if (x + 20 <= wall.x || x >= wall.x + wall.width) {
                    velocityX = -velocityX; // Bounce horizontally
                } else {
                    velocityY = -velocityY; // Bounce vertically
                }
            }
        }

        if (!collided) {
            x = newX;
            y = newY;
        }
    }

    public boolean isInHole(Hole hole) {
        return (x >= hole.x && x <= hole.x + 40 && y >= hole.y && y <= hole.y + 40);
    }

    public int getStrokes() {
        return strokes;
    }

    public void reset() {
    	x=100;
    	y=500;
        velocityX = 0;
        
        velocityY = 0;
        strokes = 0; 
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 20, 20);
    }
}
