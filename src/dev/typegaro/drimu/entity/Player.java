package dev.typegaro.drimu.entity;

import java.awt.Graphics2D;

import dev.typegaro.drimu.core.GamePanel;
import dev.typegaro.drimu.core.InputHandler;
import dev.typegaro.drimu.graphics.SpriteManager;
import dev.typegaro.drimu.geometry.Vector2D;

enum PlayerState {
    IDLE_UP,
    WALKING,
}

public class Player extends Entity {
    private InputHandler input;
    private GamePanel gp;
    private SpriteManager<PlayerState> sm = new SpriteManager<>();


    public Player(GamePanel gp, InputHandler input) {
        this.gp = gp;
        this.input = input;
        setDefaultValues();
        sm.loadSprite(PlayerState.IDLE_UP, "player/IdleU");
        sm.setState(PlayerState.IDLE_UP);
    }

    @Override
    public void setDefaultValues() {
        speed = 4;
        position = new Vector2D(100, 100);
    }

    @Override
    public void update() {
        Vector2D vec = new Vector2D();
        if (input.up) {
            vec.y -= 1;
        }
        if (input.down) {
            vec.y += 1;
        }
        if (input.left) {
            vec.x -= 1;
        }
        if (input.right) {
            vec.x += 1;
        }
        sm.update();
        position.move(vec.scale(speed));
    }
    @Override
    public void drow(Graphics2D g2) {
        sm.drow(g2, position);
    }

    @Override
    public int getX() {
        return position.x;
    }
    @Override
    public int getY() {
        return position.y;
    }
}
