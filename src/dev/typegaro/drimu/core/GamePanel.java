package dev.typegaro.drimu.core;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.lang.Runnable;
import java.lang.Thread;


public class GamePanel extends JPanel implements Runnable {
    int originalTileSize = 16;
    int tileScale = 3;
    int tileSize = originalTileSize * tileScale; 
    int screenCols = 16;
    int screenRows = 12;
    int screenWidth = tileSize * screenCols;
    int screenHeight = tileSize * screenRows;
    int FPS = 60;

    InputHandler input = new InputHandler();
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(java.awt.Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(input);
        this.setFocusable(true);
    }
    public void initGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if (input.up) {
            playerY -= playerSpeed;
        } 
        else if (input.down) {
            playerY += playerSpeed;
        }
        else if (input.left) {
            playerX -= playerSpeed;
        }
        else if (input.right) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(java.awt.Color.white);
        g2.fillRect(playerX, playerX, tileSize, tileSize);
        g2.dispose();
    }
}
