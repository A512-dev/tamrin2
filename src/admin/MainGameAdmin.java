package admin;

import data.Database;
import graphics.GamePanel;
import logic.GameEngineModel;
import model.ObstacleModel;
import model.PlayerModel;
import model.PolygonCoreModel;

public class MainGameAdmin {
    //private final GraphicsAdmin graphicsAdmin;
    private final GameEngineModel gameEngineModel;
    private final GameLoopHandler gameLoopHandler;
    private PlayerModel playerModel;
    private PolygonCoreModel polygonCoreModel;
    private GamePanel gamePanel;




    public MainGameAdmin() {
        this.playerModel = new PlayerModel(30);
        this.polygonCoreModel = new PolygonCoreModel();
        //this.graphicsAdmin = new GraphicsAdmin(this);
        this.gameEngineModel = new GameEngineModel(playerModel, polygonCoreModel);
        this.gamePanel = new GamePanel(gameEngineModel);
        this.gameLoopHandler = new GameLoopHandler(gameEngineModel, gamePanel);
        gameLoopHandler.start();
    }

    public void start() {

    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public GameEngineModel getGameEngineModel() {
        return gameEngineModel;
    }
}
