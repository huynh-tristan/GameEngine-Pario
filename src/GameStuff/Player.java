package GameStuff;

import org.newdawn.slick.Image;

import javax.swing.*;

public class Player extends Entity {

    private int[][] lvlData;
    //Class will be used to keep information of the player character including hitbox/gravity etc.
    public Player(Image front, float x, float y, float imageScale, int[][] lvlData) {
        this.frontImage = front;
        this.xDelta = x;
        this.yDelta = y;
        this.scale = imageScale;
        this.speed = 2.0f;
        this.jumpSpeed = -2.25f;
        this.lvlData = lvlData;
    }

    public void update() {
        //update the position/called in the level's update loop
        if (jump) {
            jump();
        }

        float xSpeed = 0, ySpeed = 0;
        if (this.left && !this.right) {
            xSpeed = -speed;
            //this.xDelta -= this.speed;
        } else if (!this.left && this.right) {
            xSpeed = speed;
            //this.xDelta += this.speed;
        }

        if (this.up && !this.down) {
            ySpeed = -speed;
            //this.yDelta -= this.speed;
        } else if (!this.up && this.down) {
            ySpeed = speed;
            //this.yDelta += this.speed;
        }

        if (HelperFunctions.CanMoveHere(this.xDelta + xSpeed, this.yDelta + ySpeed, this.getWidth() / 2, this.getHeight() / 2, lvlData)) {
            this.xDelta += xSpeed;
            this.yDelta += ySpeed;
        }
    }

    private void jump() {
    }
    //Something with hitbox/collision related functions
}
