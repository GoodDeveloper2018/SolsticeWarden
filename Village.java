public class Village {
    private int appreciation;
    private int population;
    private boolean helpedLastTurn;

    public Village() {
        this.appreciation = 50;
        this.population = 10;  // starting number of villagers
    }

    public void evaluateWeather(SeasonManager sm) {
        int temp = sm.getTemperature();
        int humidity = sm.getHumidity();
        boolean rain = sm.isPrecipitation();

        helpedLastTurn = false;

        if (rain && humidity > 60 && temp < 80) {
            appreciation += 5;
            helpedLastTurn = true;
        } else if (temp > 90 || humidity < 30) {
            appreciation -= 10;
            population -= 1;
        } else {
            appreciation += 1;
        }

        appreciation = Math.max(0, Math.min(100, appreciation));
        population = Math.max(0, population);
    }

    public boolean isAlive() {
        return population > 0;
    }

    public int getAppreciation() {
        return appreciation;
    }

    public int getPopulation() {
        return population;
    }

    public boolean wasHelped() {
        return helpedLastTurn;
    }
}
