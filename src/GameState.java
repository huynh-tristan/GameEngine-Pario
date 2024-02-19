import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class GameState extends BasicGameState {

    private Image player = null;
    private int xImage = 0;
    private int yImage = 0;
    private float imageScale = .5f;
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        try {
            player = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Player/p1_front.png");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }

        xImage = 0;
        yImage = 370;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.blue);
        graphics.fillRect(0,0,960,640);
        graphics.setColor(Color.white);
        graphics.drawString("Press Buttons until you lose", 300, 200);
        TiledMap tm = new TiledMap("src/res/438Map.tmx", "src/res");
        tm.render(0,0);
        player.draw(xImage, yImage, imageScale);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        if(gameContainer.getInput().isKeyPressed(Input.KEY_0)){
            stateBasedGame.enterState(1);
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_SPACE) || gameContainer.getInput().isKeyPressed(Input.KEY_W) || gameContainer.getInput().isKeyPressed(Input.KEY_UP)){
            yImage -= 32;
            player.draw(xImage, yImage, imageScale);
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_LEFT) || gameContainer.getInput().isKeyPressed(Input.KEY_A)){
            if (xImage > 0) {
                xImage -= 32;
                player.draw(xImage, yImage, imageScale);
            }
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_RIGHT) || gameContainer.getInput().isKeyPressed(Input.KEY_D)){
            if (xImage < 928) {
                xImage += 32;
                player.draw(xImage, yImage, imageScale);
                if (xImage >= 928) {
                    stateBasedGame.enterState(2);
                }
            }
        }
    }
}
