package GameStuff;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Game {

    public static final int TILE_SIZE = 32;
    public static final int TILES_BY_WIDTH = 30;
    public static final int TILES_BY_HEIGHT = 20;
    public static final int GAME_WIDTH = TILE_SIZE * TILES_BY_WIDTH;
    public static final int GAME_HEIGHT = TILE_SIZE * TILES_BY_HEIGHT;
    private AppGameContainer app;

    public Game() throws SlickException {
        app = new AppGameContainer(new LevelInitializer("Pario v2"));
        //app = new AppGameContainer(new SettingUp("Pario v1"));
        app.setDisplayMode(GAME_WIDTH, GAME_HEIGHT,false);
        app.setTargetFrameRate(100);
        app.start();
    }
}
