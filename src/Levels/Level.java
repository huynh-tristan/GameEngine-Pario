package Levels;

public class Level {
    private int[][] lvlData;

    public Level(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    public int getTileSpriteIndex(int x, int y) {
        return this.lvlData[y][x];
    }

    public int[][] getLvlData() {
        return this.lvlData;
    }
}
