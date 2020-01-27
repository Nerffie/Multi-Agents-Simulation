import environnement.Grid;

public class Game {

    public static void main(String[] args) {
        Grid grid = new Grid(5);

        grid.initialiserGrid(5,2);

        grid.afficherGrid();

        grid.listeAgents[0].move(grid);
        System.out.println("*****************");
        grid.afficherGrid();

    }
}
