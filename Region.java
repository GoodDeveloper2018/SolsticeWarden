import java.awt.*;

public class Region extends GameObject {

    private String type;
    private int natureLevel;
    private int civLevel;

    public Region(int x, int y, int width, int height, String type) {
        super(x, y, width, height);
        this.type = type;
        this.natureLevel = 50;
        this.civLevel = 20;
    }

    public void growForest(SeasonManager sm) {
        int base = 10;
        int modifier = sm.getNatureGrowthModifier();
        int resistance = civLevel;
        int growth = (base * modifier) - (resistance / 5);

        natureLevel += growth;
        if (natureLevel > 100) natureLevel = 100;
        System.out.println("Grew forest in " + type + " for +" + growth + " nature (now: " + natureLevel + ")");
    }

    @Override
    public void render(Graphics g) {
        int green = Math.min(255, natureLevel * 2);
        g.setColor(new Color(0, green, 0));
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.drawString("N:" + natureLevel, x + 5, y + 15);
    }

    @Override
    public void update() {
        //animation logic
    }

    public boolean containsPoint(int mx, int my) {
        return (mx >= x && mx <= x + width && my >= y && my <= y + height);
    }
}
