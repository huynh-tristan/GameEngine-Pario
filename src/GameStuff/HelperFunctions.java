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


}
