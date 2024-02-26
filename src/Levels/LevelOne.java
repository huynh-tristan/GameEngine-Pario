package Levels;

import GameStuff.Player;
import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import static GameStuff.Game.TILES_BY_HEIGHT;
import static GameStuff.Game.TILES_BY_WIDTH;

public class LevelOne extends BasicGameState {

    private Player player;
    private Player secondEntity;
    private Level lvlOne;

    public int getID() {
        return 0;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        defineLevel();
        Image playerImage;
        Image secondEntityImage;
        try {
            playerImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Player/p1_front.png");
            //secondEntityImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Player/p1_front.png");
            secondEntityImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Enemies/fishSwim1.png");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
        player = new Player(playerImage, 3,368,0.5f, lvlOne.getLvlData());
        secondEntity = new Player(secondEntityImage, 200, 300, 0.5f, lvlOne.getLvlData());
        secondEntity.setRotation(45);
        gameContainer.getInput().addKeyListener(new KeyboardInputs(this.player));
        gameContainer.getInput().addMouseListener(new MouseInputs(this.player));
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
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 114, 114, 114, 114, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 3, 3, 3, 3},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 4, 3, 4, 4, 4, 3, 4, 4},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 4, 3, 4, 4, 4, 3, 4, 4, 4, 4},
                {3, 4, 3, 3, 4, 3, 4, 4, 4, 4, 3, 4, 0, 0, 0, 0, 0, 0, 1, 4, 3, 4, 3, 3, 3, 4, 3, 4, 3, 4},
                {3, 3, 4, 3, 3, 3, 3, 3, 4, 3, 3, 4, 0, 0, 0, 0, 0, 1, 4, 4, 3, 4, 3, 3, 4, 4, 4, 4, 3, 4},
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
        player.renderFront();
        //secondEntity.renderFront();
        player.drawHitbox(graphics);
        //secondEntity.drawHitbox(graphics);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        player.update();
        //secondEntity.update();
        if(player.getXDelta() > 928) {
            player.updateLocation(3,368);
            stateBasedGame.enterState(2);
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_0)){
            player.updateLocation(3,368);
            stateBasedGame.enterState(1);
        }
    }
}
