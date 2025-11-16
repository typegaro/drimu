package core;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.lang.Runnable;
import java.lang.Thread;
import entity.Player;


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
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                player.update();
                repaint();
                delta--;
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
