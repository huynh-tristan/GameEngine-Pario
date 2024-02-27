package GameStuff;

import org.lwjgl.Sys;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import javax.swing.*;

public class Player extends Entity {

    private int[][] lvlData;
    //Class will be used to keep information of the player character including hitbox/gravity etc.
    public Player(Image front, float x, float y, float imageScale, int[][] lvlData) {
        this.frontImage = front;
        this.frontImage.setCenterOfRotation(front.getCenterOfRotationX() / 2, front.getCenterOfRotationY() / 2);
        this.xDelta = x;
        this.yDelta = y;
        this.scale = imageScale;
        this.speed = 1.5f;
        this.jumpSpeed = -3.25f;
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
    public void drawHitbox(Graphics graphics) {
        float angleOfRotation = getRotation();
        int[] center = getCenterOfLocation();
        float x1, y1;
        x1 = center[0] + getXDelta();
        y1 = center[1] + getYDelta();
        float px1 = x1 + getWidth() / 4;
        float px2 = x1;
        float py1 = y1;
        float py2 = y1 + getHeight() / 4;
        //graphics.drawLine(x1,y1,px2,py2);
        float qx1 = (float) (x1 + Math.cos(Math.toRadians(angleOfRotation)) * (px1 - x1) - Math.sin(Math.toRadians(angleOfRotation)) * (py1 - y1));
        float qy1 = (float) (y1 + Math.sin(Math.toRadians(angleOfRotation)) * (px1 - x1) + Math.cos(Math.toRadians(angleOfRotation)) * (py1 - y1));
        graphics.drawLine(x1,y1,qx1,qy1);
        float qx2 = (float) (x1 + Math.cos(Math.toRadians(angleOfRotation)) * (px2 - x1) - Math.sin(Math.toRadians(angleOfRotation)) * (py2 - y1));
        float qy2 = (float) (y1 + Math.sin(Math.toRadians(angleOfRotation)) * (px2 - x1) + Math.cos(Math.toRadians(angleOfRotation)) * (py2 - y1));
        graphics.drawLine(x1,y1,qx2,qy2);

        switch ((int) angleOfRotation) {
            case 0:
            case 180:
                graphics.drawRect(getXDelta(), getYDelta(), getWidth() / 2, getHeight() / 2);
                break;
            case 90:
            case 270:
                graphics.drawRect(getXDelta(), getYDelta(), getHeight() / 2, getWidth() / 2);
                break;
            default:
                float[] tl={xDelta,yDelta+getHeight()/2};
                float[] bl={xDelta,yDelta};
                float[] tr={xDelta+getWidth()/2,yDelta+getHeight()/2};
                float[] br={xDelta+getWidth()/2,yDelta};
                //tl to bl
                drawLine(graphics,tl,bl);
                drawLine(graphics,bl,br);
                drawLine(graphics,br,tr);
                drawLine(graphics,tr,tl);
        }
    }

    public boolean entityToEntityCollisionCheck(int numOfEntity) {
        return false;
    }

    public void entityCollisionInteraction() {

    }

    private void drawLine(Graphics graphics, float[] p1, float[] p2) {
        float[] updatedP1 = calcPointAfterRotation(p1);
        float[] updatedP2 = calcPointAfterRotation(p2);
        graphics.drawLine(updatedP1[0],updatedP1[1],updatedP2[0],updatedP2[1]);
    }

    private float[] calcPointAfterRotation(float[] p) {
        int[] origin = getCenterOfLocation();
        float x1 = origin[0] + getXDelta();
        float y1 = origin[1] + getYDelta();
        float angleOfRotation = getRotation();
        float newX = (float) (x1 + Math.cos(Math.toRadians(angleOfRotation)) * (p[0] - x1) - Math.sin(Math.toRadians(angleOfRotation)) * (p[1] - y1));
        float newY = (float) (y1 + Math.sin(Math.toRadians(angleOfRotation)) * (p[0] - x1) + Math.cos(Math.toRadians(angleOfRotation)) * (p[1] - y1));
        return new float[]{newX, newY};
    }
}
