package dev.typegaro.drimu.graphics;

import java.awt.Graphics2D;
import dev.typegaro.drimu.geometry.Vector2D;

public interface SpriteManagerInterface<S extends Enum<S>> {
    void loadSprite(S state, String dirPath);
    void setState(S state);
    void setState(S state, int frame);
    void nextFrame();
    void previousFrame();
    void drow(Graphics2D g2, Vector2D position);
    void update();
}
