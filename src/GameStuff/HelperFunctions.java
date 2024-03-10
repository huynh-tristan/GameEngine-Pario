package GameStuff;

import org.lwjgl.Sys;

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
}
