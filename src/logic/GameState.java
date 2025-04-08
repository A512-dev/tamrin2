package logic;

public class GameState {
    static double globalRotation = 0;
    static boolean rightWard = true;
    public static double getGlobalRotation() {
        return globalRotation;
    }
    public static void rotateGlobal(double delta) {
        globalRotation += delta;
        globalRotation %= 360;
    }

}
