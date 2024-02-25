import Levels.LevelOne;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class LevelInitializer extends StateBasedGame {

    public LevelInitializer(String name) {
        super(name);
    }

    public void initStatesList(GameContainer gameContainer) {
        //Add start screen
        this.addState(new LevelOne());
    }
}
