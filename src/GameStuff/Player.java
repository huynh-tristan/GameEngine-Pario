package GameStuff;

import org.newdawn.slick.Image;

public class Player extends Entity {

    //Class will be used to keep information of the player character including hitbox/gravity etc.
    public Player(Image front, float x, float y, float imageScale) {
        this.frontImage = front;
        this.xDelta = x;
        this.yDelta = y;
        this.scale = imageScale;
        this.speed = 2.0f;
        this.jumpSpeed = -2.25f;
    }

    public void update() {
        //update the position/called in the level's update loop
        if (this.left && !this.right) {
            this.xDelta -= this.speed;
        } else if (!this.left && this.right) {
            this.xDelta += this.speed;
        }

        if (this.up && !this.down) {
            this.yDelta -= this.speed;
        } else if (!this.up && this.down) {
            this.yDelta += this.speed;
        }
    }
    //Something with hitbox/collision related functions
}
