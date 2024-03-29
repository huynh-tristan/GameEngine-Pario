package GameStuff;

import Levels.GameState;
import Levels.CompletionState;
import Levels.GameOverState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class SettingUp extends StateBasedGame {

    public SettingUp(String title) {
        super(title);
    }
    public void initStatesList(GameContainer gameContainer) {
        this.addState(new GameState());
        this.addState(new GameOverState());
        this.addState(new CompletionState());
    }

}
