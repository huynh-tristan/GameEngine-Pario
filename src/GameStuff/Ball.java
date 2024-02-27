package GameStuff;

import org.lwjgl.Sys;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import javax.swing.*;

public class Ball extends Entity {

    private int[][] lvlData;

    //Class will be used to keep information of the player character including hitbox/gravity etc.
    public Ball(Image front, float x, float y, float imageScale, int[][] lvlData) {
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


        inTheAir = true;

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
                System.out.println(airSpeed);
                yDelta += airSpeed;
                airSpeed += gravity;
                updateX(xSpeed);

            } else if (airSpeed > 0.5){
                yDelta = HelperFunctions.GetEntityYPositionNearTile(yDelta,getHeight()/2,airSpeed);
                System.out.println(airSpeed);
                airSpeed = -airSpeed;
                airSpeed += gravity;

                airSpeed *= 0.5f;

                updateX(xSpeed);

            } else if (airSpeed < 0.2) {
                yDelta = HelperFunctions.GetEntityYPositionNearTile(yDelta,getHeight()/2,airSpeed);
                updateX(xSpeed);
                if (airSpeed > 0) {
                    resetInTheAir();
                } else {
                    airSpeed = 0.5f;
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

    @Override
    public void drawHitbox(Graphics graphics) {

    }

    @Override
    public boolean entityToEntityCollisionCheck(int numOfEntity) {
        return false;
    }

    @Override
    public void entityCollisionInteraction() {

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
            //xDelta += -xSpeed * bounce;
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