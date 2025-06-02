public class Village {
    private int villageAppreciation;
    private int villagerSatisfaction;
    private boolean helped;
    private int concentration;
    public Village (int villageAppreciation) {
        this.villageAppreciation = villageAppreciation;
    }

    public int getVillageAppreciation() {
        return villageAppreciation;
    }

    public boolean isHelped() {
        return helped;
    }

    public int getConcentration() {
        return concentration;
    }

    public boolean getVillageAppreciation(SeasonManager sm) {
        boolean helped = false;
        int temp = sm.getTemperature();
        int humidity = sm.getHumidity();
        boolean rain = sm.isPrecipitation();

        if (rain && humidity > 60 && temp < 80) {
            villageAppreciation += 5;
            helped = true;
        } else if (temp > 90 || humidity < 30) {
            villageAppreciation -= 5;
        } else {
            villageAppreciation += 1;
        }

        villageAppreciation = Math.max(0, Math.min(100, villageAppreciation));
        return helped;
    }

    private void adjustSatisfaction(boolean positive) {
        if (positive) {
            villagerSatisfaction += 5;
        }
        else {
            villagerSatisfaction -= 5;
        }
        villagerSatisfaction = Math.max(0, Math.min(100, villagerSatisfaction));
    }
}
