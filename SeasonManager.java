import java.util.ArrayList;
import java.util.Random;

public class SeasonManager {
    private Season currentSeason;
    private ArrayList<Season> Seasons;
    private int turn;
    private int temperature;
    private int humidity;
    private int windSpeed;
    private boolean precipitation;
    private int indicator;

    private Random rand = new Random();

    public SeasonManager() {
        currentSeason = Season.SPRING;
        turn = 1;
        simulateWeather();
    }

    public void nextTurn() {
        turn++;
        if (turn % 5 == 0) {
            switch (currentSeason) {
                case SPRING -> currentSeason = Season.SUMMER;
                case SUMMER -> currentSeason = Season.AUTUMN;
                case AUTUMN -> currentSeason = Season.WINTER;
                case WINTER -> currentSeason = Season.SPRING;
            }
        }
        simulateWeather();
    }
    /*
    public int getNatureGrowthModifier() {
        Season caseValue = currentSeason;
        return switch (currentSeason) {
            case SPRING -> setNatureGrowthModifier(Season.SPRING);
            case AUTUMN -> setNatureGrowthModifier(Season.AUTUMN);
            case SUMMER -> setNatureGrowthModifier(Season.SUMMER);
            case WINTER -> setNatureGrowthModifier(Season.WINTER);
        };
    }
    */
    private void simulateWeather() {
        switch (currentSeason) {
            case SPRING -> {
                temperature = rand.nextInt(20) + 50;
                humidity = rand.nextInt(30) + 50;
                windSpeed = rand.nextInt(10) + 5;
                precipitation = rand.nextDouble() < 0.6;
            }
            case SUMMER -> {
                temperature = rand.nextInt(20) + 75;
                humidity = rand.nextInt(30) + 40;
                windSpeed = rand.nextInt(15) + 10;
                precipitation = rand.nextDouble() < 0.3;
            }
            case AUTUMN -> {
                temperature = rand.nextInt(15) + 45;
                humidity = rand.nextInt(40) + 40;
                windSpeed = rand.nextInt(10) + 5;
                precipitation = rand.nextDouble() < 0.5;
            }
            case WINTER -> {
                temperature = rand.nextInt(15) + 20;
                humidity = rand.nextInt(50) + 30;
                windSpeed = rand.nextInt(20) + 10;
                precipitation = rand.nextDouble() < 0.7;
            }
        }
    }
    /*
    public int setNatureGrowthModifier(int indicator) {
        Season s = new Season;
        ArrayList<Season> Seasons =
    }
     */

    public void forcePrecipitation(boolean status) {
        precipitation = status;
    }

    public void increaseTemperature() {
        temperature += 10;
    }

    public void increaseWind() {
        windSpeed += 10;
    }

    public Season getCurrentSeason() { return currentSeason; }
    public int getTurn() { return turn; }

    public int getTemperature() { return temperature; }
    public int getHumidity() { return humidity; }
    public int getWindSpeed() { return windSpeed; }
    public boolean isPrecipitation() { return precipitation; }
}
