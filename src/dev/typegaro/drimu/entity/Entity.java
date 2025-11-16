package entity;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import geometry.Vector2D;

public abstract class Entity implements EntityInterface {
    protected int speed;
    protected int direction;
    protected Vector2D position;

    public abstract void setDefaultValues();
    public abstract void update();
    public abstract int getX();
    public abstract int getY();
    public abstract void drow(Graphics2D g2);

}

