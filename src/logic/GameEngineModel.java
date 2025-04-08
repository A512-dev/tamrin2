package logic;

import model.ObstacleModel;
import model.PlayerModel;
import model.PolygonCoreModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameEngineModel {

    private PlayerModel player;
    private PolygonCoreModel polygon;
    private List<ObstacleModel> obstacles;
    private double gameSpeed = 1.0;
    public static int totalSectors = 6;
    public static double sectorAngle = 2 * Math.PI / totalSectors;
    private int spawnCounter = 0;






    public GameEngineModel(PlayerModel player, PolygonCoreModel polygon) {
        this.player = player;
        this.polygon = polygon;
        this.obstacles = new ArrayList<>();
    }

    public void updateGame() {
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
        spawnCounter++;
        if (spawnCounter >= 30) {
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

    public void spawnObstacle() {
        int sector = (int)(Math.random() * totalSectors);
        ObstacleModel newObstacle = new ObstacleModel(300, gameSpeed/2, sector);
        obstacles.add(newObstacle);
    }

    public boolean checkCollision() {
        int playerSector = player.getCurrentSector();
        for (ObstacleModel ob : obstacles) {
            if (ob.getSector() == playerSector && ob.getDistance()- ObstacleModel.thickness< PlayerModel.radius  + PlayerModel.size) {
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