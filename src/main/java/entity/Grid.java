package entity;

import utils.Symbols;

import java.util.Arrays;

/**
 * Class stands for game 'board'. It holds results of each players' move.
 */

public class Grid {

    private String[][] grid = new String[3][3];

    public Grid() {
        initGrid();
    }

    /**
     * Initialization method for defining each of grids' cells with zero value.
     */

    private void initGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = Symbols.EMPTY.getValue();
            }
        }
    }

    public void printGrid() {
        int width = grid[0].length + 2;
        int height = grid.length + 2;
        String[][] gridWithBorders = new String[height][width];

        for (int i = 0; i < grid[0].length; i++) {
            System.arraycopy(grid[i], 0, gridWithBorders[i + 1], 1, grid.length);
        }

        for (int i = 0; i < width; i++) {
            if(i == width-1) {
                gridWithBorders[0][width-1] = Symbols.HORIZONTAL_BORDER.getValue();
                gridWithBorders[height-1][width-1] = Symbols.HORIZONTAL_BORDER.getValue();
            } else {
                gridWithBorders[0][i] = Symbols.DOUBLE_HORIZONTAL_BORDER.getValue();
                gridWithBorders[width-1][i] = Symbols.DOUBLE_HORIZONTAL_BORDER.getValue();
            }
            if(i > 0 && i < width-1) {
                gridWithBorders[i][0] = Symbols.VERTICAL_BORDER.getValue().concat(" ");
                gridWithBorders[i][width-1] = Symbols.VERTICAL_BORDER.getValue();
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if((i > 0 && i <= grid.length) && (j > 0 && j <= grid[0].length)) {
                    System.out.print(gridWithBorders[i][j] + " ");
                } else {
                    System.out.print(gridWithBorders[i][j]);
                }
            }
            System.out.println();
        }
    }

    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public boolean equals(Grid otherGrid) {
        return Arrays.deepEquals(grid, otherGrid.getGrid());
    }
}
