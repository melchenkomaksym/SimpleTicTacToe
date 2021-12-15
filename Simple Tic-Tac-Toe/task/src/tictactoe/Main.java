package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String[][] GRID;
    private static final Scanner scanner = new Scanner(System.in);
    private static String SIGN;

    public static void main(String[] args) {
        assignGrid();
        printGrid();

        playTheGame();
    }

    private static void assignGrid() {
        String[][] grid = new String[3][3];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = "_";
            }
        }
        GRID = grid;
    }

    private static int[] getCoordinates() {
        System.out.println("Enter the coordinates: ");
        int[] coordinates;
        while(true) {
            try {
                coordinates = Arrays.stream(scanner.nextLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .map(i -> i - 1)
                        .toArray();

            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if(isOutOfBounds(coordinates)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if(isOccupied(coordinates)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            break;
        }
        return coordinates;
    }

    public static void playTheGame() {
        for (int counter = 0;; counter++) {
            SIGN = counter % 2 == 0 ? "X" : "O";

            int[] coordinates = getCoordinates();

            makeTurn(coordinates, SIGN);

            if(isXwin()) {
                printGrid();
                System.out.println("X wins");
                break;
            }

            if(isOwin()) {
                printGrid();
                System.out.println("O wins");
                break;
            }

            if(isFinished()) {
                printGrid();
                System.out.println("Draw");
                break;
            }

            printGrid();
        }
    }

    public static void printGrid() {
        int width = GRID[0].length + 2;
        int height = GRID.length + 2;
        String[][] gridWithBorders = new String[height][width];

        for (int i = 0; i < GRID[0].length; i++) {
            System.arraycopy(GRID[i], 0, gridWithBorders[i + 1], 1, GRID.length);
        }

        for (int i = 0; i < width; i++) {
            if(i == width-1) {
                gridWithBorders[0][width-1] = "-";
                gridWithBorders[height-1][width-1] = "-";
            } else {
                gridWithBorders[0][i] = "--";
                gridWithBorders[width-1][i] = "--";
            }
            if(i > 0 && i < width-1) {
                gridWithBorders[i][0] = "| ";
                gridWithBorders[i][width-1] = "|";
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if((i > 0 && i <= GRID.length) && (j > 0 && j <= GRID[0].length)) {
                    System.out.print(gridWithBorders[i][j] + " ");
                } else {
                    System.out.print(gridWithBorders[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static boolean isFinished() {
        boolean isFinished = true;
        for (String[] row : GRID) {
            for (int j = 0; j < GRID[0].length; j++) {
                if (row[j].equals("_")) {
                    isFinished = false;
                    break;
                }
            }
        }
        return isFinished;
    }

    private static boolean isXwin() {
        return isWin("X");
    }

    private static boolean isOwin() {
        return isWin("O");
    }

    private static boolean isWin(String sign) {
        return isHorizontalWin(sign) || isDiagonalWin(sign) || isVerticalWin(sign);
    }

    private static boolean isHorizontalWin(String sign) {
        boolean isWin = false;

        for (String[] row : GRID) {
            if(Arrays.stream(row).allMatch(sign :: equals)) {
                isWin = true;
                break;
            }
        }

        return isWin;
    }

    private static boolean isDiagonalWin(String sign) {
        boolean isWin;

        List<String> diagonal1 = new ArrayList<>();
        for (int i = 0, j = 0; i < GRID.length && j < GRID[0].length; i++, j++) {
            diagonal1.add(GRID[i][j]);
        }
        isWin = diagonal1.stream().allMatch(sign :: equals);

        if(isWin) {
            return true;
        }

        List<String> diagonal2 = new ArrayList<>();
        for (int i = 0, j = GRID[0].length-1; i < GRID.length && j >= 0; i++, j--) {
            diagonal2.add(GRID[i][j]);
        }
        isWin = diagonal2.stream().allMatch(sign :: equals);
        return isWin;
    }

    private static boolean isVerticalWin(String sign) {
        boolean isWin = false;

        for (int i = 0; i < GRID[0].length && !isWin; i++) {
            List<String> column = new ArrayList<>();
            for (String[] strings : GRID) {
                column.add(strings[i]);
            }
            isWin = column.stream().allMatch(sign :: equals);
        }

        return isWin;
    }

    private static boolean isOutOfBounds(int[] coordinates) {
        return (coordinates[0] >= GRID.length || coordinates[0] < 0) || (coordinates[1] >= GRID[0].length || coordinates[1] < 0);
    }

    private static void makeTurn(int[] coordinates, String sign) {
        String s = sign.equals("_") ? "_" : SIGN;
        GRID[coordinates[0]][coordinates[1]] = s;
    }

    private static boolean isOccupied(int[] coordinates) {
        return !GRID[coordinates[0]][coordinates[1]].equals("_");
    }
}
