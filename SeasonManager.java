public class SeasonManager {
    private Season currentSeason;
    private int turn;
    private int temperature;
    private int humidity;
    private int wind;
    private boolean percipitation;
    private int percipitationLevel;

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

    public int getNatureGrowthModifier() {
        Season caseValue = currentSeason;
        return switch (currentSeason) {
            case SPRING -> 2;
            case AUTUMN -> 1;
            case SUMMER, WINTER -> 0;

        };
    }

    public int getTurn() {
        return turn;
    }
}
