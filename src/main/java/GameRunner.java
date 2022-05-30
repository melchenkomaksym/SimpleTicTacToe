import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameRunner {

    private Grid grid;
    private static final Scanner scanner = new Scanner(System.in);

    GameRunner(Grid grid) {
        this.grid = grid;
    }

    private int[] getCoordinates() {
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

    public boolean isFinished() {
        boolean isFinished = true;
        for (String[] row : grid.getGrid()) {
            for (int j = 0; j < grid.getGrid()[0].length; j++) {
                if (row[j].equals(Symbols.EMPTY.getValue())) {
                    isFinished = false;
                    break;
                }
            }
        }
        return isFinished;
    }

    public boolean isXwin() {
        return isWin(Symbols.X.getValue());
    }

    public boolean isOwin() {
        return isWin(Symbols.O.getValue());
    }

    public boolean isWin(String sign) {
        return isHorizontalWin(sign) || isDiagonalWin(sign) || isVerticalWin(sign);
    }

    public boolean isHorizontalWin(String sign) {
        boolean isWin = false;

        for (String[] row : grid.getGrid()) {
            if(Arrays.stream(row).allMatch(sign :: equals)) {
                isWin = true;
                break;
            }
        }

        return isWin;
    }

    public boolean isDiagonalWin(String sign) {
        boolean isWin;

        List<String> diagonal1 = new ArrayList<>();
        for (int i = 0, j = 0; i < grid.getGrid().length && j < grid.getGrid()[0].length; i++, j++) {
            diagonal1.add(grid.getGrid()[i][j]);
        }
        isWin = diagonal1.stream().allMatch(sign :: equals);

        if(isWin) {
            return true;
        }

        List<String> diagonal2 = new ArrayList<>();
        for (int i = 0, j = grid.getGrid()[0].length-1; i < grid.getGrid().length && j >= 0; i++, j--) {
            diagonal2.add(grid.getGrid()[i][j]);
        }
        isWin = diagonal2.stream().allMatch(sign :: equals);
        return isWin;
    }

    public boolean isVerticalWin(String sign) {
        boolean isWin = false;

        for (int i = 0; i < grid.getGrid()[0].length && !isWin; i++) {
            List<String> column = new ArrayList<>();
            for (String[] strings : grid.getGrid()) {
                column.add(strings[i]);
            }
            isWin = column.stream().allMatch(sign :: equals);
        }

        return isWin;
    }

    public boolean isOutOfBounds(int[] coordinates) {
        return (coordinates[0] >= grid.getGrid().length || coordinates[0] < 0) || (coordinates[1] >= grid.getGrid()[0].length || coordinates[1] < 0);
    }

    public void makeTurn(int[] coordinates, String sign) {
        grid.getGrid()[coordinates[0]][coordinates[1]] = sign;
    }

    public boolean isOccupied(int[] coordinates) {
        return !grid.getGrid()[coordinates[0]][coordinates[1]].equals(Symbols.EMPTY.getValue());
    }

    public void playTheGame() {
        for (int counter = 0;; counter++) {
            String sign = counter % 2 == 0 ? Symbols.X.getValue() : Symbols.O.getValue();

            int[] coordinates = getCoordinates();

            makeTurn(coordinates, sign);

            if(isXwin()) {
                grid.printGrid();
                System.out.println(Symbols.X.getValue().concat(" wins"));
                break;
            }

            if(isOwin()) {
                grid.printGrid();
                System.out.println(Symbols.O.getValue().concat(" wins"));
                break;
            }

            if(isFinished()) {
                grid.printGrid();
                System.out.println("Draw");
                break;
            }

            grid.printGrid();
        }
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
