package graphics;

public interface SpriteManagerInterface<S extends Enum<S>> {
    void loadSprite(S state, String dirPath);
    void setState(S state);
    void setState(S state, int frame);
    void nextFrame();
    void previousFrame();
    void drow(graphics2D g2, Vector2D position);
    void update();
}
