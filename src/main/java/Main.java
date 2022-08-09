import controller.GameRunner;
import entity.Grid;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.printGrid();

        GameRunner runner = new GameRunner(grid);

        runner.playTheGame();
    }
}
