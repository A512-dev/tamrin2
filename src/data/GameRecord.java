package data;

import java.time.LocalDateTime;

public class GameRecord {
    private String playerName;
    private double score;

    public void setTimeOfPlay(String timeOfPlay) {
        this.timeOfPlay = timeOfPlay;
    }

    public String getTimeOfPlay() {
        return timeOfPlay;
    }

    private String timeOfPlay;

    public GameRecord() {} // ← برای Jackson ضروریه
    public GameRecord(String playerName, double score, String timeOfPlay) {
        this.playerName = playerName;
        this.score = score;
        this.timeOfPlay = timeOfPlay;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getScore() {
        return score;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
