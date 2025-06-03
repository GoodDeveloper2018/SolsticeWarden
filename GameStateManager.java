import java.util.ArrayList;
import java.util.Comparator;

public class GameStateManager {
    private ArrayList<Village> villagers;

    public GameStateManager(int numVillagers) {
        villagers = new ArrayList<>();
        for (int i = 0; i < numVillagers; i++) {
            villagers.add(new Village());
        }
    }
    /*
    public void updateVillagers(ArrayList<Region> regions) {
        for (Village v : villagers) {
            if (v.isAlive()) {
                Region best = findBestRegion(regions);
                v.migrateTo(best);
            }
        }
    }

    private Region findBestRegion(ArrayList<Region> regions) {
        return regions.stream()
                .max(Comparator.comparingInt(r -> r.getNatureLevel() + r.getCivLevel()))
                .orElse(regions.get(0));
    }


    public void migrateTo() {

    }

    public int getLivingVillagerCount() {
        return (int) villagers.stream().filter(Villager::isAlive).count();
    }

    public int getAverageHappiness() {
        return (int) villagers.stream()
                .filter(Village::isAlive)
                .mapToInt(Village::getHappiness)
                .average().orElse(0);
    }

    public boolean isGameOver() {
        return getLivingVillagerCount() == 0;
    }
     */
    public ArrayList<Village> getVillagers() {
        return villagers;
    }
}
