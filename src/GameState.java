import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class GameState extends BasicGameState {
    private Player player = null;
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        Image playerImage;
        try {
            playerImage = new Image("./src/res/Platformer_Art_Complete_Pack/Base pack/Player/p1_front.png");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
        player = new Player(playerImage, 0,370,0.5f);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.blue);
        graphics.fillRect(0,0,960,640);
        graphics.setColor(Color.white);
        graphics.drawString("Press Buttons until you lose", 300, 200);
        TiledMap tm = new TiledMap("src/res/438Map.tmx", "src/res");
        tm.render(0,0);
        player.renderFront();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        if(gameContainer.getInput().isKeyPressed(Input.KEY_0)){
            stateBasedGame.enterState(1);
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_SPACE) || gameContainer.getInput().isKeyPressed(Input.KEY_W) || gameContainer.getInput().isKeyPressed(Input.KEY_UP)){
            player.updateLocation(player.getX(), player.getY() - 32);
            player.renderFront();
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_LEFT) || gameContainer.getInput().isKeyPressed(Input.KEY_A)){
            if (player.getX() > 0) {
                player.updateLocation(player.getX() - 32, player.getY());
                player.renderFront();
            }
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_RIGHT) || gameContainer.getInput().isKeyPressed(Input.KEY_D)){
            if (player.getX() < 928) {
                player.updateLocation(player.getX() + 32, player.getY());
                player.renderFront();
                if (player.getX() >= 928) {
                    stateBasedGame.enterState(2);
                }
            }
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            System.exit(0);
        }
    }
}
