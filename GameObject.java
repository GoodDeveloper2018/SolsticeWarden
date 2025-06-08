import java.awt.*;

public abstract class GameObject {
    protected int x, y, width, height;

    protected GameObject(int x, int y, int width, int height) {
        this.x      = x;
        this.y      = y;
        this.width  = width;
        this.height = height;
    }
    public abstract void render(Graphics g);
    public abstract void update();
}
