import java.io.InputStream;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GameStateManager {
    private final ArrayList<Village> villages;
    private final Random rng = new Random();

    public GameStateManager(List<Region> regions, int num) {
        villages = new ArrayList<>();
        /* pick a central region (avoid edges) */
        int grid = (int) Math.sqrt(regions.size());
        int row = 1 + rng.nextInt(grid - 2);
        int col = 1 + rng.nextInt(grid - 2);
        Region start = regions.get(row * grid + col);
        for (int i = 0; i < num; i++) villages.add(new Village(start));
    }

    public void nextTurn(SeasonManager sm, List<Region> regions) {
        for (Village v : villages) {
            if (!v.isAlive()) continue;
            v.evaluateWeather(sm);
            v.maybeMigrate(regions);
        }
    }

    public int living() {
        return (int) villages.stream().filter(Village::isAlive).count();
    }

    public int avgApp() {
        return (int) villages.stream().filter(Village::isAlive)
                .mapToInt(Village::getAppreciation).average().orElse(0);
    }

    public List<Village> getVillages() {
        return villages;
    }

    public boolean gameOver() {
        return living() == 0;
    }

    public void save(SaveLoadManager slm, SeasonManager sm, List<Region> regions) {
        slm.save(this, sm, regions);
    }

    public void load(SaveLoadManager slm, SeasonManager sm, List<Region> regions) {
        SaveData data = slm.load();
        if (data == null) return;

        sm.loadFromSave(data);

        villages.clear();
        for (SaveData.VillageSnapshot vs : data.villages) {
            Region r = regions.get(vs.regionIndex);
            Village v = new Village(r);
            while (v.getAppreciation() < vs.app) v.evaluateWeather(sm);
            villages.add(v);
        }
    }
}