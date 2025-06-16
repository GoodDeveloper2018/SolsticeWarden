import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaveLoadManager {
    private static final String FILE = "save.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public void save(GameStateManager gsm, SeasonManager sm, List<Region> regions) {
        try {
            SaveData data = new SaveData();
            data.turn          = sm.getTurn();
            data.currentSeason = sm.getCurrentSeason();
            data.temperature   = sm.getTemperature();
            data.humidity      = sm.getHumidity();
            data.windSpeed     = sm.getWindSpeed();

            data.villages = new ArrayList<>();
            for (Village v : gsm.getVillages()) {
                SaveData.VillageSnapshot vs = new SaveData.VillageSnapshot();
                vs.app  = v.getAppreciation();
                vs.pop  = v.isAlive() ? 1 : 0;
                vs.regionIndex = regions.indexOf(v.getRegion());
                data.villages.add(vs);
            }
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE), data);
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public SaveData load(){
        try { return mapper.readValue(new File(FILE), SaveData.class); }
        catch (Exception e){ return null; }
    }
}
