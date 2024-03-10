package Levels;

import GameStuff.Agent;
import GameStuff.Ball;
import GameStuff.Player;
import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.io.File;

public class LevelFour extends BasicGameState {
    private Player player;
    private Ball ball;
    private Level lvlOne;

    public int getID() {
        return 5;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        defineLevel();
        Image playerImage;
        Image ballImage;

        try {
            playerImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Player/p1_front.png");
            ballImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Items/bomb.png");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
        player = new Player(playerImage, 3,368,0.5f, lvlOne.getLvlData());
        ball = new Ball(ballImage, 390, 68, 0.5f,  lvlOne.getLvlData());

        ball.appendToEntityList(player);
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
        graphics.drawString("Level 4", 400, 400);
        player.renderFront();
        ball.renderFront();
        player.drawHitbox(graphics);
        ball.drawHitbox(graphics);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        player.update();
        ball.update();
        if(player.getXDelta() > 910) {
            player.updateLocation(3,368);
            stateBasedGame.enterState(4);
        }
        if(ball.getXDelta() > 910) {
            ball.setRight(false);
        }
        if(ball.getXDelta() < 40) {
            ball.setLeft(false);
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_0)){
            player.updateLocation(3,368);
            stateBasedGame.enterState(1);
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_1)){
            ball.setRight(false);
            ball.setLeft(false);
            ball.setYDelta(100);
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_2)){
            ball.setRight(false);
            ball.setLeft(false);
            ball.setYDelta(100);
            ball.setXDelta(390);
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_3)){
            ball.setRight(false);
            ball.setLeft(false);
            ball.setYDelta(100);
            ball.setXDelta(540);
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_4)){
            ball.setRight(false);
            ball.setLeft(false);
            ball.setYDelta(280);
            ball.setXDelta(530);
        }
    }
}

