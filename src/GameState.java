import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class GameState extends BasicGameState {

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.blue);
        graphics.fillRect(0,0,960,640);
        graphics.setColor(Color.white);
        graphics.drawString("Press Buttons until you lose", 300, 200);
        TiledMap tm = new TiledMap("src/res/438Map.tmx", "src/res");
        tm.render(0,0);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        if(gameContainer.getInput().isKeyPressed(Input.KEY_0)){
            stateBasedGame.enterState(1);
        }
    }
}
