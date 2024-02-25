package GameStuff;

import org.newdawn.slick.Image;

public class Player extends Entity {

    //Class will be used to keep information of the player character including hitbox/gravity etc.
    public Player(Image front, float x, float y, float imageScale) {
        this.frontImage = front;
        this.xDelta = x;
        this.yDelta = y;
        this.scale = imageScale;
    }
    //Something with hitbox/collision related functions
}
