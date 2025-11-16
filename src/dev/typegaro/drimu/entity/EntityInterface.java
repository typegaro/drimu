package dev.typegaro.drimu.entity;
import java.awt.Graphics2D;

public interface EntityInterface {
    public void setDefaultValues();
    public void update();
    public void drow(Graphics2D g2);
}
