import java.awt.*;

public class Region extends GameObject {

    private String type;

    public Region(int x, int y, int width, int height, String type) {
        super(x, y, width, height);
        this.type = type;
    }

    @Override
    public void render(Graphics g) {
        switch (type) {
            case "Forest": g.setColor(Color.GREEN); break;
            case "Desert": g.setColor(Color.YELLOW); break;
            default: g.setColor(Color.GRAY); break;
        }

        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.drawString(type, x + 5, y + 15);
    }

    @Override
    public void update() {
        // Update logic here
    }
}
