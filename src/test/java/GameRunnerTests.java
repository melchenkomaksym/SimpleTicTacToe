import controller.GameRunner;
import entity.Grid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameRunnerTests {

    Grid testGrid = new Grid();
    GameRunner gameRunner = new GameRunner(testGrid);
    String[][] grid;

    @Test
    public void isFinishedTest() {
        grid = new String[][] {
                {"X", "O", "X"},
                {"X", "O", "X"},
                {"O", "X", "O"}
        };

        testGrid.setGrid(grid);
        gameRunner.setGrid(testGrid);

        assertTrue(gameRunner.isFinished());

        grid = new String[][] {
                {"X", "O", "X"},
                {"X", "O", "X"},
                {"O", "_", "O"}
        };

        testGrid.setGrid(grid);
        gameRunner.setGrid(testGrid);

        assertFalse(gameRunner.isFinished());
    }

    @Test
    public void isXwinTest() {
        grid = new String[][] {
                {"X", "O", "X"},
                {"X", "O", "O"},
                {"X", "X", "O"}
        };

        testGrid.setGrid(grid);
        gameRunner.setGrid(testGrid);

        assertTrue(gameRunner.isFinished());

        grid = new String[][] {
                {"X", "O", "X"},
                {"X", "O", "X"},
                {"O", "_", "O"}
        };

        testGrid.setGrid(grid);
        gameRunner.setGrid(testGrid);

        assertFalse(gameRunner.isFinished());
    }

    @Test
    public void isOwinTest() {
        grid = new String[][] {
                {"X", "O", "X"},
                {"O", "O", "O"},
                {"X", "X", "O"}
        };

        testGrid.setGrid(grid);
        gameRunner.setGrid(testGrid);

        assertTrue(gameRunner.isFinished());

        grid = new String[][] {
                {"_", "O", "X"},
                {"X", "X", "X"},
                {"O", "_", "O"}
        };

        testGrid.setGrid(grid);
        gameRunner.setGrid(testGrid);

        assertFalse(gameRunner.isFinished());
    }

    @Test
    public void isHorizontalWinTest() {
        grid = new String[][] {
                {"X", "O", "X"},
                {"O", "O", "O"},
                {"X", "X", "O"}
        };

        testGrid.setGrid(grid);
        gameRunner.setGrid(testGrid);

        assertTrue(gameRunner.isFinished());

        grid = new String[][] {
                {"X", "O", "O"},
                {"X", "O", "X"},
                {"_", "O", "O"}
        };

        testGrid.setGrid(grid);
        gameRunner.setGrid(testGrid);

        assertFalse(gameRunner.isFinished());
    }

    @Test
    public void isVerticalWinTest() {
        grid = new String[][] {
                {"X", "O", "X"},
                {"O", "O", "X"},
                {"X", "O", "O"}
        };

        testGrid.setGrid(grid);
        gameRunner.setGrid(testGrid);

        assertTrue(gameRunner.isFinished());

        grid = new String[][] {
                {"O", "O", "O"},
                {"X", "O", "X"},
                {"O", "X", "_"}
        };

        testGrid.setGrid(grid);
        gameRunner.setGrid(testGrid);

        assertFalse(gameRunner.isFinished());
    }

    @Test
    public void isOutOfBoundsTest() {
        int[] coords = new int[] {3, 4};
        assertTrue(gameRunner.isOutOfBounds(coords));

        coords = new int[] {2, 2};
        assertFalse(gameRunner.isOutOfBounds(coords));

        coords = new int[] {-1, 1};
        assertTrue(gameRunner.isOutOfBounds(coords));
    }

    @Test
    public void isOccupiedTest() {
        grid = new String[][] {
                {"X", "O", "X"},
                {"_", "_", "_"},
                {"_", "_", "_"}
        };

        testGrid.setGrid(grid);
        gameRunner.setGrid(testGrid);

        int[] coords = new int[] {0, 1};
        assertTrue(gameRunner.isOccupied(coords));

        coords = new int[] {1, 1};
        assertFalse(gameRunner.isOccupied(coords));
    }
}
