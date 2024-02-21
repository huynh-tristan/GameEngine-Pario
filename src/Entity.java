import org.newdawn.slick.Image;

public abstract class Entity {
    protected Image frontImage = null;
    protected Image duckingImage = null;
    protected Image jumpingImage = null;
    protected Image hurtImage = null;
    //Something for movement animation if implemented
    protected float x, y;
    protected float scale;
    //something velocity x and y
    //vectors for OBB

    public void renderFront() {
        frontImage.draw(x,y,scale);
    }

    public void renderDuck() {
        duckingImage.draw(x,y,scale);
    }

    public void renderJump() {
        jumpingImage.draw(x,y,scale);
    }

    public void renderHurt() {
        hurtImage.draw(x,y,scale);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return  this.y;
    }

    public void updateScale(float newScale) {
        this.scale = newScale;
    }

    public void updateLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
