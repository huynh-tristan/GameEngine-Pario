package GameStuff;

import Levels.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class LevelInitializer extends StateBasedGame {

    public LevelInitializer(String name) {
        super(name);
    }

    public void initStatesList(GameContainer gameContainer) {
        //Add start screen

        //Level 1 shows basic movement, simple gravity with the player, and player hitbox
        this.addState(new LevelOne());
        //Level 2 shows bouncing of a circular entity and collision between the player and the ball
        this.addState(new LevelTwo());
        //Level 3 shows off a sample enemy agent
        this.addState(new LevelThree());
        //Level 4 shows off the ball interaction with interacting with corners of tiles/falling off of them
        this.addState(new LevelFour());


        this.addState(new GameOverState());
        this.addState(new CompletionState());
    }
}
