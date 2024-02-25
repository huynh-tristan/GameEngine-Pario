import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Game {

    private AppGameContainer app;

    public Game() throws SlickException {
        app = new AppGameContainer(new LevelInitializer("Pario"));
        app.setDisplayMode(960,640,false);
        app.start();
    }
}
