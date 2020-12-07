package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {


    private static final int SIZE = 4;
    private static final int SEED = 42;
    private static final Random RANDOM = new Random(SEED);

    private static class Hex {
        //instance variables
        private int _x;
        private int _y;
        private TETile _tile;

        public Hex(int x, int y, TETile tile) {
            _x = x;
            _y = y;
            _tile = tile;
        }

        //adds this hex to TILES
        public void addHex(TETile[][] tiles) {
            int x = _x;
            int y = _y;

            for (int s = 0; s < SIZE; s += 1) {
                int rowLength = SIZE + 2 * s ;
                addRow(x, y, rowLength, tiles);
                x = x - 1;
                y = y - 1;
            }

            x += 1;

            for (int s = SIZE -1; s >= 0; s -= 1) {
                int rowLength = SIZE + 2 * s ;
                addRow(x, y, rowLength, tiles);
                x = x + 1;
                y = y - 1;
            }
        }

        public void addRow(int x, int y, int rowLength, TETile[][] tiles) {
            for (int i = 0; i < rowLength; i += 1) {
                tiles[x + i][y] = _tile;
            }

        }
    }

    private static void initWorld(TETile[][] tiles) {
        for (int x = 0; x < tiles.length; x += 1) {
            for (int y = 0; y < tiles[0].length; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    //Returns a random tile out of 6 possibilities
    private static TETile randomTile() {
        int randNumber = RANDOM.nextInt(6);

        switch (randNumber) {
            case 0:
                return Tileset.FLOWER;
            case 1:
                return Tileset.MOUNTAIN;
            case 2:
                return Tileset.SAND;
            case 3:
                return Tileset.TREE;
            case 4:
                return Tileset.WALL;
            case 5:
                return Tileset.WATER;
            default:
                return null;
        }
    }

    private static void addColumnOfHex(int x, int y, int numHexes,TETile[][] world ) {
        for(int i = 0; i < numHexes; i += 1) {
            TETile tile = randomTile();
            Hex h = new Hex(x, y, tile);
            h.addHex(world);
            y = y - 2 * SIZE;
        }
    }

    private static void tesselateWorld() {
        // TODO: Fill in


        int width = 10 * SIZE;
        int height = 12 * SIZE;

        TETile[][] world = new TETile[width][height];
        TERenderer ter = new TERenderer();
        ter.initialize(width,height);
        initWorld(world);

        //First Column has 3 hex
        int x = SIZE + 1;
        int y = (height -1) - 2 * SIZE;
        addColumnOfHex(x,y,3, world);

        //4 hex
        x += 2 * SIZE - 1;
        y += SIZE;
        addColumnOfHex(x,y,4, world);

        //5 hex
        x += 2 * SIZE - 1;
        y += SIZE;
        addColumnOfHex(x,y,5, world);

        //4 hex
        x += 2 * SIZE - 1;
        y -= SIZE;
        addColumnOfHex(x,y,4, world);

        //3 hex
        x += 2 * SIZE - 1;
        y -= SIZE;
        addColumnOfHex(x,y,3, world);

        ter.renderFrame(world);
    }

    public static void main(String[] unused) {
        tesselateWorld();
    }
}
