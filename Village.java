import java.awt.*;
import java.util.Random;
import java.util.List;

public class Village {
    private static final int IDEAL_TEMP_MIN   = 60;  // °F
    private static final int IDEAL_TEMP_MAX   = 80;
    private static final int IDEAL_HUMIDITY   = 65;  // %
    private static final boolean IDEAL_RAIN   = true;

    private int appreciation = 50;
    private int population   = 10;
    private boolean helped   = false;
    private Region region;                       // current tile

    private static final Random RNG = new Random();

    public Village(Region start){ this.region = start; }
    public void evaluateWeather(SeasonManager sm){
        int  t    = sm.getTemperature();
        int  h    = sm.getHumidity();
        int  w    = sm.getWindSpeed();
        boolean r = sm.isPrecipitation();
        helped = false;

        /* perfect zone */
        if(t>=IDEAL_TEMP_MIN && t<=IDEAL_TEMP_MAX &&
                h>=IDEAL_HUMIDITY && r==IDEAL_RAIN) {
            appreciation += 8;
            helped = true;
        }
        /* good but not perfect */
        else if(r && h>60 && t<85){
            appreciation += 5;
            helped = true;
        }
        /* bad weather */
        else if(t>90 || h<30 || w>25){
            appreciation -= 10;
            population   -= 1;
        }
        /* neutral */
        else {
            appreciation += 1;
        }

        appreciation = Math.max(0, Math.min(100, appreciation));
        population   = Math.max(0, population);
    }
    public void maybeMigrate(List<Region> regions){
        Region best = regions.stream()
                .max(java.util.Comparator.comparingInt(Region::getNatureLevel))
                .orElse(region);
        region = best;
    }
    public void render(Graphics g){
        if(!isAlive()) return;
        int jitter=6;
        int cx = region.centerX()+RNG.nextInt(jitter)-jitter/2;
        int cy = region.centerY()+RNG.nextInt(jitter)-jitter/2;
        g.setColor(Color.YELLOW);
        g.fillOval(cx-3,cy-3,6,6);
    }
    public boolean isAlive(){ return population>0; }
    public int getAppreciation(){ return appreciation; }
    public Region getRegion(){ return region; }
}
