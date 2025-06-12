import java.awt.*;
import java.util.Random;

public class Village {
    private int appreciation = 50;
    private int population   = 10;
    private boolean helped   = false;
    private Region region;                  // current location

    private static final Random RNG = new Random();

    public Village(Region start){ this.region = start; }

    /* ----- environment reactions ----- */
    public void evaluateWeather(SeasonManager sm){
        int t = sm.getTemperature();
        int h = sm.getHumidity();
        boolean rain = sm.isPrecipitation();
        helped = false;

        if(rain && h>60 && t<80){ appreciation+=5; helped=true; }
        else if(t>90 || h<30){     appreciation-=10; population--; }
        else appreciation++;

        appreciation = Math.max(0,Math.min(100,appreciation));
        population   = Math.max(0,population);
    }
    public void maybeMigrate(java.util.List<Region> regions){
        Region best = regions.stream()
                .max(java.util.Comparator.comparingInt(Region::getNatureLevel))
                .orElse(region);
        region = best;
    }
    public void render(Graphics g){
        if(!isAlive()) return;
        int jitter = 6;
        int cx = region.centerX() + RNG.nextInt(jitter)-jitter/2;
        int cy = region.centerY() + RNG.nextInt(jitter)-jitter/2;
        g.setColor(Color.YELLOW);
        g.fillOval(cx-3, cy-3, 6,6);
    }

    /* accessors */
    public boolean isAlive(){ return population>0; }
    public int  getAppreciation(){ return appreciation; }
}
