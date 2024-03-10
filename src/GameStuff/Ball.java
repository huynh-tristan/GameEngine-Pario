package GameStuff;

import org.lwjgl.Sys;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;

import javax.swing.*;
import java.util.ArrayList;

public class Ball extends Entity {

    private int[][] lvlData;

    public Ball(Image front, float x, float y, float imageScale, int[][] lvlData) {
        this.frontImage = front;
        this.xDelta = x;
        this.yDelta = y;
        this.scale = imageScale;
        this.speed = 1.0f;
        this.jumpSpeed = -4.25f;
        this.lvlData = lvlData;
        this.entityCollisionCheckList = new ArrayList<>();
    }

    public void update() {
        //update the position/called in the level's update loop
        inTheAir = true;

        entityToEntityCollisionCheck(0);

        float xSpeed = 0;
        if (this.left) {
            xSpeed = -speed;
            updateX(xSpeed);
        } else if (this.right) {
            xSpeed = speed;
            updateX(xSpeed);
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

    }

    @Override
    public void drawHitbox(Graphics graphics) {
        int[] center = getCenterOfLocation();
        graphics.draw(new Circle(getXDelta() + center[0] / 2, getYDelta() + center[1] / 2, (float) center[0] / 2));
        graphics.drawLine(xDelta + center[0] / 2, yDelta + center[0] / 2, xDelta + center[0] / 2 + getWidth() / 4, yDelta + center[0] / 2);
        graphics.drawLine(xDelta + center[0] / 2, yDelta + center[0] / 2, xDelta + center[0] / 2, yDelta + center[0] / 2 + getHeight() / 4);
    }

    @Override
    public boolean entityToEntityCollisionCheck(int numOfEntity) {
        Entity player = getEntity(numOfEntity);
        float[] tr = {player.getXDelta() + player.getWidth() / 2, player.getYDelta() + player.getHeight() / 2};
        //[] br = {player.getXDelta() + player.getWidth() / 2, player.getYDelta()};
        //float[] mr = {player.getXDelta() + player.getWidth() / 2, player.getYDelta() + player.getHeight() / 4};
        float[] tl = {player.getXDelta(), player.getYDelta() + player.getHeight() / 2};
        //float[] bl = {player.getXDelta(), player.getYDelta()};
        int[] center = getCenterOfLocation();
        //int radius = center[0] / 2;

        if (tr[0] >= getXDelta() && tr[0] <= getXDelta() + getWidth()/2) {
            //System.out.println("I got to here");
            if (isCollisionOnTheLeft(numOfEntity)) {
                setRight(true);
                setLeft(false);
                return true;
            }
        }
        if (tl[0] <= getXDelta() + getWidth()/2 && tl[0] >= getXDelta()) {

            if (isCollisionOnTheRight(numOfEntity)) {
                setLeft(true);
                setRight(false);
            }
        }

        return false;
    }

    private boolean isCollisionOnTheLeft(int numOfEntity) {
        Entity player = getEntity(numOfEntity);
        float[] tr = {player.getXDelta() + player.getWidth() / 2, player.getYDelta() + player.getHeight() / 2};
        float[] br = {player.getXDelta() + player.getWidth() / 2, player.getYDelta()};
        int[] center = getCenterOfLocation();
        int radius = center[0] / 2;

        if (getYDelta() + (getHeight() / 2) <= tr[1] && getYDelta() + (getHeight() / 2) >= br[1]) {
            return true;
        }
        //check if corner is touching circle then
        //tr
        double trDist = Math.sqrt(Math.pow(tr[0] - (center[0] + getXDelta()),2) + Math.pow(tr[1] - (center[1] + getYDelta()),2));
        if (trDist <= radius) {
            return true;
        }
        //br
        double brDist = Math.sqrt(Math.pow(br[0] - (center[0] + getXDelta()),2) + Math.pow(br[1] - (center[1] + getYDelta()),2));
        if (brDist <= radius) {
            return true;
        }
        return false;
    }

    private boolean isCollisionOnTheRight(int numOfEntity) {
        Entity player = getEntity(numOfEntity);
        float[] tl = {player.getXDelta(), player.getYDelta() + player.getHeight() / 2};
        float[] bl = {player.getXDelta(), player.getYDelta()};
        int[] center = getCenterOfLocation();
        int radius = center[0] / 2;

        //check if corner is touching circle then
        //tl
        double tlDist = Math.sqrt(Math.pow(tl[0] - (center[0] + getXDelta()),2) + Math.pow(tl[1] - (center[1] + getYDelta()),2));
        if (tlDist <= radius) {
            return true;
        }
        //bl
        double blDist = Math.sqrt(Math.pow(bl[0] - (center[0] + getXDelta()),2) + Math.pow(bl[1] - (center[1] + getYDelta()),2));
        if (blDist <= radius) {
            return true;
        }

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
            if (this.right) {
                setRight(false);
                setLeft(true);
            }
            else if (this.left) {
                setLeft(false);
                setRight(true);
            }
            //xDelta += -xSpeed * bounce;
            //return;
        }
    }
}