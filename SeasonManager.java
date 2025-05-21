public class SeasonManager {
    private Season currentSeason;
    private int turn;
    private int temperature;
    private int relativeHumidity;
    private int windSpeed;
    private boolean percipitation;
    private int percipitationIntensity;

    public SeasonManager() {
        currentSeason = Season.SPRING;
        turn = 1;
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
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public int setNatureGrowthModifier(Season season) {
        int growthModifier = 0;
        if (getCurrentSeason() == Season.SPRING || getCurrentSeason() == Season.AUTUMN) {
            temperature = (int)(Math.random() * 20) + 30;
        }
        else if (getCurrentSeason() == Season.SUMMER) {
            temperature = (int)(Math.random() * 20) + 65;
        }
        else {
            temperature = (int)(Math.random() * 20) + 20;
        }
        if(50 < temperature && temperature >= 70) return growthModifier+=2;
        return growthModifier+=1;
    }

    public int getNatureGrowthModifier() {
        Season caseValue = currentSeason;
        return switch (currentSeason) {
            case SPRING -> setNatureGrowthModifier(Season.SPRING);
            case AUTUMN -> setNatureGrowthModifier(Season.AUTUMN);
            case SUMMER -> setNatureGrowthModifier(Season.SUMMER);
            case WINTER -> setNatureGrowthModifier(Season.WINTER);
        };
    }

    public int getTurn() {
        return turn;
    }
}
