package dev.typegaro.drimu.entity;

import java.awt.Graphics2D;

import dev.typegaro.drimu.core.GamePanel;
import dev.typegaro.drimu.core.InputHandler;
import dev.typegaro.drimu.graphics.SpriteManager;
import dev.typegaro.drimu.geometry.Vector2D;

enum PlayerState {
    IDLE_UP,
    RUN_UP,
    RUN_DOWN,
    RUN_RIGHT,
}

public class Player extends Entity {
    private InputHandler input;
    private GamePanel gp;
    private SpriteManager<PlayerState> sm;


    public Player(GamePanel gp, InputHandler input) {
        this.gp = gp;
        this.input = input;
        this.sm = new SpriteManager<PlayerState>(gp.tileSize);
        setDefaultValues();
        sm.loadSprite(PlayerState.IDLE_UP, "player/IdleU");
        sm.loadSprite(PlayerState.RUN_UP, "player/RunU");
        sm.loadSprite(PlayerState.RUN_DOWN, "player/RunD");
        sm.loadSprite(PlayerState.RUN_RIGHT, "player/RunR");
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
            sm.setState(PlayerState.RUN_UP);
        }
        else if (input.down) {
            vec.y += 1;
            sm.setState(PlayerState.RUN_DOWN);
        }
        else if (input.left) {
            vec.x -= 1;
            sm.setState(PlayerState.RUN_RIGHT);
        }
        else if (input.right) {
            vec.x += 1;
            sm.setState(PlayerState.RUN_RIGHT);
        }
        else {
            sm.setState(PlayerState.IDLE_UP);
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
