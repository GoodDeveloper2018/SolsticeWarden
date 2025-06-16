public class SaveData {
    public int  turn;
    public Season currentSeason;
    public int  temperature, humidity, windSpeed;
    public java.util.List<VillageSnapshot> villages;

    /** nested helper class */
    public static class VillageSnapshot {
        public int app;
        public int pop;
        public int regionIndex;     // index in regions list
    }
}
