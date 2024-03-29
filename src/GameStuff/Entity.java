package GameStuff;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayList;

public abstract class Entity {
    protected Image frontImage = null;
    protected Image duckingImage = null;
    protected Image jumpingImage = null;
    protected Image hurtImage = null;
    //Something for movement animation if implemented
    protected float xDelta, yDelta;
    protected float scale;
    protected boolean moving = false;
    protected boolean up, down, left, right, jump;
    protected float speed = 0f;
    protected float airSpeed = 0f;
    protected float gravity = 0.04f;
    protected float jumpSpeed = 0f;
    protected boolean inTheAir = false;
    
    protected ArrayList<Entity> entityCollisionCheckList;

    public Box box;

    public abstract void update();
    //vectors for OBB

    public void renderFront() {
        frontImage.draw(xDelta,yDelta,scale);
    }

    public void renderDuck() {
        duckingImage.draw(xDelta,yDelta,scale);
    }

    public void renderJump() {
        jumpingImage.draw(xDelta,yDelta,scale);
    }

    public void renderHurt() {
        hurtImage.draw(xDelta,yDelta,scale);
    }

    public void updateScale(float newScale) {
        this.scale = newScale;
    }

    public void updateLocation(float x, float y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void changeXDelta(float xDelta) {
        setXDelta(getXDelta() + xDelta);
    }

    public void changeYDelta(float yDelta) {
        setYDelta(getYDelta() + yDelta);
    }

    public void setYDelta(float yDelta) {
        this.yDelta = yDelta;
    }

    public int getYDelta() {
        return (int) this.yDelta;
    }

    public void setXDelta(float xDelta) {
        this.xDelta = xDelta;
    }

    public int getXDelta() {
        return (int) this.xDelta;
    }

    public int[] getCenterOfLocation() {
        int cx = (int) frontImage.getCenterOfRotationX();
        int cy = (int) frontImage.getCenterOfRotationY();
        //System.out.println("X center of ro: " + cx);
        //System.out.println("Y center of ro: " + cy);
        return new int[]{cx, cy};
    }

    public void setRotation(float angle) {
        this.frontImage.setRotation(angle);
        if (this.box != null) {
            this.box.updateBoxOrientation(angle);

            float[] tl={xDelta,yDelta+getHeight()*scale};
            float[] bl={xDelta,yDelta};
            float[] tr={xDelta+getWidth()*scale,yDelta+getHeight()*scale};
            float[] br={xDelta+getWidth()*scale,yDelta};

            float[] actualTR = calcPointAfterRotation(tr);
            float[] actualTL = calcPointAfterRotation(tl);
            float[] actualBR = calcPointAfterRotation(br);
            float[] actualBL = calcPointAfterRotation(bl);

            int[] centerOfImage = getCenterOfLocation();
            float[] center = new float[]{centerOfImage[0] * scale + xDelta, centerOfImage[1] * scale + yDelta};

            this.box.updateBoxCoordinates(actualTR, actualTL, actualBR, actualBL, center);
        }
    }

    public float getRotation() {
        return this.frontImage.getRotation();
    }

    public boolean isLeft() {
        return this.left;
    }

    public boolean isRight() {
        return this.right;
    }

    public boolean isUp() {
        return this.up;
    }

    public boolean isDown() {
        return this.down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUpDir(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public int getWidth() {
        return this.frontImage.getWidth();
    }

    public int getHeight() {
        return this.frontImage.getHeight();
    }

    public abstract void drawHitbox(Graphics graphics);

    public void setEntityCollisionCheckList(ArrayList<Entity> entities) {
        entityCollisionCheckList = entities;
    }

    public void appendToEntityList(Entity entity) {
        entityCollisionCheckList.add(entity);
    }

    public ArrayList<Entity> getEntityList() {
        return this.entityCollisionCheckList;
    }

    public abstract boolean entityToEntityCollisionCheck(int numOfEntity);

    public abstract void entityCollisionInteraction();

    public Entity getEntity(int numOfEntity) {
        return this.entityCollisionCheckList.get(numOfEntity);
    }

    public float[] calcPointAfterRotation(float[] p) {
        int[] origin = getCenterOfLocation();
        float x1 = origin[0] + getXDelta();
        float y1 = origin[1] + getYDelta();
        float angleOfRotation = getRotation();
        float newX = (float) (x1 + Math.cos(Math.toRadians(angleOfRotation)) * (p[0] - x1) - Math.sin(Math.toRadians(angleOfRotation)) * (p[1] - y1));
        float newY = (float) (y1 + Math.sin(Math.toRadians(angleOfRotation)) * (p[0] - x1) + Math.cos(Math.toRadians(angleOfRotation)) * (p[1] - y1));
        return new float[]{newX, newY};
    }
}
