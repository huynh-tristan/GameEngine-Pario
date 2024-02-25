package GameStuff;

import org.newdawn.slick.Image;

public abstract class Entity {
    protected Image frontImage = null;
    protected Image duckingImage = null;
    protected Image jumpingImage = null;
    protected Image hurtImage = null;
    //Something for movement animation if implemented
    protected float xDelta, yDelta;
    protected float scale;
    //something velocity x and y
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
        System.out.println("X center of ro: " + cx);
        System.out.println("Y center of ro: " + cy);
        return new int[]{cx, cy};
    }

    public void setRotation(float angle) {
        this.frontImage.setRotation(angle);
    }

    public float getRotation() {
        return this.frontImage.getRotation();
    }
}
