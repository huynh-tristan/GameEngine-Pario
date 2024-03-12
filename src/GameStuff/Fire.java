package GameStuff;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Fire extends Entity{

    public Fire(Image front, float x, float y, float imageScale, float degreeOfRotation) {
        this.frontImage = front;
        this.frontImage.setCenterOfRotation(front.getCenterOfRotationX() * imageScale, front.getCenterOfRotationY() * imageScale);
        this.xDelta = x;
        this.yDelta = y;
        this.scale = imageScale;
        setRotation(degreeOfRotation);

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

        this.box = new Box(actualTR, actualTL, actualBR, actualBL, degreeOfRotation, getWidth() * scale, getHeight() * scale, center);
    }

    @Override
    public void update() {

    }

    @Override
    public void drawHitbox(Graphics graphics) {
        float[] tl={xDelta,yDelta+getHeight()*scale};
        float[] bl={xDelta,yDelta};
        float[] tr={xDelta+getWidth()*scale,yDelta+getHeight()*scale};
        float[] br={xDelta+getWidth()*scale,yDelta};
        //tl to bl
        drawLine(graphics,tl,bl);
        drawLine(graphics,bl,br);
        drawLine(graphics,br,tr);
        drawLine(graphics,tr,tl);
    }

    @Override
    public boolean entityToEntityCollisionCheck(int numOfEntity) {
        return false;
    }

    @Override
    public void entityCollisionInteraction() {

    }

    private void drawLine(Graphics graphics, float[] p1, float[] p2) {
        float[] updatedP1 = calcPointAfterRotation(p1);
        float[] updatedP2 = calcPointAfterRotation(p2);
        graphics.drawLine(updatedP1[0],updatedP1[1],updatedP2[0],updatedP2[1]);
    }

//    private float[] calcPointAfterRotation(float[] p) {
//        int[] origin = getCenterOfLocation();
//        float x1 = origin[0] + getXDelta();
//        float y1 = origin[1] + getYDelta();
//        float angleOfRotation = getRotation();
//        float newX = (float) (x1 + Math.cos(Math.toRadians(angleOfRotation)) * (p[0] - x1) - Math.sin(Math.toRadians(angleOfRotation)) * (p[1] - y1));
//        float newY = (float) (y1 + Math.sin(Math.toRadians(angleOfRotation)) * (p[0] - x1) + Math.cos(Math.toRadians(angleOfRotation)) * (p[1] - y1));
//        return new float[]{newX, newY};
//    }
}
