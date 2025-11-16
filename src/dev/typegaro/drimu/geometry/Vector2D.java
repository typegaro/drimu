package geometry;

public class Vector2D {
    public int x;
    public int y;

    public Vector2D() {
        this(0, 0);
    }

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D sub(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D scale(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    public void move(Vector2D delta) {
        this.x += delta.x;
        this.y += delta.y;
    }
    public void zero() {
        this.x = 0;
        this.y = 0; 
    }
}
