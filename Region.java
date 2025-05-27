import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Region extends GameObject {
    private String type;
    private int natureLevel;
    private int civLevel;
    private int villagerAppreciation;

    public Region(int x, int y, int width, int height, String type) {
        super(x, y, width, height);
        this.type = type;
        this.natureLevel = 50;
        this.civLevel = 20;
    }

    public void villagerInteraction(int civLevel, int natureLevel) {
        //DETERMINANT FACTOR OF FREQUENCY TO CONTROL WEATHER (ACTIONS) ALSO NEED TO CREATE PHYSICAL BAR IN GUI LIKE PLAYER HEALTH BUT FOR VILLAGER HAPPINESS
        String[] villagerSubConcentration = {"PERFECT", "GOOD", "DECENT", "BAD", "TERRIBLE"};
        Map<Region, String> villagerInteractions = new HashMap<>();
        villagerInteractions.put(Season.SUMMER, "Thank you my Lord and Savior");
        villagerInteractions.put(Season.AUTUMN, "Growing season, Thanks IG");
        villagerInteractions.put(Season.SPRING, "SUCH A PROSPEROUS ERA I LOVE YOU");
        villagerInteractions.put(Season.WINTER, "AH HELL  TS TRASH");
        int cohesiveWeather = civLevel.applyWeather();
        this.type = civLevel.getInstance(cohesiveWeather);
        if (villagerInteractions.get()) {
            villagerAppreciation += civLevel;
        }
        else {
            villagerAppreciation =- civLevel;
        }
    }

    public void applyWeather(SeasonManager sm) {
        int temp = sm.getTemperature();
        int humidity = sm.getHumidity();
        int wind = sm.getWindSpeed();
        boolean rain = sm.isPrecipitation();

        // Nature grows if temp is moderate and it's raining
        if (rain && temp > 50 && temp < 80) {
            natureLevel += 10;
        } else if (temp > 85) {
            natureLevel -= 5; // heat damage
        }

        // Wind hurts civilization
        if (wind > 20) {
            civLevel -= 5;
        } else {
            civLevel += 2; // natural growth
        }

        natureLevel = Math.max(0, Math.min(100, natureLevel));
        civLevel = Math.max(0, Math.min(100, civLevel));
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
