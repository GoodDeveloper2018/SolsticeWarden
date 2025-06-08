import java.awt.*;

public class Region extends GameObject {
    private final String name;
    private int natureLevel;  // 0–100
    private int civLevel;     // 0–100

    public Region(int x, int y, int w, int h, String name) {
        super(x, y, w, h);
        this.name        = name;
        this.natureLevel = 50;
        this.civLevel    = 20;
    }

    /* helpers for villagers */
    public int getNatureLevel()    { return natureLevel; }
    public int getCivLevel()       { return civLevel;    }
    public int centerX()           { return x + width/2; }
    public int centerY()           { return y + height/2;}

    /* weather → environment */
    public void applyWeather(SeasonManager sm) {
        int t  = sm.getTemperature();
        int h  = sm.getHumidity();
        int w  = sm.getWindSpeed();
        boolean rain = sm.isPrecipitation();

        if (rain && t>50 && t<80) natureLevel += 10;
        else if (t>85)            natureLevel -= 5;

        civLevel += (w>20) ? -5 : 2;

        natureLevel = Math.max(0, Math.min(100,natureLevel));
        civLevel    = Math.max(0, Math.min(100,civLevel));
    }

    @Override public void render(Graphics g) {
        int green = Math.min(255, natureLevel*2);
        g.setColor(new Color(0,green,0));
        g.fillRect(x,y,width,height);

        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);
        g.drawString("N:"+natureLevel, x+4, y+14);
        g.drawString("C:"+civLevel   , x+4, y+28);
    }
    @Override public void update() {}

    public boolean containsPoint(int mx,int my){
        return mx>=x && mx<=x+width && my>=y && my<=y+height;
    }
}
