import java.util.Random;

public class SeasonManager {
    private Season currentSeason = Season.SPRING;
    private int turn             = 1;

    private int temperature;
    private int humidity;
    private int windSpeed;
    private boolean precipitation;
    private boolean m;

    private final Random rnd = new Random();

    public void nextTurn() {
        turn++;
        if (turn % 5 == 0) {              // change season every 5 turns
            currentSeason = switch (currentSeason) {
                case SPRING -> Season.SUMMER;
                case SUMMER -> Season.AUTUMN;
                case AUTUMN -> Season.WINTER;
                case WINTER -> Season.SPRING;
            };
        }
        simulateWeather();
    }

    /* initial call */
    public SeasonManager() { simulateWeather(); }

    public void loadFromSave(SaveData d){
        this.currentSeason = d.currentSeason;
        this.turn          = d.turn;
        this.temperature   = d.temperature;
        this.humidity      = d.humidity;
        this.windSpeed     = d.windSpeed;
        this.precipitation = d.windSpeed>0;  // quick derive
    }
    private boolean superUsed = false;
    public boolean invokeSuperWeather(int temp,int hum,int wind,boolean rain){
        if(superUsed) return false;
        temperature = temp; humidity = hum; windSpeed = wind; precipitation = rain;
        superUsed = true;
        return true;
    }

    private void simulateWeather() {
        switch (currentSeason) {
            case SPRING -> { temperature=rnd.nextInt(20)+50; humidity=rnd.nextInt(30)+50;
                windSpeed=rnd.nextInt(10)+5;    precipitation=rnd.nextDouble()<0.6; }
            case SUMMER -> { temperature=rnd.nextInt(20)+75; humidity=rnd.nextInt(30)+40;
                windSpeed=rnd.nextInt(15)+10;   precipitation=rnd.nextDouble()<0.3; }
            case AUTUMN -> { temperature=rnd.nextInt(15)+45; humidity=rnd.nextInt(40)+40;
                windSpeed=rnd.nextInt(10)+5;    precipitation=rnd.nextDouble()<0.5; }
            case WINTER -> { temperature=rnd.nextInt(15)+20; humidity=rnd.nextInt(50)+30;
                windSpeed=rnd.nextInt(20)+10;   precipitation=rnd.nextDouble()<0.7; }
        }
    }

    /* manual controls from GUI */
    public void forcePrecipitation(boolean r){ precipitation = r; }
    public void increaseTemperature()        { temperature  += 10; }
    public void increaseWind()               { windSpeed    += 10; }
    public boolean isSuperUsed(){ return superUsed; }

    /* getters */
    public Season getCurrentSeason(){ return currentSeason; }
    public int  getTurn()          { return turn;          }
    public int  getTemperature()   { return temperature;   }
    public int  getHumidity()      { return humidity;      }
    public int  getWindSpeed()     { return windSpeed;     }
    public boolean isPrecipitation(){ return precipitation; }
}
