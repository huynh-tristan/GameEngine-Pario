package GameStuff;

public class HelperFunctions {
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!IsSolid(x, y, lvlData)) {
            if (!IsSolid(x + width, y + height, lvlData)) {
                if (!IsSolid(x + width, y, lvlData)) {
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
            return tileX + xOff - 3;
        } else {
            return currTile * Game.TILE_SIZE + 33;
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
}
