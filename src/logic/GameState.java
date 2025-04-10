package logic;

public class GameState {
    public static String currentPlayerName = "Player";
    static double globalRotation = 0;
    static boolean rightWard = true;
    public static double bestRecord;
    public static boolean isMusicEnabled = true;
    public static boolean isHistoryEnabled = true;
    public static double getGlobalRotation() {
        return globalRotation;
    }
    public static void rotateGlobal(double delta) {
        globalRotation += delta;
        globalRotation %= 360;
    }

    public static String getBestRecord() {
        return Double.toString(bestRecord);
    }
}
