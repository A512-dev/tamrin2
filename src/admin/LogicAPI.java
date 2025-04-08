package admin;

public interface LogicAPI {
    void rotate();

    void moveLeft();

    void moveRight();

    void drop();

    void undo();

    void startPause();

    GameStateAPI getGameState();
}
