package logic;

import model.ObstacleModel;
import model.PlayerModel;
import model.PolygonCoreModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameEngineModel {
    private boolean paused = false;
    private long pausedTime = 0;
    private long totalPausedDuration = 0;

    public void setPaused(boolean paused) {
        this.paused = paused;
        if (paused) {
            pausedTime = System.currentTimeMillis();
        } else {
            totalPausedDuration += System.currentTimeMillis() - pausedTime;
        }
    }


    private PlayerModel player;
    private PolygonCoreModel polygon;
    private List<ObstacleModel> obstacles;
    private double gameSpeed = 1.0;
    public static int totalSectors = 6;
    public static double sectorAngle = 2 * Math.PI / totalSectors;
    private int spawnCounter = 0;
    private int spawnInterval = 30;
    private int minOpenSectors = 3;
    private int frameCount = 0;
    private long startTime = System.currentTimeMillis();
    private double bestTime = 0;
    public long getStartTime() {
        return startTime + totalPausedDuration;
    }

    public double getBestTime() {
        return bestTime;
    }








    public GameEngineModel(PlayerModel player, PolygonCoreModel polygon) {
        this.player = player;
        this.polygon = polygon;
        this.obstacles = new ArrayList<>();
    }

    public void updateGame() {
        if (paused) return;
        // progressively increase difficulty
        if (spawnInterval > 20) spawnInterval--;
        if (minOpenSectors > 1 && frameCount % 600 == 0) minOpenSectors--;
        if (GameState.rightWard) {
            GameState.globalRotation += 0.005;
        }
        else {
            GameState.globalRotation -= 0.005;
        }

        // Update obstacles
        Iterator<ObstacleModel> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            ObstacleModel ob = iterator.next();
            ob.update();
            if (ob.isOffScreen()) {
                iterator.remove();
            }
        }
        player.rotate();

        // Optionally: increase speed over time
        gameSpeed += 0.001;

        // تولید مانع جدید هر 30 فریم (تقریباً هر نیم‌ثانیه با 60 FPS)
        //now every 120 frames
        // تولید مانع جدید هر 120 فریم (تقریباً هر نیم‌ثانیه با 60 FPS)
        spawnCounter++;
        frameCount++;
        //double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;
        double elapsed = (System.currentTimeMillis() - startTime - totalPausedDuration) / 1000.0;
        if (elapsed > bestTime) {
            bestTime = elapsed;
        }
        if (spawnCounter >= spawnInterval * 4) {
            spawnObstacle();
            spawnCounter = 0;
        }
        if (GameState.globalRotation>1) {
            GameState.rightWard = false;
        }
        else if (GameState.globalRotation<-4) {
            GameState.rightWard = true;
        }
    }

//    public void spawnObstacle() {
//        int sector = (int)(Math.random() * totalSectors);
//        ObstacleModel newObstacle = new ObstacleModel(300, gameSpeed/2, sector);
//        obstacles.add(newObstacle);
//    }
    public void spawnObstacle() {
        double distance = 300 + Math.random() * 100;
        List<Integer> usedSectors = new ArrayList<>();
        int maxObstacles = totalSectors - 1;
        int numSectors = (int) (Math.random() * 4);

        while (usedSectors.size() != numSectors) {
            int sector = (int) (Math.random() * totalSectors);
            if (!usedSectors.contains(sector)) {
                usedSectors.add(sector);
                ObstacleModel newObstacle = new ObstacleModel(distance, gameSpeed / 2, sector);
                obstacles.add(newObstacle);
            }
        }
    }

    public boolean checkCollision() {
        int playerSector = player.getCurrentSector();
        for (ObstacleModel ob : obstacles) {
            if (ob.getSector() == playerSector && ob.getDistance()- ObstacleModel.thickness< PlayerModel.distanceFromCenter + PlayerModel.size) {
                System.out.println("Sectors: "+playerSector+"    "+ ob.getSector());
                System.out.println("angles: "+player.getAngle()+"    "+ ob.getAngle());
                System.out.println("Distance: "+  (PlayerModel.distanceFromCenter + PlayerModel.size) +"     "+ (ob.getDistance()- ObstacleModel.thickness));
                return true; // collision detected
            }
        }
        return false;
    }

    public List<ObstacleModel> getObstacles() {
        return obstacles;
    }

    public PlayerModel getPlayer() {
        return player;
    }
    public PolygonCoreModel getPolygonCore() {
        return polygon;
    }


    public int getTotalSectors() {
        return totalSectors;
    }

    public void setTotalSectors(int totalSectors) {
        this.totalSectors = totalSectors;
    }
}
/*
public class GameEngineLogic implements LogicAPI {
    private final BoardHandler boardHandler;
    private final Dropper dropper;
    private boolean isPaused = true;
    private final GameState gameState;

    @Override
    public void rotate() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void drop() {

    }

    @Override
    public void undo() {

    }

    @Override
    public void startPause() {
        isPaused = !isPaused;
    }

    @Override
    public GameStateAPI getGameState() {
        return gameState;
    }
    public void update() {
        if (!isPaused)
            dropper.update();
    }
}
*/