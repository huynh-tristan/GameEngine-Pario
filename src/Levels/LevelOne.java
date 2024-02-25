package Levels;

import GameStuff.Player;
import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import static GameStuff.Game.TILES_BY_HEIGHT;
import static GameStuff.Game.TILES_BY_WIDTH;

public class LevelOne extends BasicGameState {

    private Player player;
    private Level lvlOne;

    public int getID() {
        return 0;
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
        gameContainer.getInput().addMouseListener(new MouseInputs(this.player));

        defineLevel();
    }

    private void defineLevel() {
        int[][] lvlData;
        lvlData = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 112, 112, 112, 112, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 114, 114, 114, 114, 0, 0, 817, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 817, 0, 817, 4, 4, 4, 4, 4, 3, 3, 3, 3},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 817, 1, 4, 4, 3, 4, 4, 4, 3, 4, 4},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 817, 0, 817, 1, 4, 3, 4, 4, 4, 3, 4, 4, 4, 4},
                {3, 4, 3, 3, 4, 3, 4, 4, 4, 4, 3, 4, 822, 0, 0, 0, 0, 817, 1, 4, 3, 4, 3, 3, 3, 4, 3, 4, 3, 4},
                {3, 3, 4, 3, 3, 3, 3, 3, 4, 3, 3, 4, 0, 0, 817, 0, 0, 1, 4, 4, 3, 4, 3, 3, 4, 4, 4, 4, 3, 4},
                {3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 3, 2, 2, 2, 2, 2, 108, 4, 4, 3, 4, 4, 4, 4, 4, 3, 3, 3, 4},
                {3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 3, 3, 3, 3, 3, 4, 3, 3, 3, 4, 3, 4, 3, 4},
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 4, 3, 3, 3, 4, 3, 3, 3, 4},
                {3, 4, 3, 3, 3, 3, 3, 3, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 3, 4, 4, 4, 4}};
        lvlOne = new Level(lvlData);
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        TiledMap tm = new TiledMap("src/res/438Map.tmx", "src/res");
        tm.render(0,0);
        graphics.drawString("Level 1", 300, 200);
//        graphics.fillRect(player.getXDelta(),player.getYDelta(),200,50);
        player.renderFront();
        int[] center = player.getCenterOfLocation();
        graphics.rotate();
        graphics.drawLine(center[0] / 2 + player.getXDelta(), center[1] / 2 + player.getYDelta(), center[0] / 2 + player.getXDelta(), player.getHeight() / 2 + player.getYDelta());
        graphics.drawLine(center[0] / 2 + player.getXDelta(), center[1] / 2 + player.getYDelta(), player.getWidth() / 2 + player.getXDelta(), center[1] / 2 + player.getYDelta());
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        player.update();
        if(player.getXDelta() > 960) {
            player.updateLocation(0,370);
            stateBasedGame.enterState(2);
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_0)){
            stateBasedGame.enterState(1);
        }
    }
}
