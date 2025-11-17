package dev.typegaro.drimu.core;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.lang.Runnable;
import java.lang.Thread;
import dev.typegaro.drimu.entity.Player;


public class GamePanel extends JPanel implements Runnable {
    int originalTileSize = 16;
    int tileScale = 3;
    public int tileSize = originalTileSize * tileScale; 
    int screenCols = 16;
    int screenRows = 12;
    int screenWidth = tileSize * screenCols;
    int screenHeight = tileSize * screenRows;
    int FPS = 60;

    InputHandler input = new InputHandler();
    Player player = new Player(this, input);


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
        final double drawInterval = 1_000_000_000.0 / FPS;
        double delta = 0d;
        long lastTime = System.nanoTime();
        long timer = 0L;
        int frameCounter = 0;

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            long elapsedTime = currentTime - lastTime;
            lastTime = currentTime;

            delta += elapsedTime / drawInterval;
            timer += elapsedTime;

            while (delta >= 1) {
                player.update();
                repaint();
                delta--;
                frameCounter++;
            }

            long sleepNanos = (long) ((1 - delta) * drawInterval);
            if (sleepNanos > 0) {
                try {
                    Thread.sleep(sleepNanos / 1_000_000L, (int) (sleepNanos % 1_000_000L));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            if (timer >= 1_000_000_000L) {
                System.out.println("FPS: " + frameCounter);
                frameCounter = 0;
                timer -= 1_000_000_000L;
            }
        }
    }

    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.drow(g2);
        g2.dispose();
    }
}
