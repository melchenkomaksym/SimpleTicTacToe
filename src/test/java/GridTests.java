import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GridTests {

    @Test
    public void initGridTest() {
        Grid actualGrid = new Grid();

        String[][] grid = {
                {"_", "_", "_"},
                {"_", "_", "_"},
                {"_", "_", "_"}
        };
        Grid expectedGrid = new Grid();
        expectedGrid.setGrid(grid);

        assertTrue(actualGrid.equals(expectedGrid));
    }
}
