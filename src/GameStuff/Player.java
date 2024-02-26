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
        this.jumpSpeed = -4.25f;
        this.lvlData = lvlData;
    }

    public void update() {
        //update the position/called in the level's update loop
        if (jump) {
            jump();
        }
        if (!left && !right && !inTheAir) {
            return;
        }

        float xSpeed = 0;
        if (this.left) {
            xSpeed = -speed;
            //this.xDelta -= this.speed;
        } else if (this.right) {
            xSpeed = speed;
            //this.xDelta += this.speed;
        }

        if (!inTheAir) {
            if (!HelperFunctions.IsEntityOnFloor(xDelta,yDelta,getWidth()/2,getHeight()/2,lvlData)) {
                inTheAir = true;
            }
        }

        if (inTheAir) {
            if (HelperFunctions.CanMoveHere(xDelta,yDelta + airSpeed,getWidth()/2,getHeight()/2,lvlData)) {
                yDelta += airSpeed;
                airSpeed += gravity;
                updateX(xSpeed);
            } else {
                yDelta = HelperFunctions.GetEntityYPositionNearTile(yDelta,getHeight()/2,airSpeed);
                if (airSpeed > 0) {
                    resetInTheAir();
                } else {
                    airSpeed = .5f;
                }
                updateX(xSpeed);
            }
        } else {
            updateX(xSpeed);
        }

//        if (this.up && !this.down) {
//            ySpeed = -speed;
//            //this.yDelta -= this.speed;
//        } else if (!this.up && this.down) {
//            ySpeed = speed;
//            //this.yDelta += this.speed;
//        }

//        if (HelperFunctions.CanMoveHere(this.xDelta + xSpeed, this.yDelta + ySpeed, this.getWidth() / 2, this.getHeight() / 2, lvlData)) {
//            this.xDelta += xSpeed;
//            this.yDelta += ySpeed;
//        }
    }

    private void resetInTheAir() {
        inTheAir = false;
        airSpeed = 0;
    }

    private void updateX(float xSpeed) {
        if (HelperFunctions.CanMoveHere(xDelta,yDelta,getWidth()/2,getHeight()/2,lvlData)) {
            xDelta += xSpeed;
        } else {
            xDelta = HelperFunctions.GetEntityXPositionNearTile(xDelta,getWidth()/2, xSpeed);
            //return;
        }
    }

    private void jump() {
        if (inTheAir) {
            return;
        }
        inTheAir = true;
        airSpeed = jumpSpeed;
    }
    //Something with hitbox/collision related functions
}
