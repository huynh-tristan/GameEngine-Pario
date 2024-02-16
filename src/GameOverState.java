import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends BasicGameState {

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        graphics.drawString("Game Over", 300, 200);
        graphics.drawString("To Try Again, Press ENTER", 300, 300);
        graphics.drawString("To Quit, Press Esc", 300, 350);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        if(gameContainer.getInput().isKeyPressed(Input.KEY_ENTER)){
            stateBasedGame.enterState(0);
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            System.exit(0);
        }
    }
}
