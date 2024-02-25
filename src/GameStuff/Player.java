package GameStuff;

import org.newdawn.slick.Image;

public class Player extends Entity {
    protected int xDelta = 100;
    protected int yDelta = 100;
    //Class will be used to keep information of the player character including hitbox/gravity etc.
    public Player(Image front, float x, float y, float imageScale) {
        this.frontImage = front;
        this.x = x;
        this.y = y;
        this.scale = imageScale;
    }

    public void changeXDelta(int xDelta) {
        setXDelta(getXDelta() + xDelta);
    }

    public void changeYDelta(int yDelta) {
        setYDelta(getYDelta() + yDelta);
    }

    public void setYDelta(int yDelta) {
        this.yDelta = yDelta;
    }

    public int getYDelta() {
        return this.yDelta;
    }

    public void setXDelta(int xDelta) {
        this.xDelta = xDelta;
    }

    public int getXDelta() {
        return this.xDelta;
    }
    //Something with hitbox/collision related functions
}
