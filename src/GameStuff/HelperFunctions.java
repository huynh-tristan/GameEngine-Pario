package GameStuff;

import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HelperFunctions {
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!IsSolid(x, y, lvlData)) {
            if (!IsSolid(x + width + 2, y + height, lvlData)) {
                if (!IsSolid(x + width + 2, y, lvlData)) {
                    if (!IsSolid(x, y + height, lvlData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        if (x < 0 || x >= Game.GAME_WIDTH) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }

        float xIdx = x / Game.TILE_SIZE;
        float yIdx = y / Game.TILE_SIZE;
        int value = lvlData[(int)yIdx][(int)xIdx];
        return value != 0;
    }

    public static boolean IsEntityOnFloor(float x, float y, float width, float height, int[][] lvlData) {
        if (!IsSolid(x, y + height + 1, lvlData)) {
            if (!IsSolid(x + width, y + height + 1, lvlData)) {
                return false;
            }
        }
        return true;
    }

    public static float GetEntityXPositionNearTile(float x, float width, float xSpeed) {
        int currTile = (int) x / Game.TILE_SIZE;
        if (xSpeed > 0) {
            int tileX = currTile * Game.TILE_SIZE;
            int xOff = (int) (Game.TILE_SIZE - width);
            return tileX + xOff + 28;
        } else {
            return currTile * Game.TILE_SIZE + 35;
        }
    }

    public static float GetEntityYPositionNearTile(float y, float height, float airSpeed) {
        int currTile = (int) y / Game.TILE_SIZE;
        if (airSpeed > 0) {
            int tileY = currTile * Game.TILE_SIZE;
            int yOff = (int) (Game.TILE_SIZE - height);
            return tileY + yOff + 31;
        } else {
            return currTile * Game.TILE_SIZE;
        }
    }

    public static int[] GetCoefficientsOfALine(float[] p1, float[] p2) {
        int A = (int) (p1[1] - p2[1]);
        int B = (int) (p1[0] - p2[0]);
        int C = (int) ((p2[0] * p1[1]) - (p2[1] - p1[0]));

        return new int[]{A,B,C};
    }

    public static boolean potentialCollisionBetweenLineAndCircle(int[] lineCoefficients, int circleCenterX, int circleCenterY, int radius) {
        double distance = (Math.abs(lineCoefficients[0] * circleCenterX + lineCoefficients[1] * circleCenterY + lineCoefficients[2]) / Math.sqrt(lineCoefficients[0] * lineCoefficients[0] + lineCoefficients[1] * lineCoefficients[1]));
        return radius >= distance;
    }

    public static float DotProduct(float[] p1, float[] p2) {
        float product = 0f;
        for (int i = 0; i < 2; i++) {
            product += p1[i] * p2[i];
        }
        return product;
    }

    public static float[] Normalize(float[] p) {
        float l = (float) Math.sqrt((p[0]*p[0]) + (p[1]*p[1]));
        if (l == 0) {
            return null;
        }
        return new float[]{p[0]/l, p[1]/l};
    }
    public static boolean OrientedBoxAndOrientedBoxCollision(Box boxA, Box boxB) {
        float[] axis1A = new float[]{(float) Math.cos(Math.toRadians(boxA.orientation)), (float) Math.sin(Math.toRadians(boxA.orientation))};
        float[] axis2A = new float[]{(float) Math.sin(Math.toRadians(boxA.orientation)), (float) -Math.cos(Math.toRadians(boxA.orientation))};
        float[] axis1B = new float[]{(float) Math.cos(Math.toRadians(boxB.orientation)), (float) Math.sin(Math.toRadians(boxB.orientation))};
        float[] axis2B = new float[]{(float) Math.sin(Math.toRadians(boxB.orientation)), (float) -Math.cos(Math.toRadians(boxB.orientation))};

        //float tr, tl, br, bl of boxA after their rotations
        //now get min and max dot products
        //boxA projection on axis min and max
        //boxB projection on axis min and max
        float[] minMaxA;
        float[] minMaxB;

        //Axis1A
        minMaxA = ProjectionOnAxis(boxA, axis1A);
        minMaxB = ProjectionOnAxis(boxB, axis1A);
        if (!(minMaxA[0] <= minMaxB[1] && minMaxA[1] >= minMaxB[0])) {
            return false;
        }

        //Axis2A
        minMaxA = ProjectionOnAxis(boxA, axis2A);
        minMaxB = ProjectionOnAxis(boxB, axis2A);
        if (!(minMaxA[0] <= minMaxB[1] && minMaxA[1] >= minMaxB[0])) {
            return false;
        }

        //Axis1B
        minMaxA = ProjectionOnAxis(boxA, axis1B);
        minMaxB = ProjectionOnAxis(boxB, axis1B);
        if (!(minMaxA[0] <= minMaxB[1] && minMaxA[1] >= minMaxB[0])) {
            return false;
        }

        //Axis2B
        minMaxA = ProjectionOnAxis(boxA, axis2B);
        minMaxB = ProjectionOnAxis(boxB, axis2B);
        if (!(minMaxA[0] <= minMaxB[1] && minMaxA[1] >= minMaxB[0])) {
            return false;
        }

        return true;
    }

    public static boolean CircleOrientedBoxCheck(float[] circleCenter, float circleRadius, Box box) {
        float[] boxClosePoint = box.calculateClosestPoint(circleCenter);
        float xDist = circleCenter[0] - boxClosePoint[0];
        float yDist = circleCenter[1] - boxClosePoint[1];
        float dist = (float) Math.sqrt(xDist * xDist + yDist * yDist);
        return dist <= circleRadius;
    }

    public static float[] ProjectionOnAxis(Box box, float[] axis) {
        Float[] dotProducts = new Float[4];
        dotProducts[0] = DotProduct(axis, box.tr);
        dotProducts[1] = DotProduct(axis, box.tl);
        dotProducts[2] = DotProduct(axis, box.br);
        dotProducts[3] = DotProduct(axis, box.bl);

        float min, max;
        min = Collections.min(Arrays.asList(dotProducts));
        max = Collections.max(Arrays.asList(dotProducts));

        return new float[]{min, max};
    }

    public static float Clamp(float a, float b, float c) {
        float min = Math.min(Math.min(a,b),c);
        float max = Math.max(Math.max(a,b),c);
        if (a != min && a != max) {
            return a;
        }
        if (b != min && b != max) {
            return b;
        }
        return c;
    }
}
