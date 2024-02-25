package Levels;

import GameStuff.Player;
import Inputs.KeyboardInputs;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelOne extends BasicGameState {

    private Player player = null;

    public int getID() {
        return 1;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Image playerImage;
        try {
            playerImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Player/p1_front.png");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
        player = new Player(playerImage, 0,370,0.5f);
        gameContainer.getInput().addKeyListener(new KeyboardInputs(this.player));
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString("Level 1", 300, 200);
        graphics.fillRect(player.getXDelta(),player.getYDelta(),200,50);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
