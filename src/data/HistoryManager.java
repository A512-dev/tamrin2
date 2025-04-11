package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private static final String FILE_PATH = "history.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void saveRecord(GameRecord record) {
        List<GameRecord> history = loadHistory();
        history.add(record);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), history);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<GameRecord> loadHistory() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<List<GameRecord>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public static List<GameRecord> loadOddHistory() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            List<GameRecord> all = mapper.readValue(file, new TypeReference<List<GameRecord>>() {});

            List<GameRecord> oddIndexed = new ArrayList<>();
            for (int i = 1; i < all.size(); i += 2) {
                oddIndexed.add(all.get(i));
            }
            return oddIndexed;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public static GameRecord getBestRecordFromFile() {
        List<GameRecord> history = loadHistory(); // یا loadOddHistory() اگه بخوای فقط اون‌ها
        if (history.isEmpty()) return null;

        GameRecord best = history.get(0);
        for (GameRecord r : history) {
            if (r.getScore() > best.getScore()) {
                best = r;
            }
        }
        return best;
    }

}
