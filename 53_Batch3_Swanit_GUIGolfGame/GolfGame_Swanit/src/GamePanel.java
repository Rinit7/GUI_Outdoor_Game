//Author:Swanit Rivankar
//RollNo:2453
//Title: Golf Game
//Start Date: 3rd September 2024
//Modified Date: 22nd September 2024
//Description: This is a Golf Game implemented in Java using Swing for graphical elements. The player controls a golf ball on a 2D course filled with walls and hurdles. The goal is to guide the ball into a red hole by clicking and dragging the mouse to hit the ball in a chosen direction and force. The game tracks the number of strokes (hits) taken to reach the hole, and the player wins when the ball successfully lands in the hole. The game includes buttons for starting, restarting, and exiting, and provides real-time feedback via a scoreboard that displays the current stroke count.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private GolfBall ball;
    private Hole hole;
    private ArrayList<Wall> walls;
    private Timer timer;
    private Scoreboard scoreboard;
    private boolean gameWon;
    private boolean gameStarted;
    private Point pullStart;

    private JButton startButton;
    private JButton restartButton;
    private JButton exitButton;

    public GamePanel() {
        ball = new GolfBall(100, 500);
        hole = new Hole(700, 500);
        scoreboard = new Scoreboard(ball);
        gameWon = false;
        gameStarted = false; // Initialize gameStarted as false

        walls = new ArrayList<>();
        walls.add(new Wall(0, 0, 800, 20));   // Top border
        walls.add(new Wall(0, 0, 20, 600));   // Left border
        walls.add(new Wall(800, 0, 20, 600)); // Right border
        walls.add(new Wall(0, 580, 800, 20)); // Bottom border
        walls.add(new Wall(200, 200, 400, 20)); // Horizontal wall
        walls.add(new Wall(600, 200, 20, 400)); // Vertical wall
        walls.add(new Wall(200, 200, 20, 400)); // Vertical wall leading up
        
        //Hurdles
        walls.add(new Wall(110, 300, 100, 20));
        walls.add(new Wall(300, 20, 220,50));
        walls.add(new Wall(300, 150, 220,50));

        timer = new Timer(1000 / 90, this);

        // Mouse listener for hitting the ball
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameStarted && !gameWon) {
                    pullStart = e.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (gameStarted && !gameWon && pullStart != null) {
                    ball.hit(pullStart.x, pullStart.y, e.getX(), e.getY());
                    pullStart = null;
                }
            }
        });

        // Start button
        startButton = new JButton("Start Game");
        startButton.addActionListener(e -> startGame());

        // Restart and exit buttons
        restartButton = new JButton("Restart");
        exitButton = new JButton("Exit");


        restartButton.setVisible(false);
        exitButton.setVisible(false);


        restartButton.addActionListener(e -> restartGame());
        exitButton.addActionListener(e -> System.exit(0));

 
        setLayout(null); 
        startButton.setBounds(350, 300, 130, 30);
        restartButton.setBounds(300, 350, 100, 30);
        exitButton.setBounds(420, 350, 100, 30);


        add(startButton);
        add(restartButton);
        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(0, 100, 0)); // Dark green
        g.fillRect(0, 0, getWidth(), getHeight());


        g.setColor(new Color(144, 238, 144)); // Light green
        g.fillRect(20, 20, 760, 200); 
        g.fillRect(20, 20, 200, 570);
        g.fillRect(600, 20, 200, 570);

        ball.draw(g);
        hole.draw(g);
        for (Wall wall : walls) {
            wall.draw(g);
        }
        scoreboard.draw(g);

        if (gameWon) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("You Win! Total Strokes: " + ball.getStrokes(), 230, 300);

       
            restartButton.setVisible(true);
            exitButton.setVisible(true);
        } else {
           
            restartButton.setVisible(false);
            exitButton.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStarted && !gameWon) {
            ball.move(walls);

            // Check if the ball is in the hole
            if (ball.isInHole(hole)) {
                gameWon = true;
                repaint(); 
                return;
            }

            repaint(); 
        }
    }

    // Method to start the game
    private void startGame() {
        gameStarted = true; 
        startButton.setVisible(false); 
        timer.start(); 
    }

    // Method to restart the game
    private void restartGame() {
        gameWon = false;
        ball.reset();
        repaint();
    }
}
