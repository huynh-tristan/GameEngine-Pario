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

public class LevelThree extends BasicGameState {
    private Player player;
    private Ball ball;
    private Player secondEntity;
    private Level lvlOne;
    private Agent testAgent;
    private Agent testAgent2;

    public int getID() {
        return 4;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        defineLevel();
        Image playerImage;
        Image ballImage;
        Image secondEntityImage;
        Image testAgentImage;
        Image testAgent2Image;
        File xmlFile;
        File xmlFile2;
        try {
            playerImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Player/p1_front.png");
            ballImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Items/bomb.png");
            //secondEntityImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Player/p1_front.png");
            secondEntityImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Enemies/fishSwim1.png");
            testAgentImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Enemies/blockerMad.png");
            testAgent2Image = new Image("src/res/Platformer_Art_Complete_Pack/Base pack/Enemies/blockerSad.png");
            xmlFile = new File("./src/res/agent1Tree.xml");
            xmlFile2 = new File("./src/res/agent2Tree.xml");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
        player = new Player(playerImage, 3,368,0.5f, lvlOne.getLvlData());
        ball = new Ball(ballImage, 432, 68, 0.5f,  lvlOne.getLvlData());
        secondEntity = new Player(secondEntityImage, 200, 100, 0.5f, lvlOne.getLvlData());
        secondEntity.setRotation(160);

        testAgent = new Agent(testAgentImage, 100, 100, 1, lvlOne.getLvlData(), xmlFile);
        testAgent.appendToEntityList(player);

        testAgent2 = new Agent(testAgent2Image, 200, 200, 1, lvlOne.getLvlData(), xmlFile2);
        testAgent2.appendToEntityList(player);


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
        graphics.drawString("Level 3", 400, 400);
        player.renderFront();
        ball.renderFront();
        secondEntity.renderFront();
        player.drawHitbox(graphics);
        ball.drawHitbox(graphics);
        secondEntity.drawHitbox(graphics);

        testAgent.renderFront();
        testAgent2.renderFront();
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        player.update();
        ball.update();
        testAgent.update();
        testAgent2.update();
        secondEntity.update();
        if(player.getXDelta() > 910) {
            player.updateLocation(3,368);
            stateBasedGame.enterState(5);
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
            ball.setXDelta(432);
        }

        if(gameContainer.getInput().isKeyPressed(Input.KEY_RIGHT)){
            secondEntity.setRotation(secondEntity.getRotation() + 15);
        }
    }
}
