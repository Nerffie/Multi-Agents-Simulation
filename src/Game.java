import environnement.Grid;

public class Game {

    public static void main(String[] args) {
        Grid grid = new Grid(20);

        grid.initialiserGrid(5,2);

        grid.afficherGrid();
    }
}
